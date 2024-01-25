package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "alineacion")
public class AlineacionModel {
	int id;
	String nombre;
	
	public AlineacionModel() {
		super();
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "AlineacionModel [id=" + id + ", nombre=" + nombre + "]";
	}
	
	
	
	
}
