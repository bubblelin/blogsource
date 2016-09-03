package com.yanlin.oa.service;

import java.util.List;

import com.yanlin.oa.domain.Privilege;

public interface IPrivilegeService {

	List<Privilege> findAll();

	List<Privilege> findByIds(Long[] privilegeIds);

	List<Privilege> findTopList();

	List<String> findAllUrl();

}
