package com.yanlin.oa.service;

import java.util.List;

import com.yanlin.oa.domain.PageBean;
import com.yanlin.oa.domain.Reply;
import com.yanlin.oa.domain.Topic;
import com.yanlin.oa.utils.HQLHelper;

public interface IReplyService {

	void save(Reply model);

	List<Reply> getReplyByTopic(Topic model);

	PageBean getPageBean(HQLHelper hql,int currentPage);

}
