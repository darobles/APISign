package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mensaje_categoria")
public class MensajeCategoriaModel {
	@Id
	int id_mensaje;
	int id_categoria;
	
	
	public MensajeCategoriaModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_mensaje() {
		return id_mensaje;
	}
	public void setId_mensaje(int id_mensaje) {
		this.id_mensaje = id_mensaje;
	}
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
	}
	@Override
	public String toString() {
		return "MensajeCategoriaModel [id_mensaje=" + id_mensaje + ", id_categoria=" + id_categoria + "]";
	}
	
	
}
