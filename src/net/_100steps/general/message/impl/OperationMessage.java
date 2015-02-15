package net._100steps.general.message.impl;

import net._100steps.general.util.commontree.CommonTree;
import net._100steps.labelsys.service.model.Operation;

public class OperationMessage extends AbstractMessage
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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
