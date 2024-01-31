package cl.auter.VMSAPI.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "mensajes")
public class MessageModel implements Serializable {
	@Id
	@Column(name = "id_mensaje")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "id_tipo_letrero")
	private Integer type_sign_id;
	@Column(name = "nombre")
	private String name;
	@Column(name = "mensaje")
    private String message;
	@Column(name = "id_alineacion")
    private Integer  alignment_id;
	@Column(name = "espaciado")
    private Integer spacing;
	@Column(name = "id_grupo")
    private Integer group_id;
	@Column(name = "color_letra")
    private Integer font_color;
	public MessageModel() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Integer getType_sign_id() {
		return type_sign_id;
	}
	public void setType_sign_id(Integer type_sign_id) {
		this.type_sign_id = type_sign_id;
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
	public Integer getAlignment_id() {
		return alignment_id;
	}
	public void setAlignment_id(Integer alignment_id) {
		this.alignment_id = alignment_id;
	}
	public Integer getSpacing() {
		return spacing;
	}
	public void setSpacing(Integer spacing) {
		this.spacing = spacing;
	}
	public Integer getGroup_id() {
		return group_id;
	}
	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}
	public Integer getFont_color() {
		return font_color;
	}
	public void setFont_color(Integer font_color) {
		this.font_color = font_color;
	}
	@Override
	public String toString() {
		return "MessageEntity [id=" + id + ", type_sign_id=" + type_sign_id + ", name=" + name + ", message=" + message
				+ ", alignment_id=" + alignment_id + ", spacing=" + spacing + ", group_id=" + group_id + ", font_color="
				+ font_color + "]";
	}
	
	
    
}
