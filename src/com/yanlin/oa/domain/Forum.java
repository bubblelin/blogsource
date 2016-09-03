package com.yanlin.oa.domain;

import java.util.HashSet;
import java.util.Set;

/**
 * 论坛板块的实体
 * @author bubblelin
 *
 */

public class Forum {
	private Long id;
	private String name;
	private String description;
	private int position;
	//板块所在的所有主题
	private Set<Topic> topics = new HashSet<Topic>(); 
	//板块下的主题数量
	private int topicCount;
	//板块下的文章数量
	private int articleCount;
	//板块下的最后一个主题
	private Topic lastTopic;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPosition() {
		return position;
	}
	public void setPosition(int position) {
		this.position = position;
	}
	public Set<Topic> getTopics() {
		return topics;
	}
	public void setTopics(Set<Topic> topics) {
		this.topics = topics;
	}
	public int getTopicCount() {
		return topicCount;
	}
	public void setTopicCount(int topicCount) {
		this.topicCount = topicCount;
	}
	public int getArticleCount() {
		return articleCount;
	}
	public void setArticleCount(int articleCount) {
		this.articleCount = articleCount;
	}
	public Topic getLastTopic() {
		return lastTopic;
	}
	public void setLastTopic(Topic lastTopic) {
		this.lastTopic = lastTopic;
	}
}
