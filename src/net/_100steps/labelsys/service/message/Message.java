package net._100steps.labelsys.service.message;

import java.io.Serializable;

import net._100steps.labelsys.service.util.commontree.CommonTree;

public interface Message extends Serializable
{
	/**
	 * 获取消息码
	 * 消息码具体含义见msgcode.txt
	 * @return 消息码
	 */
	public int getMsgCode();
	
	/**
	 * 获取数据树(此树包含所有的信息)
	 * @return 树
	 */
	public CommonTree getDataTree();
}
