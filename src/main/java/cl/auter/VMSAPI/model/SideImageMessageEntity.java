package cl.auter.VMSAPI.model;

public class SideImageMessageEntity {
	int id;
	int userId;
	int location;
	String data;
	int message_id;
	
	public SideImageMessageEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}


	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getLocation() {
		return location;
	}
	public void setLocation(int location) {
		this.location = location;
	}
	public String getData() {
		return data;
	}
	public void setData(String data) {
		this.data = data;
	}
	public int getMessage_id() {
		return message_id;
	}
	public void setMessage_id(int message_id) {
		this.message_id = message_id;
	}

	@Override
	public String toString() {
		return "SideImageMessageEntity [id=" + id + ", userId=" + userId + ", location=" + location + ", data=" + data
				+ ", message_id=" + message_id + "]";
	}

}
