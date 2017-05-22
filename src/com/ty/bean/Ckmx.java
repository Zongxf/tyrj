package com.ty.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;


/**
 * Created by Administrator on 2017/3/24.
 */
@Table("view_dz_ckmx")
public class Ckmx {
    @Column
    private String bh;
    @Column
    private String xm;
    @Column
    private String bm;
    @Column
    private String ckje;
    @Column
    private String syje;
    @Column
    private String lx;
    @Column
    private String ckrq;
    @Column
    private String cksj;
    @Column
    private String zckje;

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

	public String getCkje() {
		return ckje;
	}

	public void setCkje(String ckje) {
		this.ckje = ckje;
	}

	public String getSyje() {
		return syje;
	}

	public void setSyje(String syje) {
		this.syje = syje;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getCkrq() {
		return ckrq;
	}

	public void setCkrq(String ckrq) {
		this.ckrq = ckrq;
	}

	public String getCksj() {
		return cksj;
	}

	public void setCksj(String cksj) {
		this.cksj = cksj;
	}

	public String getZckje() {
		return zckje;
	}

	public void setZckje(String zckje) {
		this.zckje = zckje;
	}
	

	
}
