package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "secuencia")
public class SequenceModel {
	@Id
	int id_secuencia;
	int id_tipo_letrero;
	String nombre;
	
	public SequenceModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId_secuencia() {
		return id_secuencia;
	}
	public void setId_secuencia(int id_secuencia) {
		this.id_secuencia = id_secuencia;
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
	@Override
	public String toString() {
		return "SecuenciaModel [id_secuencia=" + id_secuencia + ", id_tipo_letrero=" + id_tipo_letrero + ", nombre="
				+ nombre + "]";
	}
	
}
