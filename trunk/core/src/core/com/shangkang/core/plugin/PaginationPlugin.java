/**
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: Liuzh
 * Created On: 2012-5-2
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ------------     -----------     ---------------------------------------------
 *
 **/
package com.shangkang.core.plugin;

import java.lang.reflect.Field;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.cache.Cache;
import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.scripting.xmltags.DynamicContext;
import org.apache.ibatis.scripting.xmltags.SqlNode;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;

import com.shangkang.core.bo.Pagination;

@Intercepts({ @Signature(type = Executor.class, method = "query", args = {
		MappedStatement.class, Object.class, RowBounds.class,
		ResultHandler.class }) })
public class PaginationPlugin implements Interceptor {

	private Log	logger	= LogFactory.getLog(getClass());

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public Object intercept(Invocation invocation) throws Throwable
	{
		Object parameterObject = invocation.getArgs()[1];

		Pagination page = null;
		if (parameterObject != null && parameterObject instanceof Pagination)
		{
			page = (Pagination) parameterObject;
		} else if (parameterObject != null && parameterObject instanceof Map)
		{
			for (Map.Entry<String, Object> e : ((Map<String, Object>) parameterObject)
					.entrySet())
			{
				if (e.getValue() instanceof Pagination)
				{
					page = (Pagination) e.getValue();
					break;
				}
			}
		}

		if (page != null)
		{
			if (page.isPaginationFlag())
			{
				return pageIntercept(invocation);
			} else
			{
				Object params = null;

				for (Map.Entry<String, Object> e : ((Map<String, Object>) parameterObject)
						.entrySet())
				{
					if (!(e.getValue() instanceof Pagination))
					{
						params = e.getValue();
						break;
					}
				}

				invocation.getArgs()[1] = params;
			}
		}

		return invocation.proceed();
	}

	public Object plugin(Object target)
	{
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties)
	{
		// String dialectClass = properties.getProperty("dialectClass");
		// try {
		// dialect = (Dialect) Class.forName(dialectClass).newInstance();
		// } catch (InstantiationException e) {
		// e.printStackTrace();
		// } catch (IllegalAccessException e) {
		// e.printStackTrace();
		// } catch (ClassNotFoundException e) {
		// e.printStackTrace();
		// }
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	private Object pageIntercept(Invocation invocation) throws Throwable
	{
		int rowCount = 0;
		MappedStatement mappedStatement = (MappedStatement) invocation
				.getArgs()[0];
		Object parameterObject = invocation.getArgs()[1];

		Pagination page = null;
		if (parameterObject instanceof Pagination)
		{
			page = (Pagination) parameterObject;
		} else if (parameterObject instanceof Map)
		{
			for (Map.Entry<String, Object> e : ((Map<String, Object>) parameterObject)
					.entrySet())
			{
				if (e.getValue() instanceof Pagination)
				{
					page = (Pagination) e.getValue();
				} else
				{
					parameterObject = e.getValue();
				}
			}
		}

		SqlSource sqlSource = getCountSqlSource(mappedStatement,
				parameterObject);
		MappedStatement newMappedStatement = copyMappedStatementBySqlSource(
				mappedStatement, sqlSource);
		try
		{
			Cache cache = newMappedStatement.getCache();
			
			if (newMappedStatement.isUseCache() && cache != null)
			{
				Executor exe = (Executor) invocation.getTarget();

				CacheKey cacheKey = exe.createCacheKey(newMappedStatement,
						parameterObject, (RowBounds) invocation.getArgs()[2], sqlSource.getBoundSql(parameterObject));
				Object cacheResult = cache.getObject(cacheKey);
				
				if (cacheResult != null)
				{
					rowCount = (Integer) cacheResult;
				} else
				{
					rowCount = queryRowCountFromDB(mappedStatement, newMappedStatement, parameterObject, sqlSource);

					cache.putObject(cacheKey, rowCount);
				}
			} else
			{
				rowCount = queryRowCountFromDB(mappedStatement, newMappedStatement, parameterObject, sqlSource);
			}

			logger.debug("total = " + rowCount);
		} catch (Exception e)
		{
			logger.error(e,e);
			throw e;
		} 

		if (page == null)
		{
			page = new Pagination();
		}

		page.setTotal(rowCount);
		sqlSource = getPageLimitSqlSource(mappedStatement, parameterObject,
				page);
		newMappedStatement = copyMappedStatementBySqlSource(mappedStatement,
				sqlSource);
		invocation.getArgs()[0] = newMappedStatement;
		invocation.getArgs()[1] = parameterObject;
		return invocation.proceed();
	}

	private Integer queryRowCountFromDB(MappedStatement mappedStatement, MappedStatement newMappedStatement, Object parameterObject, SqlSource sqlSource) throws Throwable
	{
		Integer rowCount = null;
		ResultSet rs = null;
		PreparedStatement countStmt = null;

		try
		{
			DefaultSqlSessionFactory sf = new DefaultSqlSessionFactory(mappedStatement.getConfiguration());
			
			DefaultParameterHandler dp = new DefaultParameterHandler(
					mappedStatement, parameterObject,
					sqlSource.getBoundSql(parameterObject));
			String sql = sqlSource.getBoundSql(parameterObject).getSql();
			countStmt = sf.openSession().getConnection().prepareStatement(sql);
			dp.setParameters(countStmt);
			rs = countStmt.executeQuery();

			if (rs.next())
			{
				rowCount = rs.getInt(1);
			}

			logger.debug("sql = " + sql);
		} catch (Exception e)
		{
			logger.error(e, e);
			throw e;
		} finally
		{
			if (rs != null)
				rs.close();
			if (countStmt != null)
				countStmt.close();
		}
		
		return rowCount;
	}

	private DynamicContext getDynamicContext(MappedStatement mappedStatement,
			Object parameterObject) throws Throwable
	{
		SqlSource nowSqlSource = mappedStatement.getSqlSource();
		Class<?> sqlSourceType = nowSqlSource == null ? Object.class
				: nowSqlSource.getClass();
		Field rootSqlNodeField = sqlSourceType.getDeclaredField("rootSqlNode");
		rootSqlNodeField.setAccessible(true);
		SqlNode sqlNode = (SqlNode) rootSqlNodeField.get(nowSqlSource);
		DynamicContext context = new DynamicContext(
				mappedStatement.getConfiguration(), parameterObject);
		sqlNode.apply(context);
		return context;
	}

	private SqlSource getCountSqlSource(MappedStatement mappedStatement,
			Object parameterObject) throws Throwable
	{
		SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(
				mappedStatement.getConfiguration());
		DynamicContext context = getDynamicContext(mappedStatement, parameterObject);
		Class<?> parameterType = parameterObject == null ? Object.class
				: parameterObject.getClass();
		String newSql = "select count(1) from ( " + context.getSql() + " ) t";
		SqlSource sqlSource = sqlSourceParser.parse(newSql, parameterType, context.getBindings());
		return sqlSource;
	}

	@SuppressWarnings("rawtypes")
	private SqlSource getPageLimitSqlSource(MappedStatement mappedStatement,
			Object parameterObject, Pagination pagination) throws Throwable
	{
		SqlSourceBuilder sqlSourceParser = new SqlSourceBuilder(
				mappedStatement.getConfiguration());
		DynamicContext context = getDynamicContext(mappedStatement, parameterObject);
		Class<?> parameterType = parameterObject == null ? Object.class
				: parameterObject.getClass();

		int start = (pagination.getPageNo() - 1) * pagination.getPageSize();
		int end = pagination.getPageNo() * pagination.getPageSize();

		String newSql = getLimitString(context.getSql(), start, end);
		SqlSource sqlSource = sqlSourceParser.parse(newSql, parameterType, context.getBindings());
		return sqlSource;
	}

	private MappedStatement copyMappedStatementBySqlSource(
			MappedStatement mappedStatement, SqlSource sqlSource)
	{
		MappedStatement.Builder builder = new MappedStatement.Builder(
				mappedStatement.getConfiguration(), mappedStatement.getId(),
				sqlSource, mappedStatement.getSqlCommandType());
		builder.resource(mappedStatement.getResource());
		builder.fetchSize(mappedStatement.getFetchSize());
		builder.statementType(mappedStatement.getStatementType());
		builder.keyGenerator(mappedStatement.getKeyGenerator());
		builder.timeout(mappedStatement.getTimeout());
		builder.parameterMap(mappedStatement.getParameterMap());
		builder.resultMaps(mappedStatement.getResultMaps());
		builder.resultSetType(mappedStatement.getResultSetType());

		builder.cache(mappedStatement.getCache());
		builder.flushCacheRequired(mappedStatement.isFlushCacheRequired());
		builder.useCache(mappedStatement.isUseCache());

		MappedStatement newMappedStatement = builder.build();
		return newMappedStatement;
	}

	private String getLimitString(String sql, int offset, int limit)
	{
		StringBuilder finalSql = new StringBuilder();

		finalSql.append("select * from ( select row_.*, rownum rownum_ from ( ")
				.append(sql).append(" ) row_  where rownum <= ").append(limit)
				.append(" ) where rownum_ > ").append(offset);

		return finalSql.toString();

	}
}
