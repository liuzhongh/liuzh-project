<?xml version="1.0" encoding="UTF-8"?> 
<!-- 
 **
 * COPYRIGHT (C) 2012 3KW. ALL RIGHTS RESERVED.
 *
 * No part of this publication may be reproduced, stored in a retrieval system,
 * or transmitted, on any form or by any means, electronic, mechanical, photocopying,
 * recording, or otherwise, without the prior written permission of 3KW.
 *
 * Created By: ${user}
 * Created On: ${date}
 *
 * Amendment History:
 * 
 * Amended By       Amended On      Amendment Description
 * ************     ***********     *********************************************
 *
 **
 -->
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperPackage}.${result.getFirstCharacterUppercase(result.getTableName())}Mapper">
	<#assign parameterType="${result.getFirstCharacterUppercase(result.getTableName())}">
	<#assign tableName="${result.getTableName()}">
	<resultMap id="${parameterType}ResultMapper" type="${parameterType}">
    <#foreach prop in result.getColumns()>
		<result column="${prop.columnName}" property="${result.getFirstCharacterLowercase(prop.columnName)}"/>
	</#foreach>
	</resultMap>
	
	<#assign tmp="">
	<sql id="commonColumns">
	<#foreach prop in result.getColumns()>
		${tmp} ${prop.columnName}
		<#assign tmp=",">
	</#foreach>
	</sql>
	
	<#assign tmp="">
	<sql id="commonProcessDateColumns">
	<#foreach prop in result.getColumns()>
		<#if "DATE" == prop.jdbcTypeName || "TIME" == prop.jdbcTypeName || "TIMESTAMP" == prop.jdbcTypeName>
		${tmp} to_char(${prop.columnName}, 'yyyy-mm-dd hh24:mi:ss') ${prop.columnName}
		<#else>
		${tmp} ${prop.columnName}
		</#if>
		<#assign tmp=",">
	</#foreach>
	</sql>
	
	<sql id="commonCondition">
		<#assign tmp="">
		<#foreach prop in result.getColumns()>
		<if test="${result.getFirstCharacterLowercase(prop.columnName)}!= null and ${result.getFirstCharacterLowercase(prop.columnName)}!= ''"> 
		<#if "DATE" == prop.jdbcTypeName || "TIME" == prop.jdbcTypeName || "TIMESTAMP" == prop.jdbcTypeName>
			AND ${prop.columnName}=to_date(${"#"}{${result.getFirstCharacterLowercase(prop.columnName)}},'yyyy-mm-dd hh24:mi:ss')
		<#else>
			AND ${prop.columnName}=${"#"}{${result.getFirstCharacterLowercase(prop.columnName)}}
		</#if>
		</if>
		</#foreach>
	</sql>
	
	<insert id="save" parameterType="${parameterType}">
	  <selectKey resultType="${result.getKeyType()}" keyProperty="${result.getFirstCharacterLowercase(result.getKey())}" order="BEFORE">
	   select SEQ_${result.getTablePrefix()}${tableName}.nextVal
	   from dual
	  </selectKey>
		<![CDATA[ INSERT INTO ${result.getTablePrefix()}${tableName} ( ]]>
		<include refid="commonColumns"/>
		<![CDATA[
			) VALUES ( 
		<#assign tmp="">
		<#foreach prop in result.getColumns()>
			<#if "DATE" == prop.jdbcTypeName || "TIME" == prop.jdbcTypeName || "TIMESTAMP" == prop.jdbcTypeName>
			${tmp} to_date(${"#"}{${result.getFirstCharacterLowercase(prop.columnName)}},'yyyy-mm-dd hh24:mi:ss')
			<#else>
			${tmp} ${"#"}{${result.getFirstCharacterLowercase(prop.columnName)}}
			</#if>
			<#assign tmp=",">
		</#foreach>
  ) ]]>
	</insert>
	<update id="update" parameterType="${parameterType}">
		<![CDATA[ UPDATE ${result.getTablePrefix()}${tableName} SET 
		<#assign tmp="">
		<#foreach prop in result.getColumns()>
			<#if "DATE" == prop.jdbcTypeName || "TIME" == prop.jdbcTypeName || "TIMESTAMP" == prop.jdbcTypeName>
			${tmp} ${prop.columnName}=to_date(${"#"}{${result.getFirstCharacterLowercase(prop.columnName)}},'yyyy-mm-dd hh24:mi:ss')
			<#else>
			${tmp} ${prop.columnName}=${"#"}{${result.getFirstCharacterLowercase(prop.columnName)}} 
			</#if>
			<#assign tmp=",">
		</#foreach>
		WHERE ${result.getKey()} = ${"#"}{${result.getFirstCharacterLowercase(result.getKey())}}  ]]>
	</update>
	<delete id="deleteByPK" parameterType="${result.getKeyType()}">
		<![CDATA[ DELETE FROM ${result.getTablePrefix()}${tableName} WHERE ${result.getKey()} = ${"#"}{${result.getFirstCharacterLowercase(result.getKey())}}  ]]>
	</delete>
	<delete id="deleteByPKeys" parameterType="map">
		DELETE FROM ${result.getTablePrefix()}${tableName} WHERE
		<foreach collection="primaryKeys" index="index" item="id"
			open=" ${result.getKey()} IN(" separator="," close=") ">
			${"$"}{id}
		</foreach>
	</delete>
	<delete id="deleteByProperty" parameterType="${parameterType}">
		DELETE FROM ${result.getTablePrefix()}${tableName} WHERE 1 = 1
		<include refid="commonCondition"/>
	</delete>
	<select id="getByPK" parameterType="${result.getKeyType()}" resultMap="${parameterType}ResultMapper">
		<![CDATA[ SELECT ]]>
			 <include refid="commonProcessDateColumns"/>
		FROM ${result.getTablePrefix()}${tableName} WHERE ${result.getKey()} = ${"#"}{${result.getFirstCharacterLowercase(result.getKey())}}  
	</select>
	<select id="list" resultMap="${parameterType}ResultMapper">
		<![CDATA[ SELECT ]]>
			 <include refid="commonProcessDateColumns"/>
 		FROM ${result.getTablePrefix()}${tableName}
	</select>
	<select id="listByProperty" parameterType="${parameterType}" resultMap="${parameterType}ResultMapper">
		<![CDATA[ SELECT ]]>
			<include refid="commonProcessDateColumns"/>
		FROM ${result.getTablePrefix()}${tableName} WHERE 1=1 
		<include refid="commonCondition"/>
	</select>
	<select id="listPaginationByProperty" parameterType="${parameterType}" resultMap="${parameterType}ResultMapper">
		SELECT 
		<include refid="commonProcessDateColumns"/>
		FROM ${result.getTablePrefix()}${tableName} WHERE 1=1 
		<include refid="commonCondition"/>
	</select>
	<select id="findByCount" parameterType="${parameterType}" resultType="int">
		SELECT count(1) AS totalcount FROM ${result.getTablePrefix()}${tableName} WHERE 1=1 
		<include refid="commonCondition"/>
	</select>
</mapper>