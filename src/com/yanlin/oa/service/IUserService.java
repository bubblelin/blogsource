package com.yanlin.oa.service;

import java.util.List;

import com.yanlin.oa.domain.User;

public interface IUserService {

	List<User> findAll();

	void delete(User model);

	void save(User model);

	User getById(Long id);

	void update(User user);

	int findByLoginName(String loginName);

	User login(User model);

}
