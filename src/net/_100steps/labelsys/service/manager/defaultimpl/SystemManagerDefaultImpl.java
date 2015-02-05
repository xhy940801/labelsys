package net._100steps.labelsys.service.manager.defaultimpl;



import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.EntityDAO;
import net._100steps.labelsys.service.dao.LabelDAO;
import net._100steps.labelsys.service.dao.ModuleDAO;
import net._100steps.labelsys.service.dao.OperationDAO;
import net._100steps.labelsys.service.dao.RuleDAO;
import net._100steps.labelsys.service.dao.SystemDAO;
import net._100steps.labelsys.service.manager.SystemManager;
import net._100steps.labelsys.service.message.Message;
import net._100steps.labelsys.service.message.impl.ErrorMessage;
import net._100steps.labelsys.service.message.impl.SystemMessage;
import net._100steps.labelsys.service.model.System;

public class SystemManagerDefaultImpl implements SystemManager{
	private SystemDAO systemDAO;
	private ModuleDAO moduleDAO;
	private OperationDAO operationDAO;
	private RuleDAO ruleDAO;
	private EntityDAO entityDAO;
	private LabelDAO labelDAO;
	@Override
	@Transactional
	public Message createSystem(String systemName) {
		// TODO Auto-generated method stub
		try
		{
			System system = systemDAO.getByName(systemName);
			if(system!=null)
				return new ErrorMessage(305010);
			systemDAO.save(new System(systemName));
			return new SystemMessage(system);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(405010,e);
		}
	}

	@Override
	@Transactional
	public Message changeSystem(int systemId, String newSystemName) {
		// TODO Auto-generated method stub
		try 
		{
			System system = systemDAO.getById(systemId);
			if(system==null)
				return new ErrorMessage(305020);
			system.setName(newSystemName);
			systemDAO.update(system);
			return new SystemMessage(system);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(405020,e);
		}
	}

	@Override
	@Transactional
	public Message changeSystem(String oldSystemName, String newSystemName) {
		// TODO Auto-generated method stub
		try 
		{
			System system = systemDAO.getByName(oldSystemName);
			if(system==null)
				return new ErrorMessage(305030);
			system.setName(newSystemName);
			systemDAO.update(system);
			return new SystemMessage(system);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(405030,e);
		}
	}

	@Override
	@Transactional
	public Message deleteSystem(int systemId) {
		// TODO Auto-generated method stub
		try 
		{
			System system = systemDAO.getById(systemId);
			if(system==null)
				return new ErrorMessage(305040);
			List<Integer> systemsId = new ArrayList<Integer>();
			systemsId.add(system.getId());
			List<Integer> modulesId = moduleDAO.findModulesIdBySystems(systemsId);
			if(modulesId.size()!=0)
			{
				List<Integer> operationsId = operationDAO.findOperationsIdByModules(modulesId);
				List<Integer> entitiesId = entityDAO.findEntitiesIdByModules(modulesId);
				List<Integer> labelsId = labelDAO.findLabelsIdByModules(modulesId);
				if(operationsId.size()!=0)
				{
					List<Integer> rulesId = ruleDAO.findRulesIdByOperations(operationsId);
					if(rulesId.size()!=0)
					{
						ruleDAO.delete(rulesId);
					}
					operationDAO.delete(operationsId);
				}
				if(entitiesId.size()!=0)
					entityDAO.delete(entitiesId);
				if(labelsId.size()!=0)
					labelDAO.delete(labelsId);
				moduleDAO.delete(modulesId);
			}
			systemDAO.delete(system.getId());
			return new SystemMessage(system);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(405040,e);
		}
	}

	@Override
	@Transactional
	public Message deleteSystem(String systemName) {
		// TODO Auto-generated method stub
		try 
		{
			System system = systemDAO.getByName(systemName);
			if(system==null)
				return new ErrorMessage(305050);
			List<Integer> systemsId = new ArrayList<Integer>();
			systemsId.add(system.getId());
			List<Integer> modulesId = moduleDAO.findModulesIdBySystems(systemsId);
			if(modulesId.size()!=0)
			{
				List<Integer> operationsId = operationDAO.findOperationsIdByModules(modulesId);
				List<Integer> entitiesId = entityDAO.findEntitiesIdByModules(modulesId);
				List<Integer> labelsId = labelDAO.findLabelsIdByModules(modulesId);
				if(operationsId.size()!=0)
				{
					List<Integer> rulesId = ruleDAO.findRulesIdByOperations(operationsId);
					if(rulesId.size()!=0)
					{
						ruleDAO.delete(rulesId);
					}
					operationDAO.delete(operationsId);
				}
				if(entitiesId.size()!=0)
					entityDAO.delete(entitiesId);
				if(labelsId.size()!=0)
					labelDAO.delete(labelsId);
				moduleDAO.delete(modulesId);
			}
			systemDAO.delete(system.getId());
			return new SystemMessage(system);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(405050,e);
		}
	}
	public void setSystemDAO(SystemDAO systemDAO) {
		this.systemDAO = systemDAO;
	}
	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}
	public void setOperationDAO(OperationDAO operationDAO) {
		this.operationDAO = operationDAO;
	}
	public void setRuleDAO(RuleDAO ruleDAO) {
		this.ruleDAO = ruleDAO;
	}
	public void setEntityDAO(EntityDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
	public void setLabelDAO(LabelDAO labelDAO) {
		this.labelDAO = labelDAO;
	}
}
