package com.ty.bean;

import org.nutz.dao.entity.annotation.Column;
import org.nutz.dao.entity.annotation.Table;


/**
 * 窗口餐别日报实体类
 */
@Table("view_dz_ckcbrb")
public class Ckcbrb {
    @Column
    private String jyrq;
    @Column
    private String cs;
    @Column
    private String cblx;
    @Column
    private String xfje;//消费金额
    @Column
    private String xfcs;//消费次数
	public String getJyrq() {
		return jyrq;
	}
	public void setJyrq(String jyrq) {
		this.jyrq = jyrq;
	}
	public String getCs() {
		return cs;
	}
	public void setCs(String cs) {
		this.cs = cs;
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
	public String getXfcs() {
		return xfcs;
	}
	public void setXfcs(String xfcs) {
		this.xfcs = xfcs;
	}
    
    
	
	
	

	
}
