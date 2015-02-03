package net._100steps.labelsys.service.dao.hibernateimpl;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
/**
 * @author xiao
 */
@Entity
@Table(name="entities_labels")
class LabelEntityLinker
{
	private int id;
	private int entityId;
	private int labelId;
	
	public LabelEntityLinker()
	{
		
	}
	
	public LabelEntityLinker(int id, int entityId, int labelId)
	{
		this.id = id;
		this.entityId = entityId;
		this.labelId = labelId;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int getId()
	{
		return id;
	}

	public void setId(int id)
	{
		this.id = id;
	}

	@Column(name="entity_id")
	public int getEntityId()
	{
		return entityId;
	}

	public void setEntityId(int entityId)
	{
		this.entityId = entityId;
	}

	@Column(name="label_id")
	public int getLabelId()
	{
		return labelId;
	}

	public void setLabelId(int labelId)
	{
		this.labelId = labelId;
	}
}
