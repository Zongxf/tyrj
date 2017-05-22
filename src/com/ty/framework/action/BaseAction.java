package com.ty.framework.action;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ty.util.Page;


public class BaseAction
{
	/**
	 * 
	 */
	public Logger logger = LoggerFactory.getLogger(this.getClass());

	public BaseAction()
	{

	}

	@SuppressWarnings("unchecked")
	public void checkAuth(HttpServletRequest req, String key) throws Exception
	{
		Map<String, String> authMap = (Map<String, String>) req.getSession().getAttribute("authMap");

		if (authMap.containsKey(key))
		{
			req.setAttribute("button", authMap.get(key));
		} else
		{
			throw (new Exception());
		}
	}

	@SuppressWarnings("unchecked")
	public String checkAuthStr(HttpServletRequest req, String key)
	{
		Map<String, String> authMap = (Map<String, String>) req.getSession().getAttribute("authMap");

		return authMap.get(key);
	}


	public Page getPage(Map<String, String> map)
	{
		String pageIndex = map.get("page");//
		String pageSize = map.get("pagesize");//

		Page page = new Page();

		if (StringUtils.isEmpty(pageIndex))
		{
			page.setPageIndex(0);
		} else
		{
			page.setPageIndex(Integer.parseInt(pageIndex) - 1);
		}
		if (StringUtils.isEmpty(pageSize))
		{
			page.setPageSize(20);

		} else
		{
			page.setPageSize(Integer.parseInt(pageSize));
		}

		page.setObjCondition(map);

		return page;
	}



	public String getdateStr()
	{
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return sdf.format(data);
	}

	public String getdate()
	{
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		return sdf.format(data);
	}

	public String getYYYYMMDD()
	{
		Date data = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(data);
	}

	public String getUUID()
	{
		return UUID.randomUUID().toString().toUpperCase().replaceAll("-", "");
	}

	public Date getPreviousMonth(String yyyy, String mm)
	{

		int y = Integer.parseInt(yyyy);
		int m = Integer.parseInt(mm);

		Calendar lastDate = Calendar.getInstance();
		lastDate.set(y, m - 1, 1);
		lastDate.add(Calendar.MONTH, -1);// 减一个月，变为下月的1�??
		Date date = lastDate.getTime();
		return date;

	}


	public static boolean mkDirectory(String path)
	{
		File file = null;
		try
		{
			file = new File(path);
			if (!file.exists())
			{
				return file.mkdirs();
			}
		} catch (RuntimeException e)
		{
			e.printStackTrace();
		} finally
		{
			file = null;
		}
		return false;
	}

}
