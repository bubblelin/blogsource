package com.yanlin.oa.action;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.apache.struts2.ServletActionContext;
import org.jbpm.api.ProcessDefinition;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import sun.misc.BASE64Encoder;

import com.yanlin.oa.base.BaseAction;
import com.yanlin.oa.domain.Template;

/**
 * 流程模板的action
 * @author bubblelin
 *
 */
@Controller
@Scope("prototype")
public class TemplateAction extends BaseAction<Template>{
	private static final long serialVersionUID = 1L;
	//用于模板相应的文件上传
	private File resource;
	//用于模板相应的文件下载
	private InputStream inputStreamName;
	//给下在的文件命名
	private String fileName;
	/**
	 * 模板列表
	 */
	public String list(){
		List<Template> templates = templateService.findAll();
		this.getValueStack().set("templates", templates);
		return "list";
	}
	
	/**
	 * 删除模板
	 */
	public String delete(){
		templateService.delete(model.getId());
		return "toList";
	}
	
	/**
	 * 跳转到添加模板页面
	 */
	public String addUI(){
		//查询流程定义，用于回显
		List<ProcessDefinition> processDefinitions = processDefinitionService.findLastList();
		this.getValueStack().set("processDefinitions", processDefinitions);
		return "addUI";
	}
	
	/**
	 * 添加模板
	 */
	public String add(){
		String filePath = this.getFilePath(resource);
		
		model.setFilePath(filePath);
		templateService.save(model);
		return "toList";
	}
	

	/**
	 * 跳转到模板修改页面
	 */
	public String editUI(){
		//回显模板名称
		Template template = templateService.getById(model.getId());
		this.getValueStack().push(template);
		//回显流程定义
		List<ProcessDefinition> processDefinitions = processDefinitionService.findLastList();
		this.getValueStack().set("processDefinitions", processDefinitions);;
		return "editUI";
	}
	
	/**
	 * 修改模板
	 */
	public String edit(){
		//查询需要修改的模板
		Template template = templateService.getById(model.getId());
		template.setName(model.getName());
		template.setProcessDefinitionKey(model.getProcessDefinitionKey());
		if(resource != null){
			String filePath = template.getFilePath();
			//删除旧文件
			File oldFile = new File(filePath);
			if(oldFile.exists()){
				oldFile.delete();
			}
			//上传新文件
			filePath = this.getFilePath(resource);
			template.setFilePath(filePath);
		}
		templateService.update(template);
		return "toList";
	}
	
	/**
	 * 下载模板相应的 文件
	 */
	public String download(){
		//获取输入流
		inputStreamName = templateService.getInputStreamById(model.getId());
		Template template = templateService.getById(model.getId());
		String agent = ServletActionContext.getRequest().getHeader("User-Agent");
		try {
			fileName = this.encodeDownloadFilename(template.getName()+ ".doc", agent);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "download";
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
	
	public void setResource(File resource) {
		this.resource = resource;
	}
	public InputStream getInputStreamName() {
		return inputStreamName;
	}
	public void setInputStreamName(InputStream inputStreamName) {
		this.inputStreamName = inputStreamName;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
}
