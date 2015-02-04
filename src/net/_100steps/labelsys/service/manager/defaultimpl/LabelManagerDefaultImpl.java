package net._100steps.labelsys.service.manager.defaultimpl;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.LabelDAO;
import net._100steps.labelsys.service.dao.ModuleDAO;
import net._100steps.labelsys.service.dao.SystemDAO;
import net._100steps.labelsys.service.manager.LabelManager;
import net._100steps.labelsys.service.message.Message;
import net._100steps.labelsys.service.message.impl.ErrorMessage;
import net._100steps.labelsys.service.message.impl.GeneralMessage;
import net._100steps.labelsys.service.message.impl.LabelMessage;
import net._100steps.labelsys.service.model.Label;
import net._100steps.labelsys.service.model.Module;
import net._100steps.labelsys.service.model.System;

public class LabelManagerDefaultImpl implements LabelManager
{
	private LabelDAO labelDAO;
	private ModuleDAO moduleDAO;
	private SystemDAO systemDAO;

	@Override
	public Message createLabel(int moduleId, String name)
	{
		try
		{
			if(moduleDAO.getById(moduleId) == null)
				return new ErrorMessage(301011);
			Label label = new Label(moduleId, name);
			labelDAO.save(label);
			return new LabelMessage(label);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(401011, e);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(501011, e);
		}
	}

	@Override
	public Message createLabel(String systemName, String moduleName,
			String labelName)
	{
		try
		{
			System system = systemDAO.getByName(systemName);
			if(system == null)
				return new ErrorMessage(301021);
			Module module = moduleDAO.getByName(system.getId(), moduleName);
			if(module == null)
				return new ErrorMessage(301022);
			Label label = new Label(module.getId(), labelName);
			labelDAO.save(label);
			return new LabelMessage(label);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(401021, e);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(501021, e);
		}
	}

	@Override
	public Message changeLabel(int labelId, String newName)
	{
		try
		{
			Label label = labelDAO.getById(labelId);
			if(label == null)
				return new ErrorMessage(301031);
			label = new Label(label);
			label.setName(newName);
			labelDAO.update(label);
			return new LabelMessage(label);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(401031, e);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(501031, e);
		}
	}

	@Override
	public Message changeLabel(String systemName, String moduleName,
			String oldName, String newName)
	{
		try
		{
			System system = systemDAO.getByName(systemName);
			if(system == null)
				return new ErrorMessage(301041);
			Module module = moduleDAO.getByName(system.getId(), moduleName);
			if(module == null)
				return new ErrorMessage(301042);
			Label label = labelDAO.getByName(module.getId(), oldName);
			if(label == null)
				return new ErrorMessage(301043);
			label = new Label(label);
			label.setName(newName);
			labelDAO.update(label);
			return new LabelMessage(label);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(401041, e);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(501041, e);
		}
	}

	@Override
	public Message deleteLabel(int labelId)
	{
		try
		{
			labelDAO.delete(labelId);
			return new GeneralMessage(0, null);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(401051, e);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(501051, e);
		}
	}

	@Override
	public Message deleteLabel(String systemName, String moduleName, String labelName)
	{
		try
		{
			System system = systemDAO.getByName(systemName);
			if(system == null)
				return new ErrorMessage(301061);
			Module module = moduleDAO.getByName(system.getId(), moduleName);
			if(module == null)
				return new ErrorMessage(301062);
			Label label = labelDAO.getByName(module.getId(), labelName);
			if(label == null)
				return new ErrorMessage(301063);
			labelDAO.delete(label.getId());
			return new GeneralMessage(0, null);
		}
		catch (DAOException e)
		{
			return new ErrorMessage(401051, e);
		}
		catch (RuntimeException e)
		{
			return new ErrorMessage(501051, e);
		}
	}

	public void setLabelDAO(LabelDAO labelDAO)
	{
		this.labelDAO = labelDAO;
	}

	public void setModuleDAO(ModuleDAO moduleDAO)
	{
		this.moduleDAO = moduleDAO;
	}

	public void setSystemDAO(SystemDAO systemDAO)
	{
		this.systemDAO = systemDAO;
	}

}
