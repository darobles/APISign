package cl.auter.VMSAPI.model;

import java.sql.Timestamp;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mensaje_imagen")
public class MensajeLogModel {
	@Id
	int id_log;
	int id_mensaje;
	int id_usuario;
	int id_origen;
	Timestamp fecha_hora;
	String mensaje;
	boolean en_bd;
	
	public MensajeLogModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId_log() {
		return id_log;
	}

	public void setId_log(int id_log) {
		this.id_log = id_log;
	}

	public int getId_mensaje() {
		return id_mensaje;
	}
	
	public void setId_mensaje(int id_mensaje) {
		this.id_mensaje = id_mensaje;
	}

	public int getId_usuario() {
		return id_usuario;
	}

	public void setId_usuario(int id_usuario) {
		this.id_usuario = id_usuario;
	}

	public int getId_origen() {
		return id_origen;
	}

	public void setId_origen(int id_origen) {
		this.id_origen = id_origen;
	}

	public Timestamp getFecha_hora() {
		return fecha_hora;
	}

	public void setFecha_hora(Timestamp fecha_hora) {
		this.fecha_hora = fecha_hora;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public boolean isEn_bd() {
		return en_bd;
	}
	public void setEn_bd(boolean en_bd) {
		this.en_bd = en_bd;
	}
	
	@Override
	public String toString() {
		return "MensajeLogModel [id_log=" + id_log + ", id_mensaje=" + id_mensaje + ", id_usuario=" + id_usuario
				+ ", id_origen=" + id_origen + ", fecha_hora=" + fecha_hora + ", mensaje=" + mensaje + ", en_bd="
				+ en_bd + "]";
	}
	
	
	
	
}
