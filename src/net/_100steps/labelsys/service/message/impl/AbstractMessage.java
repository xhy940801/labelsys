package net._100steps.labelsys.service.message.impl;

import net._100steps.labelsys.service.message.Message;

public abstract class AbstractMessage implements Message
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected final int msgCode;

	protected AbstractMessage(int msgCode)
	{
		this.msgCode = msgCode;
	}

	@Override
	public int getMsgCode()
	{
		return msgCode;
	}

	@Override
	public String toString()
	{
		return "msgCode: " + msgCode;
	}

}
