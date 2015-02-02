package net._100steps.labelsys.service.dao.hibernateimpl;

import java.util.List;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.ModuleDAO;
import net._100steps.labelsys.service.model.Module;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.xiao.util.quickcache.QuickCache;

public class ModuleDAOHibernateImpl implements ModuleDAO{
	private class ModuleNameKey {
		int systemId;
		String name;
		
		ModuleNameKey(int systemId, String name) {
			this.systemId = systemId;
			this.name = name;
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof ModuleNameKey) {
				ModuleNameKey mnk = (ModuleNameKey) o;
				return mnk.systemId == systemId && mnk.name.equals(name);
			}
			return false;
		}
		
		@Override
		public int hashCode()
		{
			int result = 17;
			int c1 = systemId;
			int c2 = name.hashCode();
			result = 31 * result + c1;
			result = 31 * result + c2;
			return result;
		}
	}
	
	private SessionFactory sessionFactory;
	private QuickCache<Object, Module> cache;

	@Override
	public void save(Module module) {
		try 
		{
			sessionFactory.getCurrentSession().save(module);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public void update(Module module) {
		cache.remove(module.getId());
		try 
		{
			sessionFactory.getCurrentSession().update(module);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Module getById(int id) {
		try 
		{
			return cache.get(
					id,
					(key)->{
						return (Module) sessionFactory.getCurrentSession().get(Module.class, (Integer) key);
					});
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Module> getAll() {
		try 
		{
			return (List<Module>) sessionFactory.getCurrentSession().createQuery("from Module as m where 1").list();
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	@Override
	public Module geyByName(int systemId, String name) {
		ModuleNameKey mk = new ModuleNameKey(systemId, name);
		try 
		{
			return cache.get(
					mk,
					(key)->{
						ModuleNameKey tk = (ModuleNameKey)key;
						return (Module) sessionFactory.getCurrentSession()
								.createQuery("from Module as m where m.name = ? and m.systemId = ?")
								.setString(0, tk.name).setInteger(1, tk.systemId)
								.uniqueResult();
					});
			
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory  = sessionFactory;
	}

	public void setCacheById(QuickCache<Object, Module> cache) {
		this.cache = cache;
		this.cache.setKeyFactory(
				(value)->{
						Object[] os = new Object[2];
						os[0] = value.getId();
						os[1] = new ModuleNameKey(value.getSystemId(), value.getName());
						return os;
					});
	}

}
