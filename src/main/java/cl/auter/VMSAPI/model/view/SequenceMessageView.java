package cl.auter.VMSAPI.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "sequence_message_view")
public class SequenceMessageView {
	
	@Column(name = "id_secuencia")
	private Integer sequence_id;
	@Id
	@Column(name = "id_mensaje")
	private Integer message_id;
	@Column(name = "nombre_mensaje")
	@JsonProperty("messageName")
	private String message_name;
	@Column(name = "indice")
	private Integer index;
	@Column(name = "tiempo")
	private Integer time;
	@Column(name = "codificacion")
	private Integer coding;
	@Column(name = "mensaje")
	private String message;
	@Column(name = "id_alineacion")
	private Integer alignment_id;
	@Column(name = "nombre_alineacion")
	private String name_alignment;
	@Column(name = "espaciado")
	private Integer spacing;
	@Column(name = "color_letra")
	private Integer font_color;
	@Column(name = "id_tipo_letrero")
	private Integer type_sign_id;
	@Column(name = "nombre_tipo_letrero")
	private String type_sign_name;
	@Column(name = "alto_tipo_letrero")
	private Integer type_sign_height;
	@Column(name = "ancho_tipo_letrero")
	private Integer type_sign_whidth;
	@Column(name = "espacio_lineas")
	private Integer lines_spacing;
	@Column(name = "alto_max_simbolo")
	private Integer maximum_symbol_height;
	@Column(name = "ancho_max_simbolo")
	private Integer maximum_symbol_whidth;
	@Column(name = "id_tipo_ancho")
	private Integer type_width_id;
	@Column(name = "nombre_tipo_ancho")
	private String type_width_name;
	@Column(name = "espaciado_tipo_letrero")
	private Integer type_sign_spacing;
	@Column(name = "grano")
	private Integer grain;
	@Column(name = "id_grupo")
	private Integer group_id;
	@Column(name = "nombre_grupo")
	private String group_name;
	@Column(name = "descripcion")
	private String description;
	@Column(name = "alto_maximo")
	private Integer maximum_height;
	@Column(name = "ancho_grupo")
	private Integer group_whidth;
	
	public SequenceMessageView() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SequenceMessageView(Integer sequence_id, Integer message_id, String message_name, Integer index,
			Integer time, Integer coding, String message, Integer alignment_id, String name_alignment, Integer spacing,
			Integer font_color, Integer type_sign_id, String type_sign_name, Integer type_sign_height,
			Integer type_sign_whidth, Integer lines_spacing, Integer maximum_symbol_height,
			Integer maximum_symbol_whidth, Integer type_width_id, String type_width_name, Integer type_sign_spacing,
			Integer grain, Integer group_id, String group_name, String description, Integer maximum_height,
			Integer group_whidth) {
		super();
		this.sequence_id = sequence_id;
		this.message_id = message_id;
		this.message_name = message_name;
		this.index = index;
		this.time = time;
		this.coding = coding;
		this.message = message;
		this.alignment_id = alignment_id;
		this.name_alignment = name_alignment;
		this.spacing = spacing;
		this.font_color = font_color;
		this.type_sign_id = type_sign_id;
		this.type_sign_name = type_sign_name;
		this.type_sign_height = type_sign_height;
		this.type_sign_whidth = type_sign_whidth;
		this.lines_spacing = lines_spacing;
		this.maximum_symbol_height = maximum_symbol_height;
		this.maximum_symbol_whidth = maximum_symbol_whidth;
		this.type_width_id = type_width_id;
		this.type_width_name = type_width_name;
		this.type_sign_spacing = type_sign_spacing;
		this.grain = grain;
		this.group_id = group_id;
		this.group_name = group_name;
		this.description = description;
		this.maximum_height = maximum_height;
		this.group_whidth = group_whidth;
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

	public String getMessage_name() {
		return message_name;
	}

	public void setMessage_name(String message_name) {
		this.message_name = message_name;
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

	public Integer getCoding() {
		return coding;
	}

	public void setCoding(Integer coding) {
		this.coding = coding;
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

	public String getName_alignment() {
		return name_alignment;
	}

	public void setName_alignment(String name_alignment) {
		this.name_alignment = name_alignment;
	}

	public Integer getSpacing() {
		return spacing;
	}

	public void setSpacing(Integer spacing) {
		this.spacing = spacing;
	}

	public Integer getFont_color() {
		return font_color;
	}

	public void setFont_color(Integer font_color) {
		this.font_color = font_color;
	}

	public Integer getType_sign_id() {
		return type_sign_id;
	}

	public void setType_sign_id(Integer type_sign_id) {
		this.type_sign_id = type_sign_id;
	}

	public String getType_sign_name() {
		return type_sign_name;
	}

	public void setType_sign_name(String type_sign_name) {
		this.type_sign_name = type_sign_name;
	}

	public Integer getType_sign_height() {
		return type_sign_height;
	}

	public void setType_sign_height(Integer type_sign_height) {
		this.type_sign_height = type_sign_height;
	}

	public Integer getType_sign_whidth() {
		return type_sign_whidth;
	}

	public void setType_sign_whidth(Integer type_sign_whidth) {
		this.type_sign_whidth = type_sign_whidth;
	}

	public Integer getLines_spacing() {
		return lines_spacing;
	}

	public void setLines_spacing(Integer lines_spacing) {
		this.lines_spacing = lines_spacing;
	}

	public Integer getMaximum_symbol_height() {
		return maximum_symbol_height;
	}

	public void setMaximum_symbol_height(Integer maximum_symbol_height) {
		this.maximum_symbol_height = maximum_symbol_height;
	}

	public Integer getMaximum_symbol_whidth() {
		return maximum_symbol_whidth;
	}

	public void setMaximum_symbol_whidth(Integer maximum_symbol_whidth) {
		this.maximum_symbol_whidth = maximum_symbol_whidth;
	}

	public Integer getType_width_id() {
		return type_width_id;
	}

	public void setType_width_id(Integer type_width_id) {
		this.type_width_id = type_width_id;
	}

	public String getType_width_name() {
		return type_width_name;
	}

	public void setType_width_name(String type_width_name) {
		this.type_width_name = type_width_name;
	}

	public Integer getType_sign_spacing() {
		return type_sign_spacing;
	}

	public void setType_sign_spacing(Integer type_sign_spacing) {
		this.type_sign_spacing = type_sign_spacing;
	}

	public Integer getGrain() {
		return grain;
	}

	public void setGrain(Integer grain) {
		this.grain = grain;
	}

	public Integer getGroup_id() {
		return group_id;
	}

	public void setGroup_id(Integer group_id) {
		this.group_id = group_id;
	}

	public String getGroup_name() {
		return group_name;
	}

	public void setGroup_name(String group_name) {
		this.group_name = group_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Integer getMaximum_height() {
		return maximum_height;
	}

	public void setMaximum_height(Integer maximum_height) {
		this.maximum_height = maximum_height;
	}

	public Integer getGroup_whidth() {
		return group_whidth;
	}

	public void setGroup_whidth(Integer group_whidth) {
		this.group_whidth = group_whidth;
	}

	@Override
	public String toString() {
		return "SequenceMessageView [sequence_id=" + sequence_id + ", message_id=" + message_id + ", message_name="
				+ message_name + ", index=" + index + ", time=" + time + ", coding=" + coding + ", message=" + message
				+ ", alignment_id=" + alignment_id + ", name_alignment=" + name_alignment + ", spacing=" + spacing
				+ ", font_color=" + font_color + ", type_sign_id=" + type_sign_id + ", type_sign_name=" + type_sign_name
				+ ", type_sign_height=" + type_sign_height + ", type_sign_whidth=" + type_sign_whidth
				+ ", lines_spacing=" + lines_spacing + ", maximum_symbol_height=" + maximum_symbol_height
				+ ", maximum_symbol_whidth=" + maximum_symbol_whidth + ", type_width_id=" + type_width_id
				+ ", type_width_name=" + type_width_name + ", type_sign_spacing=" + type_sign_spacing + ", grain="
				+ grain + ", group_id=" + group_id + ", group_name=" + group_name + ", description=" + description
				+ ", maximum_height=" + maximum_height + ", group_whidth=" + group_whidth + "]";
	}
	
	
	
	
}
