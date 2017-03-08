package it.dstech.dao;

import java.util.List;

import org.hibernate.Session;

import it.dstech.config.HibernateUtil;

public class HibernateDao {
	//tjnijbn5itbinvfkirem
	private static Session session;
	
	public static Session getSession(){
		session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
		return session;
	}
	public static void closeSession(){
		session.getTransaction().commit();
		session.close();
	
	}
	public static Object persist(Object object) {

//		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.persist(object);
		session.getTransaction().commit();
//		session.close();
		return object;

	}

	@SuppressWarnings("rawtypes")
	public static List getAll(String queryHQL) {

//		Session session = HibernateUtil.getSessionFactory().openSession();
//		session.beginTransaction();
		return session.createQuery(queryHQL).list();
//		session.getTransaction().commit();
//		session.close();
		

	}

	public static Object update(Object object) {

//		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.update(object);
		session.getTransaction().commit();
//		session.close();
		return object;

	}

	public static Object delete(Object object) {

//		Session session = HibernateUtil.getSessionFactory().openSession();
		session.beginTransaction();
		session.delete(object);
		session.getTransaction().commit();
//		session.close();
		return object;

	}

}
