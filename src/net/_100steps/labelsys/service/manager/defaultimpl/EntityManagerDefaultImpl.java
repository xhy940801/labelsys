package net._100steps.labelsys.service.manager.defaultimpl;

import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.EntityDAO;
import net._100steps.labelsys.service.dao.LabelDAO;
import net._100steps.labelsys.service.dao.ModuleDAO;
import net._100steps.labelsys.service.dao.SystemDAO;
import net._100steps.labelsys.service.manager.EntityManager;
import net._100steps.labelsys.service.message.Message;
import net._100steps.labelsys.service.message.impl.EntityMessage;
import net._100steps.labelsys.service.message.impl.ErrorMessage;
import net._100steps.labelsys.service.model.Entity;
import net._100steps.labelsys.service.model.Label;
import net._100steps.labelsys.service.model.Module;
import net._100steps.labelsys.service.model.System;

public class EntityManagerDefaultImpl implements EntityManager{
	private EntityDAO entityDAO;
	private ModuleDAO moduleDAO;
	private LabelDAO labelDAO;
	private SystemDAO systemDAO;
	@Override
	@Transactional
	public Message createEntity(int moduleId, int foreignKey) {
		// TODO Auto-generated method stub
		try 
		{
			if(moduleDAO.getById(moduleId)==null)
				return new ErrorMessage(302011);
			Entity entity = entityDAO.getByForeignKey(moduleId, foreignKey);
			if(entity!=null)
				return new ErrorMessage(302012);
			Entity entitySave = new Entity(moduleId, foreignKey);
			entityDAO.save(entitySave);
			return new EntityMessage(entitySave);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(402010,e);
		}
		
	}
	@Override
	@Transactional
	public Message createEntity(String systenName,String moduleName,int foreignKey) {
		try {
			System system = systemDAO.getByName(systenName);
			if (system==null)
				return new ErrorMessage(302021);
			Module module = moduleDAO.getByName(system.getId(), moduleName);
			if (module==null)
				return new ErrorMessage(302022);
			if (entityDAO.getByForeignKey(module.getId(), foreignKey)!=null)
				return new ErrorMessage(302023);
			Entity entity = new Entity(module.getId(), foreignKey);
			entityDAO.save(entity);
			return new EntityMessage(entity);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(402020,e);
		}
	}
	@Override
	@Transactional
	public Message deleteEntity(int entityId) {
		Entity entity;
		try 
		{
			entity = entityDAO.getById(entityId);
			if (entity==null)
				return new ErrorMessage(302030);
			entityDAO.delete(entityId);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(402030,e);
		}
		return new EntityMessage(entity);
	}
	@Override
	@Transactional
	public Message deleteEntity(String systemName,String moduleName,int foreignKey) {
		try 
		{
			System system = systemDAO.getByName(systemName);
			if(system==null)
				return new ErrorMessage(302041);
			Module module = moduleDAO.getByName(system.getId(), moduleName);
			if(module==null)
				return new ErrorMessage(302042);
			Entity entity = entityDAO.getByForeignKey(module.getId(), foreignKey);
			if(entity==null)
				return new ErrorMessage(302043);
			entityDAO.delete(entity.getId());
			return new EntityMessage(entity);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(402040,e);
		}
	}
	@Override
	@Transactional
	public Message findEntitiesByLabels(List<Integer>labelsId) {
		List<Entity> entities;
		try 
		{
			entities = entityDAO.findEntitiesByLabels(labelsId);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(402030,e);
		}
		return new EntityMessage(entities);
	}
	@Override
	@Transactional
	public Message setLabels(int entityId, List<Integer> labelsId) {
		// TODO Auto-generated method stub
		Entity entity;
		try 
		{
			entity = entityDAO.getById(entityId);
			if (entity==null)
					return new ErrorMessage(302050);
			Iterator<Integer> iterable = labelsId.iterator();
			while (iterable.hasNext()) {
				if(labelDAO.getById(iterable.next())==null)
					return new ErrorMessage(302050);
			}
			entityDAO.setLabel(entityId, labelsId);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(402050,e);
		}
		return new EntityMessage(entity);
	}
	@Override
	@Transactional
	public Message setLabels(String systemName,String moduleName,int foreignKey,List<Integer>labelsId) {
		try 
		{
			System system = systemDAO.getByName(systemName);
			if(system==null)
				return new ErrorMessage(302061);
			Module module = moduleDAO.getByName(system.getId(), moduleName);
			if(module==null)
				return new ErrorMessage(302062);
			Entity entity = entityDAO.getByForeignKey(module.getId(), foreignKey);
			if(entity==null)
				return new ErrorMessage(302063);
			entityDAO.setLabel(entity.getId(), labelsId);
			return new EntityMessage(entity);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(402060,e);
		}
	}
	@Override
	@Transactional
	public Message addLabel(int entityId, int labelId) {
		
		try 
		{
			Entity entity = entityDAO.getById(entityId);
			if (labelDAO.getById(labelId)==null) 
				return new ErrorMessage(302070);
			if (entity==null)
				return new ErrorMessage(302070);
			entityDAO.addLabel(entityId, labelId);
			return new EntityMessage(entity);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(402070,e);
		}
		
	}
	@Override
	@Transactional
	public Message addLabel(String systemName,String moduleName,int foreignKey,String labelName) {
		try 
		{
			System system = systemDAO.getByName(systemName);
			if(system==null)
				return new ErrorMessage(302081);
			Module module = moduleDAO.getByName(system.getId(), moduleName);
			if(module==null)
				return new ErrorMessage(302082);
			Entity entity = entityDAO.getByForeignKey(module.getId(), foreignKey);
			if(entity==null)
				return new ErrorMessage(302083);
			Label label = labelDAO.getByName(module.getId(), labelName);
			if(label==null)
				return new ErrorMessage(302084);
			if(entityDAO.hasLabel(entity.getId(), label.getId()))
				return new ErrorMessage(302085);
			entityDAO.addLabel(entity.getId(),label.getId());
			return new EntityMessage(entity);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(402080,e);
		}
	}
	public void setEntityDAO(EntityDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
	
	public void setModuleDAO(ModuleDAO moduleDAO) {
		this.moduleDAO = moduleDAO;
	}
	
	public void setLabelDAO(LabelDAO labelDAO) {
		this.labelDAO = labelDAO;
	}
	public void setSystemDAO(SystemDAO systemDAO) {
		this.systemDAO = systemDAO;
	}
	

}
