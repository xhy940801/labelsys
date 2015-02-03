package net._100steps.labelsys.service.message.impl;

import net._100steps.labelsys.service.message.Message;

public abstract class AbstractMessage implements Message
{
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

}
