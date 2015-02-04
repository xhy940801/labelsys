package net._100steps.labelsys.service.dao.hibernateimpl;

import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.springframework.transaction.annotation.Transactional;

import net._100steps.labelsys.service.dao.DAOException;
import net._100steps.labelsys.service.dao.EntityDAO;
import net._100steps.labelsys.service.model.Entity;

public class EntityDAOHibernateImpl implements EntityDAO{
	private SessionFactory sessionFactory;
	
	@Override
	@Transactional
	public void save(Entity entity) {
		try 
		{
			sessionFactory.getCurrentSession().save(entity);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void update(Entity entity) {
		try 
		{
			sessionFactory.getCurrentSession().update(entity);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void setLabel(int entityId, List<Integer> labelId) {
		try
		{
			sessionFactory.getCurrentSession()
				.createQuery("delete from LabelEntityLinker as le where le.entityId = ?")
					.setInteger(0, entityId).executeUpdate();
			Iterator<Integer> iterable = labelId.iterator();
			while(iterable.hasNext())
			{
				LabelEntityLinker labelEntityLinker = new LabelEntityLinker();
				labelEntityLinker.setEntityId(entityId);
				labelEntityLinker.setLabelId(iterable.next());
				sessionFactory.getCurrentSession().save(labelEntityLinker);
			}
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void addLabel(int entityId, int labelId) {
		try 
		{
			LabelEntityLinker labelEntityLinker = new LabelEntityLinker();
			labelEntityLinker.setEntityId(entityId);
			labelEntityLinker.setLabelId(labelId);
			sessionFactory.getCurrentSession().save(labelEntityLinker);
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void deleteLabel(int entityId, int labelId) {
		try 
		{
			if(sessionFactory.getCurrentSession().createQuery("delete from LabelEntityLinker as le where le.entityId = ? and le.labelId = ? ").setInteger(0, entityId).setInteger(1, labelId).executeUpdate()==0)
				throw new DAOException("记录不存在");
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void delete(int entityId) {
		try 
		{
			if(sessionFactory.getCurrentSession().createQuery("delete from Entity as e where e.id = ?").setInteger(0, entityId).executeUpdate()==0)
				throw new DAOException("记录不存在");
		} catch (HibernateException e) {
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public Entity getById(int id) {
		try
		{
			return (Entity) sessionFactory.getCurrentSession().get(Entity.class, id);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public Entity getByForeignKey(int moduleId, int foreignKey) {
		try
		{
			return (Entity) sessionFactory.getCurrentSession().createQuery("from Entity as e where e.foreignKey = ? and e.moduleId = ?").setInteger(0, foreignKey).setInteger(1, moduleId).uniqueResult();
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}
	
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}
