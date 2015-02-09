package net._100steps.labelsys.service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@javax.persistence.Entity
@Table(name="entities")
public class Entity implements Serializable
{
	/**
	 * version 1.0
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int moduleId;
	private int foreignKey;

	public Entity()
	{
	}

	public Entity(int moduleId, int foreignKey)
	{
		this.moduleId = moduleId;
		this.foreignKey = foreignKey;
	}

	public Entity(Entity entity)
	{
		this.id = entity.id;
		this.moduleId = entity.moduleId;
		this.foreignKey = entity.foreignKey;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(updatable=false)
	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Column(name="module_id")
	public int getModuleId()
	{
		return this.moduleId;
	}

	public void setModuleId(int moduleId)
	{
		this.moduleId = moduleId;
	}

	@Column(name="foreign_key")
	public int getForeignKey()
	{
		return this.foreignKey;
	}

	public void setForeignKey(int foreignKey)
	{
		this.foreignKey = foreignKey;
	}

}
