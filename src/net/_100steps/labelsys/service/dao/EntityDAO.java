package net._100steps.labelsys.service.dao;

import java.util.List;

import net._100steps.labelsys.service.model.Entity;

public interface EntityDAO {
	
	
	/**
	 * 增加entity
	 * @param entity
	 * @author XT1024
	 */
	public void save(Entity entity);
	
	
	/**
	 * 更新entity
	 * @param entity
	 * @author XT1024
	 */
	public void update(Entity entity);
	
	
	/**
	 * 设置label
	 * @param labelId
	 * @author XT1024
	 */
	public void setLabel(int entityId, List<Integer> labelId);
	
	
	/**
	 * 增加label
	 * @param entityId
	 * @param labelId
	 * @author XT1024
	 */
	public void addLabel(int entityId, int labelId);
	
	
	
	/**
	 * 删除一条关联
	 * @param entityId
	 * @param labelId
	 * @author XT1024
	 */
	public void deleteLabel(int entityId,int labelId);
	
	
	
	/**
	 * 删除一个entity
	 * @param entityId
	 * @author XT1024
	 */
	public void delete(int entityId);
	
	/**
	 * 根据id获得Entity
	 * @param id
	 * @return
	 * @author XT1024
	 */
	public Entity getById(int id);
	
	
	
	/**
	 * 根据foreignKey获得entity
	 * @param foreignKey
	 * @return
	 * @author XT1024
	 */
	public Entity getByForeignKey(int moduleId, int foreignKey);
	/**
	 * 
	 * @param entityId
	 * @param labelId
	 * @return
	 */
	public Boolean hasLabel(int entityId,int labelId);
	/**
	 * 
	 * @param labelsId
	 * @return
	 */
	public List<Entity> findEntitiesByLabels(List<Integer>labelsId);
}
