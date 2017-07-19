package com.ty.bean;

import org.nutz.dao.entity.annotation.Table;

/**
 * 营业明细表实体类
 */
@Table("view_dz_yymxb")
public class Yymxb {

    private String csmx;//场所明细

    private String sbmc;//设备名称

    private String cblx;//餐别类型
    
    private String xfje;//消费金额
    
    private String jysj;//交易时间
    
    private String xflx;//消费类型
    
    private String yhkh;//银行卡号

	public String getCsmx() {
		return csmx;
	}

	public void setCsmx(String csmx) {
		this.csmx = csmx;
	}

	public String getSbmc() {
		return sbmc;
	}

	public void setSbmc(String sbmc) {
		this.sbmc = sbmc;
	}

	public String getCblx() {
		return cblx;
	}

	public void setCblx(String cblx) {
		this.cblx = cblx;
	}

	public String getXfje() {
		return xfje;
	}

	public void setXfje(String xfje) {
		this.xfje = xfje;
	}

	public String getJysj() {
		return jysj;
	}

	public void setJysj(String jysj) {
		this.jysj = jysj;
	}

	public String getXflx() {
		return xflx;
	}

	public void setXflx(String xflx) {
		this.xflx = xflx;
	}

	public String getYhkh() {
		return yhkh;
	}

	public void setYhkh(String yhkh) {
		this.yhkh = yhkh;
	}

	
    
    

}
