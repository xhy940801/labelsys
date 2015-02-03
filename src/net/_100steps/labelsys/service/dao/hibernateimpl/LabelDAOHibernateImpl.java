package net._100steps.labelsys.service.dao.hibernateimpl;

import java.util.List;

import javax.transaction.Transactional;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.LabelDAO;
import net._100steps.labelsys.service.model.Label;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
/**
 * @author xiao
 */
public class LabelDAOHibernateImpl implements LabelDAO
{
	private SessionFactory sessionFactory;

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
		try
		{
			if(sessionFactory.getCurrentSession().createQuery("delete from Label as l where l.id=?").setInteger(0, id).executeUpdate() == 0)
				throw new DAOException("记录不存在");
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
			return (Label) sessionFactory.getCurrentSession().get(Label.class, id);
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
		try
		{
			return (Label) sessionFactory.getCurrentSession()
					.createQuery("from Label as l where l.moduleId=? and l.name=?")
					.setInteger(0, moduleId).setString(1, name)
					.uniqueResult();
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
	
	public void setSessionFactory(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}

}
