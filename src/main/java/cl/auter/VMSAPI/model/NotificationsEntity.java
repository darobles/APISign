package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "notifications")
public class NotificationsEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int    id;
	private String username;
	private String clicked;
	private String showed;
	private int    maintenance_id;
	private String description;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getClicked() {
		return clicked;
	}
	public void setClicked(String clicked) {
		this.clicked = clicked;
	}
	public String getShowed() {
		return showed;
	}
	public void setShowed(String showed) {
		this.showed = showed;
	}
	public int getMaintenance_id() {
		return maintenance_id;
	}
	public void setMaintenance_id(int maintenance_id) {
		this.maintenance_id = maintenance_id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	
}


