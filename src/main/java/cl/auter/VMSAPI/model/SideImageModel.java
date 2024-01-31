package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mensaje_imagen")
public class SideImageModel {
	@Id
	Integer id_mensaje;
	Integer ubicacion_hrz;
	Integer ubicacion_vrt;
	Integer id_usuario;
	String imagen_b64;

	public SideImageModel() {
		super();
	}

	public Integer getId_mensaje() {
		return id_mensaje;
	}

	public void setId_mensaje(Integer id_mensaje) {
		this.id_mensaje = id_mensaje;
	}

	public Integer getUbicacion_hrz() {
		return ubicacion_hrz;
	}

	public void setUbicacion_hrz(Integer ubicacion_hrz) {
		this.ubicacion_hrz = ubicacion_hrz;
	}

	public Integer getUbicacion_vrt() {
		return ubicacion_vrt;
	}

	public void setUbicacion_vrt(Integer ubicacion_vrt) {
		this.ubicacion_vrt = ubicacion_vrt;
	}

	public Integer getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(Integer id_usuario) {
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
		return "SideImageModel [id_mensaje=" + id_mensaje + ", ubicacion_hrz=" + ubicacion_hrz + ", ubicacion_vrt="
				+ ubicacion_vrt + ", id_usuario=" + id_usuario + ", imagen_b64=" + imagen_b64 + "]";
	}

}
