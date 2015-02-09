package net._100steps.labelsys.service.api.rmi.defaultimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import net._100steps.labelsys.service.api.rmi.LabelManagerRO;
import net._100steps.labelsys.service.manager.LabelManager;
import net._100steps.labelsys.service.message.Message;

public class LabelManagerRODefaultImpl extends UnicastRemoteObject implements
		LabelManagerRO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private LabelManager labelManager;

	public LabelManagerRODefaultImpl() throws RemoteException
	{
		super();
	}

	@Override
	public Message createLabel(int moduleId, String name)
			throws RemoteException
	{
		return labelManager.createLabel(moduleId, name);
	}

	@Override
	public Message createLabel(String systemName, String moduleName,
			String labelName) throws RemoteException
	{
		return labelManager.createLabel(systemName, moduleName, labelName);
	}

	@Override
	public Message changeLabel(int labelId, String newName)
			throws RemoteException
	{
		return labelManager.changeLabel(labelId, newName);
	}

	@Override
	public Message changeLabel(String systemName, String moduleName,
			String oldName, String newName) throws RemoteException
	{
		return labelManager.changeLabel(systemName, moduleName, oldName, newName);
	}

	@Override
	public Message deleteLabel(int labelId) throws RemoteException
	{
		return labelManager.deleteLabel(labelId);
	}

	@Override
	public Message deleteLabel(String systemName, String moduleName,
			String labelName) throws RemoteException
	{
		return labelManager.deleteLabel(systemName, moduleName, labelName);
	}

	public void setLabelManager(LabelManager labelManager)
	{
		this.labelManager = labelManager;
	}

}
