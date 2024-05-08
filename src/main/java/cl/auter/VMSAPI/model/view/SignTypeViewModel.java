package cl.auter.VMSAPI.model.view;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "signtype_view")
public class SignTypeViewModel {
	
	@Id
	@Column(name = "id_tipo_letrero")
	int id;
	@Column(name = "nombre")
	String name;
	@Column(name = "ancho")
	int width;
	@Column(name = "alto")
	int height;
	@Column(name = "espacio_lineas")
	int lineSpacing;
	@Column(name = "alto_max_simbolo")
	int symbolMaxHeight;
	@Column(name = "id_tipo_ancho")
	int widthTypeId;
	@Column(name = "ancho_max_simbolo")
	int symbolMaxWidth;
	@Column(name = "espaciado")
	int spacing;
	@Column(name = "grano")
	int grain;
	@Column(name = "codificacion")
	int protocolId;
	@Column(name = "nombre_tipo_ancho")
	String widthTypeName;
	public SignTypeViewModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getLineSpacing() {
		return lineSpacing;
	}
	public void setLineSpacing(int lineSpacing) {
		this.lineSpacing = lineSpacing;
	}
	public int getSymbolMaxHeight() {
		return symbolMaxHeight;
	}
	public void setSymbolMaxHeight(int symbolMaxHeight) {
		this.symbolMaxHeight = symbolMaxHeight;
	}
	public int getWidthTypeId() {
		return widthTypeId;
	}
	public void setWidthTypeId(int widthTypeId) {
		this.widthTypeId = widthTypeId;
	}
	
	public int getSymbolMaxWidth() {
		return symbolMaxWidth;
	}
	public void setSymbolMaxWidth(int symbolMaxWidth) {
		this.symbolMaxWidth = symbolMaxWidth;
	}
	public int getSpacing() {
		return spacing;
	}
	public void setSpacing(int spacing) {
		this.spacing = spacing;
	}
	public int getGrain() {
		return grain;
	}
	public void setGrain(int grain) {
		this.grain = grain;
	}
	public int getProtocolId() {
		return protocolId;
	}
	public void setProtocolId(int protocolId) {
		this.protocolId = protocolId;
	}
	public String getWidthTypeName() {
		return widthTypeName;
	}
	public void setWidthTypeName(String widthTypeName) {
		this.widthTypeName = widthTypeName;
	}
	@Override
	public String toString() {
		return "SignTypeViewModel [id=" + id + ", name=" + name + ", width=" + width + ", height=" + height
				+ ", lineSpacing=" + lineSpacing + ", symbolMaxHeight=" + symbolMaxHeight + ", widthTypeId="
				+ widthTypeId + ", symbolMaxWidth=" + symbolMaxWidth + ", spacing=" + spacing + ", grain=" + grain
				+ ", protocolId=" + protocolId + ", widthTypeName=" + widthTypeName + "]";
	}

	
	
	
	
}
