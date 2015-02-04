package net._100steps.labelsys.service.manager;

import java.util.List;

import net._100steps.labelsys.service.message.Message;

public interface EntityManager {
	Message createEntity(int moduleId,int foreignKey);
	Message deleteEntity(int entityId);
	Message findEntitiesByLabels(List<Integer>labelsId);
	Message setLabels(int entityId,List<Integer>labelsId);
	Message addLabel(int entityId,int labelId);
}
