package net._100steps.labelsys.service.dao;

import java.util.List;

import net._100steps.labelsys.service.model.System;

public interface SystemDAO
{
	/**
	 * 新增记录
	 * @param system 要保存的实体,保存后的id会被写回到label中
	 * @throws DAOException 当操作失败时抛出异常
	 */
	public void save(System system);
	
	/**
	 * 更新记录
	 * @param system 要更新的实体,更新system中id属性所指向的记录
	 */
	public void update(System system);
	
	/**
	 * 通过id获取记录
	 * @param id 要获取的记录的id
	 * @return 如果找到返回实体,如果找不到返回null
	 */
	public System getById(int id);
	
	/**
	 * 通过name获取记录
	 * @param name 要获取的记录的name
	 * @return 如果找到返回实体,如果找不到返回null
	 */
	public System getByName(String name);
	
	/**
	 * 获取全部记录
	 * @return 全部记录
	 */
	public List<System> getAll();
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);
}
