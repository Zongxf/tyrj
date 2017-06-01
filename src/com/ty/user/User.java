package com.ty.user;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;

@Table("WT_GLY")
public class User {
	@Column
	private String Gly_no;
	@Column
	private String Gly_lname;
	@Column
	private String Gly_pass;
	@Column
	private String Gly_bz;
	@Column
	private String Gly_phone;
	public String getGly_no() {
		return Gly_no;
	}
	public void setGly_no(String gly_no) {
		Gly_no = gly_no;
	}
	public String getGly_lname() {
		return Gly_lname;
	}
	public void setGly_lname(String gly_lname) {
		Gly_lname = gly_lname;
	}
	public String getGly_pass() {
		return Gly_pass;
	}
	public void setGly_pass(String gly_pass) {
		Gly_pass = gly_pass;
	}
	public String getGly_bz() {
		return Gly_bz;
	}
	public void setGly_bz(String gly_bz) {
		Gly_bz = gly_bz;
	}
	public String getGly_phone() {
		return Gly_phone;
	}
	public void setGly_phone(String gly_phone) {
		Gly_phone = gly_phone;
	}

	

}
