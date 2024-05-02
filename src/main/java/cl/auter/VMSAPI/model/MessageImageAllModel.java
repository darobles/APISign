package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
public class MessageImageAllModel {
	@Id
	@JsonProperty("id")
	int id;
	@JsonProperty("messageId")
	int id_mensaje;
	@JsonProperty("side")
	int ubicacion_hrz;
	@JsonProperty("messageName")
	String nombre_mensaje;
	@JsonProperty("imageB64")
	String imagen_b64;
	
	public MessageImageAllModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public MessageImageAllModel(int id_mensaje) {
		super();
		this.id_mensaje = id_mensaje;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getNombre_mensaje() {
		return nombre_mensaje;
	}

	public void setNombre_mensaje(String nombre_mensaje) {
		this.nombre_mensaje = nombre_mensaje;
	}

	public String getImagen_b64() {
		return imagen_b64;
	}

	public void setImagen_b64(String imagen_b64) {
		this.imagen_b64 = imagen_b64;
	}

	@Override
	public String toString() {
		return "MessageImageAllModel [id=" + id + ", id_mensaje=" + id_mensaje + ", ubicacion_hrz=" + ubicacion_hrz
				+ ", nombre_mensaje=" + nombre_mensaje + ", imagen_b64=" + imagen_b64 + "]";
	}
	
}
