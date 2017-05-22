package com.ty.bean;

import org.nutz.dao.entity.annotation.Table;
import org.nutz.dao.entity.annotation.View;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * Created by Administrator on 2017/3/24.
 */
@Table("view_dz_cktj")
public class Cktj {

    private String lx;

    private String ckje;

    private String czy;
    
    private String sj;

	public String getLx() {
		return lx;
	}

	public void setLx(String lx) {
		this.lx = lx;
	}

	public String getCkje() {
		return ckje;
	}

	public void setCkje(String ckje) {
		this.ckje = ckje;
	}

	public String getCzy() {
		return czy;
	}

	public void setCzy(String czy) {
		this.czy = czy;
	}

	public String getSj() {
		return sj;
	}

	public void setSj(String sj) {
		this.sj = sj;
	}
    
    

}
