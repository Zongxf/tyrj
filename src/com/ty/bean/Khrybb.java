package com.ty.bean;

import org.nutz.dao.entity.annotation.Table;


/**
 * Created by Administrator on 2017/3/24.
 */
@Table("view_dz_khrybb")
public class Khrybb {

    private String rybh;

    private String xm;

    private String bm;
    
    private String lx;
    
    private String khsj;
    
    
    private String kh;
    
    private String czy;
    
    private double zkhrs;

	public String getRybh() {
		return rybh;
	}

	public void setRybh(String rybh) {
		this.rybh = rybh;
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

	 
	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getKhsj() {
		return khsj;
	}

	public void setKhsj(String khsj) {
		this.khsj = khsj;
	}

	public String getCzy() {
		return czy;
	}

	public void setCzy(String czy) {
		this.czy = czy;
	}

	public String getKh() {
		return kh;
	}

	public void setKh(String kh) {
		this.kh = kh;
	}

	public double getZkhrs() {
		return zkhrs;
	}

	public void setZkhrs(double zkhrs) {
		this.zkhrs = zkhrs;
	}

	 
	
}
