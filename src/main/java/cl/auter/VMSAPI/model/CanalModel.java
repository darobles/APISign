package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "canales")
public class CanalModel {
	int id_canal;
	String nombre;

	public CanalModel() {
		super();
	}
	public int getId_canal() {
		return id_canal;
	}
	public void setId_canal(int id_canal) {
		this.id_canal = id_canal;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	@Override
	public String toString() {
		return "CanalModel [id_canal=" + id_canal + ", nombre=" + nombre + "]";
	}
	
	
}
