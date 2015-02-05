package net._100steps.labelsys.service.entity;

public class LabelInfo
{
	static public enum ToUsed
	{
		ADD, REMOVE, FADD, FREMOVE, TAKEBACK
	}
	
	private Integer id;
	private String systemName, moduleName, labelName;
	private ToUsed used;
	
	public LabelInfo(Integer id)
	{
		this.id = id;
	}
	
	public LabelInfo(Integer id, ToUsed used)
	{
		this.id = id;
		this.used = used;
	}
	
	public LabelInfo(String systemName, String moduleName, String labelName)
	{
		this.systemName = systemName;
		this.moduleName = moduleName;
		this.labelName = labelName;
	}
	
	public LabelInfo(String systemName, String moduleName, String labelName, ToUsed used)
	{
		this.systemName = systemName;
		this.moduleName = moduleName;
		this.labelName = labelName;
		this.used = used;
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

	public String getLabelName()
	{
		return labelName;
	}

	public void setLabelName(String labelName)
	{
		this.labelName = labelName;
	}

	public Integer getId()
	{
		return id;
	}

	public void setId(Integer id)
	{
		this.id = id;
	}

	public ToUsed getUsed()
	{
		return used;
	}

	public void setUsed(ToUsed used)
	{
		this.used = used;
	}
	
}
