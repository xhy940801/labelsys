package net._100steps.labelsys.service.dao;

import java.util.List;

import net._100steps.labelsys.service.model.Rule;

public interface RuleDAO
{
	static public enum Order
	{
		CREATEDASC, CREATEDDESC
	}
	/**
	 * 新增规则
	 * @param rule 规则,新增的记录的id会被写回rule
	 */
	public void save(Rule rule);
	
	/**
	 * 更新规则
	 * @param rule 更新rule.id所指向的记录
	 */
	public void update(Rule rule);
	
	/**
	 * 通过id获取规则
	 * @param id 记录id
	 * @return
	 */
	public Rule getById(int id);
	
	/**
	 * 通过操作id获取规则列表
	 * @param id 操作id
	 * @param order 顺序
	 * @return
	 */
	public List<Rule> getByOperationId(int id, Order order);
	
	/**
	 * 删除
	 * @param ids
	 */
	public void delete(int id);
	
	/**
	 * 删除n个实体
	 * @param ids
	 * @return 实际删除的数量
	 */
	public int delete(Iterable<Integer> ids);
}
