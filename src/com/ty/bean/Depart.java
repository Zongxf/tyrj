package com.ty.bean;

import org.nutz.dao.entity.annotation.Table;

/**
 * 存款统计实体类
 */
@Table("view_dz_depart")
public class Depart {

    private String id;

    private String pid;

    private String text;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}
    

	

}
