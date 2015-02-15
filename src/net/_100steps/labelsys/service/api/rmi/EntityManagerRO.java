package net._100steps.labelsys.service.api.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

import net._100steps.general.message.Message;

public interface EntityManagerRO extends Remote
{
	/**
	 * 
	 * @param moduleId
	 * @param foreignKey
	 * @return
	 */
	public Message createEntity(int moduleId,int foreignKey) throws RemoteException;
	
	/**
	 * 
	 * @param systemName
	 * @param moduleName
	 * @param foreignKey
	 * @return
	 */
	public Message createEntity(String systemName,String moduleName,int foreignKey) throws RemoteException;
	/**
	 * 
	 * @param entityId
	 * @return
	 */
	public Message deleteEntity(int entityId) throws RemoteException;
	/**
	 * 
	 * @param systemName
	 * @param moduleName
	 * @param foreignKey
	 * @return
	 */
	public Message deleteEntity(String systemName,String moduleName,int foreignKey) throws RemoteException;
	/**
	 * 
	 * @param labelsId
	 * @return
	 */
	public Message findEntitiesByLabels(List<Integer>labelsId) throws RemoteException;
	/**
	 * 
	 * @param entityId
	 * @param labelsId
	 * @return
	 */
	public Message setLabels(int entityId,List<Integer>labelsId) throws RemoteException;
	/**
	 * 
	 * @param systemName
	 * @param moduleName
	 * @param foreignKey
	 * @param labelsId
	 * @return
	 */
	public Message setLabels(String systemName,String moduleName,int foreignKey,List<Integer>labelsId) throws RemoteException;
	/**
	 * 
	 * @param entityId
	 * @param labelId
	 * @return
	 */
	public Message addLabel(int entityId,int labelId) throws RemoteException;
	/**
	 * 
	 * @param systemName
	 * @param moduleName
	 * @param foreignKey
	 * @param labelName
	 * @return
	 */
	public Message addLabel(String systemName,String moduleName,int foreignKey,String labelName) throws RemoteException;
}
