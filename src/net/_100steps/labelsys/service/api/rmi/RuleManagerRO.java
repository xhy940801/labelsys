package net._100steps.labelsys.service.api.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import net._100steps.general.message.Message;

public interface RuleManagerRO extends Remote
{
	/**
	 * 创建规则
	 * @param operationId
	 * @param exp
	 * @param permission
	 * @return
	 */
	public Message createRule(int operationId, String exp, int permission) throws RemoteException;
	
	/**
	 * 创建规则
	 * @param systemName
	 * @param moduleName
	 * @param operationName
	 * @param exp
	 * @param permission
	 * @return
	 */
	public Message createRule(String systemName, String moduleName, String operationName, String exp, int permission) throws RemoteException;
	
	/**
	 * 改变规则
	 * @param id
	 * @param operationId
	 * @param exp
	 * @param permission
	 * @return
	 */
	public Message changeRule(int id, Integer operationId, String exp, Integer permission) throws RemoteException;
	
	/**
	 * 改变规则
	 * @param id
	 * @param systemName
	 * @param moduleName
	 * @param operationName
	 * @param exp
	 * @param permission
	 * @return
	 */
	public Message changeRule(int id, String systemName, String moduleName, String operationName, String exp, Integer permission) throws RemoteException;
	
	/**
	 * 删除规则
	 * @param id
	 * @return
	 */
	public Message deleteRule(int id) throws RemoteException;
}
