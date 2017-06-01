package com.ty.bean;

import org.nutz.dao.entity.annotation.Table;


/**
 * 计次消费明细实体类
 */
@Table("view_dz_jcxfmx")
public class Jcxfmx {

    private String rybh;

    private String xm;

    private String bm;
    
    private String cs;
    
    private String lx;
    
    private String xfsj;
    
    private String xfjh;
    
    private double zxfcs;

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

	 
	public String getCs() {
		return cs;
	}

	public void setCs(String cs) {
		this.cs = cs;
	}

	public String getXfjh() {
		return xfjh;
	}

	public void setXfjh(String xfjh) {
		this.xfjh = xfjh;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getXfsj() {
		return xfsj;
	}

	public void setXfsj(String xfsj) {
		this.xfsj = xfsj;
	}

	public String getXfjd() {
		return xfjh;
	}

	public void setXfjd(String xfjh) {
		this.xfjh = xfjh;
	}

	public double getZxfcs() {
		return zxfcs;
	}

	public void setZxfcs(double zxfcs) {
		this.zxfcs = zxfcs;
	}

	

	

	
}
