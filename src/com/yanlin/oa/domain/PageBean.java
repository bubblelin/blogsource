package com.yanlin.oa.domain;

import java.util.List;

/**
 * 封装分页信息
 * @author bubblelin
 *
 */
@SuppressWarnings("rawtypes")
public class PageBean {

	private int currentPage; 	//当前页
	private int pageSize;		//每页显示记录数

	private List listData;		//查询出当前页的结果集
	private int totalSize;		//总的记录数

	private int totalPage;		//总的页数
	private int beginPageIndex;	//开始页码
	private int endPageIndex;	//结束页码
	
	public PageBean() {
	}

	public PageBean(int currentPage, int pageSize, List listData, int totalSize) {
		this.currentPage = currentPage;
		this.pageSize = pageSize;
		this.listData = listData;
		this.totalSize = totalSize;
		
		this.totalPage = (this.totalSize + this.pageSize - 1) / this.pageSize;
		
		if(totalPage <= 10){
			this.beginPageIndex = 1;
			this.endPageIndex = totalPage;
		}else{
			this.beginPageIndex = this.currentPage - 4;
			this.endPageIndex = this.currentPage + 5;
			
			if(this.beginPageIndex < 1){
				this.beginPageIndex = 1;
				this.endPageIndex = 10;
			}
			if(this.endPageIndex > this.totalPage){
				this.endPageIndex = this.totalPage;
				this.beginPageIndex = this.endPageIndex - 9;
			}
		}
	}


	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List getListData() {
		return listData;
	}
	public void setListData(List listData) {
		this.listData = listData;
	}
	public int getTotalSize() {
		return totalSize;
	}
	public void setTotalSize(int totalSize) {
		this.totalSize = totalSize;
	}
	public int getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getBeginPageIndex() {
		return beginPageIndex;
	}
	public void setBeginPageIndex(int beginPageIndex) {
		this.beginPageIndex = beginPageIndex;
	}
	public int getEndPageIndex() {
		return endPageIndex;
	}
	public void setEndPageIndex(int endPageIndex) {
		this.endPageIndex = endPageIndex;
	}
	
}
