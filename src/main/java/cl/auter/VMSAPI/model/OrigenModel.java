package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "origen")
public class OrigenModel {
	@Id
	int id_origen;
	String nombre;
	public OrigenModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_origen() {
		return id_origen;
	}
	public void setId_origen(int id_origen) {
		this.id_origen = id_origen;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "OrigenModel [id_origen=" + id_origen + ", nombre=" + nombre + "]";
	}
	
}
