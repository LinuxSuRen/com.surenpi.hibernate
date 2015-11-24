/**
* Copyright © 1998-2015, Glodon Inc. All Rights Reserved.
*/
package org.suren.hibernate;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.classic.Session;
import org.suren.hibernate.po.Student;

/**
 * @author zhaoxj
 * @since jdk1.6
 * 2015年11月23日
 */
public class Test {

	/**
	 * TODO
	 * @param args
	 */
	public static void main(String[] args) {
		Configuration cfg = new AnnotationConfiguration().configure();
		SessionFactory sessionFactory = cfg.buildSessionFactory();
		
		Session session = sessionFactory.openSession();
		
		System.out.println(session);
		Transaction transaction = session.beginTransaction();
		transaction.begin();

		Student stu = new Student();
		stu.setName("ahos");
		session.save(stu);
		
		System.out.println(stu);
		
		transaction.commit();
		
		System.out.println(stu);
		
		transaction = session.beginTransaction();
		transaction.begin();
		Query query = session.createQuery("from Student");
		System.out.println(query.list());
		transaction.commit();
		
		session.close();
	}

}
