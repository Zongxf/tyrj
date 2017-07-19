package com.ty.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;


/**
 * 圈存对账明细实体类
 */
@Table("view_dz_gskyecx")
public class Gskyecx {
	
    @Column
    private String gh;
    @Column
    private String xm;
    @Column
    private String bm;
    @Column
    private String gssj;
    @Column
    private String yhkh;//银行卡号
    @Column
    private String sfzh;//身份证号
  
    @Column
    private String ljkh;//逻辑卡号
    
    @Column
    private String wlkh;//物理卡号
    @Column
    private String czye;//充值余额
    @Column
    private String btye;//补贴余额
    
    @Column
    private String zye;//总余额

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

	public String getGssj() {
		return gssj;
	}

	public void setGssj(String gssj) {
		this.gssj = gssj;
	}

	public String getYhkh() {
		return yhkh;
	}

	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}

	public String getSfzh() {
		return sfzh;
	}

	public void setSfzh(String sfzh) {
		this.sfzh = sfzh;
	}

	public String getLjkh() {
		return ljkh;
	}

	public void setLjkh(String ljkh) {
		this.ljkh = ljkh;
	}

	public String getWlkh() {
		return wlkh;
	}

	public void setWlkh(String wlkh) {
		this.wlkh = wlkh;
	}

	public String getCzye() {
		return czye;
	}

	public void setCzye(String czye) {
		this.czye = czye;
	}

	public String getBtye() {
		return btye;
	}

	public void setBtye(String btye) {
		this.btye = btye;
	}

	public String getZye() {
		return zye;
	}

	public void setZye(String zye) {
		this.zye = zye;
	}

	

	 
	


	
}
