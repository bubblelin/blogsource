package com.yanlin.oa.service;

import java.util.List;

import com.yanlin.oa.domain.Forum;
import com.yanlin.oa.domain.PageBean;
import com.yanlin.oa.domain.Topic;
import com.yanlin.oa.utils.HQLHelper;

public interface ITopicService {

	List<Topic> findTopicByForum(Forum model);

	void save(Topic model);

	Topic getById(Long id);

	PageBean getPageBean(HQLHelper hql, int currentPage);


}
