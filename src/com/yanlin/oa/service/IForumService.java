package com.yanlin.oa.service;

import java.util.List;

import com.yanlin.oa.domain.Forum;

public interface IForumService {

	List<Forum> findAll();

	Forum getById(Long id);

}
