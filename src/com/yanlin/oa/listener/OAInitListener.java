package com.yanlin.oa.listener;

import java.util.List;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.yanlin.oa.domain.Privilege;
import com.yanlin.oa.service.IPrivilegeService;
/**
 * 监听登陆用户的权限
 * @author bubblelin
 *
 */
public class OAInitListener implements ServletContextListener{

	public void contextDestroyed(ServletContextEvent arg0) {
		
	}

	public void contextInitialized(ServletContextEvent sce) {
		//ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");
		//IPrivilegeService privilegeService = (IPrivilegeService)ctx.getBean("privilegeServiceImpl");
		
		//1.方式一：在application中get；方式二：WebApplicationContextUtils.getWebApplicationContext(sc);
		WebApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(sce.getServletContext());

		//2.获取的privilegeService
		IPrivilegeService privilegeService = (IPrivilegeService)applicationContext.getBean("privilegeServiceImpl");
		
		//3.获取顶级的权限
		List<Privilege> topList = privilegeService.findTopList();
		
		//4.将顶级的权限存放在application中
		sce.getServletContext().setAttribute("privilegeTopList", topList);
		System.out.println("权限数据已经存放在application作用域了");
		
		//查询所有要进行校验的权限的url
		List<String> urlList = privilegeService.findAllUrl();
		sce.getServletContext().setAttribute("urlList", urlList);
	}

}
