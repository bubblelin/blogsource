package com.yanlin.oa.service;

import java.util.List;

import com.yanlin.oa.domain.Forum;
import com.yanlin.oa.domain.PageBean;
import com.yanlin.oa.utils.HQLHelper;

public interface IForumService {

	List<Forum> findAll();

	Forum getById(Long id);

	PageBean getPageBean(HQLHelper hql, int currentPage);

}
