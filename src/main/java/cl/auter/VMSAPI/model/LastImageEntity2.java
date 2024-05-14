package cl.auter.VMSAPI.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;


@Entity
@Table(name = "last_vms_images_2")
public class LastImageEntity2 {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "date_time")
	private LocalDateTime dateTime;
	@Column(name = "id_vms")
	private Integer idVMS; 
	@Column(name = "id_sequence")
	private Integer idSequence;
	@Column(name = "index_sequence")
	private Integer indexSequence;
	@Column(name = "image_b64")
	private String imageB64;

	public LastImageEntity2() {
		super();
		this.id = null;
		this.dateTime = null;
		this.idVMS = null;
		this.idSequence = null;
		this.indexSequence = null;
		this.imageB64 = null;
	}

	public LastImageEntity2(Integer id, LocalDateTime dateTime, Integer idVMS, Integer idSequence,
			Integer indexSequence, String imageB64) {
		super();
		this.id = id;
		this.dateTime = dateTime;
		this.idVMS = idVMS;
		this.idSequence = idSequence;
		this.indexSequence = indexSequence;
		this.imageB64 = imageB64;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public LocalDateTime getDateTime() {
		return dateTime;
	}

	public void setDateTime(LocalDateTime dateTime) {
		this.dateTime = dateTime;
	}

	public Integer getIdVMS() {
		return idVMS;
	}

	public void setIdVMS(Integer idVMS) {
		this.idVMS = idVMS;
	}

	public Integer getIdSequence() {
		return idSequence;
	}

	public void setIdSequence(Integer idSequence) {
		this.idSequence = idSequence;
	}

	public Integer getIndexSequence() {
		return indexSequence;
	}

	public void setIndexSequence(Integer indexSequence) {
		this.indexSequence = indexSequence;
	}

	public String getImageB64() {
		return imageB64;
	}

	public void setImageB64(String imageB64) {
		this.imageB64 = imageB64;
	}

	@Override
	public String toString() {
		return "LastImageEntity2 [id=" + id + ", dateTime=" + dateTime + ", idVMS=" + idVMS + ", idSequence="
				+ idSequence + ", indexSequence=" + indexSequence + ", imageB64(excerpt)=" + imageB64.substring(0, 100) + "]";
	}

	/*public String getType() {
		return (this.idSequence == null) ? "image" : "sequence";
	}*/
	

}

