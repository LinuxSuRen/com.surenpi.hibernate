/**
* Copyright © 1998-2015, surenpi.com All Rights Reserved.
*/
package org.suren.hibernate.event;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.hibernate.engine.PersistenceContext;
import org.hibernate.event.EventSource;
import org.suren.hibernate.util.EncryptUtil;

/**
 * @author suren
 * @date 2015年11月24日 上午10:26:38
 */
public class EncryptListener
{
	public void encrypt(Object entity, String[] names, Object[] states)
	{
		List<Field> fieldList = EncryptUtil.getEncryptFileds(entity);
		List<String> nameList = Arrays.asList(names);
		
		for(Field field : fieldList)
		{
			String name = field.getName();
			int index = nameList.indexOf(name);
			if(index == -1)
			{
				continue;
			}
			
			Object oldState = states[index];
			if(oldState == null)
			{
				continue;
			}
			
			states[index] = EncryptUtil.encrypt(oldState.toString());
		}
	}
	
	public void unEncrypt(Object entity, EventSource session)
	{
		List<Field> fieldList = EncryptUtil.getEncryptFileds(entity);
		for(Field field : fieldList)
		{
			PersistenceContext persistenceContext = session.getPersistenceContext();
//			boolean def = persistenceContext.isReadOnly(entity);
			
			persistenceContext.setReadOnly(entity, true);
			
			Method setterMethod = EncryptUtil.getSetter(field, entity);
			Method getterMethod = EncryptUtil.getGetter(field, entity);
			
			try
			{
				Object value = getterMethod.invoke(entity);
				
				if(value != null)
				{
					value = EncryptUtil.unEncrypt(value.toString());
					setterMethod.invoke(entity, value);
				}
			}
			catch (IllegalAccessException e)
			{
				e.printStackTrace();
			}
			catch (IllegalArgumentException e)
			{
				e.printStackTrace();
			}
			catch (InvocationTargetException e)
			{
				e.printStackTrace();
			}
			
//			persistenceContext.setReadOnly(entity, def);
		}
	}
}
