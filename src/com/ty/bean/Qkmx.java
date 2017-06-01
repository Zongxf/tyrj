package com.ty.bean;

import org.nutz.dao.entity.annotation.Table;

/**
 * 取款明细实体类
 */
@Table("view_dz_qkmx")
public class Qkmx {

    private String bh;

    private String xm;

    private String bm;
    
    private String qkje;
    
    private String syje;
    
    private String qxj;
    
    private String qbt;
    
    private String qksj;
    
    private double zqkje;

	public String getBh() {
		return bh;
	}

	public void setBh(String bh) {
		this.bh = bh;
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

	public String getQkje() {
		return qkje;
	}

	public void setQkje(String qkje) {
		this.qkje = qkje;
	}

	public String getSyje() {
		return syje;
	}

	public void setSyje(String syje) {
		this.syje = syje;
	}

	public String getQxj() {
		return qxj;
	}

	public void setQxj(String qxj) {
		this.qxj = qxj;
	}

	public String getQbt() {
		return qbt;
	}

	public void setQbt(String qbt) {
		this.qbt = qbt;
	}


	public String getQksj() {
		return qksj;
	}

	public void setQksj(String qksj) {
		this.qksj = qksj;
	}

	public double getZqkje() {
		return zqkje;
	}

	public void setZqkje(double zqkje) {
		this.zqkje = zqkje;
	}

	

	
}
