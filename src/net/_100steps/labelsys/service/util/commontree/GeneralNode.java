package net._100steps.labelsys.service.util.commontree;

public class GeneralNode implements CommonTreeNode
{
	private final String name;
	private final Object data;

	public GeneralNode(String name, Object data)
	{
		this.name = name;
		this.data = data;
	}

	@Override
	public String getName()
	{
		return name;
	}

	@Override
	public Object getData()
	{
		return data;
	}

}
