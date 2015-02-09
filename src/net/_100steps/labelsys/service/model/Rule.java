package net._100steps.labelsys.service.model;

// Generated 2015-2-1 23:54:58 by Hibernate Tools 3.4.0.CR1

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "rules")
public class Rule implements Serializable
{
	/**
	 * version 1.0
	 */
	private static final long serialVersionUID = 1L;

	private int id;
	private int operationId;
	private String exp;
	private int permission;
	private Date created;

	public Rule()
	{
		
	}

	public Rule(int operationId, String exp, int permission, Date created)
	{
		this.operationId = operationId;
		this.exp = exp;
		this.permission = permission;
		this.created = created;
	}

	public Rule(Rule rule)
	{
		this.id = rule.id;
		this.created = rule.created;
		this.exp = rule.exp;
		this.permission = rule.permission;
		this.created = rule.created;
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

	@Column(name="operation_id")
	public int getOperationId()
	{
		return this.operationId;
	}

	public void setOperationId(int operationId)
	{
		this.operationId = operationId;
	}

	@Column
	public String getExp()
	{
		return this.exp;
	}

	public void setExp(String exp)
	{
		this.exp = exp;
	}

	@Column
	public int getPermission()
	{
		return this.permission;
	}

	public void setPermission(int permission)
	{
		this.permission = permission;
	}

	@Column
	@Temporal(TemporalType.TIMESTAMP)
	public Date getCreated()
	{
		return this.created;
	}

	public void setCreated(Date created)
	{
		this.created = created;
	}
	
	@Override
	public String toString()
	{
		return "id: " + id + ";operationId: " + operationId + ";exp: " + exp + ";permission:" + permission + ";created:" + created;
	}

}
