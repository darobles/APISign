package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mensaje_imagen")
public class MessageImageModel {
	@Id
	int id_mensaje;
	int ubicacion_hrz;
	int ubicacion_vrt;
	int id_usuario;
	String imagen_b64;
	
	public MessageImageModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MessageImageModel(int id_mensaje) {
		super();
		this.id_mensaje = id_mensaje;
	}

	public int getId_mensaje() {
		return id_mensaje;
	}
	public void setId_mensaje(int id_mensaje) {
		this.id_mensaje = id_mensaje;
	}
	public int getUbicacion_hrz() {
		return ubicacion_hrz;
	}
	public void setUbicacion_hrz(int ubicacion_hrz) {
		this.ubicacion_hrz = ubicacion_hrz;
	}
	public int getUbicacion_vrt() {
		return ubicacion_vrt;
	}
	public void setUbicacion_vrt(int ubicacion_vrt) {
		this.ubicacion_vrt = ubicacion_vrt;
	}
	public int getId_usuario() {
		return id_usuario;
	}
	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}
	public String getImagen_b64() {
		return imagen_b64;
	}
	public void setImagen_b64(String imagen_b64) {
		this.imagen_b64 = imagen_b64;
	}
	@Override
	public String toString() {
		return "MensajeImagenModel [id_mensaje=" + id_mensaje + ", ubicacion_hrz=" + ubicacion_hrz + ", ubicacion_vrt="
				+ ubicacion_vrt + ", id_usuario=" + id_usuario + ", imagen_b64=" + imagen_b64 + "]";
	}
	
	
}
