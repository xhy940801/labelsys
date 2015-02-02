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
		// TODO Auto-generated method stub
		try 
		{
			sessionFactory.getCurrentSession().save(entity);
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void update(Entity entity) {
		// TODO Auto-generated method stub
		try 
		{
			sessionFactory.getCurrentSession().update(entity);
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void setLabel(int entityId, List<Integer> labelId) {
		// TODO Auto-generated method stub
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
			// TODO: handle exception
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void addLabel(int entityId, int labelId) {
		// TODO Auto-generated method stub
		try 
		{
			LabelEntityLinker labelEntityLinker = new LabelEntityLinker();
			labelEntityLinker.setEntityId(entityId);
			labelEntityLinker.setLabelId(labelId);
			sessionFactory.getCurrentSession().save(labelEntityLinker);
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void deleteLabel(int entityId, int labelId) {
		// TODO Auto-generated method stub
		try 
		{
			if(sessionFactory.getCurrentSession().createQuery("delete from LabelEntityLinker as le where le.entityId = ? and le.labelId = ? ").setInteger(0, entityId).setInteger(1, labelId).executeUpdate()==0)
				throw new DAOException("记录不存在");
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public void delete(int entityId) {
		// TODO Auto-generated method stub
		try 
		{
			if(sessionFactory.getCurrentSession().createQuery("delete from Entity as e where e.id = ?").setInteger(0, entityId).executeUpdate()==0)
				throw new DAOException("记录不存在");
		} catch (HibernateException e) {
			// TODO: handle exception
			throw new DAOException(e);
		}
	}

	@Override
	@Transactional
	public Entity getById(int id) {
		// TODO Auto-generated method stub
		try
		{
			return (Entity) sessionFactory.getCurrentSession().get(Entity.class, id);
		}
		catch (HibernateException e)
		{
			throw new DAOException(e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public List<Entity> getByForeignKey(int foreignKey) {
		// TODO Auto-generated method stub
		try
		{
			return (List<Entity>) sessionFactory.getCurrentSession().createQuery("from Entity as e where e.foreignKey = ?").setInteger(0, foreignKey).list();
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
