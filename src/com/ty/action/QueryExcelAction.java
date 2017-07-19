package com.ty.action;
/**
 * 存款统计读取全部数据
 * 用于导出
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.sql.Sql;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;

import com.ty.bean.Chrybb;
import com.ty.bean.Ckmx;
import com.ty.bean.Cktj;
import com.ty.bean.Gsbkrybb;
import com.ty.bean.Jcrybb;
import com.ty.bean.Jcxfmx;
import com.ty.bean.Khrybb;
import com.ty.bean.Qkmx;
import com.ty.bean.Xfmx;
import com.ty.bean.Xzyytj;
import com.ty.framework.action.BaseAction;

@At("/query_new")
@InjectName()
@IocBean
@Filters
public class QueryExcelAction extends BaseAction {

    @Inject
    private NutDao dao;
    
    Date now = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String hehe = dateFormat.format( now ); 


    //存款统计Json
    @At()
    public List<Cktj> queryCktjJson(Map<String, String> map) throws Exception {
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
        sql.setEntity(dao.getEntity(Cktj.class));
        sql.setCallback(Sqls.callback.entities());

        dao.execute(sql);
        List<Cktj> list = sql.getList(Cktj.class);
       
        return list;
    }
    //小组营业统计
    @At()
    public List<Xzyytj> queryXzyytjJson(Map<String, String> map) throws Exception {
    	 String str="select distinct dep_name,lname,summoney,zrs from view_dz_yysbs where 1=1 ";
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
        
        return list;
    }
    
  //存款明细Json
    @At()
    public List<Ckmx> queryCkmxJson(Map<String, String> map) throws Exception {
        String count="select * from  view_dz_ckmx where 1=1 and ckrq >= @dateb and ckrq <= @datee ";
        
        if (map.containsKey("lx") & StringUtils.isNotEmpty(map.get("lx"))) {
        	count=count+" and lx = @lx";
        }
        if (map.containsKey("bh") & StringUtils.isNotEmpty(map.get("bh"))) {
        	count=count+" and bh = @bh";
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	count=count+" and xm like '%"+map.get("xm")+"%'";
        }
        count=count+" order by ckrq desc";
        Sql sql1=Sqls.create(count);
        
        if (map.containsKey("lx") & StringUtils.isNotEmpty(map.get("lx"))) {
        	sql1.setParam("lx",map.get("lx"));
        }
        if (map.containsKey("bh") & StringUtils.isNotEmpty(map.get("bh"))) {
        	sql1.setParam("bh",map.get("bh"));
        }
        sql1.setParam("dateb",map.get("dateb"));
        sql1.setParam("datee",map.get("datee"));
        
        sql1.setEntity(dao.getEntity(Ckmx.class));
        sql1.setCallback(Sqls.callback.entities());
        dao.execute(sql1);
        
        List<Ckmx> list1 = sql1.getList(Ckmx.class);
        return list1;
    }
    
    
    

    
    //取款明细Json
    @At()
    public List<Qkmx> queryQkmxJson(Map<String, String> map) throws Exception {
        String cout="select * from  view_dz_qkmx where 1=1 ";
        if (map.containsKey("bh") & StringUtils.isNotEmpty(map.get("bh"))) {
        	cout=cout+" and bh = '"+map.get("bh")+"'";
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	cout=cout+" and xm like '%"+map.get("xm")+"%'";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	cout=cout+" and qksj >= '"+map.get("dateb")+"'";
        }else{
        	cout=cout+" and qksj >= '"+hehe+" 00:00:00'";
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	cout=cout+" and qksj <= '"+map.get("datee")+"'";
        }else{
        	cout=cout+" and qksj <= '"+hehe+" 23:59:59'";
        }
        cout=cout+"order by qksj desc";
        Sql sql1=Sqls.create(cout);
        sql1.setEntity(dao.getEntity(Qkmx.class));
        sql1.setCallback(Sqls.callback.entities());
        

        dao.execute(sql1);
        
        List<Qkmx> list1 = sql1.getList(Qkmx.class);
        
        return list1;
    }
    
    
    //消费明细Json
    @At()
    public List<Xfmx> queryXfmxJson(Map<String, String> map) throws Exception {
       
        String cout="select * from view_dz_xfmx where 1=1 and lx=0";
        if (map.containsKey("rybh") & StringUtils.isNotEmpty(map.get("rybh"))) {
        	cout=cout+" and rybh = '"+map.get("rybh")+"'";
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	cout=cout+" and xm like '%"+map.get("xm")+"%'";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	cout=cout+" and xfsj >= '"+map.get("dateb")+"'";
        }else{
        	cout=cout+" and xfsj >= '"+hehe+" 00:00:00'";
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	cout=cout+" and xfsj <= '"+map.get("datee")+"'";
        }else{
        	cout=cout+" and xfsj <= '"+hehe+" 23:59:59'";
        }
        cout=cout+"order by xfsj desc";
        
        Sql sql1=Sqls.create(cout);
        sql1.setEntity(dao.getEntity(Xfmx.class));
        sql1.setCallback(Sqls.callback.entities());
        

        dao.execute(sql1);
        
        List<Xfmx> list1 = sql1.getList(Xfmx.class);
        
        return list1;
    }
    
    
    //消费计次明细Json
    @At()
    public List<Jcxfmx> queryXfjcmxJson( Map<String, String> map) throws Exception {
      
        String cout="select * from view_dz_xfmx where 1=1 and lx=1";
        if (map.containsKey("rybh") & StringUtils.isNotEmpty(map.get("rybh"))) {
        	cout=cout+" and rybh = '"+map.get("rybh")+"'";
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	cout=cout+" and xm like '%"+map.get("xm")+"%'";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	cout=cout+" and xfsj >= '"+map.get("dateb")+"'";
        }else{
        	cout=cout+" and xfsj >= '"+hehe+" 00:00:00'";
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	cout=cout+" and xfsj <= '"+map.get("datee")+"'";
        }else{
        	cout=cout+" and xfsj <= '"+hehe+" 23:59:59'";
        }
        cout=cout+"order by xfsj desc";
        
        Sql sql1=Sqls.create(cout);
        sql1.setEntity(dao.getEntity(Jcxfmx.class));
        sql1.setCallback(Sqls.callback.entities());
        

        dao.execute(sql1);
        
        
        List<Jcxfmx> list1 = sql1.getList(Jcxfmx.class);
        return list1;
    }

    //开户人员报表
    @At()
    public List<Khrybb> queryKhrybbJson( Map<String, String> map) throws Exception {
      
        String cout="select * from view_dz_Khrybb where 1=1 ";
        if (map.containsKey("rybh") & StringUtils.isNotEmpty(map.get("rybh"))) {
        	cout=cout+" and rybh = '"+map.get("rybh")+"'";
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	cout=cout+" and xm like '%"+map.get("xm")+"%'";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	cout=cout+" and khsj >= '"+map.get("dateb")+"'";
        }else{
        	cout=cout+" and khsj >= '"+hehe+" 00:00:00'";
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	cout=cout+" and khsj <= '"+map.get("datee")+"'";
        }else{
        	cout=cout+" and khsj <= '"+hehe+" 23:59:59'";
        }
        cout=cout+"order by khsj desc";
        
        Sql sql1=Sqls.create(cout);
        sql1.setEntity(dao.getEntity(Khrybb.class));
        sql1.setCallback(Sqls.callback.entities());
        

        dao.execute(sql1);
        
        
        List<Khrybb> list1 = sql1.getList(Khrybb.class);
        return list1;
    }
    
    //撤户人员报表
    @At()
    public List<Chrybb> queryChrybbJson( Map<String, String> map) throws Exception {
      
        String cout="select * from view_dz_chrybb where 1=1 ";
        if (map.containsKey("rybh") & StringUtils.isNotEmpty(map.get("rybh"))) {
        	cout=cout+" and rybh = '"+map.get("rybh")+"'";
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	cout=cout+" and xm like '%"+map.get("xm")+"%'";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	cout=cout+" and chsj >= '"+map.get("dateb")+"'";
        }else{
        	cout=cout+" and chsj >= '"+hehe+" 00:00:00'";
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	cout=cout+" and chsj <= '"+map.get("datee")+"'";
        }else{
        	cout=cout+" and chsj <= '"+hehe+" 23:59:59'";
        }
        cout=cout+"order by chsj desc";
        
        Sql sql1=Sqls.create(cout);
        sql1.setEntity(dao.getEntity(Chrybb.class));
        sql1.setCallback(Sqls.callback.entities());
        

        dao.execute(sql1);
        
        
        List<Chrybb> list1 = sql1.getList(Chrybb.class);
        return list1;
    }
    
    
    //挂失办卡人员报表
    @At()
    public List<Gsbkrybb> queryGsbkrybbJson( Map<String, String> map) throws Exception {
      
        String cout="select * from view_dz_gsbkbb where 1=1 ";
        if (map.containsKey("rybh") & StringUtils.isNotEmpty(map.get("rybh"))) {
        	cout=cout+" and rybh = '"+map.get("rybh")+"'";
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	cout=cout+" and xm like '%"+map.get("xm")+"%'";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	cout=cout+" and bksj >= '"+map.get("dateb")+"'";
        }else{
        	cout=cout+" and bksj >= '"+hehe+" 00:00:00'";
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	cout=cout+" and bksj <= '"+map.get("datee")+"'";
        }else{
        	cout=cout+" and bksj <= '"+hehe+" 23:59:59'";
        }
        cout=cout+"order by bksj desc";
        
        Sql sql1=Sqls.create(cout);
        sql1.setEntity(dao.getEntity(Gsbkrybb.class));
        sql1.setCallback(Sqls.callback.entities());
        

        dao.execute(sql1);
        
        
        List<Gsbkrybb> list1 = sql1.getList(Gsbkrybb.class);
        return list1;
    }
    
    
    //纠错人员报表
    @At()
    public List<Jcrybb> queryJcrybbJson( Map<String, String> map) throws Exception {
      
        String cout="select * from view_dz_jcrybb where 1=1 ";
        if (map.containsKey("rybh") & StringUtils.isNotEmpty(map.get("rybh"))) {
        	cout=cout+" and rybh = '"+map.get("rybh")+"'";
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	cout=cout+" and xm like '%"+map.get("xm")+"%'";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	cout=cout+" and jcsj >= '"+map.get("dateb")+"'";
        }else{
        	cout=cout+" and jcsj >= '"+hehe+" 00:00:00'";
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	cout=cout+" and jcsj <= '"+map.get("datee")+"'";
        }else{
        	cout=cout+" and jcsj <= '"+hehe+" 23:59:59'";
        }
        cout=cout+"order by jcsj desc";
        
        Sql sql1=Sqls.create(cout);
        sql1.setEntity(dao.getEntity(Jcrybb.class));
        sql1.setCallback(Sqls.callback.entities());
        

        dao.execute(sql1);
        
        
        List<Jcrybb> list1 = sql1.getList(Jcrybb.class);
        return list1;
    }
    




}
