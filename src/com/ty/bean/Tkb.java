package com.ty.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;


/**
 * 退款表实体类
 */
@Table("view_dz_tkb")
public class Tkb {
	
    @Column
    private String gh;
    @Column
    private String xm;
    @Column
    private String bm;
    @Column
    private String tksj;
    @Column
    private String qkje;//取款金额
    @Column
    private String qxj;//取现金
    @Column
    private String qbt;//取补贴
    @Column
    private String czy;
  
    @Column
    private String ljkh;//逻辑卡号
    @Column
    private String yhkh;//银行卡号
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
	public String getTksj() {
		return tksj;
	}
	public void setTksj(String tksj) {
		this.tksj = tksj;
	}
	public String getQkje() {
		return qkje;
	}
	public void setQkje(String qkje) {
		this.qkje = qkje;
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
	public String getCzy() {
		return czy;
	}
	public void setCzy(String czy) {
		this.czy = czy;
	}
	public String getLjkh() {
		return ljkh;
	}
	public void setLjkh(String ljkh) {
		this.ljkh = ljkh;
	}
	public String getYhkh() {
		return yhkh;
	}
	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}

	

	
}
