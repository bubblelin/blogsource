package com.yanlin.oa.action;

import org.springframework.stereotype.Controller;

/**
 * 主页的action
 * @author bubblelin
 * 没有调用service就用不到@scope("prototype")
 */
@Controller
public class HomeAction {

	/**
	 * 跳转到主页
	 */
	public String index(){
		
		return "index";
	}
	
	/**
	 * 跳转到top.jsp
	 */
	public String top(){
		
		return "top";
	}
	
	/**
	 * 跳转到left.jsp
	 */
	public String left(){
		
		return "left";
	}
	
	/**
	 * 跳转到right.jsp
	 */
	public String right(){
		
		return "right";
	}
	
	/**
	 * 跳转到bottom.jsp
	 */
	public String bottom(){
		
		return "bottom";
	}
}
