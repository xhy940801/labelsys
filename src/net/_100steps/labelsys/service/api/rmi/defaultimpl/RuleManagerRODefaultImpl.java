package net._100steps.labelsys.service.api.rmi.defaultimpl;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import net._100steps.general.message.Message;
import net._100steps.labelsys.service.api.rmi.RuleManagerRO;
import net._100steps.labelsys.service.manager.RuleManager;

public class RuleManagerRODefaultImpl extends UnicastRemoteObject implements
		RuleManagerRO
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private RuleManager ruleManager;

	protected RuleManagerRODefaultImpl() throws RemoteException
	{
		super();
	}

	@Override
	public Message createRule(int operationId, String exp, int permission)
			throws RemoteException
	{
		return ruleManager.createRule(operationId, exp, permission);
	}

	@Override
	public Message createRule(String systemName, String moduleName,
			String operationName, String exp, int permission)
			throws RemoteException
	{
		return ruleManager.createRule(systemName, moduleName, operationName,
				exp, permission);
	}

	@Override
	public Message changeRule(int id, Integer operationId, String exp,
			Integer permission) throws RemoteException
	{
		return ruleManager.changeRule(id, operationId, exp, permission);
	}

	@Override
	public Message changeRule(int id, String systemName, String moduleName,
			String operationName, String exp, Integer permission)
			throws RemoteException
	{
		return ruleManager.changeRule(id, systemName, moduleName,
				operationName, exp, permission);
	}

	@Override
	public Message deleteRule(int id) throws RemoteException
	{
		return ruleManager.deleteRule(id);
	}

	public void setRuleManager(RuleManager ruleManager)
	{
		this.ruleManager = ruleManager;
	}

}
