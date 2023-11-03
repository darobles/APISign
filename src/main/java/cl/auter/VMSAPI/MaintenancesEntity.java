package cl.auter.VMSAPI;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "maintenances")
public class MaintenancesEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int    id;
	
	
	private int    vms_code;
	private String location;
	@JsonProperty("date")
	private String mant_date;
	private String type; 
	private String responsable;
	private String status;
	private String observation;
	
	@Override
	public String toString() {
		return "MaintenancesEntity [id=" + id + ", vms_code=" + vms_code + ", location=" + location + ", mant_date="
				+ mant_date + ", type=" + type + ", responsable=" + responsable + ", status=" + status + "]";
	}
	public String getObservation() {
		return observation;
	}
	public void setObservation(String observation) {
		this.observation = observation;
	}
	//@JsonIgnore --> para omitir un dato
	//@transient  --> para variables que no existen en BD
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVms_code() {
		return vms_code;
	}
	public void setVms_code(int vms_code) {
		this.vms_code = vms_code;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public String getDate() {
		return mant_date;
	}
	public void setDate(String date) {
		this.mant_date = date;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getResponsable() {
		return responsable;
	}
	public void setResponsable(String responsable) {
		this.responsable = responsable;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
}
