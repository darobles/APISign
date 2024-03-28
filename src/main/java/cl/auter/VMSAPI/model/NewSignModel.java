package cl.auter.VMSAPI.model;


public class NewSignModel {
	int id;
	String name;
	String latitude;
	String longitude;
	String location;
	int signTypeId;
	String obs;
	int address;
	int cameraId;
	int cameraType;
	int connectionId;
	int key;
	String phoneOrIP;
	String channel;
	int port;
	int portCOM;
	
	public NewSignModel() {
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
	public String getLatitude() {
		return latitude;
	}
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	public String getLongitude() {
		return longitude;
	}
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public int getSignTypeId() {
		return signTypeId;
	}
	public void setSignTypeId(int signTypeId) {
		this.signTypeId = signTypeId;
	}
	public String getObs() {
		return obs;
	}
	public void setObs(String obs) {
		this.obs = obs;
	}
	public int getAddress() {
		return address;
	}
	public void setAddress(int address) {
		this.address = address;
	}
	public int getCameraId() {
		return cameraId;
	}
	public void setCameraId(int cameraId) {
		this.cameraId = cameraId;
	}
	public int getCameraType() {
		return cameraType;
	}
	public void setCameraType(int cameraType) {
		this.cameraType = cameraType;
	}
	public int getConnectionId() {
		return connectionId;
	}
	public void setConnectionId(int connectionId) {
		this.connectionId = connectionId;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
	public String getPhoneOrIP() {
		return phoneOrIP;
	}
	public void setPhoneOrIP(String phoneOrIP) {
		this.phoneOrIP = phoneOrIP;
	}
	public int getPort() {
		return port;
	}
	public void setPort(int port) {
		this.port = port;
	}
	public int getPortCOM() {
		return portCOM;
	}
	public void setPortCOM(int portCOM) {
		this.portCOM = portCOM;
	}	
	
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	@Override
	public String toString() {
		return "NewSignModel [id=" + id + ", name=" + name + ", latitude=" + latitude + ", longitude=" + longitude
				+ ", location=" + location + ", signTypeId=" + signTypeId + ", obs=" + obs + ", address=" + address
				+ ", cameraId=" + cameraId + ", cameraType=" + cameraType + ", connectionId=" + connectionId + ", key="
				+ key + ", phoneOrIP=" + phoneOrIP + ", port=" + port + ", portCOM=" + portCOM + "]";
	}

}
