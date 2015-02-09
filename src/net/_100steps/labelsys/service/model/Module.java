package net._100steps.labelsys.service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name="modules")
public class Module implements Serializable
{
	/**
	 * version 1.0
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private int systemId;
	private String name;

	public Module()
	{
	}

	public Module(String name)
	{
		this.name = name;
	}

	public Module(Module module)
	{
		this.id = module.id;
		this.name = module.name;
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

	@Column(name="system_id")
	public int getSystemId()
	{
		return systemId;
	}

	public void setSystemId(int systemId)
	{
		this.systemId = systemId;
	}

	@Column
	public String getName()
	{
		return this.name;
	}

	public void setName(String name)
	{
		this.name = name;
	}

}
