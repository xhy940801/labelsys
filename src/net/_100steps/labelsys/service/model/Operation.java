package net._100steps.labelsys.service.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "operations")
public class Operation implements Serializable
{
	/**
	 * version 1.0
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private int moduleId;
	private String name;

	public Operation()
	{
		
	}

	public Operation(int moduleId, String name)
	{
		this.moduleId = moduleId;
		this.name = name;
	}

	public Operation(Operation operation)
	{
		this.id = operation.id;
		this.moduleId = operation.moduleId;
		this.name = operation.name;
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
