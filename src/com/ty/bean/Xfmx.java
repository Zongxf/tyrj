package com.ty.bean;

import org.nutz.dao.entity.annotation.Table;


/**
 * 消费明细实体类
 */
@Table("view_dz_xfmx")
public class Xfmx {

    private String rybh;

    private String xm;

    private String bm;
    
    private String xfje;
    
    private String syje;
    
    private String lx;
    
    
    private String xfsj;
    
    private String xfjh;
    
    private double zxfje;

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

	public String getXfje() {
		return xfje;
	}

	public void setXfje(String xfje) {
		this.xfje = xfje;
	}

	public String getSyje() {
		return syje;
	}

	public void setSyje(String syje) {
		this.syje = syje;
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

	public String getXfjh() {
		return xfjh;
	}

	public void setXfjh(String xfjh) {
		this.xfjh = xfjh;
	}

	public double getZxfje() {
		return zxfje;
	}

	public void setZxfje(double zxfje) {
		this.zxfje = zxfje;
	}

	

	
}
