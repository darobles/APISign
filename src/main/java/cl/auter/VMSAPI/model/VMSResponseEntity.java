package cl.auter.VMSAPI.model;

public class VMSResponseEntity {
	int status;
	String message;
	
	public VMSResponseEntity() {
		super();
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	@Override
	public String toString() {
		return "VMSResponseEntity [status=" + status + ", message=" + message + "]";
	}
	
	
}
