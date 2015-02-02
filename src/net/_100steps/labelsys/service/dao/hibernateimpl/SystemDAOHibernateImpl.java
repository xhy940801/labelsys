package net._100steps.labelsys.service.dao.hibernateimpl;

import java.lang.ref.SoftReference;
import java.util.List;

import javax.transaction.Transactional;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.SystemDAO;
import net._100steps.labelsys.service.model.System;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.xiao.util.quickcache.QuickCache;


public class SystemDAOHibernateImpl implements SystemDAO
{
	private SessionFactory sessionFactory;
	private QuickCache<Integer, System> cacheById;
	private QuickCache<String, System> cacheByName;
	private SoftReference<List<System>> refSystems;

	@Override
	@Transactional
	public void save(System system)
	{
		refSystems = null;
		try
		{
			sessionFactory.getCurrentSession().save(system);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void update(System system)
	{
		System cacheSys = cacheById.get(system.getId());
		if(cacheSys != null)
		{
			cacheById.remove(cacheSys.getId());
			cacheByName.remove(cacheSys.getName());
		}
		cacheSys = cacheByName.get(system.getName());
		if(cacheSys != null)
		{
			cacheById.remove(cacheSys.getId());
			cacheByName.remove(cacheSys.getName());
		}
		refSystems = null;
		try
		{
			sessionFactory.getCurrentSession().update(system);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public System getById(int id)
	{
		try
		{
			return cacheById.get(id, (key)->{return (System) sessionFactory.getCurrentSession().get(System.class, key);});
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public System getByName(String name)
	{
		try
		{
			return cacheByName.get(name, (key)->{return (System) sessionFactory.getCurrentSession().createQuery("from System as s where s.name=?").setString(0, key).uniqueResult();});
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<System> getAll()
	{
		List<System> systems = refSystems == null ? null : refSystems.get();
		if(systems != null)
			return systems;
		try
		{
			systems = (List<System>) sessionFactory.getCurrentSession().createQuery("from System as s").list();
			refSystems = new SoftReference<List<System>>(systems);
			return systems;
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	public void setCacheById(QuickCache<Integer, System> cacheById)
	{
		this.cacheById = cacheById;
	}
	
	public void setCacheByName(QuickCache<String, System> cacheByName)
	{
		this.cacheByName = cacheByName;
	}

}
