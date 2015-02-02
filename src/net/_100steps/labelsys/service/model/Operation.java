package net._100steps.labelsys.service.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "operations")
public class Operation
{

	private int id;
	private int systemId;
	private int moduleId;
	private String name;

	public Operation()
	{
		
	}

	public Operation(int systemId, int moduleId, String name)
	{
		this.systemId = systemId;
		this.moduleId = moduleId;
		this.name = name;
	}

	public Operation(Operation operation)
	{
		this.id = operation.id;
		this.systemId = operation.systemId;
		this.moduleId = operation.moduleId;
		this.name = operation.name;
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
	public int getSystemId()
	{
		return this.systemId;
	}

	public void setSystemId(int systemId)
	{
		this.systemId = systemId;
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
