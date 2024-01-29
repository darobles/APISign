package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "letreros")
public class SignModel {
	@Id
	int id_letrero;
	String nombre;
	String ubicacion;
	int id_tipo_letrero;
	String obs;
	String latitud;
	String longitud;
	
	public SignModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_letrero() {
		return id_letrero;
	}
	public void setId_letrero(int id_letrero) {
		this.id_letrero = id_letrero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public int getId_tipo_letrero() {
		return id_tipo_letrero;
	}
	public void setId_tipo_letrero(int id_tipo_letrero) {
		this.id_tipo_letrero = id_tipo_letrero;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	@Override
	public String toString() {
		return "LetreroModel [id_letrero=" + id_letrero + ", nombre=" + nombre + ", ubicacion=" + ubicacion
				+ ", id_tipo_letrero=" + id_tipo_letrero + ", obs=" + obs + ", latitud=" + latitud + ", longitud="
				+ longitud + "]";
	}
	
	
}
