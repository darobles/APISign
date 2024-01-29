package cl.auter.VMSAPI.protocol;

import java.util.List;

import cl.auter.util.VMSUtils;

import java.util.ArrayList;
import java.util.Arrays;

public class DIANMINGPackage {
    private final Byte C_ESC = 0x1B;  // Escape
    private final Byte C_STX = 0x02;  // Start byte
    private final Byte C_ETX = 0x03;  // End byte

    Integer    destinationAddress;
    Integer    sourceAddress;
    Integer    commandHead;
    List<Byte> frame;
    Byte[]     data;
    Integer    numBytes;

    public DIANMINGPackage() {
	this.destinationAddress = 0;
	this.sourceAddress      = 0;
	this.commandHead        = 0;
	this.data               = null;
    this.frame          = new ArrayList();
	this.numBytes           = 0;
    }

     public DIANMINGPackage(int destinationAddress, int sourceAddress, int commandHead, Byte[] data) {
	this.destinationAddress = destinationAddress;
	this.sourceAddress      = sourceAddress;
	this.commandHead        = commandHead;
	this.data               = data;
        this.encodeFrame(data);
     }

    public void clean() {
        if (this.data != null) {
            this.data = null;
	}
        this.frame = new ArrayList();
    }

    public Integer getDestinationAddress() {
        return this.destinationAddress;
    }

    public void setDestinationAddress(Integer destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public Integer getSourceAddress() {
        return this.sourceAddress;
    }

    public void setSourceAddress(Integer sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public Integer getCommandHead() {
        return this.commandHead;
    }

    public void setCommandHead(Integer commandHead) {
        this.commandHead = commandHead;
    }

    public Integer getNumBytes() {
        return numBytes;
    }

    public List<Byte> getFrameBytes() {
        return this.frame;
    }

    //--------------------------------------------------------------------------
    
    private List<Byte> twoBytesNumber(Integer number) {
        List<Byte> bytePair = new ArrayList<Byte>();
        if ((number != null) && ((number >= 0) && (number <= 99))) {
            bytePair.add((byte) ((number / 10) + 0x30));
            bytePair.add((byte) ((number % 10) + 0x30));
        } else {
            bytePair.add((byte) 0x00);
            bytePair.add((byte) 0x00);
        }
        return bytePair;
    }

    
    private boolean escapable(Byte item) {
        return (item == C_STX) || (item == C_ETX) || (item == C_ESC);
    }
    
    private List<Byte> escape(Byte item) {
        List<Byte> escapeList = new ArrayList<Byte>();
        
        escapeList.add(C_ESC);
        if (item == C_STX) {
            escapeList.add((byte) 0xE7);
        } else if (item == C_ETX) {
            escapeList.add((byte) 0xE8);
        } else if (item == C_ESC) {
            escapeList.add((byte) 0x00);
        }
        
        return escapeList;
    }

    //--------------------------------------------------------------------------

    public void encodeFrame(Byte[] data) {
	// Generates data without conversion
        List<Byte> byteTempBuffer = new ArrayList();
        byteTempBuffer.addAll(twoBytesNumber(this.destinationAddress));
        byteTempBuffer.addAll(twoBytesNumber(this.sourceAddress));
        byteTempBuffer.addAll(twoBytesNumber(this.commandHead));
        if (data != null) {
            for (Byte item : data) {
                byteTempBuffer.add(item);
            }
        }
        
        int CRC = getCRC(byteTempBuffer);
        byteTempBuffer.add((byte) ((CRC & 0xFF00) >> 8));
        byteTempBuffer.add((byte) (CRC & 0x00FF));
        
	// Generates converted data
        this.frame = new ArrayList();
        this.frame.add(C_STX);
        for (Byte item : byteTempBuffer) {
            if (! escapable(item)) {
                this.frame.add(item);
            } else {
                this.frame.addAll(escape(item));
            }
        }
        this.frame.add(C_ETX);
        this.numBytes = this.frame.size();
        
        byteTempBuffer.clear();
        byteTempBuffer = null;
    }

    void assignFrame(Byte[] bytes) {
	if (bytes.length >= 10) {
            this.destinationAddress = VMSUtils.BytesToInt(bytes, 1);
            this.sourceAddress      = VMSUtils.BytesToInt(bytes, 3);
            this.commandHead        = VMSUtils.BytesToInt(bytes, 5);
            Byte[] bytesTemp        = new Byte[bytes.length - 10];
            System.arraycopy(bytes, 7, bytesTemp, 0, bytes.length - 10);
            
            List<Byte> bytesDec = decode(bytesTemp);
            this.numBytes = bytesDec.size();
            this.data = new Byte[this.numBytes];
            this.data = bytesDec.toArray(this.data);
            this.frame = new ArrayList();
            this.frame = Arrays.asList(bytes);
	} else {
            this.destinationAddress = null;
            this.sourceAddress      = null;
            this.commandHead        = null;
            this.data               = null;
            this.frame              = new ArrayList();;
            this.numBytes           = 0;
	}
    }
    
    public List<Byte> decode(Byte[] bytes) {
        List<Byte> bytesList = new ArrayList<Byte>();
        boolean    escapado  = false;
        
        for (Byte item : bytes) {
            if (item != C_ESC) {
                if (! escapado) {
                    bytesList.add(item);
                } else {
                    if (item == (byte) 0xE7) {
                        bytesList.add(C_STX);
                    } else if (item == (byte) 0xE8) {
                        bytesList.add(C_ETX);
                    } else if (item == (byte) 0x00) {
                        bytesList.add(C_ESC);
                    }
                    escapado = false;
                }
            } else {
                escapado = true;
            }
        }
        return bytesList;
    }
    
    
    private int getCRC(List<Byte> frame) {
	byte treat, bcrc;
	int  wcrc = 0;

        if (frame != null) {
            for (Byte item: frame) {
                byte c = item;
                for (int j = 0; j < 8; j ++) {
                    treat = (byte) (c & 0x80);
                    c <<= 1;
                    bcrc = (byte) ((wcrc >> 8) & 0x80);

                    wcrc <<= 1;

                    if (treat != bcrc) {
                        wcrc ^= 0x1021;
                    }
                }
            }
        }
        return wcrc & 0xFFFF;
    }

}

