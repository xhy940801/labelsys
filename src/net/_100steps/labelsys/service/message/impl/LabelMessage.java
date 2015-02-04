package net._100steps.labelsys.service.message.impl;

import net._100steps.labelsys.service.model.Label;
import net._100steps.labelsys.service.util.commontree.CommonTree;

public class LabelMessage extends AbstractMessage
{
	private Label label;

	public LabelMessage(Label label)
	{
		super(0);
		this.label = label;
	}

	@Override
	public CommonTree getDataTree()
	{
		return null;
	}

}
