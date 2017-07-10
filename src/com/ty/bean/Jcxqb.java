package com.ty.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;


/**
 * 就餐详情表实体类
 */
@Table("view_dz_jcxqb")
public class Jcxqb {
    @Column
    private String gh;
    @Column
    private String xm;
    @Column
    private String bm;
    @Column
    private String cbmc;//餐别名称
    @Column
    private String jysj;//交易时间
    @Column
    private String zc;
  
    @Column
    private String wc;//午餐
    
    @Column
    private String wwc;//晚餐
    
    @Column
    private String jbc;//加班餐
    
    @Column
    private String fsd;//非时段

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

	public String getCbmc() {
		return cbmc;
	}

	public void setCbmc(String cbmc) {
		this.cbmc = cbmc;
	}

	public String getJysj() {
		return jysj;
	}

	public void setJysj(String jysj) {
		this.jysj = jysj;
	}

	public String getZc() {
		return zc;
	}

	public void setZc(String zc) {
		this.zc = zc;
	}

	public String getWc() {
		return wc;
	}

	public void setWc(String wc) {
		this.wc = wc;
	}

	public String getWwc() {
		return wwc;
	}

	public void setWwc(String wwc) {
		this.wwc = wwc;
	}

	public String getJbc() {
		return jbc;
	}

	public void setJbc(String jbc) {
		this.jbc = jbc;
	}

	public String getFsd() {
		return fsd;
	}

	public void setFsd(String fsd) {
		this.fsd = fsd;
	}

	

	


	
}
