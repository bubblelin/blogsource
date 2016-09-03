package com.yanlin.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanlin.oa.dao.IBookDao;
import com.yanlin.oa.domain.Book;
import com.yanlin.oa.service.IBookService;

@Service
@Transactional
public class BookServiceImpl implements IBookService {
	@Resource
	private IBookDao bookDao;
	
	@Override
	public void save(Book book) {
		
		bookDao.save(book);
	}

	@Override
	public void delete(Long id) {
		
		bookDao.delete(id);
	}

	@Override
	public void update(Book book) {
		
		bookDao.update(book);
	}

	@Override
	public Book getById(Long id) {
		
		return bookDao.getById(id);
	}

	@Override
	public List<Book> getByIds(Long[] ids) {
		
		return bookDao.getByIds(ids);
	}

	@Override
	public List<Book> findAll() {
		
		return bookDao.findAll();
	}

}
