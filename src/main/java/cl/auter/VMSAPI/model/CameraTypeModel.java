package cl.auter.VMSAPI.model;

public class CameraTypeModel {
	int id;
	String nombre;
	
	public CameraTypeModel() {
		super();
		// TODO Auto-generated constructor stub
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
		return "CameraTypeModel [id=" + id + ", nombre=" + nombre + "]";
	}
	
}
