package com.yanlin.oa.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.yanlin.oa.base.BaseDaoImpl;
import com.yanlin.oa.dao.IDepartmentDao;
import com.yanlin.oa.domain.Department;

/**
 * 部门dao的实现类
 * @author bubblelin
 *
 */
@Repository
@SuppressWarnings("unchecked")
public class DepartmentDaoImpl extends  BaseDaoImpl<Department> implements IDepartmentDao {
	/**
	 * 查询所有的顶级部门
	 */
	public List<Department> findTopList() {
		String hql = "FROM Department d WHERE d.parent IS NULL";
		return this.getSession().createQuery(hql).list();
	}

	/**
	 * 根据parentId查询所有的下级部门
	 */
	public List<Department> findChidList(Long parentId) {
		String hql = "FROM Department d WHERE d.parent.id=?";
		return this.getSession().createQuery(hql).setParameter(0, parentId).list();
	}


}
