package cl.auter.VMSAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "sequence_view")
public class SequenceViewModel {
	@Id
	@Column(name = "id_secuencia")
	private Integer id;	
	@Column(name = "id_tipo_letrero")
	@JsonProperty("signTypeId")
	private Integer type_sign_id;
	@Column(name = "nombre")
	private String name;
	@Column(name="nombre_tipo_letrero")
	@JsonProperty("signTypeName")
	private String sign_type_name;
	
	public SequenceViewModel() {
		super();
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

	public String getSign_type_name() {
		return sign_type_name;
	}

	public void setSign_type_name(String sign_type_name) {
		this.sign_type_name = sign_type_name;
	}

	@Override
	public String toString() {
		return "SequenceViewModel [id=" + id + ", type_sign_id=" + type_sign_id + ", name=" + name + ", sign_type_name="
				+ sign_type_name + "]";
	}
	
	
}
