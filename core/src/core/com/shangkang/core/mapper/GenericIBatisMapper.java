package com.shangkang.core.mapper;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.shangkang.core.exception.DataAccessFailureException;

public interface GenericIBatisMapper<T, PK extends Serializable> {

	/**
	 * 通过主键查询实体对象
	 * @param primaryKey
	 * @return
	 * @throws DataAccessFailureException
	 */
	public abstract T getByPK(PK primaryKey) throws DataAccessFailureException;

	/**
	 * 查询所有记录
	 * @return
	 * @throws DataAccessFailureException
	 */
	public abstract List<T> list() throws DataAccessFailureException;

	/**
	 * 根据查询条件查询所有记录
	 * @return
	 * @throws DataAccessFailureException
	 */
	public abstract List<T> listByProperty(T o)
			throws DataAccessFailureException;

	/**
	 * 根据主键删除记录
	 * @param primaryKey
	 * @return
	 * @throws DataAccessFailureException
	 */
	public abstract int deleteByPK(PK primaryKey) throws DataAccessFailureException;
	
	/**
	 * 根据多个主键删除记录
	 * @param primaryKeys
	 * @throws DataAccessFailureException
	 */
	public void deleteByPKeys(@Param("primaryKeys")List<PK> primaryKeys) throws DataAccessFailureException;

	/**
	 * 根据传入参数删除记录
	 * @param o
	 * @return
	 * @throws DataAccessFailureException
	 */
	public abstract int deleteByProperty(T o) throws DataAccessFailureException;

	/**
	 * 保存记录
	 * @param o
	 * @return
	 * @throws DataAccessFailureException
	 */
	public abstract void save(T o) throws DataAccessFailureException;

	/**
	 * 更新记录
	 * @param o
	 * @return
	 * @throws DataAccessFailureException
	 */
	public abstract int update(T o) throws DataAccessFailureException;

	/**
	 * 根据条件查询记录条数
	 * @param o
	 * @return
	 * @throws DataAccessFailureException
	 */
	public abstract int findByCount(T o) throws DataAccessFailureException;

}