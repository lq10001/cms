package com.ly.comm;

import org.nutz.dao.pager.Pager;

public class Page extends Pager {
		
	/**
	 * 
	 */
	private static final long serialVersionUID = 232222222222333L;	
	
	private int pageNum;
	
	private int numPerPage;
	
	
	public int getPageNum() {
		return super.getPageNumber();
	}

	public void setPageNum(int pageNum) {
		this.pageNum = pageNum;
		super.setPageNumber(pageNum);
	}

	public int getNumPerPage() {
		return super.getPageSize();
	}

	public void setNumPerPage(int numPerPage) {
		this.numPerPage = numPerPage;
		super.setPageSize(numPerPage);
	}

}
