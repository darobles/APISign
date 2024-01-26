package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "simbolos")
public class SimboloModel {
	@Id
	int id_grupo;
	int codigo;
	String character;
	String data;
	public SimboloModel() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId_grupo() {
		return id_grupo;
	}

	public void setId_grupo(int id_grupo) {
		this.id_grupo = id_grupo;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getCharacter() {
		return character;
	}
	public void setCharacter(String character) {
		this.character = character;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "SimboloModel [id_grupo=" + id_grupo + ", codigo=" + codigo + ", character=" + character + ", data="
				+ data + "]";
	}
	
	
}
