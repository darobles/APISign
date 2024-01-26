package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipo_ancho")
public class TipoAnchoModel {
	@Id
	int id_tipo_ancho;
	String nombre;
	public TipoAnchoModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId_tipo_ancho() {
		return id_tipo_ancho;
	}

	public void setId_tipo_ancho(int id_tipo_ancho) {
		this.id_tipo_ancho = id_tipo_ancho;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public String toString() {
		return "TipoAnchoModel [id_tipo_ancho=" + id_tipo_ancho + ", nombre=" + nombre + "]";
	}
	
}
