package com.ty.user;

import java.util.*;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.nutz.dao.*;
import org.nutz.dao.impl.NutDao;
import org.nutz.ioc.loader.annotation.*;

@IocBean(name = "userService")
public class UserServiceImpl
{

	@Inject
	private NutDao dao;

	public Logger logger = Logger.getLogger(this.getClass().getName());

	public User getUser(Map<String, String> map)
	{

		Cnd cnd=Cnd.NEW();
		if (map.containsKey("user_name") && StringUtils.isNotEmpty(map.get("user_name")))
		{
			cnd = cnd.and("Gly_no", "=", map.get("user_name").trim());
		}

		if (map.containsKey("user_passwd") && StringUtils.isNotEmpty(map.get("user_passwd")))
		{
			cnd = cnd.and("Gly_pass", "=", map.get("user_passwd").trim());
		}
		User user = dao.fetch(User.class, cnd);
        
		return user;

	}
}
