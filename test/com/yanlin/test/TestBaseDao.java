package com.yanlin.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yanlin.oa.domain.Book;
import com.yanlin.oa.service.IBookService;

/**
 * 测试BaseDao
 * @author bubblelin
 *
 */
@SuppressWarnings("resource")
public class TestBaseDao {
	
	/**
	 * 测试findAll()查询所有的方法
	 * @throws Exception
	 */
	@Test
	public void testFindAll() throws Exception {
		//初始化spring
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		IBookService bookService = (IBookService)ctx.getBean("bookServiceImpl");
		
		List<Book> bookList = bookService.findAll();
		System.out.println(bookList);
	}
	
	/**
	 * 测试根据ids查询的方法
	 * @throws Exception
	 */
	@Test
	public void testGetByIds() throws Exception {
		//初始化spring
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		IBookService bookService = (IBookService)ctx.getBean("bookServiceImpl");
		
		Long[] ids = {1L,3L}; 
		List<Book> bookList = bookService.getByIds(ids);
		System.out.println(bookList);
	}
	
	/**
	 * 测试根据id查询的方法
	 * @throws Exception
	 */
	@Test
	public void testGetById() throws Exception {
		//初始化spring
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		IBookService bookService = (IBookService)ctx.getBean("bookServiceImpl");
		
		Book book = bookService.getById(3L);
		System.out.println(book);
	}
	
	/**
	 * 测试修改的方法
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		//初始化spring
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		IBookService bookService = (IBookService)ctx.getBean("bookServiceImpl");
		
		Book book = new Book();
		book.setId(3L);
		book.setName("PHP");
		bookService.update(book);
	}
	
	/**
	 * 测试删除的方法
	 * @throws Exception
	 */
	@Test
	public void testDelete() throws Exception {
		//初始化spring
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		IBookService bookService = (IBookService)ctx.getBean("bookServiceImpl");
		
		bookService.delete(2L);
	}

	/**
	 * 测试添加的方法
	 * @throws Exception
	 */
	@Test
	public void testSave() throws Exception {
		//初始化spring
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		IBookService bookService = (IBookService)ctx.getBean("bookServiceImpl");
		
		Book book = new Book();
		book.setName("java");
		
		bookService.save(book);
	}
}
