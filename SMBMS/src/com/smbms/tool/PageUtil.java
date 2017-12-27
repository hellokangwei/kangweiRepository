package com.smbms.tool;

import java.util.List;

/**
 * 分页工具类
 */
public class PageUtil<T> {
	private Integer pageIndex = 1;
	private Integer pageSize = 5 ;
	private Integer totalCount;
	@SuppressWarnings("unused")
	private Integer totalPageCount;
	private List<T> pageList;
	private Integer num =0;//获取 pageIndex-1 * pageSize的结果
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		if(num > 0){
			this.num = num;
		}
	}
	/**
	 * 是否可以有上一页
	 */
	public boolean prevPage(){
		if(pageIndex > 1) return true;
		return false;
	}
	/**
	 * 是否有下一页
	 */
	public boolean nextPage(){
		if(pageIndex < this.getTotalPageCount()) return true;
		return false;
	}
	
	public Integer getPageIndex() {
		return pageIndex;
	}
	public void setPageIndex(Integer pageIndex) {
		if(pageIndex < 1){
			this.pageIndex = 1;
		}else if(pageIndex > getTotalPageCount()){
			this.pageIndex = getTotalPageCount();
		}else{
			this.pageIndex = pageIndex;
		}
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(Integer totalCount) {
		this.totalCount = totalCount;
	}
	public Integer getTotalPageCount() {
		return totalCount % pageSize == 0 ? (totalCount/pageSize) : (totalCount/pageSize)+1;
	}
	public void setTotalPageCount() {
		this.totalCount = totalCount % pageSize == 0 ? (totalCount/pageSize) : (totalCount/pageSize)+1;
	}
	public List<T> getPageList() {
		return pageList;
	}
	public void setPageList(List<T> pageList) {
		this.pageList = pageList;
	}
}
