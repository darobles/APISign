package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "grupos")
public class GrupoModel {
	int id_grupo;
	String nombre;
	String descripcion;
	int alto_maximo;
	int id_tipo_ancho;
	int ancho;
	
	
	
	public GrupoModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_grupo() {
		return id_grupo;
	}
	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public int getAlto_maximo() {
		return alto_maximo;
	}
	public void setAlto_maximo(int alto_maximo) {
		this.alto_maximo = alto_maximo;
	}
	public int getId_tipo_ancho() {
		return id_tipo_ancho;
	}
	public void setId_tipo_ancho(int id_tipo_ancho) {
		this.id_tipo_ancho = id_tipo_ancho;
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	@Override
	public String toString() {
		return "GrupoModel [id_grupo=" + id_grupo + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ ", alto_maximo=" + alto_maximo + ", id_tipo_ancho=" + id_tipo_ancho + ", ancho=" + ancho + "]";
	}
	
	
}
