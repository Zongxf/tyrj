package com.ty;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.annotation.*;
import org.nutz.mvc.filter.CheckSession;
import org.nutz.mvc.ioc.provider.ComboIocProvider;

@Modules(scanPackage = true)
@IocBy(type=ComboIocProvider.class,
args={"*org.nutz.ioc.loader.json.JsonLoader","ioc/",
	  "*org.nutz.ioc.loader.annotation.AnnotationIocLoader","com.ty"})
@IocBean
@Filters(@By(type=CheckSession.class, args={"userInfo", "/index.jsp"}))
//@SetupBy(CardSetupBy.class)
public class MainModule
{
	//空的主类
}
