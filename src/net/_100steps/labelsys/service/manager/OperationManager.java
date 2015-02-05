package net._100steps.labelsys.service.manager;

import net._100steps.labelsys.service.message.Message;

public interface OperationManager
{
	public Message createOperation(int moduleId, String name);
	public Message createOperation(String systemName, String moduleName, String operationName);
	public Message changeOperation(int operationId, String name);
	public Message changeOperation(String systemName, String moduleName, String oldName, String newName);
	public Message deleteOperation(int operationId);
	public Message deleteOperation(String systemName, String moduleName, String operationName);
}
