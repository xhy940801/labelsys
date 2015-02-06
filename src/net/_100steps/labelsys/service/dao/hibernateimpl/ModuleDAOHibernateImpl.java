package net._100steps.labelsys.service.dao.hibernateimpl;

import java.util.List;





import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.ModuleDAO;
import net._100steps.labelsys.service.model.Module;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

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
	@Transactional
	public void save(Module module) {
		try 
		{
			sessionFactory.getCurrentSession().save(module);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
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
	@Transactional
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
	@Transactional
	public List<Module> getAll() {
		try 
		{
			return (List<Module>) sessionFactory.getCurrentSession().createQuery("from Module as m where 1").list();
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public Module getByName(int systemId, String name) {
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
	
	@Override
	@Transactional
	public void delete(int id)
	{
		cache.remove(id);
		try
		{
			int rs = sessionFactory.getCurrentSession()
					.createQuery("delete from Module as s where s.id=?")
					.setInteger(0, id)
					.executeUpdate();
			if(rs == 0)
				throw new DAOException("记录不存在");
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}
	
	@Override
	@Transactional
	public int delete(List<Integer> ids)
	{
		for (Integer id : ids)
		{
			cache.remove(id);
		}
		try
		{
			return sessionFactory.getCurrentSession()
					.createQuery("delete from Module as m where m.id in (:ids)")
					.setParameterList("ids", ids).executeUpdate();
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}
	@Override
	@Transactional
	public int deleteBySystems(List<Integer>systemsId)
	{
		try 
		{
			return (int)sessionFactory.getCurrentSession().createQuery("delete from Module as m where m.systemId in(:systemsId)").setParameterList("systemsId", systemsId).executeUpdate();
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Integer> findModulesIdBySystems(List<Integer>systemsId) {
		try
		{
			return (List<Integer>)sessionFactory.getCurrentSession().createQuery("select m.id from Module as m where m.systemId in(:systemsId)").setParameterList("systemsId", systemsId).list();
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory  = sessionFactory;
	}
	
	public void setCache(QuickCache<Object, Module> cache) {
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
