package net._100steps.labelsys.service.message.impl;

import net._100steps.labelsys.service.model.Operation;
import net._100steps.labelsys.service.util.commontree.CommonTree;

public class OperationMessage extends AbstractMessage
{
	private final Operation operation;
	
	public OperationMessage(Operation operation)
	{
		super(0);
		this.operation = operation;
	}

	@Override
	public CommonTree getDataTree()
	{
		return null;
	}

}
