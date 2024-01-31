package cl.auter.VMSAPI.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "sign_message_view")
public class SignMessageViewModel {
	@Id
	@Column(name = "id_mensaje")
	int id;
	@Column(name = "nombre")
	String name;
	@Column(name = "id_letrero")
	int sign_id;
		
	public SignMessageViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getSign_id() {
		return sign_id;
	}
	public void setSign_id(int sign_id) {
		this.sign_id = sign_id;
	}
	@Override
	public String toString() {
		return "SignMessageViewModel [id=" + id + ", name=" + name + ", sign_id=" + sign_id + "]";
	}
	
	
	
	
}
