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
