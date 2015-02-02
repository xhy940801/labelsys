package net._100steps.labelsys.service.dao.hibernateimpl;

import java.util.List;


import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.RuleDAO;
import net._100steps.labelsys.service.model.Rule;


import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;


import com.xiao.util.quickcache.QuickCache;

public class RuleDAOHibernateImpl implements RuleDAO
{
	private SessionFactory sessionFactory;
	private QuickCache<Integer, List<Rule>> cacheByOperationId;

	@Override
	public void save(Rule rule)
	{
		cacheByOperationId.remove(rule.getOperationId());
		try
		{
			sessionFactory.getCurrentSession().save(rule);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@Override
	public void update(Rule rule)
	{
		cacheByOperationId.remove(rule.getOperationId());
		try
		{
			sessionFactory.getCurrentSession().update(rule);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@Override
	public Rule getById(int id)
	{
		try
		{
			return (Rule) sessionFactory.getCurrentSession().get(Rule.class, id);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Rule> getByOperationId(int id)
	{
		try
		{
			return cacheByOperationId.get(id, (key)->{return sessionFactory.getCurrentSession().createQuery("from Rule as r where r.operationId=?").list();});
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

	public void setCacheByOperationId(QuickCache<Integer, List<Rule>> cacheByOperationId)
	{
		this.cacheByOperationId = cacheByOperationId;
	}

}
