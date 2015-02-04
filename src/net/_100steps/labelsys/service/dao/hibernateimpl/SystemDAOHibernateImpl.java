package net._100steps.labelsys.service.dao.hibernateimpl;

import java.lang.ref.SoftReference;
import java.util.List;

import javax.transaction.Transactional;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.SystemDAO;
import net._100steps.labelsys.service.model.System;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.xiao.util.quickcache.CacheSynchronizer;
import com.xiao.util.quickcache.QuickCache;

/**
 * @author xiao
 */
public class SystemDAOHibernateImpl implements SystemDAO
{
	private SessionFactory sessionFactory;
	private QuickCache<Object, System> cache;
	private SoftReference<List<System>> refSystems;
	private CacheSynchronizer cacheSynchronizer;
	
	public SystemDAOHibernateImpl(CacheSynchronizer cacheSynchronizer)
	{
		this.cacheSynchronizer = cacheSynchronizer;
	}

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
		cache.remove(system.getId());
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
			return cache.get(
					id,
					(key) ->
					{
						return (System) sessionFactory.getCurrentSession()
								.get(System.class, (Integer) key);
					},
					(value)->
					{
						Object[] os = new Object[2];
						os[0] = value.getId();
						os[1] = value.getName();
						return os;
					});
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
			return cache.get(
					name,
					(key) ->
					{
						return (System) sessionFactory.getCurrentSession()
								.createQuery("from System as s where s.name=?")
								.setString(0, (String) key).uniqueResult();
					},
					(value)->
					{
						Object[] os = new Object[2];
						os[0] = value.getId();
						os[1] = value.getName();
						return os;
					});
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
	
	@Override
	@Transactional
	public void delete(int id)
	{
		cache.remove(id);
		cacheSynchronizer.sendSignal("module", "clear", null);
		try
		{
			int rs = sessionFactory.getCurrentSession()
					.createQuery("delete from System as s where s.id=?")
					.executeUpdate();
			if(rs == 0)
				throw new DAOException("记录不存在");
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

	public void setCache(QuickCache<Object, System> cache)
	{
		this.cache = cache;
		cacheSynchronizer.addCache(
				cache,
				"system",
				(curcache, signal, info)->
				{
					if(signal.equals("clear"))
						curcache.clear();
					else if(signal.equals("remove"))
						curcache.remove(info);
				}
			);
	}

}
