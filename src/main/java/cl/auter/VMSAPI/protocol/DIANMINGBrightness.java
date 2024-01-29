package cl.auter.VMSAPI.protocol;

public class DIANMINGBrightness {
    Integer value;
    boolean automatic;

    public DIANMINGBrightness() {
        this.value     = null;
        this.automatic = false;
    }

    public DIANMINGBrightness(Integer value, boolean automatic) {
        this.value     = value;
        this.automatic = automatic;
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

    @Override
    public String toString() {
        return "DIANMINGBrillo{" + "value=" + value + ", automatic=" + automatic + '}';
    }
    
}
