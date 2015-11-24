/**
* Copyright © 1998-2015, surenpi.com All Rights Reserved.
*/
package org.suren.hibernate.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import org.suren.hibernate.annotation.Encrypt;

/**
 * @author suren
 * @date 2015年11月23日 下午4:50:07
 */
public class EncryptUtil
{
	private static final String WD = "+++";
	
	public static String encrypt(String str)
	{
		return (str + WD);
	}
	
	public static String unEncrypt(String str)
	{
		if(str == null)
		{
			return str;
		}
		
		if(str.endsWith(WD))
		{
			return str.replace(WD, "");
		}
		else
		{
			return str;
		}
	}
	
	/**
	 * TODO
	 * @param obj
	 * @return
	 * @throws NullPointerException if the given obj is null
	 */
	public static List<Field> getEncryptFileds(Object obj)
	{
		List<Field> fieldList = new ArrayList<Field>();
		if(obj == null)
		{
			throw new NullPointerException();
		}
		
		Field[] fields = obj.getClass().getDeclaredFields();
		if(fields != null)
		{
			for(Field field : fields)
			{
				Encrypt encrypt = field.getAnnotation(Encrypt.class);
				if(encrypt != null)
				{
					fieldList.add(field);
				}
			}
		}
		
		return fieldList;
	}
	
	/**
	 * 查找属性对应的setter方法
	 * @param field
	 * @param obj
	 * @return
	 * @throws NullPointerException if the field or obj is null
	 */
	public static Method getSetter(Field field, Object obj)
	{
		if(field == null || obj == null)
		{
			throw new NullPointerException();
		}
		
		String fieldName = field.getName();
		fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		fieldName = "set" + fieldName;
		
		try
		{
			return obj.getClass().getMethod(fieldName, field.getType());
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * 查找属性对应的getter方法
	 * @param field
	 * @param obj
	 * @return
	 * @throws NullPointerException if the field or obj is null
	 */
	public static Method getGetter(Field field, Object obj)
	{
		if(field == null || obj == null)
		{
			throw new NullPointerException();
		}
		
		String fieldName = field.getName();
		fieldName = fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
		fieldName = "get" + fieldName;
		
		try
		{
			return obj.getClass().getMethod(fieldName);
		}
		catch (NoSuchMethodException e)
		{
			e.printStackTrace();
		}
		catch (SecurityException e)
		{
			e.printStackTrace();
		}
		
		return null;
	}
}
