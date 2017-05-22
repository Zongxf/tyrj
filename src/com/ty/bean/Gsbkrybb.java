package com.ty.bean;

import org.nutz.dao.entity.annotation.Table;


/**
 * Created by Administrator on 2017/3/24.
 */
@Table("view_dz_gsbkbb")
public class Gsbkrybb {

    private String rybh;

    private String xm;

    private String bm;
    
    private String bksj;
    
    private String kh;
    
    private String syje;
    
    private double zgsrs;

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

	public String getBksj() {
		return bksj;
	}

	public void setBksj(String bksj) {
		this.bksj = bksj;
	}

	public String getKh() {
		return kh;
	}

	public void setKh(String kh) {
		this.kh = kh;
	}

	public String getSyje() {
		return syje;
	}

	public void setSyje(String syje) {
		this.syje = syje;
	}

	public double getZgsrs() {
		return zgsrs;
	}

	public void setZgsrs(double zgsrs) {
		this.zgsrs = zgsrs;
	}

	 
	 
	 
	
}
