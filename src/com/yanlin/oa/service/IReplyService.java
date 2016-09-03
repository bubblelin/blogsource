package com.yanlin.oa.service;

import java.util.List;

import com.yanlin.oa.domain.Reply;
import com.yanlin.oa.domain.Topic;

public interface IReplyService {

	void save(Reply model);

	List<Reply> getReplyByTopic(Topic model);

}
