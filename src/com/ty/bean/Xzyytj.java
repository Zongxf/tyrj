package com.ty.bean;

import org.nutz.dao.entity.annotation.Table;

/**
 * 小组营业统计实体类
 */
@Table("view_dz_yysbs")
public class Xzyytj {

    private String dep_name;

    private String lname;

    private String summoney;
    
    private String zrs;
    
    private String dep_serial;

	public String getDep_name() {
		return dep_name;
	}

	public void setDep_name(String dep_name) {
		this.dep_name = dep_name;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public String getSummoney() {
		return summoney;
	}

	public void setSummoney(String summoney) {
		this.summoney = summoney;
	}

	public String getZrs() {
		return zrs;
	}

	public void setZrs(String zrs) {
		this.zrs = zrs;
	}

	public String getDep_serial() {
		return dep_serial;
	}

	public void setDep_serial(String dep_serial) {
		this.dep_serial = dep_serial;
	}

	
	
    

}
