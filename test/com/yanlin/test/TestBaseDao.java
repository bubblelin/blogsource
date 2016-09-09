package com.yanlin.test;

import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.yanlin.oa.domain.Book;
import com.yanlin.oa.service.IBookService;

/**
 * ����BaseDao
 * @author bubblelin
 *
 */
@SuppressWarnings("resource")
public class TestBaseDao {
	
	/**
	 * ����findAll()��ѯ���еķ���
	 * @throws Exception
	 */
	@Test
	public void testFindAll() throws Exception {
		//��ʼ��spring
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		IBookService bookService = (IBookService)ctx.getBean("bookServiceImpl");
		
		List<Book> bookList = bookService.findAll();
		System.out.println(bookList);
	}
	
	/**
	 * ���Ը��ids��ѯ�ķ���
	 * @throws Exception
	 */
	@Test
	public void testGetByIds() throws Exception {
		//��ʼ��spring
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		IBookService bookService = (IBookService)ctx.getBean("bookServiceImpl");
		
		Long[] ids = {1L,3L}; 
		List<Book> bookList = bookService.getByIds(ids);
		System.out.println(bookList);
	}
	
	/**
	 * ���Ը��id��ѯ�ķ���
	 * @throws Exception
	 */
	@Test
	public void testGetById() throws Exception {
		//��ʼ��spring
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		IBookService bookService = (IBookService)ctx.getBean("bookServiceImpl");
		
		Book book = bookService.getById(3L);
		System.out.println(book);
	}
	
	/**
	 * �����޸ĵķ���
	 * @throws Exception
	 */
	@Test
	public void testUpdate() throws Exception {
		//��ʼ��spring
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		IBookService bookService = (IBookService)ctx.getBean("bookServiceImpl");
		
		Book book = new Book();
		book.setId(3L);
		book.setName("PHP");
		bookService.update(book);
	}
	
	/**
	 * ����ɾ��ķ���
	 * @throws Exception
	 */
	@Test
	public void testDelete() throws Exception {
		//��ʼ��spring
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		IBookService bookService = (IBookService)ctx.getBean("bookServiceImpl");
		
		bookService.delete(2L);
	}

	/**
	 * ������ӵķ���
	 * @throws Exception
	 */
	@Test
	public void testSave() throws Exception {
		//��ʼ��spring
		ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		IBookService bookService = (IBookService)ctx.getBean("bookServiceImpl");
		
		Book book = new Book();
		book.setName("zhangsan");
		
		bookService.save(book);
	}
}
