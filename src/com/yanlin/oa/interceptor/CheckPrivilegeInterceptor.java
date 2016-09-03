package com.yanlin.oa.interceptor;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;
import com.yanlin.oa.domain.User;

/**
 * 进行Url的拦截器
 * @author bubblelin
 *
 */
public class CheckPrivilegeInterceptor extends AbstractInterceptor{
	private static final long serialVersionUID = 1L;

	/**
	 * 拦截url的方法
	 */
	@SuppressWarnings("unchecked")
	public String intercept(ActionInvocation ai) throws Exception {
		System.out.println("ִ拦截器已经执行--------------------");
		User loginUser = (User)ServletActionContext.getRequest().getSession().getAttribute("loginUser");
		String url = ai.getProxy().getNamespace() + ai.getProxy().getActionName();
		System.out.println("url:"+url);
		
		if(url.endsWith("UI")){
			url = url.substring(0, url.length()-2);
		}
		
		if(loginUser != null){
			//1.如果用户已经登陆
				//a.用户没有权限，跳转到没有权限的提示页面
				//b.用户有权限，放行
			List<String> urlList = (List<String>)ServletActionContext.getServletContext().getAttribute("urlList");
			if(urlList.contains(url)){
				if(loginUser.hasPrivilegeByUrl(url)){
					return ai.invoke();
				}else{
					return "noPrivilegeUI";
				}
			}else{
				return ai.invoke();
			}
			
		}else{
			//2.如果用户没有登陆
				//a.访问/user_login
				//b.访问非/user_login
			if("/user_login".equals(url)){
				return ai.invoke();
			}else{
				return "loginUI";
			}
		}
	}

}
