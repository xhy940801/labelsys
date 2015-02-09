package net._100steps.labelsys.service.api.rmi;

import java.rmi.Remote;
import java.rmi.RemoteException;

import net._100steps.labelsys.service.entity.EntityInfo;
import net._100steps.labelsys.service.entity.LabelInfo;
import net._100steps.labelsys.service.entity.OperationInfo;
import net._100steps.labelsys.service.message.Message;

public interface OperationManagerRO extends Remote
{
	public Message createOperation(int moduleId, String name) throws RemoteException;
	public Message createOperation(String systemName, String moduleName, String operationName) throws RemoteException;
	public Message changeOperation(int operationId, String name) throws RemoteException;
	public Message changeOperation(String systemName, String moduleName, String oldName, String newName) throws RemoteException;
	public Message deleteOperation(int operationId) throws RemoteException;
	public Message deleteOperation(String systemName, String moduleName, String operationName) throws RemoteException;
	public Message checkPermission(OperationInfo operationInfo, Iterable<LabelInfo> labelInfos) throws RemoteException;
	public Message checkPermission(OperationInfo operationInfo, EntityInfo entityInfo, Iterable<LabelInfo> labelInfos) throws RemoteException;
}
