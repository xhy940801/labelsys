package net._100steps.labelsys.service.api.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import net._100steps.general.message.Message;

public interface LabelManagerRO extends Remote
{
	/**
	 * 创城label
	 * @param moduleId
	 * @param name
	 * @return
	 */
	public Message createLabel(int moduleId, String name) throws RemoteException;
	
	/**
	 * 创建label
	 * @param systemName 系统名称
	 * @param moduleName 模块名称
	 * @param labelName label名称
	 * @return
	 */
	public Message createLabel(String systemName, String moduleName, String labelName) throws RemoteException;
	
	/**
	 * 修改label
	 * @param labelId
	 * @param newName
	 * @return
	 */
	public Message changeLabel(int labelId, String newName) throws RemoteException;
	
	/**
	 * 修改label
	 * @param systemName
	 * @param moduleName
	 * @param oldName
	 * @param newName
	 * @return
	 */
	public Message changeLabel(String systemName, String moduleName, String oldName, String newName) throws RemoteException;
	
	/**
	 * 删除label
	 * @param labelId
	 * @return
	 */
	public Message deleteLabel(int labelId) throws RemoteException;
	
	/**
	 * 删除label
	 * @param systemName
	 * @param moduleName
	 * @param name
	 * @return
	 */
	public Message deleteLabel(String systemName, String moduleName, String labelName) throws RemoteException;
}
