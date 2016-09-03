package com.yanlin.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yanlin.oa.base.BaseDaoImpl;
import com.yanlin.oa.dao.IPrivilegeDao;
import com.yanlin.oa.domain.Privilege;

/**
 * 权限dao的实现类
 * @author bubblelin
 *
 */
@Repository
@SuppressWarnings("unchecked")
public class PrivilegeDaoImpl extends BaseDaoImpl<Privilege> implements IPrivilegeDao{
	/**
	 * 查询所有顶级权限
	 */
	public List<Privilege> findTopList() {
		String hql = "FROM Privilege p WHERE p.parent IS NULL";
		return this.getSession().createQuery(hql).list();
	}

	/**
	 * 查询所有的权限对应的url
	 */
	public List<String> findAllUrl() {
		String hql = "select url from Privilege where url is not null";
		return  this.getSession().createQuery(hql).list();
	}

}
