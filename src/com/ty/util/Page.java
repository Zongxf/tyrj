package com.ty.util;

import java.util.List;

public class Page implements java.io.Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2945322338644266295L;

	/**
	 * @param page
	 */
	public void setPageProperty(Page page) {
			// 设置取数据的区间   
			this.setPageIndex(page.getPageIndex());
			this.setPageSize(page.getPageSize());
	}

	/** 总记录数 */
	private int totalProperty;

	/** 分页结果 */
	private List<?> list;

	/** 开始页码 */
	private int pageIndex;

	/** 每页多少 */
	private int pageSize;

	/** 成功与否 */
	private boolean success;

	/** 查询条件 */
	private Object objCondition;

	public int getTotalProperty() {
		return totalProperty;
	}

	public void setTotalProperty(int totalProperty) {
		this.totalProperty = totalProperty;
	}

	
	public List<?> getList() {
		return list;
	}

	public void setList(List<?> list) {
		this.list = list;
	}

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public Object getObjCondition() {
		return objCondition;
	}

	public void setObjCondition(Object objCondition) {
		this.objCondition = objCondition;
	}



}
