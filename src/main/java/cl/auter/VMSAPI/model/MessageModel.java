package cl.auter.VMSAPI.model;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "mensajes")
public class MessageModel{
	@Id
	@Column(name = "id_mensaje")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int message_id;
	@Column(name = "id_tipo_letrero")
	@JsonProperty("type_sign_id")
	private int type;
	@Column(name = "nombre")
	private String name;
	@Column(name = "mensaje")
    private String message;
	@Column(name = "id_alineacion")
    private int  alignmentId;
	@Column(name = "espaciado")
    private int spacing;
	@Column(name = "id_grupo")
    private int group_id;
	@Column(name = "color_letra")
    private int font_color;
	@Column(name = "id_letrero")
    private int sign_id;
	
	public MessageModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getMessage_id() {
		return message_id;
	}



	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}



	public int getType() {
		return type;
	}



	public void setType(int type) {
		this.type = type;
	}



	public String getName() {
		return name;
	}



	public void setName(String name) {
		this.name = name;
	}



	public String getMessage() {
		return message;
	}



	public void setMessage(String message) {
		this.message = message;
	}



	public int getAlignmentId() {
		return alignmentId;
	}

	public void setAlignmentId(int alignmentId) {
		this.alignmentId = alignmentId;
	}

	public int getSpacing() {
		return spacing;
	}



	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}



	public int getGroup_id() {
		return group_id;
	}



	public void setGroup_id(int group_id) {
		this.group_id = group_id;
	}



	public int getFont_color() {
		return font_color;
	}

	public void setFont_color(int font_color) {
		this.font_color = font_color;
	}

	public int getSign_id() {
		return sign_id;
	}

	public void setSign_id(int sign_id) {
		this.sign_id = sign_id;
	}

	@Override
	public String toString() {
		return "MessageModel [message_id=" + message_id + ", type=" + type + ", name=" + name + ", message=" + message
				+ ", alignmentId=" + alignmentId + ", spacing=" + spacing + ", group_id=" + group_id + ", font_color="
				+ font_color + ", sign_id=" + sign_id + "]";
	}

	
}
