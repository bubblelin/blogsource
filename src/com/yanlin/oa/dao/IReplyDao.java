package com.yanlin.oa.dao;

import java.util.List;

import com.yanlin.oa.base.IBaseDao;
import com.yanlin.oa.domain.PageBean;
import com.yanlin.oa.domain.Reply;
import com.yanlin.oa.domain.Topic;

public interface IReplyDao extends IBaseDao<Reply>{

	List<Reply> getReplyByTopic(Topic model);

	/*PageBean getPageBean(int currentPage, Topic model);*/


}
