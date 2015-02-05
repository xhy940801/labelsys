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
import net._100steps.labelsys.service.manager.ModuleManager;
import net._100steps.labelsys.service.message.Message;
import net._100steps.labelsys.service.message.impl.ErrorMessage;
import net._100steps.labelsys.service.message.impl.ModuleMessage;
import net._100steps.labelsys.service.model.Module;
import net._100steps.labelsys.service.model.System;

public class ModuleManagerDefaultImpl implements ModuleManager{
	private ModuleDAO moduleDAO;
	private SystemDAO systemDAO;
	private OperationDAO operationDAO;
	private RuleDAO ruleDAO;
	private EntityDAO entityDAO;
	private LabelDAO labelDAO;
	@Override
	@Transactional
	public Message createModule(int systemId, String moduleName) {
		// TODO Auto-generated method stub
		try 
		{
			System system = systemDAO.getById(systemId);
			if(system==null)
				return new ErrorMessage(306011);
			if(moduleDAO.getByName(system.getId(), moduleName)!=null)
				return new ErrorMessage(306012);
			Module module = new Module();
			module.setName(moduleName);
			module.setSystemId(system.getId());
			moduleDAO.save(module);
			return new ModuleMessage(module);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(406010,e);
		}
	}

	@Override
	@Transactional
	public Message createModule(String systemName, String moduleName) {
		// TODO Auto-generated method stub
		try 
		{
			System system = systemDAO.getByName(systemName);
			if(system==null)
				return new ErrorMessage(306021);
			if(moduleDAO.getByName(system.getId(), moduleName)!=null)
				return new ErrorMessage(306022);
			Module module = new Module();
			module.setName(moduleName);
			module.setSystemId(system.getId());
			moduleDAO.save(module);
			return new ModuleMessage(module);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(406020,e);
		}
	}

	@Override
	@Transactional
	public Message changeModule(int moduleId, String newModuleName) {
		// TODO Auto-generated method stub
		try 
		{
			Module module = moduleDAO.getById(moduleId);
			if(module == null)
				return new ErrorMessage(306031);
			module.setName(newModuleName);
			moduleDAO.update(module);
			return new ModuleMessage(module);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(406030,e);
		}
	}

	@Override
	@Transactional
	public Message changeModule(String systemName, String oldModuleName,
			String newModuleName) {
		// TODO Auto-generated method stub
		try 
		{
			System system = systemDAO.getByName(systemName);
			if(system==null)
				return new ErrorMessage(306041);
			Module module = moduleDAO.getByName(system.getId(), oldModuleName);
			if(module==null)
				return new ErrorMessage(306042);
			module.setName(newModuleName);
			moduleDAO.update(module);
			return new ModuleMessage(module);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(406040,e);
		}
	}

	@Override
	@Transactional
	public Message deleteModule(int moduleId) {
		// TODO Auto-generated method stub
		try 
		{
			Module module = moduleDAO.getById(moduleId);
			if(module==null)
				return new ErrorMessage(306051);
			List<Integer>modulesId = new ArrayList<Integer>();
			modulesId.add(module.getId());
			List<Integer>operationsId = operationDAO.findOperationsIdByModules(modulesId);
			if(operationsId.size()!=0)
			{
				List<Integer>rulesId = ruleDAO.findRulesIdByOperations(operationsId);
				if(rulesId.size()!=0)
					ruleDAO.delete(rulesId);
			}
			List<Integer> entitiesId = entityDAO.findEntitiesIdByModules(modulesId);
			if(entitiesId.size()!=0)
				entityDAO.delete(entitiesId);
			List<Integer> labelsId = labelDAO.findLabelsIdByModules(modulesId);
			if(labelsId.size()!=0)
				labelDAO.delete(labelsId);
			moduleDAO.delete(module.getId());
			return new ModuleMessage(module);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(406050,e);
		}
	}

	@Override
	@Transactional
	public Message deleteModule(String systemName, String moduleName) {
		// TODO Auto-generated method stub
		try 
		{
			System system = systemDAO.getByName(systemName);
			if(system==null)
				return new ErrorMessage(306061);
			Module module = moduleDAO.getByName(system.getId(), moduleName);
			if(module==null)
				return new ErrorMessage(306062);
			List<Integer>modulesId = new ArrayList<Integer>();
			modulesId.add(module.getId());
			List<Integer>operationsId = operationDAO.findOperationsIdByModules(modulesId);
			if(operationsId.size()!=0)
			{
				List<Integer>rulesId = ruleDAO.findRulesIdByOperations(operationsId);
				if(rulesId.size()!=0)
					ruleDAO.delete(rulesId);
			}
			List<Integer> entitiesId = entityDAO.findEntitiesIdByModules(modulesId);
			if(entitiesId.size()!=0)
				entityDAO.delete(entitiesId);
			List<Integer> labelsId = labelDAO.findLabelsIdByModules(modulesId);
			if(labelsId.size()!=0)
				labelDAO.delete(labelsId);
			moduleDAO.delete(module.getId());
			return new ModuleMessage(module);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(406060,e);
		}
	}
	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}
	public void setSystemDAO(SystemDAO systemDAO) {
		this.systemDAO = systemDAO;
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
