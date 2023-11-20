package cl.auter.VMSAPI;

import java.beans.Transient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.core.util.ByteArrayBuilder;


@Entity
@Table(name = "last_vms_images")
public class LastImageEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int    id;
	private int    vmsId;
	private String type; 
	private byte[] image;
	private int    sequence_id;
	private String date;
	@javax.persistence.Transient
	private String strImg;

	public String getStrImg() {
		return strImg;
	}
	public void setStrImg(String strImg) {
		this.strImg = strImg;
	}
	public int getVms_id() {
		return vmsId;
	}
	public void setVms_id(int vms_id) {
		this.vmsId = vms_id;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public byte[] getImage() {
		return image;
	}
	public void setImage(byte[] image) {
		this.image = image;
	}
	public int getSequence_id() {
		return sequence_id;
	}
	public void setSequence_id(int sequence_id) {
		this.sequence_id = sequence_id;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
	
	

}

