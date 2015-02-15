package net._100steps.labelsys.service.api.rmi.defaultimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import net._100steps.general.message.Message;
import net._100steps.labelsys.service.api.rmi.SystemManagerRO;
import net._100steps.labelsys.service.manager.SystemManager;

public class SystemManagerRODefaultImpl extends UnicastRemoteObject implements
		SystemManagerRO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private SystemManager systemManager;

	protected SystemManagerRODefaultImpl() throws RemoteException
	{
		super();
	}

	@Override
	public Message createSystem(String systemName) throws RemoteException
	{
		return systemManager.createSystem(systemName);
	}

	@Override
	public Message changeSystem(int systemId, String newSystemName)
			throws RemoteException
	{
		return systemManager.changeSystem(systemId, newSystemName);
	}

	@Override
	public Message changeSystem(String oldSystemName, String newSystemName)
			throws RemoteException
	{
		return systemManager.changeSystem(oldSystemName, newSystemName);
	}

	@Override
	public Message deleteSystem(int systemId) throws RemoteException
	{
		return systemManager.deleteSystem(systemId);
	}

	@Override
	public Message deleteSystem(String systemName) throws RemoteException
	{
		return systemManager.deleteSystem(systemName);
	}

	public void setSystemManager(SystemManager systemManager)
	{
		this.systemManager = systemManager;
	}

}
