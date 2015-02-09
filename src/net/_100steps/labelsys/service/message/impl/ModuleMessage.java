package net._100steps.labelsys.service.message.impl;

import java.util.ArrayList;
import java.util.List;


import net._100steps.labelsys.service.model.Module;
import net._100steps.labelsys.service.util.commontree.CommonTree;

public class ModuleMessage extends AbstractMessage{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Module> modules;
	public ModuleMessage(Module module) {
		super(0);
		modules = new ArrayList<Module>();
		modules.add(module);
	}
	public ModuleMessage(List<Module>modules) {
		super(0);
		this.modules = modules;
	}
	@Override
	public CommonTree getDataTree() {
		// TODO Auto-generated method stub
		return null;
	}
	

}
