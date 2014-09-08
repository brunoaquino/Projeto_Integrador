package br.com.ECommerce.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

import br.com.ECommerce.modelo.entidade.Pessoa;

public class HibernateUtil {

	static SessionFactory sessionFactory;

	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				AnnotationConfiguration ac = new AnnotationConfiguration();
				
				//Annotations  das classes
				ac.addAnnotatedClass(Pessoa.class);
				
				sessionFactory = ac.configure("hibernate.cfg.xml").buildSessionFactory();
			} catch (Throwable ex) {
				System.err.println("Initial SessionFactory creation failed."+ ex);
				throw new ExceptionInInitializerError(ex);
			}
			return sessionFactory;
		} else {
			return sessionFactory;
		}
	}
}