package com.ty.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

public class WBeanUtils
{
	/**
	 * 将一�? Map 对象转化为一�? JavaBean
	 * 
	 * @param type
	 *            要转化的类型
	 * @param map
	 *            包含属�?��?�的 map
	 * @return 转化出来�? JavaBean 对象
	 * @throws IntrospectionException
	 *             如果分析类属性失�?
	 * @throws IllegalAccessException
	 *             如果实例�? JavaBean 失败
	 * @throws InstantiationException
	 *             如果实例�? JavaBean 失败
	 * @throws InvocationTargetException
	 *             如果调用属�?�的 setter 方法失败
	 */
	@SuppressWarnings("rawtypes")
	public static Object convertMap(Class type, Map map) throws IntrospectionException, IllegalAccessException, InstantiationException, InvocationTargetException
	{
		BeanInfo beanInfo = Introspector.getBeanInfo(type); // 获取类属�?
		Object obj = type.newInstance(); // 创建 JavaBean 对象

		// �? JavaBean 对象的属性赋�?
		PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
		for (int i = 0; i < propertyDescriptors.length; i++)
		{
			PropertyDescriptor descriptor = propertyDescriptors[i];
			String propertyName = descriptor.getName();

			if (map.containsKey(propertyName))
			{
				// 下面�?句可�? try 起来，这样当�?个属性赋值失败的时�?�就不会影响其他属�?�赋值�??
				Object value = map.get(propertyName);

				Object[] args = new Object[1];
				args[0] = value;

				descriptor.getWriteMethod().invoke(obj, args);
			}
		}
		return obj;
	}

}
