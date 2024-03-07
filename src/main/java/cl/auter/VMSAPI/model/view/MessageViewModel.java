package cl.auter.VMSAPI.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name = "message_view")
public class MessageViewModel {

    @Id
    @Column(name = "id_mensaje")
    Integer id;
    @Column(name = "nombre")
    String  name;
    @Column(name = "mensaje")
    String  message;
    @Column(name = "id_alineacion")
    Integer alignmentId;
    @Column(name = "nombre_alineacion")
    String  alignmentName;
    @Column(name = "espaciado")
    Integer spacing;
    @Column(name = "color_letra")
    Integer colour;
    @Column(name = "id_tipo_letrero")
    Integer signTypeId;
    @Column(name = "nombre_tipo_letrero")
    String  signTypeName;
    @Column(name = "alto_tipo_letrero")
    Integer signTypeHeight;
    @Column(name = "ancho_tipo_letrero")
    Integer signTypeWidth;
    @Column(name = "espacio_lineas")
    Integer lineSpacing;
    @Column(name = "alto_max_simbolo")
    Integer maxSymbolHeight;
    @Column(name = "ancho_max_simbolo")
    Integer maxSymbolWidth;
    @Column(name = "id_tipo_ancho")
    Integer widthTypeId;
    @Column(name = "nombre_tipo_ancho")
    String  widthTypeName;
    @Column(name = "espaciado_tipo_letrero")
    Integer signTypeSpacing;
    @Column(name = "grano")
    Integer grain;
    @Column(name = "codificacion")
    Integer protocol;
    @Column(name = "id_grupo")
    Integer groupId;
    @Column(name = "nombre_grupo")
    String  groupName;
    @Column(name = "descripcion")
    String  description;
    @Column(name = "alto_maximo")
    Integer maxHeight;
    @Column(name = "ancho_grupo")
    Integer groupWidth;
	public MessageViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
	public Integer getId() {
		return id;
	}



	public Integer side() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
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



	public Integer getAlignmentId() {
		return alignmentId;
	}



	public void setAlignmentId(Integer alignmentId) {
		this.alignmentId = alignmentId;
	}



	public String getAlignmentName() {
		return alignmentName;
	}



	public void setAlignmentName(String alignmentName) {
		this.alignmentName = alignmentName;
	}



	public Integer getSpacing() {
		return spacing;
	}



	public void setSpacing(Integer spacing) {
		this.spacing = spacing;
	}



	public Integer getColour() {
		return colour;
	}



	public void setColour(Integer colour) {
		this.colour = colour;
	}



	public Integer getSignTypeId() {
		return signTypeId;
	}



	public void setSignTypeId(Integer signTypeId) {
		this.signTypeId = signTypeId;
	}



	public String getSignTypeName() {
		return signTypeName;
	}



	public void setSignTypeName(String signTypeName) {
		this.signTypeName = signTypeName;
	}



	public Integer getSignTypeHeight() {
		return signTypeHeight;
	}



	public void setSignTypeHeight(Integer signTypeHeight) {
		this.signTypeHeight = signTypeHeight;
	}



	public Integer getSignTypeWidth() {
		return signTypeWidth;
	}



	public void setSignTypeWidth(Integer signTypeWidth) {
		this.signTypeWidth = signTypeWidth;
	}



	public Integer getLineSpacing() {
		return lineSpacing;
	}



	public void setLineSpacing(Integer lineSpacing) {
		this.lineSpacing = lineSpacing;
	}



	public Integer getMaxSymbolHeight() {
		return maxSymbolHeight;
	}



	public void setMaxSymbolHeight(Integer maxSymbolHeight) {
		this.maxSymbolHeight = maxSymbolHeight;
	}



	public Integer getMaxSymbolWidth() {
		return maxSymbolWidth;
	}



	public void setMaxSymbolWidth(Integer maxSymbolWidth) {
		this.maxSymbolWidth = maxSymbolWidth;
	}



	public Integer getWidthTypeId() {
		return widthTypeId;
	}



	public void setWidthTypeId(Integer widthTypeId) {
		this.widthTypeId = widthTypeId;
	}



	public String getWidthTypeName() {
		return widthTypeName;
	}



	public void setWidthTypeName(String widthTypeName) {
		this.widthTypeName = widthTypeName;
	}



	public Integer getSignTypeSpacing() {
		return signTypeSpacing;
	}



	public void setSignTypeSpacing(Integer signTypeSpacing) {
		this.signTypeSpacing = signTypeSpacing;
	}



	public Integer getGrain() {
		return grain;
	}



	public void setGrain(Integer grain) {
		this.grain = grain;
	}



	public Integer getProtocol() {
		return protocol;
	}



	public void setProtocol(Integer protocol) {
		this.protocol = protocol;
	}



	public Integer getGroupId() {
		return groupId;
	}



	public void setGroupId(Integer groupId) {
		this.groupId = groupId;
	}



	public String getGroupName() {
		return groupName;
	}



	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Integer getMaxHeight() {
		return maxHeight;
	}



	public void setMaxHeight(Integer maxHeight) {
		this.maxHeight = maxHeight;
	}



	public Integer getGroupWidth() {
		return groupWidth;
	}



	public void setGroupWidth(Integer groupWidth) {
		this.groupWidth = groupWidth;
	}



	@Override
	public String toString() {
		return "MessageModel [id=" + id + ", name=" + name + ", message=" + message + ", alignmentId=" + alignmentId
				+ ", alignmentName=" + alignmentName + ", spacing=" + spacing + ", colour=" + colour + ", signTypeId="
				+ signTypeId + ", signTypeName=" + signTypeName + ", signTypeHeight=" + signTypeHeight
				+ ", signTypeWidth=" + signTypeWidth + ", lineSpacing=" + lineSpacing + ", maxSymbolHeight="
				+ maxSymbolHeight + ", maxSymbolWidth=" + maxSymbolWidth + ", widthTypeId=" + widthTypeId
				+ ", widthTypeName=" + widthTypeName + ", signTypeSpacing=" + signTypeSpacing + ", grain=" + grain
				+ ", protocol=" + protocol + ", groupId=" + groupId + ", groupName=" + groupName + ", description="
				+ description + ", maxHeight=" + maxHeight + ", groupWidth=" + groupWidth + "]";
	}
    
    
    
}