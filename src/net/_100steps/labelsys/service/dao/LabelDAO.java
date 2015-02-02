package net._100steps.labelsys.service.dao;

import java.util.List;

import net._100steps.labelsys.service.model.Label;

public interface LabelDAO
{
	/**
	 * 保存label
	 * @param label 要保存的实体, 保存后的id会被写回到label中
	 * @throws DAOException 当操作失败时抛出异常
	 */
	public void save(Label label);
	
	/**
	 * 更新label
	 * @param label 要更新的label,更新label中id属性所指向的记录
	 * @throws DAOException 当操作失败时抛出异常
	 */
	public void update(Label label);
	
	/**
	 * 删除
	 * @param id 要删除的记录的id
	 * @throws DAOException 当操作失败时抛出异常
	 */
	public void delete(int id);
	
	/**
	 * 通过id获取记录
	 * @param id 要获取的记录的id
	 * @return 如果找到,则返回实体,如果没找到,则返回null
	 */
	public Label getById(int id);
	
	/**
	 * 获取实体所拥有的所有标签
	 * @param id 实体的id
	 * @return 标签列表
	 */
	public List<Label> getByEntityId(int id);
}
