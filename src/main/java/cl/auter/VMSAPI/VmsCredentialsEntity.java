package cl.auter.VMSAPI;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "vms_credentials")
public class VmsCredentialsEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@JsonProperty("vms_id")
	private int vmsId;
	private String cam_username;
	private String cam_password;
	private String ip_address;
	private String port;
	
	
	
	public String getIp_address() {
		return ip_address;
	}
	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}
	public String getPort() {
		return port;
	}
	public void setPort(String port) {
		this.port = port;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getVms_id() {
		return vmsId;
	}
	public void setVms_id(int vms_id) {
		this.vmsId = vms_id;
	}
	public String getCam_username() {
		return cam_username;
	}
	public void setCam_username(String cam_username) {
		this.cam_username = cam_username;
	}
	public String getCam_password() {
		return cam_password;
	}
	public void setCam_password(String cam_password) {
		this.cam_password = cam_password;
	}
	
	
}
