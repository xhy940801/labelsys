package net._100steps.general.message.impl;


import java.util.ArrayList;
import java.util.List;

import net._100steps.general.util.commontree.CommonTree;
import net._100steps.labelsys.service.model.Label;

public class LabelMessage extends AbstractMessage
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
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