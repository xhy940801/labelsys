package net._100steps.labelsys.service.model;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Entity;

@Entity
@Table(name="modules")
public class Module
{

	private int id;
	private int system_id;
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
	public int getId()
	{
		return this.id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Column(name="system_id")
	public int getSystem_id()
	{
		return system_id;
	}

	public void setSystem_id(int system_id)
	{
		this.system_id = system_id;
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
