package com.ty.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;


/**
 * 圈存对账明细实体类
 */
@Table("view_dz_qcdzmx")
public class Qcdzmx {
    @Column
    private String gh;
    @Column
    private String xm;
    @Column
    private String bm;
    @Column
    private String jysj;
    @Column
    private String jyje;
    @Column
    private String lx;
  
    @Column
    private String zqcje;

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

	
	public String getJysj() {
		return jysj;
	}

	public void setJysj(String jysj) {
		this.jysj = jysj;
	}

	public String getJyje() {
		return jyje;
	}

	public void setJyje(String jyje) {
		this.jyje = jyje;
	}

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getZqcje() {
		return zqcje;
	}

	public void setZqcje(String zqcje) {
		this.zqcje = zqcje;
	}


	
}
