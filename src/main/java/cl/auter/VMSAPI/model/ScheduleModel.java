package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "schedule_vms")
public class ScheduleModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	Integer id;
	Integer id_vms;
	String  hour;
	Integer days;
	Integer id_message;
	Integer id_sequence;
	
	public ScheduleModel() {
		super();
	}
	
	public ScheduleModel(Integer id, Integer id_vms, String hour, Integer days, Integer id_message,
			Integer id_sequence) {
		super();
		this.id = id;
		this.id_vms = id_vms;
		this.hour = hour;
		this.days = days;
		this.id_message = id_message;
		this.id_sequence = id_sequence;
	}
	
	public Integer getId() {
		return id;
	}
	
	public void setId(Integer id) {
		this.id = id;
	}
	
	public Integer getId_vms() {
		return id_vms;
	}
	
	public void setId_vms(Integer id_vms) {
		this.id_vms = id_vms;
	}
	
	public String getHour() {
		return hour;
	}
	
	public void setHour(String hour) {
		this.hour = hour;
	}
	
	public Integer getDays() {
		return days;
	}
	
	public void setDays(Integer days) {
		this.days = days;
	}
	
	public Integer getId_message() {
		return id_message;
	}
	
	public void setId_message(Integer id_message) {
		this.id_message = id_message;
	}
	
	public Integer getId_sequence() {
		return id_sequence;
	}
	
	public void setId_sequence(Integer id_sequence) {
		this.id_sequence = id_sequence;
	}

	@Override
	public String toString() {
		return "ScheduleModel [id=" + id + ", id_vms=" + id_vms + ", hour=" + hour + ", days=" + days + ", id_message="
				+ id_message + ", id_sequence=" + id_sequence + "]";
	}

}
