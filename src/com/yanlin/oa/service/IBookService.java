package com.yanlin.oa.service;

import java.util.List;

import com.yanlin.oa.domain.Book;

public interface IBookService {

	/**
	 * 添加
	 */
	void save(Book book);
	
	/**
	 * 根据id删除
	 */
	void delete(Long id);
	
	/** 
	 * 修改
	 */
	void update(Book book);
	
	/**
	 * 根据id查询
	 */
	Book getById(Long id);
	
	/**
	 * 根据ids查询
	 */
	List<Book> getByIds(Long[] ids);
	
	/**
	 * 查询所有
	 */
	List<Book> findAll();
}
