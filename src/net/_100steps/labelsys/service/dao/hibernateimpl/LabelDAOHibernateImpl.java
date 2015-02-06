package net._100steps.labelsys.service.dao.hibernateimpl;

import java.util.List;

import javax.transaction.Transactional;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.LabelDAO;
import net._100steps.labelsys.service.model.Label;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.xiao.util.quickcache.QuickCache;




/**
 * @author xiao
 */
public class LabelDAOHibernateImpl implements LabelDAO
{
	private class LabelNameKey
	{
		int moduleId;
		String name;
		LabelNameKey(int moduleId, String name)
		{
			this.moduleId = moduleId;
			this.name = name;
		}
		
		@Override
		public boolean equals(Object o)
		{
			if (o instanceof LabelNameKey)
			{
				LabelNameKey mnk = (LabelNameKey) o;
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
	private QuickCache<Object, Label> cache;

	@Override
	@Transactional
	public void save(Label label)
	{
		try
		{
			sessionFactory.getCurrentSession().save(label);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void update(Label label)
	{
		cache.remove(label.getId());
		try
		{
			sessionFactory.getCurrentSession().update(label);
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
		try
		{
			if(sessionFactory.getCurrentSession().createQuery("delete from Label as l where l.id=?").setInteger(0, id).executeUpdate() == 0)
				throw new DAOException("记录不存在");
			sessionFactory.getCurrentSession()
					.createQuery("delete from LabelEntityLinker as le where le.labelId = ?")
					.setInteger(0, id)
					.executeUpdate();
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
		for(Integer id : ids)
		{
			cache.remove(id);
		}

		try
		{
			int rs = sessionFactory.getCurrentSession()
					.createQuery("delete from Label as l where l.id in (:ids)")
					.setParameterList("ids", ids)
					.executeUpdate();
			sessionFactory.getCurrentSession()
					.createQuery("delete from LabelEntityLinker as le where le.labelId in (:ids)")
					.setParameterList("ids", ids)
					.executeUpdate();
			return rs;
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public Label getById(int id)
	{
		try
		{
			return cache.get(id, (key)->{return (Label) sessionFactory.getCurrentSession().get(Label.class, (Integer) key);});
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Label> getByEntityId(int id)
	{
		try
		{
			return (List<Label>) sessionFactory.getCurrentSession().createQuery("from Label as l where id in (from LabelEntityLinker as linker where linker.entityId=?)").setInteger(0, id).list();
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}
	
	@Override
	@Transactional
	public Label getByName(int moduleId, String name)
	{
		LabelNameKey nk = new LabelNameKey(moduleId, name);
		try
		{
			return cache.get(nk, (key)->
			{
				LabelNameKey tk = (LabelNameKey) key;
				return (Label) sessionFactory.getCurrentSession()
						.createQuery("from Label as l where l.moduleId=? and l.name=?")
						.setInteger(0, tk.moduleId).setString(1, tk.name)
						.uniqueResult();
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
	public List<Label> findByName(int moduleId, String name)
	{
		return (List<Label>) sessionFactory.getCurrentSession()
				.createQuery("from Label as l where l.moduleId=? and l.name like %?%")
				.setInteger(0, moduleId).setString(1, name)
				.list();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Integer> findLabelsIdByModules(List<Integer> modulesId)
	{
		try
		{
			return (List<Integer>) sessionFactory
					.getCurrentSession()
					.createQuery(
							"select l.id from Label as l where l.moduleId in(:modulesId)")
					.setParameterList("modulesId", modulesId).list();
		}
		catch (HibernateException e)
		{
			// TODO: handle exception
			throw new DAOException(e);
		}
	}
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

	public void setCache(QuickCache<Object, Label> cache)
	{
		this.cache = cache;
		cache.setKeyFactory((value)->
		{
			Object[] os = new Object[2];
			os[0] = value.getId();
			os[1] = new LabelNameKey(value.getModuleId(), value.getName());
			return os;
		});
	}

}
