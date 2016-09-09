package com.yanlin.oa.domain;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
/**
 * 申请的实体
 * @author bubblelin
 *
 */
public class Application {
	
	public static final String STATUS_RUNNING = "审批中";
	public static final String STATUS_APPROVED = "已审批";
	public static final String STATUS_UNAPPROVED = "未通过";
	
	private Long id;
	private String title;
	private User applicant;
	private Date applyTime;
	private String status;
	private String filePath;
	private Template template;
	private Set<ApproveInfo> approveInfos = new HashSet<ApproveInfo>();
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public User getApplicant() {
		return applicant;
	}
	public void setApplicant(User applicant) {
		this.applicant = applicant;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Template getTemplate() {
		return template;
	}
	public void setTemplate(Template template) {
		this.template = template;
	}
	public Set<ApproveInfo> getApproveInfos() {
		return approveInfos;
	}
	public void setApproveInfos(Set<ApproveInfo> approveInfos) {
		this.approveInfos = approveInfos;
	}
}
