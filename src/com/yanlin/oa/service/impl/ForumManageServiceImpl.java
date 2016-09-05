package com.yanlin.oa.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yanlin.oa.dao.IForumManageDao;
import com.yanlin.oa.domain.Forum;
import com.yanlin.oa.domain.PageBean;
import com.yanlin.oa.service.IForumManageService;
import com.yanlin.oa.utils.HQLHelper;
/**
 * 板块管理的service
 * @author bubblelin
 *
 */
@Service
@Transactional
public class ForumManageServiceImpl implements IForumManageService{

	@Resource
	private IForumManageDao forumManageDao;

	public List<Forum> findAll() {
		return forumManageDao.findAll();
	}

	public void delete(Long id) {
		forumManageDao.delete(id);
	}

	public void save(Forum model) {
		forumManageDao.save(model);
	}

	public Forum getById(Long id) {
		return forumManageDao.getById(id);
	}

	public void update(Forum forum) {
		forumManageDao.update(forum);
	}

	public void moveUp(Long id) {
		forumManageDao.moveUp(id);
	}

	public void moveDown(Long id) {
		forumManageDao.moveDown(id);
	}

	public PageBean getPageBean(HQLHelper hql, int currentPage) {
		return  forumManageDao.getPageBean(hql, currentPage);
	}
}
