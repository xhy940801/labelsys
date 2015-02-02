package net._100steps.labelsys.service.dao.hibernateimpl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.ModuleDAO;
import net._100steps.labelsys.service.model.Module;

public class ModuleDAOHibernateImpl implements ModuleDAO{
	private SessionFactory sessionFactory;
	@Override
	public void save(Module module) {
		// TODO Auto-generated method stub
		try 
		{
			sessionFactory.getCurrentSession().save(module);
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}

	@Override
	public void update(Module module) {
		// TODO Auto-generated method stub
		try 
		{
			sessionFactory.getCurrentSession().update(module);
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}

	@Override
	public Module getById(int id) {
		// TODO Auto-generated method stub
		try 
		{
			return (Module) sessionFactory.getCurrentSession().get(Module.class, id);
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Module> getAll() {
		// TODO Auto-generated method stub
		try 
		{
			return (List<Module>) sessionFactory.getCurrentSession().createQuery("from Module as m where 1").list();
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}

	@Override
	public Module geyByName(String name) {
		// TODO Auto-generated method stub
		try 
		{
			return (Module) sessionFactory.getCurrentSession().createQuery("from Module as m where m.name = ?").setString(0, name).uniqueResult();
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory  = sessionFactory;
	}

}
