package net._100steps.labelsys.service.model;
// Generated 2015-2-1 23:54:58 by Hibernate Tools 3.4.0.CR1

import java.util.Date;

/**
 * Rules generated by hbm2java
 */
public class Rule {

	private int id;
	private int operationId;
	private String exp;
	private int permission;
	private Date created;

	public Rule() {
	}

	public Rule(int operationId, String exp, int permission, Date created) {
		this.operationId = operationId;
		this.exp = exp;
		this.permission = permission;
		this.created = created;
	}
	public Rule(Rule rule) {
		this.id = rule.id;
		this.created = rule.created;
		this.exp = rule.exp;
		this.permission = rule.permission;
		this.created = rule.created;
	}
	
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getOperationId() {
		return this.operationId;
	}

	public void setOperationId(int operationId) {
		this.operationId = operationId;
	}

	public String getExp() {
		return this.exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public int getPermission() {
		return this.permission;
	}

	public void setPermission(int permission) {
		this.permission = permission;
	}

	public Date getCreated() {
		return this.created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

}