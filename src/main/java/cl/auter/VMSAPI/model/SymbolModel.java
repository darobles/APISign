package cl.auter.VMSAPI.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "simbolos")
public class SymbolModel{
	/**
	 * 
	 */

	@Id
	Integer id;
	Integer   id_grupo;
	Integer   codigo;
	Character caracter;
	String    data;
	Integer   ancho;
	
	public SymbolModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public Integer getId() {
		return id;
	}



	public void setId(Integer id) {
		this.id = id;
	}



	public Integer getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(Integer id_grupo) {
		this.id_grupo = id_grupo;
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public Character getCaracter() {
		return caracter;
	}

	public void setCaracter(Character caracter) {
		this.caracter = caracter;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getAncho() {
		return ancho;
	}

	public void setAncho(Integer ancho) {
		this.ancho = ancho;
	}
	

	@Override
	public String toString() {
		return "SymbolModel [id=" + id + ", id_grupo=" + id_grupo + ", codigo=" + codigo + ", caracter=" + caracter
				+ ", data=" + data + ", ancho=" + ancho + "]";
	}
	
}
