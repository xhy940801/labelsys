package net._100steps.labelsys.service.message.impl;

import net._100steps.labelsys.service.util.commontree.CommonTree;
import net._100steps.labelsys.service.util.commontree.GeneralCommonTree;
import net._100steps.labelsys.service.util.commontree.GeneralNode;

public class GeneralMessage extends AbstractMessage
{
	private final Object msg;
	
	public GeneralMessage(int msgCode, Object msg)
	{
		super(msgCode);
		this.msg = msg;
	}

	public Object getMsg()
	{
		return msg;
	}

	@Override
	public CommonTree getDataTree()
	{
		CommonTree tree = new GeneralCommonTree();
		tree.add(new GeneralNode("code", msgCode));
		tree.add(new GeneralNode("data", msg));
		return tree;
	}
}
