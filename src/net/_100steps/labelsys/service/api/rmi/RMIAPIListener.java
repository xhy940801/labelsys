package net._100steps.labelsys.service.api.rmi;

import java.net.MalformedURLException;
import java.rmi.AlreadyBoundException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * Application Lifecycle Listener implementation class RMIAPIListener
 *
 */
@WebListener
public class RMIAPIListener implements ServletContextListener
{
	/**
	 * Default constructor.
	 */
	public RMIAPIListener()
	{
		
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent event)
	{
		ApplicationContext applicationContext = WebApplicationContextUtils.getWebApplicationContext(event.getServletContext());
		try
		{
			LocateRegistry.createRegistry((int) applicationContext.getBean("api-rmiPort"));
			
			EntityManagerRO entityManagerRO = (EntityManagerRO) applicationContext.getBean("entityManagerRO");
			LabelManagerRO labelManabgerRO = (LabelManagerRO) applicationContext.getBean("labelManagerRO");
			ModuleManagerRO moduleManagerRO = (ModuleManagerRO) applicationContext.getBean("moduleManagerRO");
			OperationManagerRO operationManagerRO = (OperationManagerRO) applicationContext.getBean("operationManagerRO");
			RuleManagerRO ruleManagerRO = (RuleManagerRO) applicationContext.getBean("ruleManagerRO");
			SystemManagerRO systemManagerRO = (SystemManagerRO) applicationContext.getBean("systemManagerRO");
			
			String baseUrl = "rmi:" + applicationContext.getBean("api-rmiHost") + ":" + applicationContext.getBean("api-rmiPort") + "/";
			Naming.bind(baseUrl + "EntityManagerRO", entityManagerRO);
			Naming.bind(baseUrl + "LabelManabgerRO", labelManabgerRO);
			Naming.bind(baseUrl + "ModuleManagerRO", moduleManagerRO);
			Naming.bind(baseUrl + "OperationManagerRO", operationManagerRO);
			Naming.bind(baseUrl + "RuleManagerRO", ruleManagerRO);
			Naming.bind(baseUrl + "SystemManagerRO", systemManagerRO);
		}
		catch (RemoteException e)
		{
			e.printStackTrace();
		}
		catch (MalformedURLException e)
		{
			e.printStackTrace();
		}
		catch (AlreadyBoundException e)
		{
			e.printStackTrace();
		}
		
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent event)
	{
		
	}

}
