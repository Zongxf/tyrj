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
        String str="select * from  view_dz_cktj where 1=1";
        if (map.containsKey("lx") & StringUtils.isNotEmpty(map.get("lx"))) {
        	str=str+" and lx = @lx";
        }
        if (map.containsKey("czy") & StringUtils.isNotEmpty(map.get("czy"))) {
        	str=str+" and czy = @czy";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	str=str+" and sj >= @dateb";
        }else{
        	str=str+" and sj >= @dateb";
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	str=str+" and sj <= @datee";
        }else{
        	str=str+" and sj <= @datee";
        }
       
        
        Sql sql=Sqls.create(str);
        if (map.containsKey("lx") & StringUtils.isNotEmpty(map.get("lx"))) {
        	sql.setParam("lx",map.get("lx"));
        }
        if (map.containsKey("czy") & StringUtils.isNotEmpty(map.get("czy"))) {
        	sql.setParam("czy",map.get("czy"));
        }
        sql.setParam("dateb",map.get("dateb"));
        sql.setParam("datee",map.get("datee"));
        sql.setCallback(new QueryRecordCallback());

        dao.execute(sql);
        List<Cktj> list = sql.getList(Cktj.class);
       
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
        	count=count+" and xm like %@xm%";
        }
        count=count+"order by ckrq desc";
        Sql sql1=Sqls.create(count);
        
        if (map.containsKey("lx") & StringUtils.isNotEmpty(map.get("lx"))) {
        	sql1.setParam("lx",map.get("lx"));
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	sql1.setParam("xm",map.get("xm"));
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
        String cout="select sum(qkje) as zqkje from  view_dz_qkmx where 1=1 ";
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
        sql1.setCallback(new QueryRecordCallback());
        

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
        sql1.setCallback(new QueryRecordCallback());
        

        dao.execute(sql1);
        
        List<Xfmx> list1 = sql1.getList(Xfmx.class);
        
        return list1;
    }
    
    
    //消费计次明细Json
    @At()
    public List<Xfmx> queryXfjcmxJson( Map<String, String> map) throws Exception {
      
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
        sql1.setCallback(new QueryRecordCallback());
        

        dao.execute(sql1);
        
        
        List<Xfmx> list1 = sql1.getList(Xfmx.class);
        return list1;
    }



}
