package cl.auter.VMSAPI.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class UsersEntity {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int    id;
	private String username;
	private String password;
	private String nombre;
	private String user_type;
	private int role_id;
		
	public UsersEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getUser_type() {
		return user_type;
	}
	public void setUser_type(String user_type) {
		this.user_type = user_type;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public int getRole_id() {
		return role_id;
	}
	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}
	
	@Override
	public String toString() {
		return "UsersEntity [id=" + id + ", username=" + username + ", password=" + password + ", nombre=" + nombre
				+ ", user_type=" + user_type + ", role_id=" + role_id + "]";
	}
	

}
