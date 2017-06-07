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
import org.nutz.mvc.annotation.Ok;

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

@At("/query_print")
@InjectName()
@IocBean
@Filters
public class QueryPrintAction extends BaseAction {

    @Inject
    private NutDao dao;
    
    Date now = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String hehe = dateFormat.format( now ); 


    //存款统计Json
    @At()
    @Ok("json")
    public List<Cktj> queryCktjJson(String lx,String czy,String dateb,String datee) throws Exception {
    	 String str="select lx,sum(je) as ckje, czy from  view_dz_cktj where 1=1";
         if (StringUtils.isNotEmpty(lx)&&!"".equals(lx)) {
         	str=str+" and lx = "+lx;
         }
         if (!"".equals(czy) & StringUtils.isNotEmpty(czy)) {
         	str=str+" and czy = '"+czy+"'";
         }
         if (!"".equals(dateb) & StringUtils.isNotEmpty(dateb)) {
         	str=str+" and sj >= '"+dateb+"'";
         }else{
         	str=str+" and sj >= '"+hehe+" 00:00:00'";
         }
         if (!"".equals(datee) & StringUtils.isNotEmpty(datee)) {
         	str=str+" and sj <= '"+datee+"'";
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
    @Ok("json")
    public List<Xzyytj> queryXzyytjJson(String dateb,String datee,String yyz) throws Exception {
    	 String str="select distinct dep_name,lname,summoney,zrs from view_dz_yysbs where 1=1";
         if (!"".equals(dateb) & StringUtils.isNotEmpty(dateb)) {
         	str=str+" and rq >= '"+dateb+"'";
         }else{
         	str = str +" and rq >= '"+hehe+" 00:00:00'";
         }
         if (!"".equals(datee) & StringUtils.isNotEmpty(datee) ){
         	str=str+" and rq <= '"+datee+"'";
         }else{
         	str = str + " and rq <= '"+hehe+" 23:59:59'";
         }
         if (!"".equals(yyz) & StringUtils.isNotEmpty(yyz)) {
         	str=str+" and dep_serial ='"+yyz+"'";
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
    @Ok("json")
    public List<Ckmx> queryCkmxJson(String lx,String bh,String xm,String dateb,String datee) throws Exception {
        String count="select * from  view_dz_ckmx where 1=1 and ckrq >= @dateb and ckrq <= @datee ";
        
        if (!"".equals(lx) & StringUtils.isNotEmpty(lx)) {
        	count=count+" and lx = "+lx;
        }
        if (!"".equals(bh)& StringUtils.isNotEmpty(bh)) {
        	count=count+" and bh = '"+bh+"'";
        }
        if (!"".equals(xm) & StringUtils.isNotEmpty(xm)) {
        	count=count+" and xm like '%"+xm+"%'";
        }
        count=count+"order by ckrq desc";
        Sql sql1=Sqls.create(count);
        
       
        sql1.setParam("dateb",dateb);
        sql1.setParam("datee",datee);
        
        sql1.setEntity(dao.getEntity(Ckmx.class));
        sql1.setCallback(Sqls.callback.entities());
        dao.execute(sql1);
        
        List<Ckmx> list1 = sql1.getList(Ckmx.class);
        return list1;
    }
    
    
    

    
    //取款明细Json
    @At()
    @Ok("json")
    public List<Qkmx> queryQkmxJson(String bh,String xm ,String dateb,String datee) throws Exception {
        String cout="select * from  view_dz_qkmx where 1=1 ";
        if (!"".equals(bh) & StringUtils.isNotEmpty(bh)) {
        	cout=cout+" and bh = '"+bh+"'";
        }
        if (!"".equals(xm) & StringUtils.isNotEmpty(xm)) {
        	cout=cout+" and xm like '%"+xm+"%'";
        }
        if (!"".equals(dateb) & StringUtils.isNotEmpty(dateb)) {
        	cout=cout+" and qksj >= '"+dateb+"'";
        }else{
        	cout=cout+" and qksj >= '"+hehe+" 00:00:00'";
        }
        if (!"".equals(datee) & StringUtils.isNotEmpty(datee)) {
        	cout=cout+" and qksj <= '"+datee+"'";
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
    @Ok("json")
    public List<Xfmx> queryXfmxJson(String rybh,String xm,String xfsj,String dateb,String datee) throws Exception {
       
        String cout="select * from view_dz_xfmx where 1=1 and lx=0";
        if (!"".equals(rybh) & StringUtils.isNotEmpty(rybh)) {
        	cout=cout+" and rybh = '"+rybh+"'";
        }
        if (!"".equals(xm) & StringUtils.isNotEmpty(xm)) {
        	cout=cout+" and xm like '%"+xm+"%'";
        }
        if (!"".equals(dateb) & StringUtils.isNotEmpty(dateb)) {
        	cout=cout+" and xfsj >= '"+dateb+"'";
        }else{
        	cout=cout+" and xfsj >= '"+hehe+" 00:00:00'";
        }
        if (!"".equals(datee) & StringUtils.isNotEmpty(datee)) {
        	cout=cout+" and xfsj <= '"+datee+"'";
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
    @Ok("json")
    public List<Jcxfmx> queryXfjcmxJson(String rybh,String xm,String xfsj,String dateb,String datee) throws Exception {
      
        String cout="select * from view_dz_xfmx where 1=1 and lx=1";
        if (!"".equals(rybh) & StringUtils.isNotEmpty(rybh)) {
        	cout=cout+" and rybh = '"+rybh+"'";
        }
        if (!"".equals(xm) & StringUtils.isNotEmpty(xm)) {
        	cout=cout+" and xm like '%"+xm+"%'";
        }
        if (!"".equals(dateb) & StringUtils.isNotEmpty(dateb)) {
        	cout=cout+" and xfsj >= '"+dateb+"'";
        }else{
        	cout=cout+" and xfsj >= '"+hehe+" 00:00:00'";
        }
        if (!"".equals(datee) & StringUtils.isNotEmpty(datee)) {
        	cout=cout+" and xfsj <= '"+datee+"'";
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
    @Ok("json")
    public List<Khrybb> queryKhrybbJson( String rybh,String xm,String dateb,String datee,String kssj) throws Exception {
      
        String cout="select * from view_dz_Khrybb where 1=1 ";
        if (!"".equals(rybh) & StringUtils.isNotEmpty(rybh)) {
        	cout=cout+" and rybh = '"+rybh+"'";
        }
        if (!"".equals(xm) & StringUtils.isNotEmpty(xm)) {
        	cout=cout+" and xm like '%"+xm+"%'";
        }
        if (!"".equals(dateb) & StringUtils.isNotEmpty(dateb)) {
        	cout=cout+" and khsj >= '"+dateb+"'";
        }else{
        	cout=cout+" and khsj >= '"+hehe+" 00:00:00'";
        }
        if (!"".equals(datee) & StringUtils.isNotEmpty(datee)) {
        	cout=cout+" and khsj <= '"+datee+"'";
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
    @Ok("json")
    public List<Chrybb> queryChrybbJson( String rybh,String xm,String dateb,String chsj,String datee) throws Exception {
      
        String cout="select * from view_dz_chrybb where 1=1 ";
        if (!"".equals(rybh) & StringUtils.isNotEmpty(rybh)) {
        	cout=cout+" and rybh = '"+rybh+"'";
        }
        if (!"".equals(xm) & StringUtils.isNotEmpty(xm)) {
        	cout=cout+" and xm like '%"+xm+"%'";
        }
        if (!"".equals(dateb) & StringUtils.isNotEmpty(dateb)) {
        	cout=cout+" and chsj >= '"+dateb+"'";
        }else{
        	cout=cout+" and chsj >= '"+hehe+" 00:00:00'";
        }
        if (!"".equals(datee) & StringUtils.isNotEmpty(datee)) {
        	cout=cout+" and chsj <= '"+datee+"'";
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
    @Ok("json")
    public List<Gsbkrybb> queryGsbkrybbJson( String rybh,String xm,String dateb,String datee,String bksj) throws Exception {
      
        String cout="select * from view_dz_gsbkbb where 1=1 ";
        if (!"".equals(rybh) & StringUtils.isNotEmpty(rybh)) {
        	cout=cout+" and rybh = '"+rybh+"'";
        }
        if (!"".equals(xm)& StringUtils.isNotEmpty(xm)) {
        	cout=cout+" and xm like '%"+xm+"%'";
        }
        if (!"".equals(dateb) & StringUtils.isNotEmpty(dateb)) {
        	cout=cout+" and bksj >= '"+dateb+"'";
        }else{
        	cout=cout+" and bksj >= '"+hehe+" 00:00:00'";
        }
        if (!"".equals(datee) & StringUtils.isNotEmpty(datee)) {
        	cout=cout+" and bksj <= '"+datee+"'";
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
    @Ok("json")
    public List<Jcrybb> queryJcrybbJson(String rybh,String xm,String dateb,String datee,String jcsj) throws Exception {
      
        String cout="select * from view_dz_jcrybb where 1=1 ";
        if (!"".equals(rybh) & StringUtils.isNotEmpty(rybh)) {
        	cout=cout+" and rybh = '"+rybh+"'";
        }
        if (!"".equals(xm) & StringUtils.isNotEmpty(xm)) {
        	cout=cout+" and xm like '%"+xm+"%'";
        }
        if (!"".equals(dateb) & StringUtils.isNotEmpty(dateb)) {
        	cout=cout+" and jcsj >= '"+dateb+"'";
        }else{
        	cout=cout+" and jcsj >= '"+hehe+" 00:00:00'";
        }
        if (!"".equals(datee) & StringUtils.isNotEmpty(datee)) {
        	cout=cout+" and jcsj <= '"+datee+"'";
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
