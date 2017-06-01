package com.ty.bean;

import org.nutz.dao.entity.annotation.Table;


/**
 * 撤户人员实体类
 */
@Table("view_dz_chrybb")
public class Chrybb {

    private String rybh;

    private String xm;

    private String bm;
    
    private String txj;
    
    private String tbt;
    
    private String chsj;
    
    
    private String ip;
    
    private String czy;
    
    private double zchrs;

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

	public String getTxj() {
		return txj;
	}

	public void setTxj(String txj) {
		this.txj = txj;
	}

	public String getTbt() {
		return tbt;
	}

	public void setTbt(String tbt) {
		this.tbt = tbt;
	}

	public String getChsj() {
		return chsj;
	}

	public void setChsj(String chsj) {
		this.chsj = chsj;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getCzy() {
		return czy;
	}

	public void setCzy(String czy) {
		this.czy = czy;
	}

	public double getZchrs() {
		return zchrs;
	}

	public void setZchrs(double zchrs) {
		this.zchrs = zchrs;
	}

	 
	 
	
}
