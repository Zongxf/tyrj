package com.ty.bean;

import org.nutz.dao.entity.annotation.Table;

/**
 * 用户列表实体类
 */
@Table("view_dz_userlist")
public class Userlist {

    private String gh;

    private String xm;

    private String bm;
    
    private String ljkh;//逻辑卡号
    
    private String sfzh;//身份证号
    
    private String lxfs;//联系方式
   
    private String yhkh;//银行卡号
    
    private String khsj;//开户时间
    
    private String sex;//性别
    
    

    
	public String getKhsj() {
		return khsj;
	}

	public void setKhsj(String khsj) {
		this.khsj = khsj;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getLxfs() {
		return lxfs;
	}

	public void setLxfs(String lxfs) {
		this.lxfs = lxfs;
	}

	public String getGh() {
		return gh;
	}

	public void setGh(String gh) {
		this.gh = gh;
	}

	public String getXm() {
		return xm;
	}

	public void setXm(String xm) {
		this.xm = xm;
	}

	public String getBm() {
		return bm;
	}

	public void setBm(String bm) {
		this.bm = bm;
	}

	public String getLjkh() {
		return ljkh;
	}

	public void setLjkh(String ljkh) {
		this.ljkh = ljkh;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getYhkh() {
		return yhkh;
	}

	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}

	
    

	

}
