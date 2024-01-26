package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "categorias")
public class CategoriaModel {
	@Id
	int id_categoria;
	
	String nombre;
	
	String descripcion;
		
	public CategoriaModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_categoria() {
		return id_categoria;
	}
	public void setId_categoria(int id_categoria) {
		this.id_categoria = id_categoria;
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
	@Override
	public String toString() {
		return "CategoriaModel [id_categoria=" + id_categoria + ", nombre=" + nombre + ", descripcion=" + descripcion
				+ "]";
	}
	
	
}
