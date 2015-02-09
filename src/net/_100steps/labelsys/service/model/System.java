package net._100steps.labelsys.service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "systems")
public class System implements Serializable
{
	/**
	 * version 1.0
	 */
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;

	public System()
	{
		
	}

	public System(String name)
	{
		this.name = name;
	}
	
	public System(System system)
	{
		this.id = system.id;
		this.name = system.name;
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
