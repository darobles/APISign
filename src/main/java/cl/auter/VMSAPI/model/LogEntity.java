package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "log_register")
public class LogEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int    id;
	private String date; 
	private String event_code;
	private String event_user;
	private String description;
	private String device_type;
	private int    device_id;
	
	@Override
	public String toString() {
		return "LogEntity [id=" + id + ", date=" + date + ", event_code=" + event_code + ", event_user=" + event_user
				+ ", description=" + description + ", device_type=" + device_type + ", device_id=" + device_id + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getEvent_code() {
		return event_code;
	}

	public void setEvent_code(String event_code) {
		this.event_code = event_code;
	}

	public String getEvent_user() {
		return event_user;
	}

	public void setEvent_user(String event_user) {
		this.event_user = event_user;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getDevice_type() {
		return device_type;
	}

	public void setDevice_type(String device_type) {
		this.device_type = device_type;
	}

	public int getDevice_id() {
		return device_id;
	}

	public void setDevice_id(int device_id) {
		this.device_id = device_id;
	}
	
	
}
