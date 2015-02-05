package net._100steps.labelsys.service.manager;

import java.util.List;

import net._100steps.labelsys.service.message.Message;

public interface EntityManager {
	/**
	 * 
	 * @param moduleId
	 * @param foreignKey
	 * @return
	 */
	public Message createEntity(int moduleId,int foreignKey);
	
	/**
	 * 
	 * @param systemName
	 * @param moduleName
	 * @param foreignKey
	 * @return
	 */
	public Message createEntity(String systemName,String moduleName,int foreignKey);
	/**
	 * 
	 * @param entityId
	 * @return
	 */
	public Message deleteEntity(int entityId);
	/**
	 * 
	 * @param systemName
	 * @param moduleName
	 * @param foreignKey
	 * @return
	 */
	public Message deleteEntity(String systemName,String moduleName,int foreignKey);
	/**
	 * 
	 * @param labelsId
	 * @return
	 */
	public Message findEntitiesByLabels(List<Integer>labelsId);
	/**
	 * 
	 * @param entityId
	 * @param labelsId
	 * @return
	 */
	public Message setLabels(int entityId,List<Integer>labelsId);
	/**
	 * 
	 * @param systemName
	 * @param moduleName
	 * @param foreignKey
	 * @param labelsId
	 * @return
	 */
	public Message setLabels(String systemName,String moduleName,int foreignKey,List<Integer>labelsId);
	/**
	 * 
	 * @param entityId
	 * @param labelId
	 * @return
	 */
	public Message addLabel(int entityId,int labelId);
	/**
	 * 
	 * @param systemName
	 * @param moduleName
	 * @param foreignKey
	 * @param labelName
	 * @return
	 */
	public Message addLabel(String systemName,String moduleName,int foreignKey,String labelName);
	
}
