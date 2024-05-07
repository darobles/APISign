package cl.auter.VMSAPI.model;

import cl.auter.VMSAPI.protocol.DIANMINGBrightness;

public class BrightnessEntity {
    Integer value;
    boolean automatic;
	public BrightnessEntity() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	// JPérez 2024.05.07
	public BrightnessEntity(DIANMINGBrightness dmBrightness) {
		super();
		this.value = dmBrightness.getValue();
		this.automatic = dmBrightness.isAutomatic();
	}
	
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	public boolean isAutomatic() {
		return automatic;
	}
	public void setAutomatic(boolean automatic) {
		this.automatic = automatic;
	}
    
    
}
