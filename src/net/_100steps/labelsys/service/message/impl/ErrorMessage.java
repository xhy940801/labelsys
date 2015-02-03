package net._100steps.labelsys.service.message.impl;

import net._100steps.labelsys.service.util.commontree.CommonTree;
import net._100steps.labelsys.service.util.commontree.GeneralCommonTree;
import net._100steps.labelsys.service.util.commontree.GeneralNode;

public class ErrorMessage extends AbstractMessage
{
	private final Exception exception;

	public ErrorMessage(int errorCode)
	{
		super(errorCode);
		this.exception = null;
	}
	
	public ErrorMessage(int errorCode, Exception exception)
	{
		super(errorCode);
		this.exception = exception;
	}
	
	public Exception getException()
	{
		return exception;
	}

	@Override
	public int getMsgCode()
	{
		return msgCode;
	}

	@Override
	public CommonTree getDataTree()
	{
		CommonTree tree = new GeneralCommonTree();
		tree.add(new GeneralNode("code", msgCode));
		return tree;
	}

}
