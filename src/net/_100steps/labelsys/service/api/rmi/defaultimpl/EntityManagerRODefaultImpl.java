package net._100steps.labelsys.service.api.rmi.defaultimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.List;

import net._100steps.labelsys.service.api.rmi.EntityManagerRO;
import net._100steps.labelsys.service.manager.EntityManager;
import net._100steps.labelsys.service.message.Message;

public class EntityManagerRODefaultImpl extends UnicastRemoteObject implements
		EntityManagerRO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;
	
	public EntityManagerRODefaultImpl() throws RemoteException
	{
		super();
	}

	@Override
	public Message createEntity(int moduleId, int foreignKey)
			throws RemoteException
	{
		return entityManager.createEntity(moduleId, foreignKey);
	}

	@Override
	public Message createEntity(String systemName, String moduleName,
			int foreignKey) throws RemoteException
	{
		return entityManager.createEntity(systemName, moduleName, foreignKey);
	}

	@Override
	public Message deleteEntity(int entityId) throws RemoteException
	{
		return entityManager.deleteEntity(entityId);
	}

	@Override
	public Message deleteEntity(String systemName, String moduleName,
			int foreignKey) throws RemoteException
	{
		return entityManager.deleteEntity(systemName, moduleName, foreignKey);
	}

	@Override
	public Message findEntitiesByLabels(List<Integer> labelsId)
			throws RemoteException
	{
		return entityManager.findEntitiesByLabels(labelsId);
	}

	@Override
	public Message setLabels(int entityId, List<Integer> labelsId)
			throws RemoteException
	{
		return entityManager.setLabels(entityId, labelsId);
	}

	@Override
	public Message setLabels(String systemName, String moduleName,
			int foreignKey, List<Integer> labelsId) throws RemoteException
	{
		return entityManager.setLabels(systemName, moduleName, foreignKey, labelsId);
	}

	@Override
	public Message addLabel(int entityId, int labelId) throws RemoteException
	{
		return entityManager.addLabel(entityId, labelId);
	}

	@Override
	public Message addLabel(String systemName, String moduleName,
			int foreignKey, String labelName) throws RemoteException
	{
		return entityManager.addLabel(systemName, moduleName, foreignKey, labelName);
	}

	public void setEntityManager(EntityManager entityManager)
	{
		this.entityManager = entityManager;
	}

}
