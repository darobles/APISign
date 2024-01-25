package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "letreros_com")
public class LetreroComModel {
	int id_letrero;
	int id_conexion;
	String fono;
	int clave;
	String canal;
	int direccion;
	int port;
	String camara;
	
	
	
	public LetreroComModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_letrero() {
		return id_letrero;
	}
	public void setId_letrero(int id_letrero) {
		this.id_letrero = id_letrero;
	}
	public int getId_conexion() {
		return id_conexion;
	}
	public void setId_conexion(int id_conexion) {
		this.id_conexion = id_conexion;
	}
	public String getFono() {
		return fono;
	}
	public void setFono(String fono) {
		this.fono = fono;
	}
	public int getClave() {
		return clave;
	}
	public void setClave(int clave) {
		this.clave = clave;
	}
	public String getCanal() {
		return canal;
	}
	public void setCanal(String canal) {
		this.canal = canal;
	}
	public int getDireccion() {
		return direccion;
	}
	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public String getCamara() {
		return camara;
	}
	public void setCamara(String camara) {
		this.camara = camara;
	}
	@Override
	public String toString() {
		return "LetreroComModel [id_letrero=" + id_letrero + ", id_conexion=" + id_conexion + ", fono=" + fono
				+ ", clave=" + clave + ", canal=" + canal + ", direccion=" + direccion + ", port=" + port + ", camara="
				+ camara + "]";
	}
	
	
	
}
