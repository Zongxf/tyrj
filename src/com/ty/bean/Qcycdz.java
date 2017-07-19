package com.ty.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;


/**
 * 圈存异常对账实体类
 */
@Table("view_dz_qcycdz")
public class Qcycdz {
    @Column
    private String gh;//工号
    @Column
    private String xm;
    @Column
    private String bm;
    @Column
    private String yhkh;//银行卡号
    @Column
    private String qcje;//交易金额
    @Column
    private String jysj;
    @Column
    private String jydm;//交易代码
    @Column
    private String jylsh;//交易流水号
    @Column
    private String jyzdls;//交易终端流水
    @Column
    private String czydm;//操作员代码
    @Column
    private String hisjyls;//HIS交易流水
    @Column
    private String hisjysj;//HIS交易时间
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
	public String getYhkh() {
		return yhkh;
	}
	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}
	public String getQcje() {
		return qcje;
	}
	public void setQcje(String qcje) {
		this.qcje = qcje;
	}
	public String getJysj() {
		return jysj;
	}
	public void setJysj(String jysj) {
		this.jysj = jysj;
	}
	public String getJydm() {
		return jydm;
	}
	public void setJydm(String jydm) {
		this.jydm = jydm;
	}
	public String getJylsh() {
		return jylsh;
	}
	public void setJylsh(String jylsh) {
		this.jylsh = jylsh;
	}
	public String getJyzdls() {
		return jyzdls;
	}
	public void setJyzdls(String jyzdls) {
		this.jyzdls = jyzdls;
	}
	public String getCzydm() {
		return czydm;
	}
	public void setCzydm(String czydm) {
		this.czydm = czydm;
	}
	public String getHisjyls() {
		return hisjyls;
	}
	public void setHisjyls(String hisjyls) {
		this.hisjyls = hisjyls;
	}
	public String getHisjysj() {
		return hisjysj;
	}
	public void setHisjysj(String hisjysj) {
		this.hisjysj = hisjysj;
	}

	 
	
}
