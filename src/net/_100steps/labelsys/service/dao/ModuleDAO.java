package net._100steps.labelsys.service.dao;

import java.util.List;



import net._100steps.labelsys.service.model.Module;

public interface ModuleDAO {
	/**
	 * 增加module
	 * @param module
	 * @author XT1024
	 */
	public void save(Module module);
	
	
	/**
	 * 更新module
	 * @param module
	 * @author XT1024
	 */
	public void update(Module module);
	
	
	/**
	 * 根据id获得module
	 * @param id
	 * @return module
	 * @author XT1024
	 */
	public Module getById(int id);
	
	
	/**
	 * 获得全部module
	 * @return List<module>
	 * @author XT1024
	 */
	public List<Module> getAll();
	
	
	/**
	 * 根据名字获得module
	 * @param name
	 * @return module
	 * @author XT1024
	 */
	public Module getByName(int systemId, String name);
	
	/**
	 * 删除
	 * @param id
	 */
	public void delete(int id);
}
