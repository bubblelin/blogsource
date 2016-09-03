package com.yanlin.oa.service;

import java.util.List;

import com.yanlin.oa.domain.Department;

public interface IDepartmentService {

	List<Department> findAll();

	void delete(Department model);

	Department getById(Long id);

	void save(Department model);

	void update(Department department);

	List<Department> findTopList();

	List<Department> findChidList(Long parentId);


}
