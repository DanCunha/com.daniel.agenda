package com.daniel.agenda.modelo.persistencia.entidades;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.SequenceGenerator;

@MappedSuperclass
public class ValueObject implements Serializable{

	private static final long serialVersionUID = -906128743532960536L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="mySequence")
	@SequenceGenerator(name = "mySequence", sequenceName = "ms")
	@Column
	private Long entityId;

	public ValueObject() {
	}
	
	public void setEntityId(Long entityId) {
		this.entityId = entityId;
	}
	
	public Long getEntityId() {
		return entityId;
	}
	
	@Override
	public boolean equals(Object obj){
		return this.entityId.equals(  ((ValueObject)obj).getEntityId()  );
	}
	
	@Override
	public int hashCode(){
		return super.hashCode();
	}
	
}
