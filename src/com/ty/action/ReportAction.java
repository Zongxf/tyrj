package com.ty.action;
/**
 * 报表模块的页面跳转
 * 以及JSON数据的获取方法类
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.nutz.dao.Cnd;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ty.bean.Chrybb;
import com.ty.bean.Gsbkrybb;
import com.ty.bean.Jcrybb;
import com.ty.bean.Khrybb;
import com.ty.framework.action.BaseAction;
import com.ty.util.Page;

@At("/report")
@InjectName()
@IocBean
@Filters
public class ReportAction extends BaseAction{

	 private Map<String, Object> resMap;
	    @Inject
	    private NutDao dao;

        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yy-MM-dd");
        String hehe= sdf.format(date);
        
	    public ReportAction() {
	        resMap = new HashMap<String, Object>();

	    }
	    
	    @At()
	    @Ok("jsp:jsp/report/khrybb")
	    public void khrybbReport(HttpServletRequest req) throws Exception {

	    }
	    
	    @At()
	    @Ok("jsp:jsp/report/gsbkryb")
	    public void gsbkrybReport(HttpServletRequest req) throws Exception {

	    }
	    
	    @At()
	    @Ok("jsp:jsp/report/chrybb")
	    public void chrybbReport(HttpServletRequest req) throws Exception {

	    }
	    
	    @At()
	    @Ok("jsp:jsp/report/jcrybb")
	    public void jcrybbReport(HttpServletRequest req) throws Exception {

	    }
	    //开户人员报表
	    @At()
	    @Ok("json")
	    public Map<String,Object> khrybbReportJson(@Param("..")Map<String,String> map,HttpServletRequest req) throws Exception{
	    	resMap.clear();
	    	Page page=getPage(map);
	    	Cnd cnd=Cnd.NEW();
	    	
	    	if(map.containsKey("rybh") & StringUtils.isNotEmpty(map.get("rybh"))){
	    		cnd.and("rybh","=" ,map.get("rybh"));
	    	}
	    	if(map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))){
	    		cnd.and("xm","like" ,map.get("xm"));
	    	}
	    	if(map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))){
	    		cnd.and("khsj",">=" ,map.get("dateb"));
	    	}else{
	    		cnd.and("khsj",">=" ,hehe+" 00:00:00");
	    	}
	    	if(map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))){
	    		cnd.and("khsj","<=" ,map.get("datee"));
	    	}else{
	    		cnd.and("khsj","<=" ,hehe+" 23:59:59");
	    	}
	    	
	    	cnd.desc("khsj");
	    	
	    	List<Khrybb> list = dao.query(Khrybb.class, cnd , dao.createPager(page.getPageIndex()+1, page.getPageSize()));
	    	int i = dao.count(Khrybb.class,cnd);
	    	 
	    	resMap.put("Total", i);
	        resMap.put("Rows", list);
	    	
			return resMap;
	    	
	    }
	    //挂失人员报表
	    @At()
	    @Ok("json")
	    public Map<String,Object> gsrybbReportJson(@Param("..")Map<String,String> map,HttpServletRequest req) throws Exception{
	    	resMap.clear();
	    	Page page=getPage(map);
	    	Cnd cnd=Cnd.NEW();
	    	
	    	if(map.containsKey("rybh") & StringUtils.isNotEmpty(map.get("rybh"))){
	    		cnd.and("rybh","=" ,map.get("rybh"));
	    	}
	    	if(map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))){
	    		cnd.and("xm","like" ,map.get("xm"));
	    	}
	    	if(map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))){
	    		cnd.and("bksj",">=" ,map.get("dateb"));
	    	}else{
	    		cnd.and("bksj",">=" ,hehe+" 00:00:00");
	    	}
	    	if(map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))){
	    		cnd.and("bksj","<=" ,map.get("datee"));
	    	}else{
	    		cnd.and("bksj","<=" ,hehe+" 23:59:59");
	    	}
	    	
	    	cnd.desc("bksj");
	    	
	    	List<Gsbkrybb> list = dao.query(Gsbkrybb.class, cnd , dao.createPager(page.getPageIndex()+1, page.getPageSize()));
	    	int i = dao.count(Gsbkrybb.class,cnd);
	    	
	    	 resMap.put("Total", i);
	         resMap.put("Rows", list);
	    	
			return resMap;
	    	
	    }
	     
	    //撤户人员报表
	    @At()
	    @Ok("json")
	    public Map<String,Object> chrybbReportJson(@Param("..")Map<String,String> map,HttpServletRequest req) throws Exception{
	    	resMap.clear();
	    	Page page=getPage(map);
	    	Cnd cnd=Cnd.NEW();
	    	
	    	if(map.containsKey("rybh") & StringUtils.isNotEmpty(map.get("rybh"))){
	    		cnd.and("rybh","=" ,map.get("rybh"));
	    	}
	    	if(map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))){
	    		cnd.and("xm","like" ,map.get("xm"));
	    	}
	    	if(map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))){
	    		cnd.and("chsj",">=" ,map.get("dateb"));
	    	}else{
	    		cnd.and("chsj",">=" ,hehe+" 00:00:00");
	    	}
	    	if(map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))){
	    		cnd.and("chsj","<=" ,map.get("datee"));
	    	}else{
	    		cnd.and("chsj","<=" ,hehe+" 23:59:59");
	    	}
	    	
	    	cnd.desc("chsj");
	    	
	    	List<Chrybb> list = dao.query(Chrybb.class, cnd , dao.createPager(page.getPageIndex()+1, page.getPageSize()));
	    	int i = dao.count(Chrybb.class,cnd);
	    	
	    	
	    	 resMap.put("Total", i);
	         resMap.put("Rows", list);
	    	
			return resMap;
	    	
	    }
	    
	    
	    //纠错人员报表
	    @At()
	    @Ok("json")
	    public Map<String,Object> jcrybbReportJson(@Param("..")Map<String,String> map,HttpServletRequest req) throws Exception{
	    	resMap.clear();
	    	Page page=getPage(map);
	    	Cnd cnd=Cnd.NEW();
	    	if(map.containsKey("rybh") & StringUtils.isNotEmpty(map.get("rybh"))){
	    		cnd.and("rybh","=" ,map.get("rybh"));
	    	}
	    	if(map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))){
	    		cnd.and("xm","like" ,map.get("xm"));
	    	}
	    	if(map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))){
	    		cnd.and("jcsj",">=" ,map.get("dateb"));
	    	}else{
	    		cnd.and("jcsj",">=" ,hehe+" 00:00:00");
	    	}
	    	if(map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))){
	    		cnd.and("jcsj","<=" ,map.get("datee"));
	    	}else{
	    		cnd.and("jcsj","<=" ,hehe+" 23:59:59");
	    	}
	    	
	    	cnd.desc("jcsj");
	    	
	    	List<Jcrybb> list = dao.query(Jcrybb.class, cnd , dao.createPager(page.getPageIndex()+1, page.getPageSize()));
	    	int i = dao.count(Jcrybb.class,cnd);
	    	
	    	 resMap.put("Total", i);
	         resMap.put("Rows", list);
	    	
			return resMap;
	    	
	    }
}
