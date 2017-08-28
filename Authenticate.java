package org.model;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class Authenticate {

	public boolean checkUser(Login user) {
		SessionFactory sf = new Configuration().configure().buildSessionFactory();
		Session session = sf.openSession();
		session.beginTransaction();
		try {
		Login l = session.get(Login.class,user.getUserName());
		//System.out.println(l.getUserName()+l.getPassword()+user.getUserName()+user.getPassword());
		if(l.getUserName().equals(user.getUserName()) && l.getPassword().equals(user.getPassword()))
			return true;
		}
		catch(Exception e) {
			return false;
		}
		return false;
	}

}
