package cl.auter.VMSAPI.model;

public class Cabinet {
	int cabinetId;
	String cabinetName;
	Double temperature;
	Double voltage1;
	Double voltage2;
	boolean doorSwitch1;
	boolean doorSwitch2;
	int brightness;
	int photosensitive1;
	int photosensitive2;
	public Cabinet() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getCabinetId() {
		return cabinetId;
	}
	public void setCabinetId(int cabinetId) {
		this.cabinetId = cabinetId;
	}
	public String getCabinetName() {
		return cabinetName;
	}
	public void setCabinetName(String cabinetName) {
		this.cabinetName = cabinetName;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getVoltage1() {
		return voltage1;
	}
	public void setVoltage1(Double voltage1) {
		this.voltage1 = voltage1;
	}
	public Double getVoltage2() {
		return voltage2;
	}
	public void setVoltage2(Double voltage2) {
		this.voltage2 = voltage2;
	}
	public boolean isDoorSwitch1() {
		return doorSwitch1;
	}
	public void setDoorSwitch1(boolean doorSwitch1) {
		this.doorSwitch1 = doorSwitch1;
	}
	public boolean isDoorSwitch2() {
		return doorSwitch2;
	}
	public void setDoorSwitch2(boolean doorSwitch2) {
		this.doorSwitch2 = doorSwitch2;
	}
	public int getBrightness() {
		return brightness;
	}
	public void setBrightness(int brightness) {
		this.brightness = brightness;
	}
	public int getPhotosensitive1() {
		return photosensitive1;
	}
	public void setPhotosensitive1(int photosensitive1) {
		this.photosensitive1 = photosensitive1;
	}
	public int getPhotosensitive2() {
		return photosensitive2;
	}
	public void setPhotosensitive2(int photosensitive2) {
		this.photosensitive2 = photosensitive2;
	}
	
	
}
