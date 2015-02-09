package net._100steps.labelsys.service.api.rmi.defaultimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import net._100steps.labelsys.service.api.rmi.OperationManagerRO;
import net._100steps.labelsys.service.entity.EntityInfo;
import net._100steps.labelsys.service.entity.LabelInfo;
import net._100steps.labelsys.service.entity.OperationInfo;
import net._100steps.labelsys.service.manager.OperationManager;
import net._100steps.labelsys.service.message.Message;

public class OperationManagerRODefaultImpl extends UnicastRemoteObject
		implements OperationManagerRO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private OperationManager operationManager;

	protected OperationManagerRODefaultImpl() throws RemoteException
	{
		super();
	}

	@Override
	public Message createOperation(int moduleId, String name)
			throws RemoteException
	{
		return operationManager.createOperation(moduleId, name);
	}

	@Override
	public Message createOperation(String systemName, String moduleName,
			String operationName) throws RemoteException
	{
		return operationManager.createOperation(systemName, moduleName, operationName);
	}

	@Override
	public Message changeOperation(int operationId, String name)
			throws RemoteException
	{
		return operationManager.changeOperation(operationId, name);
	}

	@Override
	public Message changeOperation(String systemName, String moduleName,
			String oldName, String newName) throws RemoteException
	{
		return operationManager.changeOperation(systemName, moduleName, oldName, newName);
	}

	@Override
	public Message deleteOperation(int operationId) throws RemoteException
	{
		return operationManager.deleteOperation(operationId);
	}

	@Override
	public Message deleteOperation(String systemName, String moduleName,
			String operationName) throws RemoteException
	{
		return operationManager.deleteOperation(systemName, moduleName, operationName);
	}

	@Override
	public Message checkPermission(OperationInfo operationInfo,
			Iterable<LabelInfo> labelInfos) throws RemoteException
	{
		return operationManager.checkPermission(operationInfo, labelInfos);
	}

	@Override
	public Message checkPermission(OperationInfo operationInfo,
			EntityInfo entityInfo, Iterable<LabelInfo> labelInfos)
			throws RemoteException
	{
		return operationManager.checkPermission(operationInfo, entityInfo, labelInfos);
	}

	public void setOperationManager(OperationManager operationManager)
	{
		this.operationManager = operationManager;
	}

}
