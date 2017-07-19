package com.ty.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;


/**
 * 操作员日志实体类
 */
@Table("view_dz_czyrz")
public class Czyrz {
    @Column
    private String gh;
    @Column
    private String xm;
    @Column
    private String bm;
    @Column
    private String ljkh;//逻辑卡号
    @Column
    private String sj;
    @Column
    private String cklx;//存款类型
  
    @Column
    private String czy;
    @Column
    private String yje;//原金额
    
    @Column
    private String jyje;//交易金额
    
    @Column
    private String xye;//现余额

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

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}

	public String getCklx() {
		return cklx;
	}

	public void setCklx(String cklx) {
		this.cklx = cklx;
	}

	public String getCzy() {
		return czy;
	}

	public void setCzy(String czy) {
		this.czy = czy;
	}

	public String getYje() {
		return yje;
	}

	public void setYje(String yje) {
		this.yje = yje;
	}

	public String getJyje() {
		return jyje;
	}

	public void setJyje(String jyje) {
		this.jyje = jyje;
	}

	public String getXye() {
		return xye;
	}

	public void setXye(String xye) {
		this.xye = xye;
	}

	
	
	

	
}
