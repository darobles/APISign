package cl.auter.util;

public class JWTResponse {
	private String genMessage;
	private String userType;
	
	public String getGenMessage() {
		return genMessage;
	}
	public void setGenMessage(String genMessage) {
		this.genMessage = genMessage;
	}
	public String getUserType() {
		return userType;
	}
	public void setUserType(String userType) {
		this.userType = userType;
	}
}
