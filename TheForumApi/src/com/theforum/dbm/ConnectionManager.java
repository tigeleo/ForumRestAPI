package com.theforum.dbm;

import java.util.Date;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import com.theforum.db.Auth;
import com.theforum.db.User;

/**
 * Single tone class that implement secure connection with database
 * 
 * @author DavidBD
 *
 */
public class ConnectionManager {
	private static ConnectionManager cm = null;

	private Configuration configuration;
	private ServiceRegistry serviceRegistry;
	private SessionFactory sessionFactory;
	
	private ConnectionManager() {
		// loads configuration and mappings
		this.configuration = new Configuration().configure();
		this.serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();

		// builds a session factory from the service registry
		this.sessionFactory = configuration.buildSessionFactory(serviceRegistry);

	}

	public static ConnectionManager factory() {
		if (cm == null) {
			cm = new ConnectionManager();
		}
		return cm;
	}

	/**
	 * Check if login already exists in database
	 * @param login
	 * @return true if exists
	 */
	public synchronized boolean checkLoginExists(String login) {
		// obtains the session
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from com.theforum.db.Auth where login = :login ");
		query.setParameter("login", login);
		List match = query.list();
		session.close();
		if(match!=null && match.size()>0) {
			return true;
		}else {
			return false;
		}
	}
	
	
	
	public synchronized Auth authenticate(String login, String password) {
		// obtains the session
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from com.theforum.db.Auth where login = :login and password = :password ");
		query.setParameter("login", login);
		query.setParameter("password", password);
		final List<Auth> match = query.list();
		session.close();
		if(match!=null && match.size()>0) {
			return match.get(0);
		}else {
			return null;
		}		
	}
	
	/**
	 * Create row with user detailes
	 * @param u
	 */
	public synchronized void saveUserDetails(User u) {

		// obtains the session
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		u.setCreated(new Date());
		u.setUpdated(new Date());
		session.save(u);
		
		session.getTransaction().commit();
		session.close();

	}
	
	/**
	 * Create new row to authenticate. 
	 * @param au
	 */
	public synchronized void registration(Auth au) {
		
		// obtains the session
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		au.setCreated(new Date());
		au.setUpdated(new Date());
		session.save(au);
		
		session.getTransaction().commit();
		session.close();
		
	}
	
	
	
	public void destroyConnection() {
		StandardServiceRegistryBuilder.destroy(this.serviceRegistry);		
		cm=null;
	}
}
