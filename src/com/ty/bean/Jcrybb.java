package com.ty.bean;

import org.nutz.dao.entity.annotation.Table;


/**
 * 纠错人员报表实体类
 * 
 */
@Table("view_dz_jcrybb")
public class Jcrybb {

    private String rybh;

    private String xm;

    private String bm;
    
    private String bh;
    
    private String jcje;
    
    private String ysje;
    
    
    private String jcsj;
    
    private String kh;
    
    private double zjcrs;

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

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
	}

	public String getJcje() {
		return jcje;
	}

	public void setJcje(String jcje) {
		this.jcje = jcje;
	}

	public String getYsje() {
		return ysje;
	}

	public void setYsje(String ysje) {
		this.ysje = ysje;
	}

	public String getJcsj() {
		return jcsj;
	}

	public void setJcsj(String jcsj) {
		this.jcsj = jcsj;
	}


	public String getKh() {
		return kh;
	}

	public void setKh(String kh) {
		this.kh = kh;
	}

	public double getZjcrs() {
		return zjcrs;
	}

	public void setZjcrs(double zjcrs) {
		this.zjcrs = zjcrs;
	}

 
	
}
