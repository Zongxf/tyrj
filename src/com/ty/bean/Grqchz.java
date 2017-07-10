package com.ty.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;


/**
 * 个人圈存汇总实体类
 */
@Table("view_dz_skgrqchz")
public class Grqchz {
    @Column
    private String gh;//工号
    @Column
    private String xm;//姓名
    @Column
    private String bmmc;//部门
    @Column
    private String sbmc;//设备名称
    @Column
    private String jysj;//交易时间
    @Column
    private String jyqye;//交易前余额
    @Column
    private String xfmx;//交易金额
    @Column
    private String jyhye;//交易后余额
    @Column
    private String jyqze;//交易前总额
    @Column
    private String jyze;//交易总额
    @Column
    private String jyhze;//交易后总额
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
	public String getBmmc() {
		return bmmc;
	}
	public void setBmmc(String bmmc) {
		this.bmmc = bmmc;
	}
	public String getSbmc() {
		return sbmc;
	}
	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}
	public String getJysj() {
		return jysj;
	}
	public void setJysj(String jysj) {
		this.jysj = jysj;
	}
	public String getJyqye() {
		return jyqye;
	}
	public void setJyqye(String jyqye) {
		this.jyqye = jyqye;
	}
	public String getXfmx() {
		return xfmx;
	}
	public void setXfmx(String xfmx) {
		this.xfmx = xfmx;
	}
	public String getJyhye() {
		return jyhye;
	}
	public void setJyhye(String jyhye) {
		this.jyhye = jyhye;
	}
	public String getJyqze() {
		return jyqze;
	}
	public void setJyqze(String jyqze) {
		this.jyqze = jyqze;
	}
	public String getJyze() {
		return jyze;
	}
	public void setJyze(String jyze) {
		this.jyze = jyze;
	}
	public String getJyhze() {
		return jyhze;
	}
	public void setJyhze(String jyhze) {
		this.jyhze = jyhze;
	}

	
	
}
