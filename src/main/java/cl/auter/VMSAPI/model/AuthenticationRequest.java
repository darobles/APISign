package cl.auter.VMSAPI.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "login")
public class AuthenticationRequest {

	@Schema(description = "Unique identifier of the Contact.", example = "admin", required = false)
	private String username;
	@Schema(description = "Password of the id.", example = "Autersa1735!", required = false)
	private String password;

	public AuthenticationRequest(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}

	public AuthenticationRequest() {

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

	@Override
	public String toString() {
		return "AuthenticationRequest [username=" + username + ", password=" + password + "]";
	}

	
	
}
