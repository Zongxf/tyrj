package com.ty.action;
/**
 * 导出前200条数据的excel模板类
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
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

import com.ty.bean.Chb;
import com.ty.bean.Ckcbrb;
import com.ty.bean.Czyrz;
import com.ty.bean.Gskyecx;
import com.ty.bean.Jcqktjb;
import com.ty.bean.Jcxqb;
import com.ty.bean.Tkb;
import com.ty.bean.Yymxb;
import com.ty.framework.action.BaseAction;

@At("/queryYybb")
@InjectName()
@IocBean
@Filters
public class queryYybbAction extends BaseAction{

	 private Map<String, Object> resMap;
	    @Inject
	    private NutDao dao;
        

	    public queryYybbAction() {
	        resMap = new HashMap<String, Object>();

	    }
	    
	    
	    Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String hehe = dateFormat.format( now ); 
	    
	    //营业明细表Json
	    @At()
	    @Ok("json")
	    public Map<String, Object> queryYymxbJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
	        resMap.clear();
	        String str = "select * from view_dz_yymxb where 1=1 ";
	        if (map.containsKey("cs") & StringUtils.isNotEmpty(map.get("cs"))) {
	        	str = str + " and csmc in("+map.get("cs").toString()+"'0')";
	        }
	        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
	        	str = str + " and jysj >= '"+map.get("dateb").toString()+"'";
	        }else{
	        	str = str + " and jysj >= '"+ hehe+" 00:00:00"+"'";
	        	
	        }
	        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
	        	str = str + " and jysj <= '"+map.get("datee").toString()+"'";
	        }else{
	        	str = str + " and jysj <= '"+ hehe+" 23:59:59"+"'";
	        }
	        str = str + " order by jysj desc";
	        
	        Sql sql = Sqls.create(str);
	        sql.setCallback(new QueryRecordCallback());
	        
	        dao.execute(sql);
	        List<Yymxb> list = sql.getList(Yymxb.class);
	        
	        resMap.put("Total", list.size());
	        resMap.put("Rows", list);
	        return resMap;
	    }
	    
	    
	    //营业汇总表Json
	    @At()
	    @Ok("json")
	    public Map<String, Object> queryYyhzbJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
	        resMap.clear();
	        String str = "select  csmc,sbmc,COUNT(cblx) as zrs, SUM(xfje) as zje,xflx from view_dz_yymxb  where 1=1";


	        if (map.containsKey("cs") & StringUtils.isNotEmpty(map.get("cs"))) {
	        	str = str + " and csmc in("+map.get("cs").toString()+"'0')";
	        }
	        
	        if (map.containsKey("xflx") & StringUtils.isNotEmpty(map.get("xflx"))) {
	        	str = str + " and xflx = '"+map.get("xflx").toString()+"'";
	        }
	        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
	        	str = str + " and jysj >= '"+map.get("dateb").toString()+"'";
	        }else{
	        	str = str + " and jysj >= '"+ hehe+" 00:00:00"+"'";
	        	
	        }
	        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
	        	str = str + " and jysj <= '"+map.get("datee").toString()+"'";
	        }else{
	        	str = str + " and jysj <= '"+ hehe+" 23:59:59"+"'";
	        }
	        str = str + " group by csmc,sbmc,xflx";
	        
	        Sql sql = Sqls.create(str);
	        sql.setCallback(new QueryRecordCallback());
	        
	        dao.execute(sql);
	        List<Yymxb> list = sql.getList(Yymxb.class);
	        
	        resMap.put("Total", list.size());
	        resMap.put("Rows", list);
	        return resMap;
	    }
	    
	    
	    //操作员日志Json
	    @At()
	    @Ok("json")
	    public Map<String, Object> queryCzyrzJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
	        resMap.clear();
	        String str = "select * from view_dz_czyrz where 1=1 ";
	        if (map.containsKey("czy") & StringUtils.isNotEmpty(map.get("czy"))) {
	        	str = str + " and czy = '"+map.get("czy").toString()+"'";
	        }
	        if (map.containsKey("cklx") & StringUtils.isNotEmpty(map.get("cklx"))) {
	        	str = str + " and cklx = '"+map.get("cklx").toString()+"'";
	        }
	        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
	        	str = str + " and sj >= '"+map.get("dateb").toString()+"'";
	        }else{
	        	str = str + " and sj >= '"+ hehe+" 00:00:00"+"'";
	        	
	        }
	        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
	        	str = str + " and sj <= '"+map.get("datee").toString()+"'";
	        }else{
	        	str = str + " and sj <= '"+ hehe+" 23:59:59"+"'";
	        }
	        str = str + " order by sj desc";
	        
	        Sql sql = Sqls.create(str);
	        sql.setCallback(new QueryRecordCallback());
	        
	        dao.execute(sql);
	        List<Czyrz> list = sql.getList(Czyrz.class);
	        
	        resMap.put("Total", list.size());
	        resMap.put("Rows", list);
	        return resMap;
	    }
	    
	    
	    //撤户表Json
	    @At()
	    @Ok("json")
	    public Map<String, Object> queryChbJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
	        resMap.clear();
	        String str = "select * from view_dz_chb where 1=1 ";
	        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
	        	str = str + " and xm in ("+map.get("xm").toString()+"'0')";
	        }
	        if (map.containsKey("bm") & StringUtils.isNotEmpty(map.get("bm"))) {
	        	str = str + " and bm in ("+map.get("bm").toString()+"'0')";
	        }
	        if (map.containsKey("gh") & StringUtils.isNotEmpty(map.get("gh"))) {
	        	str = str + " and gh = '"+map.get("gh").toString()+"'";
	        }
	        
	        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
	        	str = str + " and chsj >= '"+map.get("dateb").toString()+"'";
	        }else{
	        	str = str + " and chsj >= '"+ hehe+" 00:00:00"+"'";
	        	
	        }
	        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
	        	str = str + " and chsj <= '"+map.get("datee").toString()+"'";
	        }else{
	        	str = str + " and chsj <= '"+ hehe+" 23:59:59"+"'";
	        }
	        str = str + " order by chsj desc";
	        
	        Sql sql = Sqls.create(str);
	        sql.setCallback(new QueryRecordCallback());
	        
	        dao.execute(sql);
	        List<Chb> list = sql.getList(Chb.class);
	        
	        resMap.put("Total", list.size());
	        resMap.put("Rows", list);
	        return resMap;
	    }
	    
	    
	    //退款表Json
	    @At()
	    @Ok("json")
	    public Map<String, Object> queryTkbJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
	        resMap.clear();
	        String str = "select * from view_dz_tkb where 1=1 ";
	        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
	        	str = str + " and xm in ("+map.get("xm").toString()+"'0')";
	        }
	        if (map.containsKey("bm") & StringUtils.isNotEmpty(map.get("bm"))) {
	        	str = str + " and bm in ("+map.get("bm").toString()+"'0')";
	        }
	        if (map.containsKey("gh") & StringUtils.isNotEmpty(map.get("gh"))) {
	        	str = str + " and gh = '"+map.get("gh").toString()+"'";
	        }
	        
	        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
	        	str = str + " and tksj >= '"+map.get("dateb").toString()+"'";
	        }else{
	        	str = str + " and tksj >= '"+ hehe+" 00:00:00"+"'";
	        	
	        }
	        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
	        	str = str + " and tksj <= '"+map.get("datee").toString()+"'";
	        }else{
	        	str = str + " and tksj <= '"+ hehe+" 23:59:59"+"'";
	        }
	        str = str + " order by tksj desc";
	        
	        Sql sql = Sqls.create(str);
	        sql.setCallback(new QueryRecordCallback());
	        
	        dao.execute(sql);
	        List<Tkb> list = sql.getList(Tkb.class);
	        
	        resMap.put("Total", list.size());
	        resMap.put("Rows", list);
	        return resMap;
	    }
	    
	    
	    //就餐详情表Json
	    @At()
	    @Ok("json")
	    public Map<String, Object> queryJcxqbJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
	        resMap.clear();
	        String str = "select * from view_dz_jcxqb where 1=1 ";
	        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
	        	str = str + " and xm in ("+map.get("xm").toString()+"'0')";
	        }
	        if (map.containsKey("bm") & StringUtils.isNotEmpty(map.get("bm"))) {
	        	str = str + " and bm in ("+map.get("bm").toString()+"'0')";
	        }
	        if (map.containsKey("gh") & StringUtils.isNotEmpty(map.get("gh"))) {
	        	str = str + " and gh = '"+map.get("gh").toString()+"'";
	        }
	        
	        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
	        	str = str + " and jysj >= '"+map.get("dateb").toString()+"'";
	        }else{
	        	str = str + " and jysj >= '"+ hehe+" 00:00:00"+"'";
	        	
	        }
	        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
	        	str = str + " and jysj <= '"+map.get("datee").toString()+"'";
	        }else{
	        	str = str + " and jysj <= '"+ hehe+" 23:59:59"+"'";
	        }
	        str = str + " order by jysj desc";
	        
	        Sql sql = Sqls.create(str);
	        sql.setCallback(new QueryRecordCallback());
	        
	        dao.execute(sql);
	        List<Jcxqb> list = sql.getList(Jcxqb.class);
	        
	        resMap.put("Total", list.size());
	        resMap.put("Rows", list);
	        return resMap;
	    }
	    
	    
	    //就餐详情统计表Json
	    @At()
	    @Ok("json")
	    public Map<String, Object> queryJcqktjbJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
	        resMap.clear();
	        String str = "select g.zrs ,h.zc,cast(((h.zc*1.00)/g.zrs)*100 as decimal(18,2)) as zcl,"
                         +" g.zrs-h.zc as fzc,cast((((g.zrs-h.zc)*1.00)/g.zrs)*100 as decimal(18,2)) as fzcl,"
	        		     +" i.wc,cast(((i.wc*1.00)/g.zrs)*100 as decimal(18,2)) as wcl,"
                         +" g.zrs-i.wc as fwc,cast((((g.zrs-i.wc)*1.00)/g.zrs)*100 as decimal(18,2)) as fwcl,"
	        		     +" j.wwc,cast(((j.wwc*1.00)/g.zrs)*100 as decimal(18,2)) as wwcl,"
                         +" g.zrs-j.wwc as fwwc,cast((((g.zrs-j.wwc)*1.00)/g.zrs)*100 as decimal(18,2)) as fwwcl,"
	        		     +" k.jbc,cast(((k.jbc*1.00)/g.zrs)*100 as decimal(18,2)) as jbcl,"
                         +" l.fsd, cast(((l.fsd*1.00)/g.zrs)*100 as decimal(18,2)) as fsdl,"
	        		     +" t.zzw, cast(((t.zzw*1.00)/g.zrs)*100 as decimal(18,2)) as zzwl,"
                         +" g.zrs-t.zzw as fzzw, cast((((g.zrs-t.zzw)*1.00)/g.zrs)*100 as decimal(18,2)) as fzzwl";
	        
	       
	        
	        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))&map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
	        	str = str +" from (select count(a.alluser) as zrs from (select distinct user_serial as alluser from dt_user ) a) g,"
                        +" (select COUNT(b.name) as zc from (select distinct xm as name from view_dz_jcxqb where zc != '0' and jysj>='"+map.get("dateb").toString()+"' and jysj<='"+map.get("datee").toString()+"') b) h,"
	        		     +" (select COUNT(c.name) as wc from (select distinct xm as name from view_dz_jcxqb where wc != '0' and jysj>='"+map.get("dateb").toString()+"' and jysj<='"+map.get("datee").toString()+"') c) i,"
                        +" (select COUNT(d.name) as wwc from (select distinct xm as name from view_dz_jcxqb where wwc != '0' and jysj>='"+map.get("dateb").toString()+"' and jysj<='"+map.get("datee").toString()+"') d) j,"
	        		     +" (select COUNT(e.name) as jbc from (select distinct xm as name from view_dz_jcxqb where jbc != '0' and jysj>='"+map.get("dateb").toString()+"' and jysj<='"+map.get("datee").toString()+"') e) k,"
                        +" (select COUNT(f.name) as fsd from (select distinct xm as name from view_dz_jcxqb where fsd != '0' and jysj>='"+map.get("dateb").toString()+"' and jysj<='"+map.get("datee").toString()+"') f) l,"
	        		     +" (select COUNT(s.name) as zzw from (select distinct xm as name from view_dz_jcxqb where zc != '0' and jysj>='"+map.get("dateb").toString()+"' and jysj<='"+map.get("datee").toString()+"' and wc != '0' and wwc!='0') s) t ";
	        }else{
	        	str = str +" from (select count(a.alluser) as zrs from (select distinct user_serial as alluser from dt_user ) a) g,"
                        +" (select COUNT(b.name) as zc from (select distinct xm as name from view_dz_jcxqb where zc != '0' and jysj>='"+ hehe+" 00:00:00"+"' and jysj<='"+hehe+" 23:59:59"+"') b) h,"
	        		     +" (select COUNT(c.name) as wc from (select distinct xm as name from view_dz_jcxqb where wc != '0' and jysj>='"+ hehe+" 00:00:00"+"' and jysj<='"+hehe+" 23:59:59"+"') c) i,"
                        +" (select COUNT(d.name) as wwc from (select distinct xm as name from view_dz_jcxqb where wwc != '0' and jysj>='"+ hehe+" 00:00:00"+"' and jysj<='"+hehe+" 23:59:59"+"') d) j,"
	        		     +" (select COUNT(e.name) as jbc from (select distinct xm as name from view_dz_jcxqb where jbc != '0' and jysj>='"+ hehe+" 00:00:00"+"' and jysj<='"+hehe+" 23:59:59"+"') e) k,"
                        +" (select COUNT(f.name) as fsd from (select distinct xm as name from view_dz_jcxqb where fsd != '0' and jysj>='"+ hehe+" 00:00:00"+"' and jysj<='"+hehe+" 23:59:59"+"') f) l,"
	        		     +" (select COUNT(s.name) as zzw from (select distinct xm as name from view_dz_jcxqb where zc != '0' and jysj>='"+ hehe+" 00:00:00"+"' and jysj<='"+hehe+" 23:59:59"+"' and wc != '0' and wwc!='0') s) t ";
	        	
	        }
	       
	        
	        Sql sql = Sqls.create(str);
	        sql.setCallback(new QueryRecordCallback());
	        
	        dao.execute(sql);
	        List<Jcqktjb> list = sql.getList(Jcqktjb.class);
	        
	        resMap.put("Total", list.size());
	        resMap.put("Rows", list);
	        return resMap;
	    }
	    
	    
	    //窗口餐别日报Json
	    @At()
	    @Ok("json")
	    public Map<String, Object> queryCkcbrbJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
	        resMap.clear();
	        String str = "select * from view_dz_ckcbrb where 1=1 ";
	        if (map.containsKey("cblx") & StringUtils.isNotEmpty(map.get("cblx"))) {
	        	str = str + " and cblx = '"+map.get("cblx").toString()+"'";
	        }
	        if (map.containsKey("cs") & StringUtils.isNotEmpty(map.get("cs"))) {
	        	str = str + " and cs in ("+map.get("cs").toString()+"'0')";
	        }
	        
	        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
	        	str = str + " and jyrq >= '"+map.get("dateb").toString()+"'";
	        }else{
	        	str = str + " and jyrq >= '"+ hehe+" 00:00:00"+"'";
	        	
	        }
	        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
	        	str = str + " and jyrq <= '"+map.get("datee").toString()+"'";
	        }else{
	        	str = str + " and jyrq <= '"+ hehe+" 23:59:59"+"'";
	        }
	        str = str + " order by jyrq desc";
	        
	        Sql sql = Sqls.create(str);
	        sql.setCallback(new QueryRecordCallback());
	        
	        dao.execute(sql);
	        List<Ckcbrb> list = sql.getList(Ckcbrb.class);
	        
	        resMap.put("Total", list.size());
	        resMap.put("Rows", list);
	        return resMap;
	    }
	    
	    
	  //挂失余额查询Json
	    @At()
	    @Ok("json")
	    public Map<String, Object> queryGskyecxJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
	        resMap.clear();
	        String str = "select * from view_dz_gskyecx where 1=1 ";
	        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
	        	str = str + " and xm in ("+map.get("xm").toString()+"'0')";
	        }
	        if (map.containsKey("bm") & StringUtils.isNotEmpty(map.get("bm"))) {
	        	str = str + " and bm in ("+map.get("bm").toString()+"'0')";
	        }
	        if (map.containsKey("gh") & StringUtils.isNotEmpty(map.get("gh"))) {
	        	str = str + " and gh = '"+map.get("gh").toString()+"'";
	        }
	        
	        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
	        	str = str + " and gssj >= '"+map.get("dateb").toString()+"'";
	        }else{
	        	str = str + " and gssj >= '"+ hehe+" 00:00:00"+"'";
	        	
	        }
	        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
	        	str = str + " and gssj <= '"+map.get("datee").toString()+"'";
	        }else{
	        	str = str + " and gssj <= '"+ hehe+" 23:59:59"+"'";
	        }
	        str = str + " order by gssj desc";
	        
	        Sql sql = Sqls.create(str);
	        sql.setCallback(new QueryRecordCallback());
	        
	        dao.execute(sql);
	        List<Gskyecx> list = sql.getList(Gskyecx.class);
	        
	        resMap.put("Total", list.size());
	        resMap.put("Rows", list);
	        return resMap;
	    }
	    
}
