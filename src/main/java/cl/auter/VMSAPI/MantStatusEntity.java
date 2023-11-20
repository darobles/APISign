package cl.auter.VMSAPI;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "maintenance_status")
public class MantStatusEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int    id;
	private String name;
	
	@Override
	public String toString() {
		return "MantTypeEntity [id=" + id + ", name=" + name + "]";
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
}
