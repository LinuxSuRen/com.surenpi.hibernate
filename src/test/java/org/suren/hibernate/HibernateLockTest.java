/**
* Copyright © 1998-2015, surenpi.com All Rights Reserved.
*/
package org.suren.hibernate;

import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.suren.hibernate.po.Student;

/**
 * @author suren
 * @date 2015年12月2日 下午3:11:03
 */
public class HibernateLockTest
{

	/**
	 * TODO
	 * @param args
	 */
	public static void main(String[] args)
	{
		Configuration cfg = new AnnotationConfiguration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		Student stu = new Student();
		stu.setName(String.valueOf(System.currentTimeMillis()));
		
		Transaction transaction = session.beginTransaction();
		transaction.begin();
		
		Query query = session.createQuery("from Student as Student where Student.name = 'ahos+++'");
//		query.setLockMode("Student", LockMode.UPGRADE);
		query.setLockMode("Student", LockMode.WRITE);
		
		query.list();
		
		try
		{
			System.out.println("prepare to sleep");
			Thread.sleep(90000);
			System.out.println("done to sleep");
		}
		catch(InterruptedException e)
		{
			e.printStackTrace();
		}
		
		session.save(stu);
		
		transaction.commit();
	}

}
