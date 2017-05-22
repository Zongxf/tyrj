package com.ty.bean;

import java.text.SimpleDateFormat;
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
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

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
    public void query(HttpServletRequest req) throws Exception {

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
    	  Sql sql=Sqls.create("select distinct lx from  view_dz_cktj GROUP BY lx,czy");
    	  Sql sql1=Sqls.create("select distinct czy from  view_dz_cktj GROUP BY lx,czy");
          sql.setCallback(new QueryRecordCallback());
          sql1.setCallback(new QueryRecordCallback());
         
          List<Cktj> list =   dao.execute(sql).getList(Cktj.class);
          req.setAttribute("cklx", list);
          
          List<Cktj> list1 =   dao.execute(sql1).getList(Cktj.class);
          req.setAttribute("czy", list1);
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
        String cout="select sum(xfje) as zxfje from view_dz_xfmx where 1=1 and lx=1";
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
        List<Xfmx> list =dao.query(Xfmx.class, cnd,dao.createPager(page.getPageIndex()+1, page.getPageSize()));
        int i= dao.count(Xfmx.class,cnd);
        
        Sql sql1=Sqls.create(cout);
        sql1.setCallback(new QueryRecordCallback());
        

        dao.execute(sql1);
        
        
        List<Xfmx> list1 = sql1.getList(Xfmx.class);
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
