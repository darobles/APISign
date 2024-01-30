package cl.auter.VMSAPI.model.view;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "signtype_view")
public class SignTypeViewModel {
	
	@Id
	int id_tipo_letrero;
	String nombre;
	int ancho;
	int alto;
	int espacio_lineas;
	int alto_max_simbolo;
	int id_tipo_ancho;
	int espaciado;
	int grano;
	int codificacion;
	String nombre_tipo_ancho;
	public SignTypeViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_tipo_letrero() {
		return id_tipo_letrero;
	}
	public void setId_tipo_letrero(int id_tipo_letrero) {
		this.id_tipo_letrero = id_tipo_letrero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getAncho() {
		return ancho;
	}
	public void setAncho(int ancho) {
		this.ancho = ancho;
	}
	public int getAlto() {
		return alto;
	}
	public void setAlto(int alto) {
		this.alto = alto;
	}
	public int getEspacio_lineas() {
		return espacio_lineas;
	}
	public void setEspacio_lineas(int espacio_lineas) {
		this.espacio_lineas = espacio_lineas;
	}
	public int getAlto_max_simbolo() {
		return alto_max_simbolo;
	}
	public void setAlto_max_simbolo(int alto_max_simbolo) {
		this.alto_max_simbolo = alto_max_simbolo;
	}
	public int getId_tipo_ancho() {
		return id_tipo_ancho;
	}
	public void setId_tipo_ancho(int id_tipo_ancho) {
		this.id_tipo_ancho = id_tipo_ancho;
	}
	public int getEspaciado() {
		return espaciado;
	}
	public void setEspaciado(int espaciado) {
		this.espaciado = espaciado;
	}
	public int getGrano() {
		return grano;
	}
	public void setGrano(int grano) {
		this.grano = grano;
	}
	public int getCodificacion() {
		return codificacion;
	}
	public void setCodificacion(int codificacion) {
		this.codificacion = codificacion;
	}
	public String getNombre_tipo_ancho() {
		return nombre_tipo_ancho;
	}
	public void setNombre_tipo_ancho(String nombre_tipo_ancho) {
		this.nombre_tipo_ancho = nombre_tipo_ancho;
	}
	@Override
	public String toString() {
		return "SignTypeViewModel [id_tipo_letrero=" + id_tipo_letrero + ", nombre=" + nombre + ", ancho=" + ancho
				+ ", alto=" + alto + ", espacio_lineas=" + espacio_lineas + ", alto_max_simbolo=" + alto_max_simbolo
				+ ", id_tipo_ancho=" + id_tipo_ancho + ", espaciado=" + espaciado + ", grano=" + grano
				+ ", codificacion=" + codificacion + ", nombre_tipo_ancho=" + nombre_tipo_ancho + "]";
	}
	
	
	
}
