package com.yanlin.oa.utils;

import java.util.ArrayList;
import java.util.List;

/**
 * 辅助生成HQL语句的工具
 * @author bubblelin
 *
 */
public class HQLHelper {

	private String fromStr;
	private String whereStr="";
	private String orderByStr="";
	private List<Object> args = new ArrayList<Object>();//封装HQL中对应的参数信息
	public HQLHelper(){}
	
	/**
	 * 通过构造方法拼接from语句
	 * @param clazz
	 */
	public HQLHelper(Class clazz){
		this.fromStr = "from "+clazz.getSimpleName()+" o ";
	}
	
	/**
	 * 拼接where字句
	 * @return
	 */
	public void addWhere(String condition,Object...args){
		if(this.whereStr.length() == 0){
			this.whereStr = " where "+condition;//第一次拼接
		}else{
			this.whereStr += " and "+condition;//第一次以后的拼接
		}
		
		if(args != null && args.length > 0){
			for(Object o : args){
				this.args.add(o);	//封装参数
			}
		}
	}
	
	/**
	 * 拼接orderby语句
	 * @return
	 */
	public void addOrderBy(String orderBy,boolean asc){
		if(this.orderByStr.length() == 0){
			this.orderByStr = " order by "+orderBy+(asc ?" asc ":" desc ");
		}else{
			this.orderByStr += " , "+orderBy+(asc?" asc ":" desc ");
		}
		
	}
	/**
	 * 1.获取listData集合的HQL语句
	 * @return
	 */
	public String getListHQL(){
		
		return this.fromStr + this.whereStr + this.orderByStr;
	}
	
	/**
	 * 2.获取统计数量的HQL语句
	 * @return
	 */
	public String getCountHQL(){
		return "select count(*)"+this.fromStr+this.whereStr;
	}
	

	public List<Object> getArgs() {
		return args;
	}

	public void setArgs(List<Object> args) {
		this.args = args;
	}
	
	
	
}
