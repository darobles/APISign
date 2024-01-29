package cl.auter.util;

public class JWTResponse {
	private String genMessage;
	private String role;
	
	public String getGenMessage() {
		return genMessage;
	}
	public void setGenMessage(String genMessage) {
		this.genMessage = genMessage;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "JWTResponse [genMessage=" + genMessage + ", userType=" + role + "]";
	}
	
	
	
}
