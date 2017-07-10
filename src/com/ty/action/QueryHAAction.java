package com.ty.action;
/**
 * 海安定制报表，页面跳转，以及JSON数据传输
 */
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
import javax.servlet.http.HttpSession;

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

import com.ty.bean.Czyrz;
import com.ty.bean.Depart;
import com.ty.bean.Grqchz;
import com.ty.bean.Jcqktjb;
import com.ty.bean.Place;
import com.ty.bean.Qcdzb;
import com.ty.bean.Qcdzmx;
import com.ty.bean.Qcycdz;
import com.ty.bean.Sbqcb;
import com.ty.bean.Userlist;
import com.ty.framework.action.BaseAction;
import com.ty.util.Page;

@At("/queryha")
@InjectName()
@IocBean
@Filters
public class QueryHAAction extends BaseAction {

    private Map<String, Object> resMap;
    @Inject
    private NutDao dao;
    
    Date now = new Date(); 
	SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	String hehe = dateFormat.format( now ); 


    public QueryHAAction() {
        resMap = new HashMap<String, Object>();

    }
    // 部门列表
    @At()
    @Ok("jsp:jsp/user/depart")
    public void queryDepart(HttpServletRequest req) throws Exception {
    	HttpSession session = req.getSession();
    	session.removeAttribute("departlist");
        String count="select * from view_dz_depart";
        
        Sql sql=Sqls.create(count);
        sql.setEntity(dao.getEntity(Depart.class));
        sql.setCallback(Sqls.callback.entities());
        
        dao.execute(sql);
        
        List<Depart> list = sql.getList(Depart.class);
        for(int i=0;i<list.size();i++){
        	String id = list.get(i).getId();
        	String pid = list.get(i).getPid();
        	if(id.equals("0")&&pid.equals("0")){
        		list.get(i).setPid("");;
        	};
        }
        session.setAttribute("departlist", list);
    }
    
    
    // 部门列表
    @At()
    @Ok("jsp:jsp/user/place")
    public void queryPlace(HttpServletRequest req) throws Exception {
    	HttpSession session = req.getSession();
    	session.removeAttribute("placelist");
        String count="select * from view_dz_place";
        
        Sql sql=Sqls.create(count);
        sql.setEntity(dao.getEntity(Place.class));
        sql.setCallback(Sqls.callback.entities());
        
        dao.execute(sql);
        
        List<Place> list = sql.getList(Place.class);
        for(int i=0;i<list.size();i++){
        	String id = list.get(i).getId();
        	String pid = list.get(i).getPid();
        	if(id.equals("0")&&pid.equals("0")){
        		list.get(i).setPid("");;
        	};
        }
        session.setAttribute("placelist", list);
    }
    // 人员列表
    @At()
    @Ok("jsp:jsp/user/userlist")
    public void queryUser(HttpServletRequest req,String xm) throws Exception {
    	if(StringUtils.isNotEmpty(xm)){
    		req.setAttribute("xm", xm);
    	}
    	
    }
    
    // 圈存对账明细
    @At()
    @Ok("jsp:jsp/hadzbb/qcbb/qcdzmx")
    public void queryQcdzmx(HttpServletRequest req) throws Exception {
          
    }
    // 圈存对账表
    @At()
    @Ok("jsp:jsp/hadzbb/qcbb/qcdzb")
    public void queryQcdzb(HttpServletRequest req) throws Exception {
          
    }
    // 圈存异常对账
    @At()
    @Ok("jsp:jsp/hadzbb/qcbb/qcycdz")
    public void queryQcycdz(HttpServletRequest req) throws Exception {
          
    }
    
    //个人圈存汇总
    @At()
    @Ok("jsp:jsp/hadzbb/qcbb/grqchz")
    public void queryGrqchz(HttpServletRequest req) throws Exception {
    }
    
    //设备圈存汇总
    @At()
    @Ok("jsp:jsp/hadzbb/qcbb/sbqchz")
    public void querySbqchz(HttpServletRequest req) throws Exception {
    }
    
  //营业明细表
    @At()
    @Ok("jsp:jsp/hadzbb/yybb/yymxb")
    public void queryYymxb(HttpServletRequest req) throws Exception {
    }
    
  //营业汇总表
    @At()
    @Ok("jsp:jsp/hadzbb/yybb/yyhzb")
    public void queryYyhzb(HttpServletRequest req) throws Exception {
    }
    
 
    //管理员日志
    @At()
    @Ok("jsp:jsp/hadzbb/yybb/glyrz")
    public void queryGlyrz(HttpServletRequest req) throws Exception {
    	String count = "select distinct czy from view_dz_czyrz ";
    	Sql sql = Sqls.create(count);
    	sql.setEntity(dao.getEntity(Czyrz.class));
    	sql.setCallback(Sqls.callback.entities());
    	
    	dao.execute(sql);
    	List<Czyrz> list = sql.getList(Czyrz.class);
        req.setAttribute("czylist", list);
    	
    } 
   
    //退款表
    @At()
    @Ok("jsp:jsp/hadzbb/other/tkb")
    public void queryTkb(HttpServletRequest req) throws Exception {
    } 
    
  //出纳报表
    @At()
    @Ok("jsp:jsp/hadzbb/other/cnbb")
    public void queryCnbb(HttpServletRequest req) throws Exception {
    }
    
  //就餐情况统计表
    @At()
    @Ok("jsp:jsp/hadzbb/other/jcqktjb")
    public void queryJcqktjb(HttpServletRequest req) throws Exception {
    	String datee = req.getParameter("datee");
    	String dateb = req.getParameter("dateb");
    	
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
    
   
    
    if (StringUtils.isNotEmpty(dateb)&StringUtils.isNotEmpty(datee)) {
    	str = str +" from (select count(a.alluser) as zrs from (select distinct user_serial as alluser from dt_user ) a) g,"
                +" (select COUNT(b.name) as zc from (select distinct xm as name from view_dz_jcxqb where zc != '0' and jysj>='"+dateb+"' and jysj<='"+datee+"') b) h,"
    		     +" (select COUNT(c.name) as wc from (select distinct xm as name from view_dz_jcxqb where wc != '0' and jysj>='"+dateb+"' and jysj<='"+datee+"') c) i,"
                +" (select COUNT(d.name) as wwc from (select distinct xm as name from view_dz_jcxqb where wwc != '0' and jysj>='"+dateb+"' and jysj<='"+datee+"') d) j,"
    		     +" (select COUNT(e.name) as jbc from (select distinct xm as name from view_dz_jcxqb where jbc != '0' and jysj>='"+dateb+"' and jysj<='"+datee+"') e) k,"
                +" (select COUNT(f.name) as fsd from (select distinct xm as name from view_dz_jcxqb where fsd != '0' and jysj>='"+dateb+"' and jysj<='"+datee+"') f) l,"
    		     +" (select COUNT(s.name) as zzw from (select distinct xm as name from view_dz_jcxqb where zc != '0' and jysj>='"+dateb+"' and jysj<='"+datee+"' and wc != '0' and wwc!='0') s) t ";
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
    
    req.setAttribute("Jcqktjb", list);
    req.setAttribute("Dateb", dateb);
    req.setAttribute("Datee", datee);
    } 
   
    
  //就餐详情表
    @At()
    @Ok("jsp:jsp/hadzbb/other/jcxqb")
    public void queryJcxqb(HttpServletRequest req) throws Exception {
    } 
    
    //窗口餐别日报
    @At()
    @Ok("jsp:jsp/hadzbb/other/ckcbrb")
    public void queryCkcbrb(HttpServletRequest req) throws Exception {
    } 
    
    
    //撤户表
    @At()
    @Ok("jsp:jsp/hadzbb/other/chb")
    public void queryChb(HttpServletRequest req) throws Exception {
    } 
    
    //挂失卡余额查询
    @At()
    @Ok("jsp:jsp/hadzbb/other/gskyecx")
    public void queryGskyecx(HttpServletRequest req) throws Exception {
    } 
    
    //获取部门列表Json
    @At()
    @Ok("")
    public void queryDepartmentJson(HttpServletRequest req) throws Exception {
    	HttpSession session = req.getSession();
    	session.removeAttribute("departlist");
        String count="select * from view_dz_depart";
        
        Sql sql=Sqls.create(count);
        sql.setEntity(dao.getEntity(Depart.class));
        sql.setCallback(Sqls.callback.entities());
        
        dao.execute(sql);
        
        List<Depart> list = sql.getList(Depart.class);
        for(int i=0;i<list.size();i++){
        	String id = list.get(i).getId();
        	String pid = list.get(i).getPid();
        	if(id.equals("0")&&pid.equals("0")){
        		list.get(i).setPid("");;
        	};
        }
        session.setAttribute("departlist", list);
    }
    

    //获取人员列表Json
    @At()
    @Ok("json")
    public List<Userlist> queryUserlistJson(HttpServletRequest req,String xm,String dep) throws Exception {
    	HttpSession session = req.getSession();
    	session.removeAttribute("xm");
    	String count="select xm from view_dz_userlist where 1=1 ";
        if(StringUtils.isNotEmpty(xm)){
        	count= count+" and xm like '%"+xm+"%'";
        }
        if(StringUtils.isNotEmpty(dep)){
        	count= count+" and bm in ("+dep+"'0')";
        }
        
        
        Sql sql=Sqls.create(count);
        sql.setEntity(dao.getEntity(Userlist.class));
        sql.setCallback(Sqls.callback.entities());
        
        dao.execute(sql);
        
        List<Userlist> list = sql.getList(Userlist.class);
        Userlist u = new Userlist();
        u.setXm(xm);
        list.add(u);
        return list;
    }
    
    
  //获取场所列表Json
    @At()
    @Ok("")
    public void queryPlaceJson(HttpServletRequest req) throws Exception {
    	HttpSession session = req.getSession();
    	session.removeAttribute("placelist");
        String count="select * from view_dz_place";
        
        Sql sql=Sqls.create(count);
        sql.setEntity(dao.getEntity(Place.class));
        sql.setCallback(Sqls.callback.entities());
        
        dao.execute(sql);
        
        List<Place> list = sql.getList(Place.class);
        for(int i=0;i<list.size();i++){
        	String id = list.get(i).getId();
        	String pid = list.get(i).getPid();
        	if(id.equals("0")&&pid.equals("0")){
        		list.get(i).setPid("");;
        	};
        }
        session.setAttribute("placelist", list);
    }
    

    //圈存对账明细Json
    @At()
    @Ok("json")
    public Map<String, Object> queryQcdzmxJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
        resMap.clear();
        String str = "select * from view_dz_qcdzmx where 1=1";
        String count="select sum(jyje) as zqcje from  view_dz_qcdzmx where 1=1";
        
        if (map.containsKey("gh") & StringUtils.isNotEmpty(map.get("gh"))) {
        	str=str+" and gh = '"+map.get("gh")+"'";
        	count=count+" and gh = '"+map.get("gh")+"'";
        }
        if (map.containsKey("bm") & StringUtils.isNotEmpty(map.get("bm"))) {
        	str=str+" and bm in ("+map.get("bm")+"'0')";
        	count=count+" and bm in ("+map.get("bm")+"'0')";
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	str=str+" and xm in ("+map.get("xm")+"'0')";
        	count=count+" and xm in ("+map.get("xm")+"'0')";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	str=str+" and jysj >= '"+map.get("dateb")+"'";
        	count=count+" and jysj >= '"+map.get("dateb")+"'";
        }else{
        	str=str+" and jysj >= '"+hehe+" 00:00:00'";
        	count=count+" and jysj >= '"+hehe+" 00:00:00'";
        	
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	str=str+" and jysj <= '"+map.get("datee")+"'";
        	count=count+" and jysj <= '"+map.get("datee")+"'";
        }else{
        	str=str+" and jysj <= '"+hehe+" 23:59:59'";
        	count=count+" and jysj <= '"+hehe+" 23:59:59'";
        }
        str+="order by jysj desc";
        
        Sql sql=Sqls.create(str);
        sql.setCallback(new QueryRecordCallback());
        
        dao.execute(sql);
        List<Qcdzmx> list = sql.getList(Qcdzmx.class);
       
        Sql sql1=Sqls.create(count);
        sql1.setCallback(new QueryRecordCallback());
        
        dao.execute(sql1);
        
        List<Qcdzmx> list1 = sql1.getList(Qcdzmx.class);
        String str1 = list1.toString();
        int start = str1.indexOf(":")+1;
        int end = str1.indexOf("}");
        String sum = str1.substring(start, end-1);
        resMap.put("Total", list.size());
        resMap.put("ZQCJE", sum);
        resMap.put("Rows", list);
        return resMap;
    }
    
    
    //圈存异常对账Json
    @At()
    @Ok("json")
    public Map<String, Object> queryQcycdzJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
        resMap.clear();
        Page page = getPage(map);
        Cnd cnd = Cnd.NEW();
        if (map.containsKey("gh") & StringUtils.isNotEmpty(map.get("gh"))) {
        	cnd.and("gh", "=", map.get("gh").toString());
        }
        if (map.containsKey("bm") & StringUtils.isNotEmpty(map.get("bm"))) {
        	cnd.and("bm", "=", map.get("bm").toString());
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	cnd.and("xm", "like", map.get("xm").toString());
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	cnd.and("jysj", ">=", map.get("dateb").toString());
        }else{
        	cnd.and("jysj", "=", hehe+" 00:00:00");
        	
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	cnd.and("jysj", "<=", map.get("datee").toString());
        }else{
        	cnd.and("jysj", "<=", hehe+" 23:59:59");
        }
        cnd.desc("jysj");
        
        List<Qcycdz> list = dao.query(Qcycdz.class, cnd, dao.createPager(page.getPageIndex() + 1, page.getPageSize()));
        int i = dao.count(Qcycdz.class, cnd);
        
        resMap.put("Total", i);
        resMap.put("Rows", list);
        return resMap;
    }
    
    
    
    //水控个人圈存汇总Json
    @At()
    @Ok("json")
    public Map<String, Object> queryGrqchzJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
        resMap.clear();
        String str = "select * from  view_dz_skgrqchz where 1=1";
        String count="select isnull(sum(jyqye),0) as jyqze ,isnull(sum(xfmx),0) as jyze,isnull(sum(jyhye),0) as jyhze from  view_dz_skgrqchz where 1=1";
        if (map.containsKey("gh") & StringUtils.isNotEmpty(map.get("gh"))) {
        	str = str+" and gh='"+map.get("gh").toString()+"'";
        	count = count+" and gh='"+map.get("gh").toString()+"'";
        }
        if (map.containsKey("bm") & StringUtils.isNotEmpty(map.get("bm"))) {
        	str = str+" and bm in ("+map.get("bm").toString()+"'0')";
        	count = count+" and bm in ("+map.get("bm").toString()+"'0')";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	str = str+" and jysj >='"+map.get("dateb").toString()+"'";
        	count = count+" and jysj >='"+map.get("dateb").toString()+"'";
        }else{
        	str = str+" and jysj >='"+hehe+" 00:00:00'";
        	count = count+" and jysj >='"+hehe+" 00:00:00'";
        	
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	str = str+" and jysj <='"+map.get("datee").toString()+"'";
        	count = count+" and jysj <='"+map.get("datee").toString()+"'";
        }else{
        	str = str+" and jysj <='"+hehe+"  23:59:59'";
        	count = count+" and jysj <='"+hehe+"  23:59:59'";
        }
        str = str+" order by jysj desc ";
        
        Sql sql1 = Sqls.create(str);
        sql1.setCallback(new QueryRecordCallback());
        
        dao.execute(sql1);
        List<Grqchz> list1 = sql1.getList(Grqchz.class);
      
        Sql sql = Sqls.create(count);
        sql.setCallback(new SqlCallback() {
			@Override
			public Object invoke(Connection arg0, ResultSet arg1, Sql arg2)
					throws SQLException {
				List<String> list = new ArrayList<String>();
				while(arg1.next()){
					list.add(arg1.getString("jyqze"));
					list.add(arg1.getString("jyze"));
					list.add(arg1.getString("jyhze"));
				}
				return list;
			}
		});
        dao.execute(sql);
        Double jyqze = Double.parseDouble(sql.getList(String.class).get(0));
        Double jyze = Double.parseDouble(sql.getList(String.class).get(1));
        Double jyhze = Double.parseDouble(sql.getList(String.class).get(2));
        
        resMap.put("Total", list1.size());
        resMap.put("JYQZE", jyqze);
        resMap.put("JYZE", jyze);
        resMap.put("JYHZE", jyhze);
        resMap.put("Rows", list1);
        return resMap;
    }

    
    //圈存对账表Json
    @At()
    @Ok("json")
    public Map<String, Object> queryQcdzbJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
        resMap.clear();
        String str = "select * from view_dz_qcdzb where 1=1 ";
        if (map.containsKey("gh") & StringUtils.isNotEmpty(map.get("gh"))) {
        	str = str + " and gh = '"+map.get("gh").toString()+"'";
        }
        if (map.containsKey("bm") & StringUtils.isNotEmpty(map.get("bm"))) {
        	
        	str = str + " and bm in ("+map.get("bm").toString()+"'0')";
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	str = str + " and xm in ("+map.get("xm").toString()+"'0')";
        }
        if (map.containsKey("sfly") & StringUtils.isNotEmpty(map.get("sfly"))) {
        	str = str + " and sfly = '"+map.get("sfly").toString()+"'";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	str = str + " and qcsj >= '"+map.get("dateb").toString()+"'";
        }else{
        	str = str + " and qcsj >= '"+ hehe+" 00:00:00"+"'";
        	
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	str = str + " and qcsj <= '"+map.get("datee").toString()+"'";
        }else{
        	str = str + " and qcsj <= '"+ hehe+" 23:59:59"+"'";
        }
        str = str + " order by qcsj desc";
        
        Sql sql = Sqls.create(str);
        sql.setCallback(new QueryRecordCallback());
        
        dao.execute(sql);
        List<Qcdzb> list = sql.getList(Qcdzb.class);
        
        resMap.put("Total", list.size());
        resMap.put("Rows", list);
        return resMap;
    }
    
    
    //设备圈存表Json
    @At()
    @Ok("json")
    public Map<String, Object> querySbqcbJson(@Param("..") Map<String, String> map,HttpServletRequest req) throws Exception {
        resMap.clear();
        String str = "select * from view_dz_Sbqcb where 1=1 ";
        if (map.containsKey("gh") & StringUtils.isNotEmpty(map.get("gh"))) {
        	str = str + " and gh = '"+map.get("gh").toString()+"'";
        }
        if (map.containsKey("bm") & StringUtils.isNotEmpty(map.get("bm"))) {
        	
        	str = str + " and bm in ("+map.get("bm").toString()+"'0')";
        }
        if (map.containsKey("xm") & StringUtils.isNotEmpty(map.get("xm"))) {
        	str = str + " and xm in ("+map.get("xm").toString()+"'0')";
        }
        if (map.containsKey("sfly") & StringUtils.isNotEmpty(map.get("sfly"))) {
        	str = str + " and sfly = '"+map.get("sfly").toString()+"'";
        }
        if (map.containsKey("dateb") & StringUtils.isNotEmpty(map.get("dateb"))) {
        	str = str + " and qcsj >= '"+map.get("dateb").toString()+"'";
        }else{
        	str = str + " and qcsj >= '"+ hehe+" 00:00:00"+"'";
        	
        }
        if (map.containsKey("datee") & StringUtils.isNotEmpty(map.get("datee"))) {
        	str = str + " and qcsj <= '"+map.get("datee").toString()+"'";
        }else{
        	str = str + " and qcsj <= '"+ hehe+" 23:59:59"+"'";
        }
        str = str + " order by qcsj desc";
        
        Sql sql = Sqls.create(str);
        sql.setCallback(new QueryRecordCallback());
        
        dao.execute(sql);
        List<Sbqcb> list = sql.getList(Sbqcb.class);
        
        resMap.put("Total", list.size());
        resMap.put("Rows", list);
        return resMap;
    }
    

}
