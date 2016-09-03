package com.yanlin.oa.action;

import java.util.Date;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.yanlin.oa.base.BaseAction;
import com.yanlin.oa.domain.Reply;
import com.yanlin.oa.domain.Topic;

/**
 * 回帖的Action
 * @author bubblelin
 *
 */
@Controller
@Scope("prototype")
public class ReplyAction extends BaseAction<Reply>{
	private static final long serialVersionUID = 1L;
	//属性驱动，获取帖子id
	private Long topicId;

	/**
	 * 跳转到回复页面
	 */
	public String addUI(){
		Topic topic = topicService.getById(topicId);
		this.getValueStack().push(topic);
		
		return "addUI";
	}
	
	/**
	 * 发表回帖
	 * @return
	 */
	public String add(){
		Topic topic = topicService.getById(topicId);
		
		model.setTopic(topic);
		model.setAuthor(this.getLoginUser());
		model.setPostTime(new Date());
		model.setIpAddress(this.getIpAddress());
		model.setDeleted(0);
		
		replyService.save(model);
		
		return "topicShow";
	}
	
	
	
	
	
	
	
	
	
	public Long getTopicId() {
		return topicId;
	}
	public void setTopicId(Long topicId) {
		this.topicId = topicId;
	}
}
