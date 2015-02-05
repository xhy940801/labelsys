package net._100steps.labelsys.service.manager.defaultimpl;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Stack;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.EntityDAO;
import net._100steps.labelsys.service.dao.LabelDAO;
import net._100steps.labelsys.service.dao.ModuleDAO;
import net._100steps.labelsys.service.dao.OperationDAO;
import net._100steps.labelsys.service.dao.RuleDAO;
import net._100steps.labelsys.service.dao.SystemDAO;
import net._100steps.labelsys.service.entity.EntityInfo;
import net._100steps.labelsys.service.entity.LabelInfo;
import net._100steps.labelsys.service.entity.OperationInfo;
import net._100steps.labelsys.service.manager.OperationManager;
import net._100steps.labelsys.service.message.Message;
import net._100steps.labelsys.service.message.impl.ErrorMessage;
import net._100steps.labelsys.service.message.impl.GeneralMessage;
import net._100steps.labelsys.service.message.impl.OperationMessage;
import net._100steps.labelsys.service.message.impl.RuleMessage;
import net._100steps.labelsys.service.model.Entity;
import net._100steps.labelsys.service.model.Label;
import net._100steps.labelsys.service.model.Module;
import net._100steps.labelsys.service.model.Operation;
import net._100steps.labelsys.service.model.Rule;
import net._100steps.labelsys.service.model.System;

public class OperationManagerDefaultImpl implements OperationManager
{
	private SystemDAO systemDAO;
	private ModuleDAO moduleDAO;
	private OperationDAO operationDAO;
	private LabelDAO labelDAO;
	private RuleDAO ruleDAO;
	private EntityDAO entityDAO;

	@Override
	public Message createOperation(int moduleId, String name)
	{
		try
		{
			Module module = moduleDAO.getById(moduleId);
			if(module == null)
				return new ErrorMessage(303011);
			Operation operation = new Operation(moduleId, name);
			operationDAO.save(operation);
			return new OperationMessage(operation);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(403011);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(503011);
		}
	}

	@Override
	public Message createOperation(String systemName, String moduleName,
			String operationName)
	{
		try
		{
			System system = systemDAO.getByName(systemName);
			if(system == null)
				return new ErrorMessage(303021);
			Module module = moduleDAO.getByName(system.getId(), moduleName);
			if(module == null)
				return new ErrorMessage(303022);
			Operation operation = new Operation(module.getId(), operationName);
			operationDAO.save(operation);
			return new OperationMessage(operation);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(403021);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(503021);
		}
	}

	@Override
	public Message changeOperation(int operationId, String name)
	{
		try
		{
			Operation operation = operationDAO.getById(operationId);
			if(operation == null)
				return new ErrorMessage(303041);
			operation = new Operation(operation);
			operation.setName(name);
			operationDAO.update(operation);
			return new OperationMessage(operation);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(403031);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(503031);
		}
	}

	@Override
	public Message changeOperation(String systemName, String moduleName,
			String oldName, String newName)
	{
		try
		{
			System system = systemDAO.getByName(systemName);
			if(system == null)
				return new ErrorMessage(303041);
			Module module = moduleDAO.getByName(system.getId(), moduleName);
			if(module == null)
				return new ErrorMessage(303042);
			Operation operation = operationDAO.getByName(module.getId(), oldName);
			if(operation == null)
				return new ErrorMessage(303041);
			operation = new Operation(operation);
			operation.setName(newName);
			operationDAO.update(operation);
			return new OperationMessage(operation);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(403041);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(503041);
		}
	}

	@Override
	public Message deleteOperation(int operationId)
	{
		try
		{
			operationDAO.delete(operationId);
			return new GeneralMessage(0, null);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(403051);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(503051);
		}
	}

	@Override
	public Message deleteOperation(String systemName, String moduleName,
			String operationName)
	{
		try
		{
			System system = systemDAO.getByName(systemName);
			if(system == null)
				return new ErrorMessage(303061);
			Module module = moduleDAO.getByName(system.getId(), moduleName);
			if(module == null)
				return new ErrorMessage(303062);
			Operation operation = operationDAO.getByName(module.getId(), operationName);
			if(operation == null)
				return new ErrorMessage(303063);
			operationDAO.delete(operation.getId());
			return new OperationMessage(operation);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(403061);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(503061);
		}
	}
	
	private class OperationNotFoundException extends RuntimeException
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = -4018573355498335166L;
	}
	
	private class EntityNotFoundException extends RuntimeException
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = -8318400485632084322L;
	}
	
	private class LabelNotFoundException extends RuntimeException
	{

		/**
		 * 
		 */
		private static final long serialVersionUID = -304249833948369225L;
	}
	
	private class IGNException extends RuntimeException
	{
		/**
		 * 
		 */
		private static final long serialVersionUID = 8674305487558450031L;

		IGNException(String msg)
		{
			super(msg);
		}
	}
	
	@Override
	public Message checkPermission(OperationInfo operationInfo,
			Iterable<LabelInfo> labelInfos)
	{
		try
		{
			Iterable<Rule> rules = this.getOperationRules(operationInfo, RuleDAO.Order.CREATEDDESC);
			Collection<Integer> labels = this.getLabels(labelInfos);
			for(Rule rule : rules)
				if(this.check(rule.getExp(), labels))
					return new RuleMessage(rule);
			return new GeneralMessage(0, null);
		}
		catch (LabelNotFoundException e)
		{
			return new ErrorMessage(303071, e);
		}
		catch (OperationNotFoundException e)
		{
			return new ErrorMessage(303072, e);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(403071);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(503071);
		}
	}

	@Override
	public Message checkPermission(OperationInfo operationInfo,
			EntityInfo entityInfo, Iterable<LabelInfo> labelInfos)
	{
		try
		{
			Iterable<Rule> rules = this.getOperationRules(operationInfo, RuleDAO.Order.CREATEDDESC);
			Set<Integer> labels = this.getEntityLabels(entityInfo);
			this.combine(labelInfos, labels);
			for(Rule rule : rules)
				if(this.check(rule.getExp(), labels))
					return new RuleMessage(rule);
			return new GeneralMessage(0, null);
		}
		catch (LabelNotFoundException e)
		{
			return new ErrorMessage(303081, e);
		}
		catch (OperationNotFoundException e)
		{
			return new ErrorMessage(303082, e);
		}
		catch (EntityNotFoundException e)
		{
			return new ErrorMessage(303083, e);
		}
		catch (IGNException e)
		{
			return new ErrorMessage(303084, e);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(403081);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(503081);
		}
	}
	
	public void setSystemDAO(SystemDAO systemDAO)
	{
		this.systemDAO = systemDAO;
	}

	public void setModuleDAO(ModuleDAO moduleDAO)
	{
		this.moduleDAO = moduleDAO;
	}

	public void setOperationDAO(OperationDAO operationDAO)
	{
		this.operationDAO = operationDAO;
	}

	public void setLabelDAO(LabelDAO labelDAO)
	{
		this.labelDAO = labelDAO;
	}
	
	public void setRuleDAO(RuleDAO ruleDAO)
	{
		this.ruleDAO = ruleDAO;
	}

	public void setEntityDAO(EntityDAO entityDAO)
	{
		this.entityDAO = entityDAO;
	}

	private Iterable<Rule> getOperationRules(OperationInfo info, RuleDAO.Order order)
	{
		Operation operation;
		if(info.getId() == null)
		{
			if(info.getSystemName() == null || info.getModuleName() == null || info.getOperationName() == null)
				throw new OperationNotFoundException();
			System system = systemDAO.getByName(info.getSystemName());
			if(system == null)
				throw new OperationNotFoundException();
			Module module = moduleDAO.getByName(system.getId(), info.getModuleName());
			if(module == null)
				throw new OperationNotFoundException();
			operation = operationDAO.getByName(module.getId(), info.getOperationName());
		}
		else
			operation = operationDAO.getById(info.getId());
		if(operation == null)
			throw new OperationNotFoundException();
		return ruleDAO.getByOperationId(operation.getId(), order);
	}
	
	private Collection<Integer> getLabels(Iterable<LabelInfo> labels)
	{
		Set<Integer> labelSet = new HashSet<Integer>();
		for(LabelInfo info : labels)
		{
			Label label;
			if(info.getId() == null)
			{
				if(info.getLabelName() == null || info.getModuleName() == null || info.getSystemName() == null)
					throw new LabelNotFoundException();
				System system = systemDAO.getByName(info.getSystemName());
				if(system == null)
					throw new LabelNotFoundException();
				Module module = moduleDAO.getByName(system.getId(), info.getModuleName());
				if(module == null)
					throw new LabelNotFoundException();
				label = labelDAO.getByName(module.getId(), info.getLabelName());
			}
			else
				label = labelDAO.getById(info.getId());
			if(label == null)
				throw new LabelNotFoundException();
			labelSet.add(label.getId());
		}
		return labelSet;
	}
	
	private Set<Integer> getEntityLabels(EntityInfo info)
	{
		Entity entity;
		if(info.getId() == null)
		{
			if(info.getForeignKey() == null || info.getModuleName() == null || info.getSystemName() == null)
				throw new EntityNotFoundException();
			System system = systemDAO.getByName(info.getSystemName());
			if(system == null)
				throw new EntityNotFoundException();
			Module module = moduleDAO.getByName(system.getId(), info.getModuleName());
			if(module == null)
				throw new EntityNotFoundException();
			entity = entityDAO.getByForeignKey(module.getId(), info.getForeignKey());
		}
		else
			entity = entityDAO.getById(info.getId());
		if(entity == null)
			throw new EntityNotFoundException();
		List<Label> labelList = labelDAO.getByEntityId(entity.getId());
		Set<Integer> intSet = new HashSet<Integer>();
		labelList.forEach((value)->intSet.add(value.getId()));
		return intSet;
	}
	
	private void combine(Iterable<LabelInfo> labelInfos, Set<Integer> labels)
	{
		for(LabelInfo info : labelInfos)
		{
			Label label;
			switch (info.getUsed())
			{
			case ADD:
				label = getLabel(info);
				if(labels.add(label.getId()) == false)
					throw new IGNException("add label:{" + label + "} fail");
				break;
			case FADD:
				label = getLabel(info);
				labels.add(label.getId());
				break;
			case FREMOVE:
				label = getLabel(info);
				labels.remove(label.getId());
				break;
			case REMOVE:
				label = getLabel(info);
				if(labels.remove(label.getId()) == false)
					throw new IGNException("remove label:{" + label + "} fail");
				break;
			case TAKEBACK:
				label = getLabel(info);
				if(labels.contains(label.getId()))
					labels.remove(label.getId());
				else
					labels.add(label.getId());
				break;
			}
		}
	}
	
	private Label getLabel(LabelInfo info)
	{
		Label label;
		if(info.getId() == null)
		{
			if(info.getLabelName() == null || info.getModuleName() == null || info.getSystemName() == null)
				throw new LabelNotFoundException();
			System system = systemDAO.getByName(info.getSystemName());
			if(system == null)
				throw new LabelNotFoundException();
			Module module = moduleDAO.getByName(system.getId(), info.getModuleName());
			if(module == null)
				throw new LabelNotFoundException();
			label = labelDAO.getByName(module.getId(), info.getLabelName());
		}
		else
			label = labelDAO.getById(info.getId());
		if(label == null)
			throw new LabelNotFoundException();
		return label;
	}
	
	private boolean check(String exp, Collection<Integer> labels)
	{
		String[] subexps = exp.split(",");
		Stack<Boolean> stack = new Stack<>();
		for(String se : subexps)
		{
			switch (se)
			{
			case "!":
				stack.push(!stack.pop());
				break;
			case "&":
				stack.push(stack.pop() && stack.pop());
				break;
			case "|":
				stack.push(stack.pop() || stack.pop());
				break;
			default:
				Integer res = Integer.valueOf(se);
				stack.push(labels.contains(res));
				break;
			}
		}
		return stack.pop();
	}

}
