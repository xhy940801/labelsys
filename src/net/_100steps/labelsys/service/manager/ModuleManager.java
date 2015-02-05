package net._100steps.labelsys.service.manager;

import net._100steps.labelsys.service.message.Message;

public interface ModuleManager {
	/**
	 * 
	 * @param systemId
	 * @param moduleName
	 * @return
	 */
	public Message createModule(int systemId,String moduleName);
	/**
	 * 
	 * @param systemName
	 * @param moduleName
	 * @return
	 */
	public Message createModule(String systemName,String moduleName);
	/**
	 * 
	 * @param moduleId
	 * @param newModuleName
	 * @return
	 */
	public Message changeModule(int moduleId,String newModuleName);
	/**
	 * 
	 * @param systemName
	 * @param oldModuleName
	 * @param newModuleName
	 * @return
	 */
	public Message changeModule(String systemName,String oldModuleName, String newModuleName);
	/**
	 * 
	 * @param moduleId
	 * @return
	 */
	public Message deleteModule(int moduleId);
	/**
	 * 
	 * @param systemName
	 * @param moduleName
	 * @return
	 */
	public Message deleteModule(String systemName,String moduleName);
}
