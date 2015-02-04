package net._100steps.labelsys.service.manager.defaultimpl;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.LabelDAO;
import net._100steps.labelsys.service.dao.ModuleDAO;
import net._100steps.labelsys.service.dao.OperationDAO;
import net._100steps.labelsys.service.dao.SystemDAO;
import net._100steps.labelsys.service.manager.OperationManager;
import net._100steps.labelsys.service.message.Message;
import net._100steps.labelsys.service.message.impl.ErrorMessage;
import net._100steps.labelsys.service.message.impl.GeneralMessage;
import net._100steps.labelsys.service.message.impl.OperationMessage;
import net._100steps.labelsys.service.model.Module;
import net._100steps.labelsys.service.model.Operation;
import net._100steps.labelsys.service.model.System;

public class OperationManagerDefaultImpl implements OperationManager
{
	private SystemDAO systemDAO;
	private ModuleDAO moduleDAO;
	private OperationDAO operationDAO;
	private LabelDAO labelDAO;

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

}
