package cl.auter.VMSAPI.model.view;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.fasterxml.jackson.annotation.JsonProperty;

import cl.auter.VMSAPI.model.Cabinet;

@Entity
@Table(name = "vms_view")
public class VMSViewModel {
	@Id
	@JsonProperty("id")
	int id_letrero;
	@JsonProperty("name")
	String nombre;
	@JsonProperty("location")
	String ubicacion;
	@JsonProperty("latitude")
	String latitud;
	@JsonProperty("longitude")
	String longitud;
	@JsonProperty("signTypeId")
	int id_tipo_letrero;
	@JsonProperty("signTypeName")
	String nombre_tipo_letrero;
	@JsonProperty("protocolName")
	String protocol_name;
	@JsonProperty("protocolId")
	int codificacion;
	@JsonProperty("obs")
	String obs;
	@JsonProperty("connectionId")
	int id_conexion;
	@JsonProperty("connectionName")
	String nombre_conexion;
	@JsonProperty("phoneOrIP")
	String fono;
	@JsonProperty("key")
	int clave;
	@JsonProperty("portCOM")
	String canal;
	@JsonProperty("address")
	int direccion;
	@JsonProperty("port")
	int port;
	@JsonProperty("camera")
	String camara;
	@JsonProperty("cameraType")
	String camera_type;
	@Transient
	List<Cabinet> cabinets;
	
	public VMSViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId_letrero() {
		return id_letrero;
	}
	public void setId_letrero(int id_letrero) {
		this.id_letrero = id_letrero;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getUbicacion() {
		return ubicacion;
	}
	public void setUbicacion(String ubicacion) {
		this.ubicacion = ubicacion;
	}
	public String getLatitud() {
		return latitud;
	}
	public void setLatitud(String latitud) {
		this.latitud = latitud;
	}
	public String getLongitud() {
		return longitud;
	}
	public void setLongitud(String longitud) {
		this.longitud = longitud;
	}
	public int getId_tipo_letrero() {
		return id_tipo_letrero;
	}
	public void setId_tipo_letrero(int id_tipo_letrero) {
		this.id_tipo_letrero = id_tipo_letrero;
	}
	public String getNombre_tipo_letrero() {
		return nombre_tipo_letrero;
	}
	public void setNombre_tipo_letrero(String nombre_tipo_letrero) {
		this.nombre_tipo_letrero = nombre_tipo_letrero;
	}
	
	public String getProtocol_name() {
		return protocol_name;
	}

	public void setProtocol_name(String protocol_name) {
		this.protocol_name = protocol_name;
	}

	public int getCodificacion() {
		return codificacion;
	}
	public void setCodificacion(int codificacion) {
		this.codificacion = codificacion;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public int getId_conexion() {
		return id_conexion;
	}
	public void setId_conexion(int id_conexion) {
		this.id_conexion = id_conexion;
	}
	public String getNombre_conexion() {
		return nombre_conexion;
	}
	public void setNombre_conexion(String nombre_conexion) {
		this.nombre_conexion = nombre_conexion;
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

	public String getCamera_type() {
		return camera_type;
	}

	public void setCamera_type(String camera_type) {
		this.camera_type = camera_type;
	}
	
	

	public List<Cabinet> getCabinets() {
		return cabinets;
	}

	public void setCabinets(List<Cabinet> cabinets) {
		this.cabinets = cabinets;
	}

	@Override
	public String toString() {
		return "VMSViewModel [id_letrero=" + id_letrero + ", nombre=" + nombre + ", ubicacion=" + ubicacion
				+ ", latitud=" + latitud + ", longitud=" + longitud + ", id_tipo_letrero=" + id_tipo_letrero
				+ ", nombre_tipo_letrero=" + nombre_tipo_letrero + ", codificacion=" + codificacion + ", obs=" + obs
				+ ", id_conexion=" + id_conexion + ", nombre_conexion=" + nombre_conexion + ", fono=" + fono
				+ ", clave=" + clave + ", canal=" + canal + ", direccion=" + direccion + ", port=" + port + ", camara="
				+ camara + ", camara_type=" + camera_type + "]";
	}
	
	
}
