package net._100steps.labelsys.service.manager;

import net._100steps.general.message.Message;

public interface LabelManager
{
	/**
	 * 创城label
	 * @param moduleId
	 * @param name
	 * @return
	 */
	public Message createLabel(int moduleId, String name);
	
	/**
	 * 创建label
	 * @param systemName 系统名称
	 * @param moduleName 模块名称
	 * @param labelName label名称
	 * @return
	 */
	public Message createLabel(String systemName, String moduleName, String labelName);
	
	/**
	 * 修改label
	 * @param labelId
	 * @param newName
	 * @return
	 */
	public Message changeLabel(int labelId, String newName);
	
	/**
	 * 修改label
	 * @param systemName
	 * @param moduleName
	 * @param oldName
	 * @param newName
	 * @return
	 */
	public Message changeLabel(String systemName, String moduleName, String oldName, String newName);
	
	/**
	 * 删除label
	 * @param labelId
	 * @return
	 */
	public Message deleteLabel(int labelId);
	
	/**
	 * 删除label
	 * @param systemName
	 * @param moduleName
	 * @param name
	 * @return
	 */
	public Message deleteLabel(String systemName, String moduleName, String labelName);
}
