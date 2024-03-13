package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "mensajes")
public class MensajeModel {
	@Id
	@JsonProperty("message_id")
	int id_mensaje;
	@JsonProperty("sign_type_id")
	int id_tipo_letrero;
	@JsonProperty("name")
	String nombre;
	@JsonProperty("message")
	String mensaje;
	@JsonProperty("alignmentId")
	int id_alineacion;
	@JsonProperty("spacing")
	int espaciado;
	@JsonProperty("groupId")
	int id_grupo;
	@JsonProperty("text_color")
	int color_letra;
	public MensajeModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_mensaje() {
		return id_mensaje;
	}
	public void setId_mensaje(int id_mensaje) {
		this.id_mensaje = id_mensaje;
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
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public int getId_alineacion() {
		return id_alineacion;
	}
	public void setId_alineacion(int id_alineacion) {
		this.id_alineacion = id_alineacion;
	}
	public int getEspaciado() {
		return espaciado;
	}
	public void setEspaciado(int espaciado) {
		this.espaciado = espaciado;
	}
	public int getId_grupo() {
		return id_grupo;
	}
	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}
	public int getColor_letra() {
		return color_letra;
	}
	public void setColor_letra(int color_letra) {
		this.color_letra = color_letra;
	}
	@Override
	public String toString() {
		return "MensajeModel [id_mensaje=" + id_mensaje + ", id_tipo_letrero=" + id_tipo_letrero + ", nombre=" + nombre
				+ ", mensaje=" + mensaje + ", id_alineacion=" + id_alineacion + ", espaciado=" + espaciado
				+ ", id_grupo=" + id_grupo + ", color_letra=" + color_letra + "]";
	}
	
	
}
