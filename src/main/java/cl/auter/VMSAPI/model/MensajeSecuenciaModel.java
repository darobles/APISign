package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mensaje_secuencia")
public class MensajeSecuenciaModel {
	@Id
	int id_secuencia;
	int id_mensaje;
	int indice;
	int tiempo;
	public MensajeSecuenciaModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_secuencia() {
		return id_secuencia;
	}
	public void setId_secuencia(int id_secuencia) {
		this.id_secuencia = id_secuencia;
	}
	public int getId_mensaje() {
		return id_mensaje;
	}
	public void setId_mensaje(int id_mensaje) {
		this.id_mensaje = id_mensaje;
	}
	public int getIndice() {
		return indice;
	}
	public void setIndice(int indice) {
		this.indice = indice;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	@Override
	public String toString() {
		return "MensajeSecuenciaModel [id_secuencia=" + id_secuencia + ", id_mensaje=" + id_mensaje + ", indice="
				+ indice + ", tiempo=" + tiempo + "]";
	}
	
}
