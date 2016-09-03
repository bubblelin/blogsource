package com.yanlin.oa.service;

import java.util.List;
import java.util.Set;

import com.yanlin.oa.domain.Role;

public interface IRoleService {

	List<Role> findAll();

	void delete(Role model);

	Role getById(Long id);

	void update(Role role);

	void save(Role model);

	List<Role> findByIds(Long[] roleIds);

	List<Role> getByIds(Long[] roleIds);


}
