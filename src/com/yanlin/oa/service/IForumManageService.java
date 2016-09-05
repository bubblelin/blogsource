package com.yanlin.oa.service;

import java.util.List;

import com.yanlin.oa.domain.Forum;
import com.yanlin.oa.domain.PageBean;
import com.yanlin.oa.utils.HQLHelper;

public interface IForumManageService {

	List<Forum> findAll();

	void delete(Long id);

	void save(Forum model);

	Forum getById(Long id);

	void update(Forum forum);

	void moveUp(Long id);

	void moveDown(Long id);

	PageBean getPageBean(HQLHelper hql, int currentPage);

}
