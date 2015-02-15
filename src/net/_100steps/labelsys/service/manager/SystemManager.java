package net._100steps.labelsys.service.manager;

import net._100steps.general.message.Message;

public interface SystemManager {
	/**
	 * 
	 * @param systemName
	 * @return
	 */
	public Message createSystem(String systemName);
	
	/**
	 * 
	 * @param systemId
	 * @param newSystemName
	 * @return
	 */
	public Message changeSystem(int systemId,String newSystemName);
	
	/**
	 * 
	 * @param oldSystemName
	 * @param newSystemName
	 * @return
	 */
	public Message changeSystem(String oldSystemName,String newSystemName);
	
	/**
	 * 
	 * @param systemId
	 * @return
	 */
	public Message deleteSystem(int systemId);
	/**
	 * 
	 * @param systemName
	 * @return
	 */
	public Message deleteSystem(String systemName);

}
