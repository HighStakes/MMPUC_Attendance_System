package mac;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import mac.Login;
import mac.HibernateUtil;

public class LoginDAO {

	@SuppressWarnings("unchecked")
	public boolean verifyUser(String username, String password) {

		System.out.println(" In Login DAO");
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Session session = sessionFactory.openSession();
		Transaction transaction = session.beginTransaction();
		boolean isUserAvailable = false;
		try {
			List<Login> users = session.createQuery("FROM Login").list();
			for (Login user : users) {

				System.out.println("User Name: " + user.getUserName());
				System.out.println("Password: " + user.getPassword());
				if(user.getUserName().equals(username) && user.getPassword().equals(password)){
					isUserAvailable = true;
				}
			}
			
		} catch (HibernateException e) {
			e.printStackTrace();
		} finally{
			transaction.commit();
			session.close();
		}
		return isUserAvailable;
		 
	}

}
