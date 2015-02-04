package net._100steps.labelsys.service.dao;

import java.util.List;

import net._100steps.labelsys.service.model.Entity;

public interface EntityDAO {
	
	
	/**
	 * add entity
	 * @param entity
	 * @author XT1024
	 */
	public void save(Entity entity);
	
	
	/**
	 * update entity
	 * @param entity
	 * @author XT1024
	 */
	public void update(Entity entity);
	
	
	/**
	 * set label
	 * @param labelId
	 * @author XT1024
	 */
	public void setLabel(int entityId, List<Integer> labelId);
	
	
	/**
	 * add label
	 * @param entityId
	 * @param labelId
	 * @author XT1024
	 */
	public void addLabel(int entityId, int labelId);
	
	
	
	/**
	 * delete Label-Entity
	 * @param entityId
	 * @param labelId
	 * @author XT1024
	 */
	public void deleteLabel(int entityId,int labelId);
	
	
	
	/**
	 * delete entity
	 * @param entityId
	 * @author XT1024
	 */
	public void delete(int entityId);
	
	/**
	 * @param id
	 * @return
	 * @author XT1024
	 */
	public Entity getById(int id);
	
	
	
	/**
	 * @param foreignKey
	 * @return
	 * @author XT1024
	 */
	public Entity getByForeignKey(int moduleId, int foreignKey);
	
	/**
	 * @param entityId
	 * @param labelId
	 * @return
	 */
	public Boolean hasLabel(int entityId,int labelId);
	
	/**
	 * @param labelsId
	 * @return
	 */
	public List<Entity> findEntitiesByLabels(List<Integer>labelsId);
	
}
