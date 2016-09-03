package com.yanlin.oa.base;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;

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
		String hql = "FROM " + clazz.getSimpleName() + " WHERE id in (:ids)";
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
}
