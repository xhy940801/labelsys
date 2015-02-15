package net._100steps.labelsys.service.api.rmi.defaultimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import net._100steps.general.message.Message;
import net._100steps.labelsys.service.api.rmi.ModuleManagerRO;
import net._100steps.labelsys.service.manager.ModuleManager;

public class ModuleManagerRODefaultImpl extends UnicastRemoteObject implements
		ModuleManagerRO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ModuleManager moduleManager;

	protected ModuleManagerRODefaultImpl() throws RemoteException
	{
		super();
	}

	@Override
	public Message createModule(int systemId, String moduleName)
			throws RemoteException
	{
		return moduleManager.createModule(systemId, moduleName);
	}

	@Override
	public Message createModule(String systemName, String moduleName)
			throws RemoteException
	{
		return moduleManager.createModule(systemName, moduleName);
	}

	@Override
	public Message changeModule(int moduleId, String newModuleName)
			throws RemoteException
	{
		return moduleManager.changeModule(moduleId, newModuleName);
	}

	@Override
	public Message changeModule(String systemName, String oldModuleName,
			String newModuleName) throws RemoteException
	{
		return moduleManager.changeModule(systemName, oldModuleName, newModuleName);
	}

	@Override
	public Message deleteModule(int moduleId) throws RemoteException
	{
		return moduleManager.deleteModule(moduleId);
	}

	@Override
	public Message deleteModule(String systemName, String moduleName)
			throws RemoteException
	{
		return moduleManager.deleteModule(systemName, moduleName);
	}

	public void setModuleManager(ModuleManager moduleManager)
	{
		this.moduleManager = moduleManager;
	}

}
