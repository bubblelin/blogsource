package com.yanlin.oa.base;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Properties;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

import com.yanlin.oa.domain.PageBean;
import com.yanlin.oa.utils.HQLHelper;

/*
 * ͨ通用的dao实现类
 */
@SuppressWarnings("unchecked")
public class BaseDaoImpl<T> implements IBaseDao<T>{
	@Resource
	private SessionFactory sessionFactory;
	private Class<T> clazz;
	
	public BaseDaoImpl(){
		//获取实体对象
		ParameterizedType genericSuperclass = (ParameterizedType)getClass().getGenericSuperclass();
		Type[] type = genericSuperclass.getActualTypeArguments();
		clazz = (Class<T>) type[0];
	}
	
	@Override
	public void save(T entity) {
		getSession().save(entity);
		
	}

	@Override
	public void delete(Long id) {
		getSession().delete(getSession().get(clazz, id));
		
	}

	@Override
	public void update(T entity) {
		getSession().update(entity);
		
	}

	@Override
	public T getById(Long id) {
		return (T) getSession().get(clazz, id);
	}

	@Override
	public List<T> getByIds(Long[] ids) {
		String hql = " FROM " + clazz.getSimpleName() + " WHERE id in (:ids)";
		Query query = getSession().createQuery(hql);
		query.setParameterList("ids", ids);
		return query.list();
	}

	@Override
	public List<T> findAll() {
		String hql = "FROM " + clazz.getSimpleName();
		return getSession().createQuery(hql).list();
	}
	
	/**
	 * 获取工厂对象
	 */
	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 公共分页
	 */
	public PageBean getPageBean(HQLHelper hql, int currentPage) {
		int pageSize = this.getPageSize();
		int firstResult = (currentPage - 1)*pageSize;
		String listHQL = hql.getListHQL();
		String countHQL = hql.getCountHQL();
		List<Object> args = hql.getArgs();
		//1.通过分页查询获取当前页的结果集
		Query query = this.getSession().createQuery(listHQL);
		if(args != null && args.size()>0){
			int index = 0;
			for(Object o : args){
				query.setParameter(index++, o);
			}
		}
		query.setFirstResult(firstResult);
		query.setMaxResults(pageSize);
		List listData = query.list();
		//2.获取总记录数
		query = this.getSession().createQuery(countHQL);
		if(args != null && args.size()>0){
			int index = 0;
			for(Object o : args){
				query.setParameter(index++, o);
			}
			
		}
		Long totalSize = (Long) query.uniqueResult();
		return new PageBean(currentPage, pageSize, listData, totalSize.intValue());
	}

	/**
	 * 获取配置文件的pageSize
	 * @return
	 */
	private int getPageSize() {
		int pageSize = 10;
		Properties p = new Properties();
		InputStream in = Thread.currentThread().getContextClassLoader().getResourceAsStream("page.properties");
		try {
			p.load(in);
			String str = (String)p.get("pageSize");
			pageSize = Integer.parseInt(str);
		} catch (IOException e) {
			pageSize = 10;
			e.printStackTrace();
		} finally {
			try {
				if(in != null){
					in.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return pageSize;
	}
}
