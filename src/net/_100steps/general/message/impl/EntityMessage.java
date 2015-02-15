package net._100steps.general.message.impl;

import java.util.ArrayList;
import java.util.List;

import net._100steps.general.util.commontree.CommonTree;
import net._100steps.labelsys.service.model.Entity;

public class EntityMessage extends AbstractMessage{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Entity> entities;

	public EntityMessage(Entity entity)
	{
		super(0);
		entities = new ArrayList<Entity>();
		entities.add(entity);
	}
	
	public EntityMessage(List<Entity> entities)
	{
		super(0);
		this.entities = entities;
	}

	@Override
	public CommonTree getDataTree()
	{
		return null;
	}

}
