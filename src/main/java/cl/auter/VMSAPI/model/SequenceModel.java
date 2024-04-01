package cl.auter.VMSAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "secuencias")
public class SequenceModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id_secuencia")
	private Integer id;	
	@Column(name = "id_letrero")
	private Integer sign_id;
	@Column(name = "nombre")
	private String name;
	public SequenceModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getSign_id() {
		return sign_id;
	}
	public void setSign_id(Integer sign_id) {
		this.sign_id = sign_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "SequenceModel [id=" + id + ", sign_id=" + sign_id + ", name=" + name + "]";
	}
	
	
 
	
	
}
