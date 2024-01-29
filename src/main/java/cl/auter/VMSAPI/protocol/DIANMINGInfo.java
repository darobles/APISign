/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cl.auter.VMSAPI.protocol;

/**
 *
 * @author jperez
 */
public class DIANMINGInfo {
    private Double  temperature;
    private Boolean doorSwitch1;
    private Boolean doorSwitch2;
    private Double  voltage1;
    private Double  voltage2;
    private Integer brightness;
    private Integer photosensitive1;
    private Integer photosensitive2;

    public DIANMINGInfo() {
        this.temperature     = null;
        this.doorSwitch1     = null;
        this.doorSwitch2     = null;
        this.voltage1        = null;
        this.voltage2        = null;
        this.brightness      = null;
        this.photosensitive1 = null;
        this.photosensitive2 = null;
    }

    public Double getTemperature() {
        return this.temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Boolean getDoorSwitch1() {
        return this.doorSwitch1;
    }

    public void setDoorSwitch1(Boolean doorSwitch1) {
        this.doorSwitch1 = doorSwitch1;
    }

    public Boolean getDoorSwitch2() {
        return this.doorSwitch2;
    }

    public void setDoorSwitch2(Boolean doorSwitch2) {
        this.doorSwitch2 = doorSwitch2;
    }

    public Double getVoltage1() {
        return this.voltage1;
    }

    public void setVoltage1(Double voltage1) {
        this.voltage1 = voltage1;
    }

    public Double getVoltage2() {
        return this.voltage2;
    }

    public void setVoltage2(Double voltage2) {
        this.voltage2 = voltage2;
    }

    public Integer getBrightness() {
        return this.brightness;
    }

    public void setBrightness(Integer brightness) {
        this.brightness = brightness;
    }

    public Integer getPhotosensitive1() {
        return this.photosensitive1;
    }

    public void setPhotosensitive1(Integer photosensitive1) {
        this.photosensitive1 = photosensitive1;
    }

    public Integer getPhotosensitive2() {
        return this.photosensitive2;
    }

    public void setPhotosensitive2(Integer photosensitive2) {
        this.photosensitive2 = photosensitive2;
    }
    
    public void setAll(Byte[] frame, int index) {
	byte currentByte;
	int  value, factor;

        value  = 0;
        factor = 1000;
	for (int i = index + 0; i < index + 4; i ++, factor /= 10) {
            currentByte = frame[i];
            value      += (currentByte - ((currentByte >= 0x30) ? 0x30 : 0x2A)) * factor;
	}
	this.temperature = (double) value / 100.0;

        value  = 0;
        factor = 1000;
	for (int i = index + 8; i < index + 12; i ++, factor /= 10) {
            currentByte = frame[i];
            value      += (currentByte - ((currentByte >= 0x30) ? 0x30 : 0x2A)) * factor;
	}
	this.doorSwitch1 = (value & 0x0001) > 0;
	this.doorSwitch2 = (value & 0x0002) > 0;

        value  = 0;
        factor = 1000;
	for (int i = index + 12; i < index + 16; i ++, factor /= 10) {
            value += (frame[i] - 0x30) * factor;
	}
	this.voltage1 = (double) value / 100.0;

        value  = 0;
        factor = 1000;
	for (int i = index + 16; i < index + 20; i ++, factor /= 10) {
            value += (frame[i] - 0x30) * factor;
	}
	this.voltage2 = (double) value / 100.0;

        value  = 0;
        factor = 1000;
	for (int i = index + 20; i < index + 24; i ++, factor /= 10) {
            value += (frame[i] - 0x30) * factor;
	}
	this.brightness = value;

        value  = 0;
        factor = 1000;
	for (int i = index + 24; i < index + 28; i ++, factor /= 10) {
            value += (frame[i] - 0x30) * factor;
	}
	this.photosensitive1 = value;

        value  = 0;
        factor = 1000;
	for (int i = index + 28; i < index + 32; i ++, factor /= 10) {
            value += (frame[i] - 0x30) * factor;
	}
	this.photosensitive2 = value;        
    }
    
}
