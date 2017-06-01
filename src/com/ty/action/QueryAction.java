package com.ty.action;
/**
 * 存款统计、明细模块的页面的跳转
 * 以及对应JSON数据的获取
 */
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.sql.callback.QueryRecordCallback;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ty.bean.Ckmx;
import com.ty.bean.Cktj;
import com.ty.bean.Jcxfmx;
import com.ty.bean.Qkmx;
import com.ty.bean.Xfmx;
import com.ty.bean.Xzyytj;
import com.ty.framework.action.BaseAction;
import com.ty.util.Page;

@At("/query")
@InjectName()
@IocBean
@Filters
public class QueryAction extends BaseAction {

    private Map<String, Object> resMap;
    @Inject
    private NutDao dao;
    
    Date now = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String hehe = dateFormat.format( now ); 


    public QueryAction() {
        resMap = new HashMap<String, Object>();

    }


    @At()
    @Ok("jsp:jsp/yyjs")
    public void query(String dateb,String datee,HttpServletRequest req) throws Exception {
    	int a = 0;//总人数
    	 Double b = 0.0;//总变化
    	String khrs = "select count(*) as khrs from view_dz_khrybb where 1 = 1 ";
    	String chrs = "select count(*) as chrs,SUM(txj)+SUM(tbt) as bh  from view_dz_chrybb  where 1 = 1 ";
    	String gsrs = "select count(*) as gsrs,sum(syje) as bh from view_dz_gsbkbb where 1=1";
    	String xjckrs =  "select count(a.bh) as xjckrs,sum(a.ckje) as xjbh from dbo.view_dz_ckmx a left join dbo.xf_mx b on a.bh=b.user_serial "
    			+"where a.lx=1 and b.gly_no != 'CBC' "; 
    	String btckrs =  "select count(*) as btckrs,sum(ckje) as btbh from view_dz_ckmx where lx=2 "; 
    	String qcrs = "select count(a.bh) as qcrs,sum(a.ckje) as qcbh from dbo.view_dz_ckmx a left join dbo.xf_mx b on a.bh=b.user_serial "
                   +" where a.lx=1 and b.gly_no = 'CBC'";
    	String qkrs =  "select count(*) as qkrs,sum(qkje) as qkbh from view_dz_qkmx where 1=1 "; 
    	if(StringUtils.isNotEmpty(dateb)){
    		khrs = khrs + " and khsj >= '"+dateb+"'";
    		chrs = chrs + " and chsj >= '"+dateb+"'";
    		gsrs = gsrs + " and bksj >= '"+dateb+"'";
    		xjckrs = xjckrs + " and ckrq >= '"+dateb+"'";
    		btckrs = btckrs + " and ckrq >= '"+dateb+"'";
    		qkrs = qkrs + " and qksj >= '"+dateb+"'";
    		qcrs = qcrs + " and ckrq >= '"+dateb+"'";
    		
    	}else{
    		khrs = khrs + " and khsj >= '"+hehe +" 00:00:00'";
    		chrs = chrs + " and chsj >= '"+hehe +" 00:00:00'";
    		gsrs = gsrs + " and bksj >= '"+hehe +" 00:00:00'";
    		xjckrs = xjckrs + " and ckrq >= '"+hehe +" 00:00:00'";
    		btckrs = btckrs + " and ckrq >= '"+hehe +" 00:00:00'";
    		qkrs = qkrs + " and qksj >= '"+hehe +" 00:00:00'";
    		qcrs = qcrs + " and ckrq >= '"+hehe +" 00:00:00'";
    		dateb = hehe+" 00:00:00";
    	}
    	if(StringUtils.isNotEmpty(datee)){
    		khrs = khrs + " and khsj <= '"+datee+"'";
    		chrs = chrs + " and chsj <= '"+datee+"'";
    		gsrs = gsrs + " and bksj <= '"+datee+"'";
    		xjckrs = xjckrs + " and ckrq <= '"+datee+"'";
    		btckrs = btckrs + " and ckrq <= '"+datee+"'";
    		qkrs = qkrs + " and qksj <= '"+datee+"'";
    		qcrs = qcrs + " and ckrq <= '"+datee+"'";
    		
    	}else{
    		khrs = khrs + " and khsj <= '"+hehe +" 23:59:59'";
    		chrs = chrs + " and chsj <= '"+hehe +" 23:59:59'";
    		gsrs = gsrs + " and bksj <= '"+hehe +" 23:59:59'";
    		xjckrs = xjckrs + " and ckrq <= '"+hehe +" 23:59:59'";
    		btckrs = btckrs + " and ckrq <= '"+hehe +" 23:59:59'";
    		qkrs = qkrs + " and qksj <= '"+hehe +" 23:59:59'";
    		qcrs = qcrs + " and ckrq <= '"+hehe +" 23:59:59'";
    		datee = hehe+" 23:59:59";
    	}
    	
    	Sql sql = Sqls.create(khrs);
    	Sql sql1 = Sqls.create(chrs);
    	Sql sql2 = Sqls.create(gsrs);
    	Sql sql3 = Sqls.create(xjckrs);
    	Sql sql4 = Sqls.create(btckrs);
    	Sql sql5 = Sqls.create(qkrs);
    	Sql sql6 = Sqls.create(qcrs);
    	sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet arg1, Sql arg2)
					throws SQLException {
				List<String> list = new ArrayList<String>();
				while(arg1.next()){
					list.add(arg1.getString("khrs"));
				}
				return list;
			}
		});
    	sql1.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet arg1, Sql arg2)
					throws SQLException {
				List<String> list = new ArrayList<String>();
				while(arg1.next()){
					list.add(arg1.getString("chrs"));
					list.add(arg1.getString("bh"));
				}
				return list;
				
			}
		});
    	
    	sql2.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet arg1, Sql arg2)
					throws SQLException {
				List<String> list = new ArrayList<String>();
				while(arg1.next()){
					list.add(arg1.getString("gsrs"));
					list.add(arg1.getString("bh"));
				}
				return list;
				
			}
		});
    	sql3.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet arg1, Sql arg2)
					throws SQLException {
				List<String> list = new ArrayList<String>();
				while(arg1.next()){
					list.add(arg1.getString("xjckrs"));
					list.add(arg1.getString("xjbh"));
				}
				return list;
				
			}
		});
    	sql4.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet arg1, Sql arg2)
					throws SQLException {
				List<String> list = new ArrayList<String>();
				while(arg1.next()){
					list.add(arg1.getString("btckrs"));
					list.add(arg1.getString("btbh"));
				}
				return list;
				
			}
		});
    	sql5.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet arg1, Sql arg2)
					throws SQLException {
				List<String> list = new ArrayList<String>();
				while(arg1.next()){
					list.add(arg1.getString("qkrs"));
					list.add(arg1.getString("qkbh"));
				}
				return list;
				
			}
		});
    	sql6.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet arg1, Sql arg2)
					throws SQLException {
				List<String> list = new ArrayList<String>();
				while(arg1.next()){
					list.add(arg1.getString("qcrs"));
					list.add(arg1.getString("qcbh"));
				}
				return list;
				
			}
		});
    	
    	dao.execute(sql);
    	dao.execute(sql1);
    	dao.execute(sql2);
    	dao.execute(sql3);
    	dao.execute(sql4);
    	dao.execute(sql5);
    	dao.execute(sql6);
    	
    	String khrs1 = sql.getList(String.class).get(0);
    	String chrs1 = sql1.getList(String.class).get(0);
    	String gsrs1 = sql2.getList(String.class).get(0);
    	String xjckrs1 = sql3.getList(String.class).get(0);
    	String btckrs1 = sql4.getList(String.class).get(0);
    	String qkrs1 = sql5.getList(String.class).get(0);
    	String qcrs1 = sql6.getList(String.class).get(0);
    	
    	//String khrs2 = sql.getList(String.class).get(1);
    	String chrs2 = sql1.getList(String.class).get(1);
    	String gsrs2 = sql2.getList(String.class).get(1);
    	String xjckrs2 = sql3.getList(String.class).get(1);
    	String btckrs2 = sql4.getList(String.class).get(1);
    	String qkrs2 = sql5.getList(String.class).get(1);
    	String qcrs2 = sql6.getList(String.class).get(1);
    	
    	if(StringUtils.isNotEmpty(khrs1)){
    		a = a+Integer.parseInt(khrs1);
    	}
    	if(StringUtils.isNotEmpty(chrs1)){
    		a = a+Integer.parseInt(chrs1);
    	}
    	if(StringUtils.isNotEmpty(gsrs1)){
    		a = a+Integer.parseInt(gsrs1);
    	}
    	if(StringUtils.isNotEmpty(xjckrs1)){
    		a = a+Integer.parseInt(xjckrs1);
    	}
    	if(StringUtils.isNotEmpty(btckrs1)){
    		a = a+Integer.parseInt(btckrs1);
    	}
    	if(StringUtils.isNotEmpty(qkrs1)){
    		a = a+Integer.parseInt(qkrs1);
    	}
    	if(StringUtils.isNotEmpty(qcrs1)){
    		a = a+Integer.parseInt(qcrs1);
    	}
    	
    	/*if(!"".equals(khrs2)){
    		b = b+Integer.parseInt(khrs2);
    	}*/
    	if(StringUtils.isNotEmpty(chrs2)&&!"".equals(chrs2)){
    		b = b+Double.parseDouble(chrs2);
    	}else{
    		chrs2 = "0:00";
    	}
    	if(StringUtils.isNotEmpty(gsrs2)&&!"".equals(gsrs2)){
    		b = b+Double.parseDouble(gsrs2);
    	}else{
    		gsrs2 = "0:00";  
    		}
    	if(StringUtils.isNotEmpty(xjckrs2)&&!"".equals(xjckrs2)){
    		b = b+Double.parseDouble(xjckrs2);
    	}else{
    		xjckrs2 = "0:00";
    	}
    	if(StringUtils.isNotEmpty(btckrs2)&&!"".equals(btckrs2)){
    		b = b+Double.parseDouble(btckrs2);
    	}else{
    		btckrs2 = "0:00";
    	}
    	if(StringUtils.isNotEmpty(qkrs2)&&!"".equals(qkrs2)){
    		b = b+Double.parseDouble(qkrs2);
    	}else{
    		qkrs2 = "0:00";
    	}
    	if(StringUtils.isNotEmpty(qcrs2)&&!"".equals(qcrs2)){
    		b = b+Double.parseDouble(qcrs2);
    	}else{
    		qcrs2 = "0:00";
    	}
    	
    	req.setAttribute("khrs", khrs1);
    	
    	req.setAttribute("chrs", chrs1);
    	req.setAttribute("chrsbh", chrs2);
    	
    	req.setAttribute("gsrs", gsrs1);
    	req.setAttribute("gsrsbh", gsrs2);
    	
    	req.setAttribute("xjckrs", xjckrs1);
    	req.setAttribute("xjbh", xjckrs2);
    	
    	req.setAttribute("btckrs", btckrs1);
    	req.setAttribute("btbh", btckrs2);
    	
    	req.setAttribute("qkrs", qkrs1);
    	req.setAttribute("qkbh", qkrs2);
    	
    	req.setAttribute("qcrs", qcrs1);
    	req.setAttribute("qcbh", qcrs2);
    	BigDecimal   a1   =   new   BigDecimal(b);  
    	req.setAttribute("zrs", a );
    	req.setAttribute("zbh", a1.setScale(2,   BigDecimal.ROUND_HALF_UP).doubleValue());
    	
    	req.setAttribute("dateb", dateb);
    	req.setAttribute("datee", datee);
    	
    	
    	
    	
		
    	
    	
    }
    //存款统计
    @At()
    @Ok("jsp:jsp/tongji/cktj")
    public void queryCktj(HttpServletRequest req) throws Exception {
    	  Sql sql=Sqls.create("select distinct lx from  view_dz_cktj GROUP BY lx,czy");
    	  Sql sql1=Sqls.create("select distinct czy from  view_dz_cktj GROUP BY lx,czy");
          sql.setCallback(new QueryRecordCallback());
          sql1.setCallback(new QueryRecordCallback());
         
          List<Cktj> list =   dao.execute(sql).getList(Cktj.class);
          req.setAttribute("cklx", list);
          
          List<Cktj> list1 =   dao.execute(sql1).getList(Cktj.class);
          req.setAttribute("czy", list1);
    }
    
  //存款明细
    @At()
    @Ok("jsp:jsp/mx/ckmx")
    public void queryCkmx(HttpServletRequest req) throws Exception {
    	  Sql sql=Sqls.create("select distinct lx from  view_dz_ckmx GROUP BY lx");
          sql.setCallback(new QueryRecordCallback());
         
          List<Cktj> list =   dao.execute(sql).getList(Cktj.class);
          req.setAttribute("cklx", list);
          
    }
    
    //取款明细
    @At()
    @Ok("jsp:jsp/mx/qkmx")
    public void queryQkmx(HttpServletRequest req) throws Exception {
          
    }
    
    //消费明细
    @At()
    @Ok("jsp:jsp/mx/xfmx")
    public void queryXfmx(HttpServletRequest req) throws Exception {
          
    }
  //消费计次明细
    @At()
    @Ok("jsp:jsp/mx/jcmx")
    public void queryXfjcmx(HttpServletRequest req) throws Exception {
          
    }
   
    
    //小组营业统计
    @At()
    @Ok("jsp:jsp/tongji/xzyytj")
    public void queryXzyytj(HttpServletRequest req) throws Exception {
    }
   
    //存款统计Json
    @At()
    @Ok("json")
    public Map<String, Object> queryCktjJson(@Param("..") Map<String, String> map) throws Exception {
        resMap.clear();
        String str="select lx,sum(je) as ckje, czy from  view_dz_cktj where 1=1";
        if (map.containsKey("lx") & StringUtils.isNotEmpty(map.get("lx"))) {
        	str=str+" and lx = "+map.get("lx");
        }
        if (map.containsKey("czy") & StringUtils.isNotEmpty(map.get("czy"))) {
        	str=str+" and czy = '"+map.get("czy")+"'";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	str=str+" and sj >= '"+map.get("dateb")+"'";
        }else{
        	str=str+" and sj >= '"+hehe+" 00:00:00'";
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	str=str+" and sj <= '"+map.get("datee")+"'";
        }else{
        	str=str+" and sj <= '"+hehe+" 23:59:59'";
        }
        str=str+"group by lx,czy";
        
        Sql sql=Sqls.create(str);
        sql.setCallback(new QueryRecordCallback());

        dao.execute(sql);
        List<Cktj> list = sql.getList(Cktj.class);
        //System.out.println(list);
        resMap.put("Total", list.size());
        resMap.put("Rows", list);
        return resMap;
    }
    
    //小组营业统计Json
    @At()
    @Ok("json")
    public Map<String, Object> queryXzyytjJson(@Param("..") Map<String, String> map) throws Exception {
        resMap.clear();
       
        String str="select distinct dep_name,lname,summoney,zrs from view_dz_yysbs where 1=1";
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	str=str+" and rq >= '"+map.get("dateb")+"'";
        }else{
        	str = str +" and rq >= '"+hehe+" 00:00:00'";
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	str=str+" and rq <= '"+map.get("datee")+"'";
        }else{
        	str = str + " and rq <= '"+hehe+" 23:59:59'";
        }
        if (map.containsKey("yyz") & StringUtils.isNotEmpty(map.get("yyz"))) {
        	str=str+" and dep_serial ='"+map.get("yyz")+"'";
        }
           
        Sql sql=Sqls.create(str);
        sql.setEntity(dao.getEntity(Xzyytj.class));
        sql.setCallback(Sqls.callback.entities());

        dao.execute(sql);
        List<Xzyytj> list = sql.getList(Xzyytj.class);
        double zyye=0.0;
        double zyyrs=0.0;
        if(!"".equals(list)){
        	 for(Xzyytj x:list){
             	zyye = zyye+Double.parseDouble(x.getSummoney());
             	zyyrs = zyyrs+Double.parseDouble(x.getZrs());
             }
        }
       
        //System.out.println(list);
        resMap.put("Total", list.size());
        resMap.put("Rows", list);
        resMap.put("zyye", zyye);
        resMap.put("zyyrs", zyyrs);
        return resMap;
    }
    
    
    //存款明细Json
    @At()
    @Ok("json")
    public Map<String, Object> queryCkmxJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
        resMap.clear();
        Page page = getPage(map);
        Cnd cnd = Cnd.NEW();
        String count="select sum(ckje) as zckje from  view_dz_ckmx where 1=1";
        
        if (map.containsKey("lx") & StringUtils.isNotEmpty(map.get("lx"))) {
        	cnd.and("lx", "=", map.get("lx").toString());
        	count=count+" and lx = "+map.get("lx");
        }
        if (map.containsKey("bh") & StringUtils.isNotEmpty(map.get("bh"))) {
        	cnd.and("bh", "=", map.get("bh").toString());
        	count=count+" and bh = '"+map.get("bh")+"'";
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	cnd.and("xm", "like", map.get("xm").toString());
        	count=count+" and xm like '%"+map.get("xm")+"%'";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	cnd.and("ckrq", ">=", map.get("dateb").toString());
        	count=count+" and ckrq >= '"+map.get("dateb")+"'";
        }else{
        	cnd.and("ckrq", "=", hehe+" 00:00:00");
        	count=count+" and ckrq >= '"+hehe+" 00:00:00'";
        	
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	cnd.and("ckrq", "<=", map.get("datee").toString());
        	count=count+" and ckrq <= '"+map.get("datee")+"'";
        }else{
        	cnd.and("ckrq", "<=", hehe+" 23:59:59");
        	count=count+" and ckrq <= '"+hehe+" 23:59:59'";
        }
        cnd.desc("ckrq");
        
        List<Ckmx> list = dao.query(Ckmx.class, cnd, dao.createPager(page.getPageIndex() + 1, page.getPageSize()));
        int i = dao.count(Ckmx.class, cnd);
        Sql sql1=Sqls.create(count);
        sql1.setCallback(new QueryRecordCallback());
        
        dao.execute(sql1);
        
        List<Ckmx> list1 = sql1.getList(Ckmx.class);
        String str = list1.toString();
        int start = str.indexOf(":")+1;
        int end = str.indexOf("}");
        String sum = str.substring(start, end-1);
        resMap.put("Total", i);
        resMap.put("ZCK", sum);
        resMap.put("Rows", list);
        return resMap;
    }
    
    
    

    
    //取款明细Json
    @At()
    @Ok("json")
    public Map<String, Object> queryQkmxJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
        resMap.clear();
        Page page = getPage(map);
        Cnd cnd = Cnd.NEW();
        String cout="select sum(qkje) as zqkje from  view_dz_qkmx where 1=1 ";
        if (map.containsKey("bh") & StringUtils.isNotEmpty(map.get("bh"))) {
        	cnd.and("bh", "=", map.get("bh").toString());
        	cout=cout+" and bh = '"+map.get("bh")+"'";
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	cnd.and("xm", "like", map.get("xm").toString());
        	cout=cout+" and xm like '%"+map.get("xm")+"%'";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	cnd.and("qksj", ">=", map.get("dateb").toString());
        	cout=cout+" and qksj >= '"+map.get("dateb")+"'";
        }else{
        	cnd.and("qksj", ">=", hehe+" 00:00:00");
        	cout=cout+" and qksj >= '"+hehe+" 00:00:00'";
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	cnd.and("qksj", "<=", map.get("datee").toString());
        	cout=cout+" and qksj <= '"+map.get("datee")+"'";
        }else{
        	cnd.and("qksj", "<=", hehe+" 23:59:59");
        	cout=cout+" and qksj <= '"+hehe+" 23:59:59'";
        }
        cnd.desc("qksj");
        
        List<Qkmx> list = dao.query(Qkmx.class, cnd, dao.createPager(page.getPageIndex() + 1, page.getPageSize()));
        int i = dao.count(Qkmx.class, cnd);
        Sql sql1=Sqls.create(cout);
        sql1.setCallback(new QueryRecordCallback());
        

        dao.execute(sql1);
        
        List<Qkmx> list1 = sql1.getList(Qkmx.class);
        String str = list1.toString();
        int start = str.indexOf(":")+1;
        int end = str.indexOf("}");
        String sum = str.substring(start, end-1);
        resMap.put("Total", i);
        resMap.put("ZQK", sum);
        resMap.put("Rows", list);
        
        return resMap;
    }
    
    
    //消费明细Json
    @At()
    @Ok("json")
    public Map<String, Object> queryXfmxJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
        resMap.clear();
        Page page = getPage(map);
        Cnd cnd = Cnd.NEW();
        cnd.and("lx","=","0");
        String cout="select sum(xfje) as zxfje from view_dz_xfmx where 1=1 and lx=0";
        if (map.containsKey("rybh") & StringUtils.isNotEmpty(map.get("rybh"))) {
        	cnd.and("rybh","=",map.get("rybh"));
        	cout=cout+" and rybh = '"+map.get("rybh")+"'";
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	cnd.and("xm","like",map.get("xm"));
        	cout=cout+" and xm like '%"+map.get("xm")+"%'";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	cnd.and("xfsj",">=",map.get("dateb"));
        	cout=cout+" and xfsj >= '"+map.get("dateb")+"'";
        }else{
        	cnd.and("xfsj",">=",hehe+" 00:00:00");
        	cout=cout+" and xfsj >= '"+hehe+" 00:00:00'";
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	cnd.and("xfsj","<=",map.get("datee"));
        	cout=cout+" and xfsj <= '"+map.get("datee")+"'";
        }else{
        	cnd.and("xfsj","<=",hehe+" 23:59:59");
        	cout=cout+" and xfsj <= '"+hehe+" 23:59:59'";
        }
       cnd.desc("xfsj");
        
        List<Xfmx> list=dao.query(Xfmx.class, cnd,dao.createPager(page.getPageIndex()+1, page.getPageSize()));
        int i=dao.count(Xfmx.class,cnd);
        Sql sql1=Sqls.create(cout);
        sql1.setCallback(new QueryRecordCallback());
        

        dao.execute(sql1);
        
        List<Xfmx> list1 = sql1.getList(Xfmx.class);
        
        String str = list1.toString();
        int start = str.indexOf(":")+1;
        int end = str.indexOf("}");
        String sum = str.substring(start, end-1);
        resMap.put("Total", i);
        resMap.put("ZXFJE", sum);
        resMap.put("Rows", list);
        return resMap;
    }
    
    
    //消费计次明细Json
    @At()
    @Ok("json")
    public Map<String, Object> queryXfjcmxJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
        resMap.clear();
        Page page = getPage(map);
        Cnd cnd = Cnd.NEW();
        cnd.and("lx","=","1");
        String cout="select sum(cs) as zxfcs from view_dz_jcxfmx where 1=1 and lx=1";
        if (map.containsKey("rybh") & StringUtils.isNotEmpty(map.get("rybh"))) {
        	cnd.and("rybh","=",map.get("rybh"));
        	cout=cout+" and rybh = '"+map.get("rybh")+"'";
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	cnd.and("xm","like",map.get("xm"));
        	cout=cout+" and xm like '%"+map.get("xm")+"%'";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	cnd.and("xfsj",">=",map.get("dateb"));
        	cout=cout+" and xfsj >= '"+map.get("dateb")+"'";
        }else{
        	cnd.and("xfsj",">=",hehe+" 00:00:00");
        	cout=cout+" and xfsj >= '"+hehe+" 00:00:00'";
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	cnd.and("xfsj","<=",map.get("datee"));
        	cout=cout+" and xfsj <= '"+map.get("datee")+"'";
        }else{
        	cnd.and("xfsj","<=",hehe+" 23:59:59");
        	cout=cout+" and xfsj <= '"+hehe+" 23:59:59'";
        }
        cnd.desc("xfsj");
        List<Jcxfmx> list =dao.query(Jcxfmx.class, cnd,dao.createPager(page.getPageIndex()+1, page.getPageSize()));
        int i= dao.count(Jcxfmx.class,cnd);
        
        Sql sql1=Sqls.create(cout);
        sql1.setCallback(new QueryRecordCallback());
        

        dao.execute(sql1);
        
        
        List<Jcxfmx> list1 = sql1.getList(Jcxfmx.class);
        String str = list1.toString();
        int start = str.indexOf(":")+1;
        int end = str.indexOf("}");
        String sum = str.substring(start, end-1);
        resMap.put("Total", i);
        resMap.put("ZXFJC", sum);
        resMap.put("Rows", list);
        return resMap;
    }



}
