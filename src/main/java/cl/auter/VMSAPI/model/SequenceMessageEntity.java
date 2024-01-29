package cl.auter.VMSAPI.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mensaje_secuencia")
public class SequenceMessageEntity {
	@Id
	@Column(name = "id_secuencia")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "indice")
    private Integer index;
	@Column(name = "tiempo")
    private Integer time;
	@Column(name = "id_mensaje")
    private Integer message_id;

	public SequenceMessageEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public SequenceMessageEntity(Integer id, Integer index, Integer time, Integer message_id) {
		super();
		this.id = id;
		this.index = index;
		this.time = time;
		this.message_id = message_id;
	
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
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
	
	public Integer getMessage_id() {
		return message_id;
	}

	public void setMessage_id(Integer message_id) {
		this.message_id = message_id;
	}

	@Override
	public String toString() {
		return "SequenceMessageEntity [id=" + id + ", index=" + index + ", time=" + time + ", message_id=" + message_id
				+ "]";
	}	
    
    
}
