package com.yanlin.oa.base;

import java.util.List;

/**
 * 通用的dao
 * @author bubblelin
 *
 */
public interface IBaseDao<T> {

	/**
	 * 添加
	 */
	void save(T entity);
	
	/**
	 * 删除
	 */
	void delete(Long id);
	
	/** 
	 * 修改
	 */
	void update(T entity);
	
	/**
	 * 查询一个
	 */
	T getById(Long id);
	
	/**
	 * 查询多个
	 */
	List<T> getByIds(Long[] ids);
	
	/**
	 * 查询所有
	 */
	List<T> findAll();
}
