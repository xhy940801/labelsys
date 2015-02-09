package net._100steps.labelsys.service.message.impl;

import net._100steps.labelsys.service.model.Rule;
import net._100steps.labelsys.service.util.commontree.CommonTree;

public class RuleMessage extends AbstractMessage
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Rule rule;
	
	public RuleMessage(Rule rule)
	{
		super(0);
		this.rule = rule;
	}

	@Override
	public CommonTree getDataTree()
	{
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public String toString()
	{
		return "type: RuleMessage msgcode: " + msgCode + "rule:{ " + rule + " }";
	}

}
