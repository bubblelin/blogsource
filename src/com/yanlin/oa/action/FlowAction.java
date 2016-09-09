package com.yanlin.oa.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.yanlin.oa.domain.Application;
import com.yanlin.oa.domain.ApproveInfo;
import com.yanlin.oa.domain.PageBean;
import com.yanlin.oa.domain.Template;
import com.yanlin.oa.domain.User;
import com.yanlin.oa.service.IApplicationService;
import com.yanlin.oa.service.IApproveInfoService;
import com.yanlin.oa.service.IFlowService;
import com.yanlin.oa.service.ITemplateService;
import com.yanlin.oa.utils.HQLHelper;
/**
 * 审批流转的action类 
 * @author bubblelin
 *
 */
@Controller
@Scope("prototype")
public class FlowAction extends ActionSupport{
	private static final long serialVersionUID = 1L;
	@Resource
	private ITemplateService templateService;
	@Resource
	private IFlowService flowService;
	@Resource
	private IApplicationService applicationService;
	@Resource
	private IApproveInfoService approveInfoService;
	
	private Long templateId;//属性驱动，
	private File resource;//用于文件上传
	private int currentPage = 1;//分页查询
	private String status;//分页条件
	private InputStream inputStreamName;//Y用于文件上传
	private Long applicationId;
	private String fileName;
	
	
	/**
	 * 起草申请列表(显示所有模板)
	 */
	public String templateList(){
		List<Template> templateList = templateService.findAll();
		ActionContext.getContext().getValueStack().set("templateList", templateList);
		return "templateList";
	}
	
	/**
	 * 跳转到提交申请页面
	 */
	public String submitUI(){
		return "submitUI";
	}
	
	/**
	 * 提交申请
	 */
	public String  submit(){
		Template template = templateService.getById(templateId);
		Application appl = new Application();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		//处理上传的文件,并得到文件保存的路径
		String filePath = this.getFilePath(resource);
		String title = template.getName()+"_"+this.getLoginUser().getName()+"_"+sdf.format(new Date());
		System.out.println("-------------------title："+title);
		System.out.println("-------------------title："+title);
		//保存申请的信息
		appl.setApplicant(this.getLoginUser());
		appl.setApplyTime(new Date());
		appl.setFilePath(filePath);
		appl.setStatus(Application.STATUS_RUNNING);
		appl.setTemplate(template);//使用的模板
		appl.setTitle(title);//模板名称+申请人名字+日期
		flowService.submit(appl);
		
		return "toMyApplicationList";
	}
	
	/**
	 * 我的申请列表
	 */
	public String myApplicationList(){
		List<Template> templateList = templateService.findAll();
		ActionContext.getContext().getValueStack().set("templateList", templateList);
		
		//带分页查询登陆用户的申请
		HQLHelper hql = new HQLHelper(Application.class);
		if(this.getLoginUser() != null){
			hql.addWhere("o.applicant=?", this.getLoginUser());
		}
		if(templateId != null){
			hql.addWhere("o.template.id=?", templateId);
		}
		if(status != null && !"".equals(status.trim())){
			hql.addWhere("o.status=?", status);
		}
		hql.addOrderBy("o.applyTime", true);
		PageBean pb = applicationService.pageBean(hql,currentPage);
		ActionContext.getContext().getValueStack().push(pb);
		
		return "myAppplicationList";
	}
	
	/**
	 * 查看申请信息(下载申请的文件)
	 */
	public String download(){

		inputStreamName = applicationService.getInputStreamById(applicationId);
		Application application = applicationService.getById(applicationId);
		
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		try {
			fileName = this.encodeDownloadFilename(application.getTitle()+".doc", agent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "download";
	}
	
	/**
	 * 查看流转记录（审批记录）
	 */
	public String historyApproveList(){
		List<ApproveInfo> historyApproveList = approveInfoService.findApproveInfoListByApplicationId(applicationId);
		ActionContext.getContext().getValueStack().set("historyApproveList", historyApproveList);
		return "historyApproveList";
	}
	
	/**
	 * 待我审批（我的任务列表）
	 */
	public String myTaskList(){

		return "myTaskList";
	}
	
	/**
	 * 跳转到审批页面
	 */
	public String approveUI(){
		
		return "approveUI";
	}
	
	/**
	 * 审批处理
	 */
	public String approve(){
		return "toMyTaskList";
	}

	//获取申请人的名字，即从session中得到当前登陆的用户
	private User getLoginUser(){
		return (User) ServletActionContext.getRequest().getSession().getAttribute("loginUser");
	}

	/**
	 * 获取上传到服务器中的文件的目录
	 * @return
	 */
	private String getFilePath(File srcFile) {
		String filePath = null;
		if(resource != null){
			String realPath = ServletActionContext.getServletContext().getRealPath("/WEB-INF/uploadFiles");
			SimpleDateFormat sdf = new SimpleDateFormat("/yyyy-MM-dd/");
			String dateStr = sdf.format(new Date());
			dateStr = realPath + dateStr;
			File dateFile = new File(dateStr);
			if(!dateFile.exists()){
				dateFile.mkdirs();
			}
			filePath = dateStr+UUID.randomUUID().toString().replace("-", "")+".doc";
			//移动文件，即上传文件
			File dest = new File(filePath);
			srcFile.renameTo(dest);
		}
		System.out.println(filePath);
		return filePath;
	}
	
	/**
	 * 下载文件时，针对不同浏览器，进行附件名的编码
	 * @param filename 下载文件名
	 * @param agent 客户端浏览器(通过request.getHeader("user-agent")获得)
	 * @return 编码后的下载附件名
	 * @throws IOException
	 */
	public String encodeDownloadFilename(String filename, String agent) throws IOException{
		if(agent.contains("MSIE")){ // W3C浏览器
			filename = URLEncoder.encode(filename,"utf-8");
		}else{ // 非IE浏览器
			filename = new String(filename.getBytes("UTF-8"),"ISO-8859-1");
		}
		return filename;
	}
	
	public void setTemplateId(Long templateId) {
		this.templateId = templateId;
	}
	public Long getTemplateId() {
		return templateId;
	}
	public void setResource(File resource) {
		this.resource = resource;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public void setApplicationId(Long applicationId) {
		this.applicationId = applicationId;
	}
	public InputStream getInputStreamName() {
		return inputStreamName;
	}
	public String getFileName() {
		return fileName;
	}
}
