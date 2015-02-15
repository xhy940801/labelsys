package net._100steps.general.message.impl;

import java.util.ArrayList;
import java.util.List;

import net._100steps.general.util.commontree.CommonTree;
import net._100steps.labelsys.service.model.System;

public class SystemMessage extends AbstractMessage {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<System> systems;
	public SystemMessage(System system) {
		super(0);
		systems = new ArrayList<System>();
		systems.add(system);
	}
	public SystemMessage(List<System> systems) {
		super(0);
		this.systems = systems;
	}
	@Override
	public CommonTree getDataTree() {
		// TODO Auto-generated method stub
		return null;
	}

}
