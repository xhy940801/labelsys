package net._100steps.labelsys.service.manager.defaultimpl;

import java.util.Date;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.LabelDAO;
import net._100steps.labelsys.service.dao.ModuleDAO;
import net._100steps.labelsys.service.dao.OperationDAO;
import net._100steps.labelsys.service.dao.RuleDAO;
import net._100steps.labelsys.service.dao.SystemDAO;
import net._100steps.labelsys.service.manager.RuleManager;
import net._100steps.labelsys.service.message.Message;
import net._100steps.labelsys.service.message.impl.ErrorMessage;
import net._100steps.labelsys.service.message.impl.RuleMessage;
import net._100steps.labelsys.service.model.Module;
import net._100steps.labelsys.service.model.Operation;
import net._100steps.labelsys.service.model.Rule;
import net._100steps.labelsys.service.model.System;

public class RuleManagerDefaultImpl implements RuleManager
{	
	private LabelDAO labelDAO;
	private OperationDAO operationDAO;
	private RuleDAO ruleDAO;
	private SystemDAO systemDAO;
	private ModuleDAO moduleDAO;

	@Override
	public Message createRule(int operationId, String exp, int permission)
	{
		try
		{
			Operation operation = operationDAO.getById(operationId);
			if(operation == null)
				return new ErrorMessage(304011);
			checkExp(exp);
			Rule rule = new Rule(operationId, exp, permission, new Date());
			ruleDAO.save(rule);
			return new RuleMessage(rule);
		}
		catch (IllegalArgumentException e)
		{
			return new ErrorMessage(304011, e);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(404011, e);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(503011, e);
		}
	}
	
	@Override
	public Message createRule(String systemName, String moduleName,
			String operationName, String exp, int permission)
	{
		try
		{
			System system = systemDAO.getByName(systemName);
			if(system == null)
				return new ErrorMessage(304021);
			Module module = moduleDAO.getByName(system.getId(), moduleName);
			if(module == null)
				return new ErrorMessage(304022); 
			Operation operation = operationDAO.getByName(module.getId(), operationName);
			if(operation == null)
				return new ErrorMessage(304023);
			checkExp(exp);
			Rule rule = new Rule(operation.getId(), exp, permission, new Date());
			ruleDAO.save(rule);
			return new RuleMessage(rule);
		}
		catch (IllegalArgumentException e)
		{
			return new ErrorMessage(304011, e);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(404011, e);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(503011, e);
		}
	}

	@Override
	public Message changeRule(int id, Integer operationId, String exp,
			Integer permission)
	{
		try
		{
			Rule rule = ruleDAO.getById(id);
			if(rule == null)
				return new ErrorMessage(304031);
			rule = new Rule(rule);
			if(operationId != null)
			{
				Operation operation = operationDAO.getById(operationId);
				if(operation == null)
					return new ErrorMessage(304031);
				rule.setOperationId(operationId);
			}
			if(exp != null)
			{
				checkExp(exp);
				rule.setExp(exp);
			}
			if(permission != null)
				rule.setPermission(permission);
			ruleDAO.update(rule);
			return new RuleMessage(rule);
		}
		catch (IllegalArgumentException e)
		{
			return new ErrorMessage(304031, e);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(404031, e);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(503031, e);
		}
	}
	
	@Override
	public Message changeRule(int id, String systemName, String moduleName,
			String operationName, String exp, Integer permission)
	{
		try
		{
			Rule rule = ruleDAO.getById(id);
			if(rule == null)
				return new ErrorMessage(304041);
			rule = new Rule(rule);
			if(systemName != null && moduleName != null && operationName != null)
			{
				System system = systemDAO.getByName(systemName);
				if(system == null)
					return new ErrorMessage(304042);
				Module module = moduleDAO.getByName(system.getId(), moduleName);
				if(module == null)
					return new ErrorMessage(304043); 
				Operation operation = operationDAO.getByName(module.getId(), operationName);
				if(operation == null)
					return new ErrorMessage(304044);
				rule.setOperationId(operation.getId());
			}
			if(exp != null)
			{
				checkExp(exp);
				rule.setExp(exp);
			}
			if(permission != null)
				rule.setPermission(permission);
			ruleDAO.update(rule);
			return new RuleMessage(rule);
		}
		catch (IllegalArgumentException e)
		{
			return new ErrorMessage(304041, e);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(404041, e);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(503031, e);
		}
	}

	@Override
	public Message deleteRule(int id)
	{
		try
		{
			Rule rule = ruleDAO.getById(id);
			if(rule == null)
				return new ErrorMessage(304051);
			ruleDAO.delete(id);
			return new RuleMessage(rule);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(404051, e);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(503051, e);
		}
	}

	private void checkExp(String exp)
	{
		String[] subexps = exp.split(",");
		int idNum = 0;
		for(String e : subexps)
		{
			switch (e)
			{
			case "&":
			case "|":
				--idNum;
				break;
			case "!":
				break;
			default:
				int id;
				try
				{
					id = Integer.parseInt(e);
				}
				catch (NumberFormatException e1)
				{
					throw new IllegalArgumentException("表达式错误", e1);
				}
				if(labelDAO.getById(id) == null)
					throw new IllegalArgumentException("id: " + id + "不存在");
				++idNum;
			}
		}
		--idNum;
		if(idNum != 0)
			throw new IllegalArgumentException("表达式错误");
	}

	public void setLabelDAO(LabelDAO labelDAO)
	{
		this.labelDAO = labelDAO;
	}

	public void setOperationDAO(OperationDAO operationDAO)
	{
		this.operationDAO = operationDAO;
	}

	public void setRuleDAO(RuleDAO ruleDAO)
	{
		this.ruleDAO = ruleDAO;
	}

	public void setSystemDAO(SystemDAO systemDAO)
	{
		this.systemDAO = systemDAO;
	}

	public void setModuleDAO(ModuleDAO moduleDAO)
	{
		this.moduleDAO = moduleDAO;
	}

}
