package net._100steps.labelsys.service.dao.hibernateimpl;

import java.util.List;

import javax.transaction.Transactional;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.RuleDAO;
import net._100steps.labelsys.service.model.Rule;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;

import com.xiao.util.quickcache.QuickCache;
/**
 * @author xiao
 */
public class RuleDAOHibernateImpl implements RuleDAO
{
	private SessionFactory sessionFactory;
	private QuickCache<Integer, List<Rule>> cacheByOperationId;

	@Override
	@Transactional
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
	@Transactional
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
	@Transactional
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
	@Transactional
	public List<Rule> getByOperationId(int id, final Order order)
	{
		try
		{
			return cacheByOperationId.get(
					id,
					(key)->
					{
						switch (order)
						{
						case CREATEDASC:
							return sessionFactory.getCurrentSession()
									.createQuery("from Rule as r where r.operationId=? order by r.created asc").setInteger(0, key).list();
						case CREATEDDESC:
							return sessionFactory.getCurrentSession()
									.createQuery("from Rule as r where r.operationId=? order by r.created desc").setInteger(0, key).list();
						default:
							return sessionFactory.getCurrentSession()
									.createQuery("from Rule as r where r.operationId=?").setInteger(0, key).list();
						}
					});
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
