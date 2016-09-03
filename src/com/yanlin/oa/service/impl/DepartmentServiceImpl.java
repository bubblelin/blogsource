package com.yanlin.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanlin.oa.dao.IDepartmentDao;
import com.yanlin.oa.domain.Department;
import com.yanlin.oa.service.IDepartmentService;
/**
 * 部门的service实现类
 * @author bubblelin
 *
 */
@Service
@Transactional
public class DepartmentServiceImpl implements IDepartmentService {

	@Resource
	private IDepartmentDao departmentDao; 
	
	public List<Department> findAll() {
		return departmentDao.findAll();
	}

	public void delete(Department model) {
		departmentDao.delete(model.getId());
	}

	public Department getById(Long id) {
		return departmentDao.getById(id);
	}

	public void save(Department model) {
		departmentDao.save(model);
	}

	public void update(Department department) {
		departmentDao.update(department);
	}

	public List<Department> findTopList() {
		return departmentDao.findTopList();
	}

	public List<Department> findChidList(Long parentId) {
		return departmentDao.findChidList(parentId);
	}



}
