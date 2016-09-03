package com.yanlin.oa.domain;

/**
 * 回复的实体
 * @author bubblelin
 *
 */
public class Reply extends Article {
	private int deleted;	//删除标志：默认0，表示已删除
	private Topic topic;
	
	public int getDeleted() {
		return deleted;
	}
	public void setDeleted(int deleted) {
		this.deleted = deleted;
	}
	public Topic getTopic() {
		return topic;
	}
	public void setTopic(Topic topic) {
		this.topic = topic;
	}
}
