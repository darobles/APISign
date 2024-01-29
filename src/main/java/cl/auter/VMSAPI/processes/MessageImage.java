package cl.auter.VMSAPI.processes;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mensaje_imagen")
public class MessageImage {
	@Id
	int id_mensaje;
	int ubicacion_hrz;
	int ubicacion_vrt;
	int id_usuario;
	String imagen_b64;
	
	public MessageImage() {
		super();
		// TODO Auto-generated constructor stub
	}
	public MessageImage(int messageId) {
		super();
        this.message  = dao.getMessage(messageId);
        this.customText = null;
        if (message != null) {
            this.symbols      = dao.getSymbols(message.getGroupId(), message.getMessage());
            this.segmentWidth = this.message.getProtocol() == Constants.ID_DIANMING ? DIANMING.DM_SEGMENT_WIDTH : this.message.getSignTypeWidth();
            build();
        } else {
            this.symbols      = null;
            this.segmentWidth = null;
        }

		// TODO Auto-generated constructor stub
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
		return "MessageImagenModel [id_mensaje=" + id_mensaje + ", ubicacion_hrz=" + ubicacion_hrz + ", ubicacion_vrt="
				+ ubicacion_vrt + ", id_usuario=" + id_usuario + ", imagen_b64=" + imagen_b64 + "]";
	}
	
	
}
