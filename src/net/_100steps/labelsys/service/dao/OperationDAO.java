package net._100steps.labelsys.service.dao;

import net._100steps.labelsys.service.model.Operation;

public interface OperationDAO {
	/**
	 * 增加operation
	 * @param operation
	 * @author XT1024
	 */
	public void save(Operation operation);
	
	
	/**
	 * 更新operation
	 * @param operation
	 * @author XT1024
	 */
	public void update(Operation operation);
	
	/**
	 * 根据id获得operation
	 * @param id
	 * @return
	 * @author XT1024
	 */
	public Operation getById(int id);
	
	/**
	 * 根据name获得operation
	 * @param name
	 * @param moduleId
	 * @return
	 * @author XT1024
	 */
	public Operation getByName(int moduleId, String name);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);
	
	/**
	 * 删除n个实体
	 * @param ids
	 * @return 实际删除的数量
	 */
	public int delete(Iterable<Integer> ids);
}
