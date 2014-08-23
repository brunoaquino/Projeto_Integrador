package br.com.ECommerce.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import br.com.ECommerce.model.entities.Pessoa;

public class HibernateUtil {

	private static SessionFactory factory;  
	  
    static {  
        Configuration cfg = new Configuration().configure("hibernate.cfg.xml"); // Nao preciso chamar .configure(), porque nao uso hibernate.cfg.xml, uso hibernate.properties  
        
        
        // Mapeamento das Class com annotations
        cfg.addAnnotatedClass(Pessoa.class);
        ServiceRegistry serviceRegistry = new ServiceRegistryBuilder().applySettings(cfg.getProperties()).buildServiceRegistry();  
        factory = cfg.buildSessionFactory(serviceRegistry);  
    }  
      
    public static Session getSession() {  
        return factory.openSession();  
    }

}
