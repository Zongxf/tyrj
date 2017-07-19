package com.ty.user;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;

@At("/timer")
@InjectName()
@IocBean
@Filters
public class TimerAction extends HttpServlet{
	
	public String xjckrs1 = "0.0";
	public String ryye1 = "0.0";
	public String qcrs1 = "0.0";

	/**
	 * 定时器执行文件
	 */
	private static final long serialVersionUID = 1L;
	@Inject
	private NutDao dao;

	Date now = new Date();
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String hehe = dateFormat.format(now);

	  @At()
	  @Ok("")
	public void showAll(HttpServletRequest request,
			HttpServletResponse response) throws Exception{
		String xjckrs = "select count(a.bh) as xjckrs,sum(a.ckje) as xjbh from dbo.view_dz_ckmx a left join dbo.xf_mx b on a.bh=b.user_serial "
				+ "where a.lx=1 and b.gly_no != 'CBC' and ckrq >= '"+hehe+" 00:00:00' and ckrq <= '"+hehe+" 23:59:59'";
		String ryye = "select count(rq) as n, sum(new_money/100) as ryye from view_dz_yysb where  rq >= '"+hehe+" 00:00:00' and rq <= '"+hehe+" 23:59:59' ";
		String qcrs = "select count(a.bh) as qcrs,sum(a.ckje) as qcbh from dbo.view_dz_ckmx a left join dbo.xf_mx b on a.bh=b.user_serial "
				+ " where a.lx=1 and b.gly_no = 'CBC' and ckrq >= '"+hehe+" 00:00:00' and ckrq <= '"+hehe+" 23:59:59'";
		Sql sql = Sqls.create(xjckrs);
		Sql sql1 = Sqls.create(ryye);
		Sql sql2 = Sqls.create(qcrs);

		sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet arg1, Sql arg2)
					throws SQLException {
				List<String> list = new ArrayList<String>();
				while (arg1.next()) {
					list.add(arg1.getString("xjbh"));
				}
				return list;

			}
		});
		sql1.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet arg1, Sql arg2)
					throws SQLException {
				List<String> list = new ArrayList<String>();
				while (arg1.next()) {
					if(StringUtils.isNotEmpty(arg1.getString("ryye"))&&!"".equals(arg1.getString("ryye"))){
						list.add("0.0");
					}else{
					list.add(arg1.getString("ryye"));
					}
				}
				return list;

			}
		});
		sql2.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet arg1, Sql arg2)
					throws SQLException {
				List<String> list = new ArrayList<String>();
				while (arg1.next()) {
					list.add(arg1.getString("qcbh"));
				}
				return list;

			}
		});

		dao.execute(sql);
		dao.execute(sql1);
		dao.execute(sql2);

		
		 xjckrs1 = sql.getList(String.class).get(0);
		 ryye1 = sql1.getList(String.class).get(0);
		 qcrs1 = sql2.getList(String.class).get(0);

		HttpSession session = request.getSession(true);
		session.removeAttribute("xjbh");
		session.setAttribute("xjbh", xjckrs1);
		session.removeAttribute("ryye");
		session.setAttribute("ryye", ryye1);
		session.removeAttribute("qcbh");
		session.setAttribute("qcbh", qcrs1);
	}

}
