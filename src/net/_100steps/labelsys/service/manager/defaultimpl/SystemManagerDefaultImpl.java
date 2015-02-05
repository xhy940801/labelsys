package net._100steps.labelsys.service.manager.defaultimpl;



import org.springframework.transaction.annotation.Transactional;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.SystemDAO;
import net._100steps.labelsys.service.manager.SystemManager;
import net._100steps.labelsys.service.message.Message;
import net._100steps.labelsys.service.message.impl.ErrorMessage;
import net._100steps.labelsys.service.message.impl.SystemMessage;
import net._100steps.labelsys.service.model.System;

public class SystemManagerDefaultImpl implements SystemManager{
	private SystemDAO systemDAO;
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
}
