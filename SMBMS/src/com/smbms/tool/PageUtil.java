package com.smbms.tool;

import java.util.List;

/**
 * ��ҳ������
 */
public class PageUtil<T> {
	private Integer pageIndex = 1;
	private Integer pageSize = 5 ;
	private Integer totalCount;
	@SuppressWarnings("unused")
	private Integer totalPageCount;
	private List<T> pageList;
	private Integer num =0;//��ȡ pageIndex-1 * pageSize�Ľ��
	
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		if(num > 0){
			this.num = num;
		}
	}
	/**
	 * �Ƿ��������һҳ
	 */
	public boolean prevPage(){
		if(pageIndex > 1) return true;
		return false;
	}
	/**
	 * �Ƿ�����һҳ
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
