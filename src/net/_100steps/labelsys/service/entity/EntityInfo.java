package net._100steps.labelsys.service.entity;

public class EntityInfo
{
	private Integer id;
	private String systemName, moduleName;
	private Integer foreignKey;
	
	public EntityInfo(Integer id)
	{
		this.id = id;
	}
	
	public EntityInfo(String systemName, String moduleName, Integer foreignKey)
	{
		this.systemName = systemName;
		this.moduleName = moduleName;
		this.foreignKey = foreignKey;
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

	public Integer getForeignKey()
	{
		return foreignKey;
	}

	public void setForeignKey(Integer foreignKey)
	{
		this.foreignKey = foreignKey;
	}
}
