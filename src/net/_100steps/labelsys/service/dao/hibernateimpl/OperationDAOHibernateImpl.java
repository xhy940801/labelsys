package net._100steps.labelsys.service.dao.hibernateimpl;

import javax.transaction.Transactional;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.OperationDAO;
import net._100steps.labelsys.service.model.Operation;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.xiao.util.quickcache.QuickCache;



public class OperationDAOHibernateImpl implements OperationDAO{
	private class OperationNameKey {
		int moduleId;
		String name;
		
		OperationNameKey(int moduleId, String name) {
			this.moduleId = moduleId;
			this.name = name;
		}
		
		@Override
		public boolean equals(Object o) {
			if(o instanceof OperationNameKey) {
				OperationNameKey mnk = (OperationNameKey) o;
				return mnk.moduleId == moduleId && mnk.name.equals(name);
			}
			return false;
		}
		
		@Override
		public int hashCode()
		{
			int result = 17;
			int c1 = moduleId;
			int c2 = name.hashCode();
			result = 31 * result + c1;
			result = 31 * result + c2;
			return result;
		}
	}
	
	private SessionFactory sessionFactory;
	private QuickCache<Object, Operation> cache;
	
	@Override
	@Transactional
	public void save(Operation operation) {
		// TODO Auto-generated method stub
		try 
		{
			sessionFactory.getCurrentSession().save(operation);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void update(Operation operation) {
		cache.remove(operation.getId());
		try 
		{
			sessionFactory.getCurrentSession().update(operation);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public Operation getById(int id) {
		try
		{
			return cache.get(
					id,
					(key)->{
						return (Operation) sessionFactory.getCurrentSession()
								.get(Operation.class, (Integer) key);
					});
			
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public Operation getByName(int moduleId, String name) {
		OperationNameKey nk = new OperationNameKey(moduleId, name);
		try 
		{
			return cache.get(
					nk,
					(key)->{
						OperationNameKey tk = (OperationNameKey) key;
						return (Operation) sessionFactory.getCurrentSession()
								.createQuery("from Operation as o where o.name = ? and o.moduleId = ?")
								.setString(0, tk.name).setInteger(1, tk.moduleId)
								.uniqueResult();
					});
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}
	
	@Override
	@Transactional
	public void delete(int id) {
		cache.remove(id);
		try {
			int rs = sessionFactory.getCurrentSession()
					.createQuery("delete from Operation as o where o.id = ?")
					.setInteger(0, id).executeUpdate();
			if(rs == 0)
				throw new DAOException("记录不存在");
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}
	
	@Override
	@Transactional
	public int delete(Iterable<Integer> ids) {
		StringBuilder builder = new StringBuilder();
		for(Integer id : ids)
		{
			cache.remove(id);
			builder.append(id).append(',');
		}
		builder.append(-1);
		try {
			return sessionFactory.getCurrentSession()
					.createQuery("delete from Operation as o where o.id in (?)")
					.setString(0, builder.toString())
					.executeUpdate();
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public void setCache(QuickCache<Object, Operation> cache) {
		this.cache = cache;
		this.cache.setKeyFactory(
				(value)->{
					Object[] os = new Object[2];
					os[0] = value.getId();
					os[1] = new OperationNameKey(value.getModuleId(), value.getName());
					return os;
				});
	}

}
