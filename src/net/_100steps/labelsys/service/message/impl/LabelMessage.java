package net._100steps.labelsys.service.message.impl;


import java.util.ArrayList;
import java.util.List;
import net._100steps.labelsys.service.model.Label;
import net._100steps.labelsys.service.util.commontree.CommonTree;

public class LabelMessage extends AbstractMessage
{
	private List<Label> labels;
	public LabelMessage(Label label)
	{
		super(0);
		labels = new ArrayList<Label>();
		labels.add(label);
	}
	
	public LabelMessage(List<Label> labels)
	{
		super(0);
		this.labels = labels;
	}

	@Override
	public CommonTree getDataTree()
	{
		return null;
	}

}
