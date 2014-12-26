package com.iems.core.servlet.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class
 * ShutdownServletContextListener
 *
 */
@WebListener
public class ShutdownServletContextListener implements ServletContextListener {

	/**
	 * Default constructor.
	 */
	public ShutdownServletContextListener() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub

		// mysql
		try {
			com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown();
			System.out.println("com.mysql.jdbc.AbandonedConnectionCleanupThread.shutdown");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		/*try {
			org.logicalcobwebs.proxool.ProxoolFacade.shutdown(1000);
			System.out.println("org.logicalcobwebs.proxool.ProxoolFacade.shutdown");
		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
	}

}
