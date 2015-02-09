package net._100steps.labelsys.service.api.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import net._100steps.labelsys.service.message.Message;

public interface SystemManagerRO extends Remote
{
	/**
	 * 
	 * @param systemName
	 * @return
	 */
	public Message createSystem(String systemName) throws RemoteException;
	
	/**
	 * 
	 * @param systemId
	 * @param newSystemName
	 * @return
	 */
	public Message changeSystem(int systemId,String newSystemName) throws RemoteException;
	
	/**
	 * 
	 * @param oldSystemName
	 * @param newSystemName
	 * @return
	 */
	public Message changeSystem(String oldSystemName,String newSystemName) throws RemoteException;
	
	/**
	 * 
	 * @param systemId
	 * @return
	 */
	public Message deleteSystem(int systemId) throws RemoteException;
	/**
	 * 
	 * @param systemName
	 * @return
	 */
	public Message deleteSystem(String systemName) throws RemoteException;

}
