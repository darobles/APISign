package cl.auter.VMSAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "secuencias")
public class SequenceModel {
	@Id
	@Column(name = "id_secuencia")
	private Integer id;	
	@Column(name = "id_letrero")
	private Integer type_sign_id;
	@Column(name = "nombre")
	private String name;
	
	public SequenceModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SequenceModel(Integer id, Integer type_sign_id, String name) {
		super();
		this.id = id;
		this.type_sign_id = type_sign_id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getType_sign_id() {
		return type_sign_id;
	}

	public void setType_sign_id(Integer type_sign_id) {
		this.type_sign_id = type_sign_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "SequenceEntity [id=" + id + ", type_sign_id=" + type_sign_id + ", name=" + name + "]";
	}
	
	
}
