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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Integer id;
	@Column(name = "id_secuencia")
	private Integer sequence_id;
	@Column(name = "indice")
    private Integer order;
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
		this.order = index;
		this.time = time;
		this.message_id = message_id;
	
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

	public Integer getOrder() {
		return order;
	}

	public void setOrder(Integer order) {
		this.order = order;
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
		return "SequenceMessageEntity [id=" + id + ", sequence_id=" + sequence_id + ", order=" + order + ", time="
				+ time + ", message_id=" + message_id + "]";
	}

}
