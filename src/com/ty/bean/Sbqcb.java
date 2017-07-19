package com.ty.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;


/**
 * 设备圈存表实体类
 */
@Table("view_dz_sbqcb")
public class Sbqcb {
    @Column
    private String gh;
    @Column
    private String xm;
    @Column
    private String bm;
    @Column
    private String qcsj;
    @Column
    private String qcje;
    @Column
    private String sfly;
  
    @Column
    private String lysj;
    @Column
    private String sfzh;

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

	public String getQcsj() {
		return qcsj;
	}

	public void setQcsj(String qcsj) {
		this.qcsj = qcsj;
	}

	public String getQcje() {
		return qcje;
	}

	public void setQcje(String qcje) {
		this.qcje = qcje;
	}

	public String getSfly() {
		return sfly;
	}

	public void setSfly(String sfly) {
		this.sfly = sfly;
	}

	public String getLysj() {
		return lysj;
	}

	public void setLysj(String lysj) {
		this.lysj = lysj;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	
	

	
}
