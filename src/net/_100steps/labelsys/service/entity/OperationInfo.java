package net._100steps.labelsys.service.entity;

public class OperationInfo
{
	private Integer id;
	private String systemName, moduleName, operationName;
	
	public OperationInfo(Integer id)
	{
		this.id = id;
	}
	
	public OperationInfo(String systemName, String moduleName, String operationName)
	{
		this.systemName = systemName;
		this.moduleName = moduleName;
		this.operationName = operationName;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public String getSystemName()
	{
		return systemName;
	}

	public void setSystemName(String systemName)
	{
		this.systemName = systemName;
	}

	public String getModuleName()
	{
		return moduleName;
	}

	public void setModuleName(String moduleName)
	{
		this.moduleName = moduleName;
	}

	public String getOperationName()
	{
		return operationName;
	}

	public void setOperationName(String operationName)
	{
		this.operationName = operationName;
	}
}
