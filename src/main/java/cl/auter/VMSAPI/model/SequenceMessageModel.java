package cl.auter.VMSAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "mensaje_secuencia")
public class SequenceMessageModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	Integer id;
	@JsonProperty("sequenceId")
	@Column(name = "id_secuencia")
    Integer sequence_id;
	@Column(name = "id_mensaje")
	Integer message_id;
	@JsonProperty("index")
	@Column(name = "indice")
    Integer index;
	@Column(name = "tiempo")
    Integer time;

    public SequenceMessageModel() {
    	super();
    }

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getSequence_id() {
		return sequence_id;
	}

	public void setSequence_id(Integer sequence_id) {
		this.sequence_id = sequence_id;
	}

	public Integer getMessage_id() {
		return message_id;
	}

	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public Integer getTime() {
		return time;
	}

	public void setTime(Integer time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "SequenceMessageModel [id=" + id + ", sequence_id=" + sequence_id + ", message_id=" + message_id
				+ ", index=" + index + ", time=" + time + "]";
	}
    
    

}
