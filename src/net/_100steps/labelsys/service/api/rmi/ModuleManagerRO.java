package net._100steps.labelsys.service.api.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import net._100steps.labelsys.service.message.Message;

public interface ModuleManagerRO extends Remote
{
	/**
	 * 
	 * @param systemId
	 * @param moduleName
	 * @return
	 */
	public Message createModule(int systemId,String moduleName) throws RemoteException;
	/**
	 * 
	 * @param systemName
	 * @param moduleName
	 * @return
	 */
	public Message createModule(String systemName,String moduleName) throws RemoteException;
	/**
	 * 
	 * @param moduleId
	 * @param newModuleName
	 * @return
	 */
	public Message changeModule(int moduleId,String newModuleName) throws RemoteException;
	/**
	 * 
	 * @param systemName
	 * @param oldModuleName
	 * @param newModuleName
	 * @return
	 */
	public Message changeModule(String systemName,String oldModuleName, String newModuleName) throws RemoteException;
	/**
	 * 
	 * @param moduleId
	 * @return
	 */
	public Message deleteModule(int moduleId) throws RemoteException;
	/**
	 * 
	 * @param systemName
	 * @param moduleName
	 * @return
	 */
	public Message deleteModule(String systemName,String moduleName) throws RemoteException;
}
