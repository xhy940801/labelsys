package net._100steps.labelsys.service.api.rmi;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;

public class ROFactory
{
	private String host;
	private int port;
	
	public ROFactory()
	{
		host = "//localhost";
		port = 1099;
	}
	
	public ROFactory(String host, int port)
	{
		this.host = host;
		this.port = port;
	}
	
	public Object getRO(String roName) throws MalformedURLException, RemoteException, NotBoundException
	{
		String url = "rmi:" + host + ":" + port + "/" + roName;
		return Naming.lookup(url);
	}
}
