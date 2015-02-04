package net._100steps.labelsys.service.manager.defaultimpl;

import java.util.List;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.EntityDAO;
import net._100steps.labelsys.service.manager.EntityManager;
import net._100steps.labelsys.service.message.Message;
import net._100steps.labelsys.service.message.impl.EntityMessage;
import net._100steps.labelsys.service.message.impl.ErrorMessage;
import net._100steps.labelsys.service.model.Entity;

public class EntityManagerDefaultImpl implements EntityManager{
	private EntityDAO entityDAO;
	@Override
	public Message createEntity(int moduleId, int foreignKey) {
		// TODO Auto-generated method stub
		Entity entity = new Entity();
		try 
		{
			entity.setModuleId(moduleId);
			entity.setForeignKey(foreignKey);
			entityDAO.save(entity);
			
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(402010,e);
		}
		return new EntityMessage(entity);
	}
	//Message deleteEntity(int entityId);
	//Message findEntityByLabels(List<Integer>labelsId);
	@Override
	public Message setLabels(int entityId, List<Integer> labelsId) {
		// TODO Auto-generated method stub
		Entity entity = entityDAO.getById(entityId);
		if (entity==null)
			return new ErrorMessage(502040);
		try 
		{
			entityDAO.setLabel(entityId, labelsId);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(402040,e);
		}
		return new EntityMessage(entity);
	}

	@Override
	public Message addLabel(int entityId, int labelId) {
		Entity entity = entityDAO.getById(entityId);
		if (entity==null)
			return new ErrorMessage(502050);
		try 
		{
			entityDAO.addLabel(entityId, labelId);
		} catch (DAOException e) {
			// TODO: handle exception
			return new ErrorMessage(402050,e);
		}
		return new EntityMessage(entity);
	}
	public void setEntityDAO(EntityDAO entityDAO) {
		this.entityDAO = entityDAO;
	}
	

}
