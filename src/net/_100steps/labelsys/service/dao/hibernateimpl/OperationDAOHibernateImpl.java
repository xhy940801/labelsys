package net._100steps.labelsys.service.dao.hibernateimpl;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.OperationDAO;
import net._100steps.labelsys.service.model.Entity;
import net._100steps.labelsys.service.model.Module;
import net._100steps.labelsys.service.model.Operation;



public class OperationDAOHibernateImpl implements OperationDAO{
	private SessionFactory sessionFactory;
	@Override
	public void save(Operation operation) {
		// TODO Auto-generated method stub
		try 
		{
			sessionFactory.getCurrentSession().save(operation);
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}

	@Override
	public void update(Operation operation) {
		// TODO Auto-generated method stub
		try 
		{
			sessionFactory.getCurrentSession().update(operation);
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}

	@Override
	public Operation getById(int id) {
		// TODO Auto-generated method stub
		try
		{
			return (Operation) sessionFactory.getCurrentSession().get(Operation.class, id);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@Override
	public Operation getByName(String name) {
		// TODO Auto-generated method stub
		try 
		{
			return (Operation) sessionFactory.getCurrentSession().createQuery("from Operation as o where o.name = ?").setString(0, name).uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
