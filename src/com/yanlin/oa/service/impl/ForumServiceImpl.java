package com.yanlin.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanlin.oa.dao.IForumDao;
import com.yanlin.oa.domain.Forum;
import com.yanlin.oa.service.IForumService;

/**
 * 参与板块操作的service实现类
 * @author bubblelin
 *
 */
@Service
@Transactional
public class ForumServiceImpl implements IForumService{

	@Resource
	private IForumDao forumDao;

	public List<Forum> findAll() {
		return forumDao.findAll();
	}

	public Forum getById(Long id) {
		return forumDao.getById(id);
	}
}
