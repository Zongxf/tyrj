package com.ty.user;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.nutz.dao.Sqls;
import org.nutz.dao.impl.NutDao;
import org.nutz.dao.impl.sql.callback.QueryRecordCallback;
import org.nutz.dao.sql.Sql;
import org.nutz.dao.sql.SqlCallback;
import org.nutz.ioc.annotation.InjectName;
import org.nutz.ioc.loader.annotation.Inject;
import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.lang.Files;
import org.nutz.mvc.annotation.At;
import org.nutz.mvc.annotation.Attr;
import org.nutz.mvc.annotation.Fail;
import org.nutz.mvc.annotation.Filters;
import org.nutz.mvc.annotation.Ok;
import org.nutz.mvc.annotation.Param;

import com.ty.bean.Node;
import com.ty.framework.action.BaseAction;


@At("/user")
@InjectName()
@IocBean
@Fail("jsp:/common/authError")
public class UserAction extends BaseAction
{

	/**
	 * 
	 */

	@Inject
	private UserServiceImpl userService;
	
	private Map<String, Object> resMap;
	@Inject
	private NutDao dao;

	public UserAction()
	{
		resMap = new HashMap<String, Object>();

	}
	
	@At()
	@Ok("jsp:jsp/changepasswd")
	public void changePasswd()
	{
	}
	
	@At()
	@Ok("json")
	@Filters()
	public Map<String, Object> savePasswd(@Param("::user.") User user)
	{
		resMap.clear();
		try
		{   
			String Gly_no=user.getGly_no();
			String Gly_pass=user.getGly_pass();
			String str="update WT_GLY set Gly_pass = '"+Gly_pass+"' where Gly_no = '"+Gly_no+"'"; 
			Sql sql=Sqls.create(str);
			sql.setCallback(new QueryRecordCallback());
			dao.execute(sql);
			resMap.put("success", true);
		} catch (Exception e)
		{
			e.printStackTrace();
			resMap.put("success", false);

		}
		return resMap;
	}


	@At()
	@Ok("json")
	@Filters
	public Map<String, Object> checkLogin(@Attr("userInfo") User user, HttpServletRequest req)
	{

		try
		{

			if (null == user)
			{
				resMap.put("success", false);
			} else
			{
				resMap.put("success", true);
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			resMap.put("success", false);
		}
		return resMap;
	}
	
	@At()
	@Ok("json")
	@Filters
	public Map<String, Object> login(HttpServletRequest req, @Param("..") Map<String, String> map)
	{
		HttpSession session = req.getSession();
		session.removeAttribute("userInfo");
		session.removeAttribute("authMap");
		session.removeAttribute("nodelist");
		resMap.clear();

		try
		{
			// �ۺ�ϵͳ�û���¼
			User user = userService.getUser(map);
			
			if (null != user)
			{
//				System.out.println(map.get("user_passwd"));
//				System.out.println(user.getGly_pass());
				
				if (map.get("user_passwd").equals(user.getGly_pass()))
				{
					session.setAttribute("userInfo", user);
					resMap.put("success", true);
					resMap.put("user_name", user.getGly_no());
					
					
					
					List<Node> nodelist = new ArrayList<Node>();

					try
					{
						SAXReader saxReader = new SAXReader();
						Document document = saxReader.read(Files.findFile("authority/" + user.getGly_no() + ".xml"));

						// �γɲ˵���Ϣ
						List list = document.selectNodes("//module");
						Iterator iter = list.iterator();
						Node node = null;
						while (iter.hasNext())
						{
							node = new Node();
							Element element = (Element) iter.next();
							node.setId(element.attribute("id").getText());
							node.setPid(element.attribute("pid").getText());
							node.setText(element.attribute("text").getText());
							nodelist.add(node);
						}

						list = document.selectNodes("//module/node");
						iter = list.iterator();
						while (iter.hasNext())
						{
							node = new Node();
							Element element = (Element) iter.next();
							node.setId(element.attribute("id").getText());
							node.setPid(element.attribute("pid").getText());
							node.setText(element.attribute("text").getText());
							node.setUrl(element.attribute("url").getText());
							nodelist.add(node);
						}

						session.setAttribute("nodelist", nodelist);

						// ��ȡȨ����Ϣ
						list = document.selectNodes("//module/node/auth");
						iter = list.iterator();

						Map<String, String> authMap = new HashMap<String, String>();
						while (iter.hasNext())
						{
							Element element = (Element) iter.next();
							authMap.put(element.attribute("name").getText(), element.attribute("button").getText());
						}

						session.setAttribute("authMap", authMap);

					} catch (Exception e)
					{
						e.printStackTrace();
					}
					
				} else
				{
					resMap.put("success", false);
				}
			}

		} catch (Exception e)
		{
			e.printStackTrace();
			resMap.put("success", false);
		}

		return resMap;

	}
    //退出
	@At()
	@Ok("redirect:/index.jsp")
	public String logout(HttpServletRequest req)
	{
		req.getSession().removeAttribute("userInfo");
		return "logout";

	}
	
}
