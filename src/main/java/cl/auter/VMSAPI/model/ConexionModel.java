package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "conexiones")
public class ConexionModel {
	
	int id_conexion;
	String nombre;

	public ConexionModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_conexion() {
		return id_conexion;
	}
	public void setId_conexion(int id_conexion) {
		this.id_conexion = id_conexion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "ConexionModel [id_conexion=" + id_conexion + ", nombre=" + nombre + "]";
	}
	
	
	
	
}
