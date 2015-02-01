package net._100steps.labelsys.service.model;
// Generated 2015-2-1 23:54:58 by Hibernate Tools 3.4.0.CR1

/**
 * Labels generated by hbm2java
 */
public class Label{

	private int id;
	private int systemId;
	private int moduleId;
	private String name;

	public Label() {
	}

	public Label(int systemId, int moduleId, String name) {
		this.systemId = systemId;
		this.moduleId = moduleId;
		this.name = name;
	}
	public Label(Label label) {
		this.id = label.id;
		this.systemId = label.systemId;
		this.moduleId = label.moduleId;
		this.name = label.name;
	}
	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getSystemId() {
		return this.systemId;
	}

	public void setSystemId(int systemId) {
		this.systemId = systemId;
	}

	public int getModuleId() {
		return this.moduleId;
	}

	public void setModuleId(int moduleId) {
		this.moduleId = moduleId;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

}