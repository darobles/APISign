package cl.auter.VMSAPI.protocol;

import cl.auter.VMSAPI.model.MessageModel;
import cl.auter.VMSAPI.model.SignModel;
import cl.auter.VMSAPI.model.view.VMSViewModel;
import cl.auter.VMSAPI.model.MessageImage;
import cl.auter.util.VMSUtils;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.commons.lang3.ArrayUtils;

/**
 *
 * @author jperez
 */
public class DIANMING {
    private static final Logger LOGGER           = Logger.getLogger(DIANMING.class.getName());
    private static final int    DM_PACKAGE_SIZE  = 2048;
    private static final String DM_NEWLINE       = "\r\n";
    public  static final int    DM_SEGMENT_WIDTH = 288;

    private DIANMINGPackage command;
    private DIANMINGPackage response;
    private String          serverURL;
    private int             portURL;
    private Socket          socket;
    private int             destinationAddress;
    private int             sourceAddress;
    private int             connectionMode;
    
    public DIANMING() {
	this.command            = new DIANMINGPackage();
	this.response           = new DIANMINGPackage();
        this.sourceAddress      = 0;
        this.destinationAddress = 0;
        this.connectionMode     = 0;
        this.serverURL          = "localhost";
        this.portURL            = 8080;
        this.socket             = null;
    }
    
    public DIANMING(VMSViewModel letrero) {
	this.command            = new DIANMINGPackage();
	this.response           = new DIANMINGPackage();
        this.sourceAddress      = 0;
        this.destinationAddress = 0;
        this.connectionMode     = 3;
        this.serverURL          = letrero.getFono();
        this.portURL            = letrero.getPort();
        try {
            this.socket         = new Socket(this.serverURL, this.portURL);
        } catch (Exception ex) {
            this.socket         = null;
        }
    }

    //--------------------------------------------------------------------------
    
    public void setAddresses(int address) {
        this.setDestinationAddress(address);
        this.setSourceAddress(99);
    }
    
    public int getDestinationAddress() {
        return this.destinationAddress;
    }

    public void setDestinationAddress(int destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public int getSourceAddress() {
        return this.sourceAddress;
    }

    public void setSourceAddress(int sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public List<Byte> getCommand() {
        return this.command.getFrameBytes();
    }

    public List<Byte> getResponse() {
        return this.response.getFrameBytes();
    }
    
    public void cleanCommand() {
        this.command.clean();
    }
    
    public void cleanResponse() {
        this.response.clean();
    }

    private void clean() {
        this.command.clean();
        this.response.clean();
    }

    //--------------------------------------------------------------------------
    
    private String playlistName(Integer numeroPlaylist) {
	return "play" + VMSUtils.ZeroPad(numeroPlaylist, 2) + ".lst";
    }
    
    private String BMPName(Integer numeroBMP) {
        return VMSUtils.ZeroPad(numeroBMP, 3) + ".bmp";
    }
    
    private boolean sendPackage() {
	// Cleans response object
        this.cleanResponse();

	if ((this.connectionMode == 3) && (this.socket != null)) {  // If it's a socket connection
            boolean ok = true;
            try {
                boolean preOpened = true;
                if (! this.socket.isConnected()) {
                    preOpened = false;
                    this.socket = new Socket(this.serverURL, this.portURL);
                }
                this.socket.setSoTimeout(3000);
                this.socket.setKeepAlive(true);
                this.socket.setTcpNoDelay(true);

                // Send bytes to socket
                DataOutputStream outStream = new DataOutputStream(this.socket.getOutputStream());
                List<Byte>       bytesList = this.command.getFrameBytes();
                Byte[]           bytes     = new Byte[bytesList.size()];
                bytesList.toArray(bytes);
                outStream.write(ArrayUtils.toPrimitive(bytes), 0, bytesList.size());
                outStream.flush();
                
                // Lee respuesta de socket tras esperar hasta 3 segundos
                DataInputStream inStream  = new DataInputStream(this.socket.getInputStream());
                int             available = 0;
                int             attempts  = 0;
                while (((available = inStream.available()) == 0) && (attempts < 300)) {
                    Thread.sleep(10);
                    attempts ++;
                }
                if (available > 0) {
                    List<Byte> readByteList = new ArrayList();
                    int        readByte;
                    while (inStream.available() > 0) {
                        readByte = inStream.read();
                        if (readByte >= 0) {
                            readByteList.add((byte) readByte);
                        } else {
                            break;
                        }
                    }
                    Byte[] readByteArray = new Byte[readByteList.size()];
                    readByteList.toArray(readByteArray);
                    this.response.assignFrame(readByteArray);
                } 
                
                if (! preOpened) {
                    this.socket.close();
                }
                
            } catch (Exception ex) {
                LOGGER.log(Level.SEVERE, "Exception in DIANMING.enviarPackage()", ex);
                ok = false;
            } finally {
                return ok;
            }
        }

	return false;
    }
    
    boolean sendFile(int commandHead, String fileName, ByteArrayInputStream contents) throws InterruptedException {
        byte[]  byteBuffer = new byte[DM_PACKAGE_SIZE + 20];
        int     bytesRead;
        int     offset     = 0;
        boolean ok         = true;
        //int     amount     = contents.available();
        
        do {
            bytesRead = contents.read(byteBuffer, 0, DM_PACKAGE_SIZE);
            
            if (bytesRead > 0) {
                // Sets up frame to send
                int    idx      = 0;
                byte[] byteSend = new byte[bytesRead + fileName.length() + 9];

                // (-) New contents, first package
                // (+) Contents to add
                byteSend[idx ++] = ((offset == 0) ? (byte) '-' : (byte) '+');
                String offsetString = VMSUtils.ZeroPad(offset, 8); // Offset, "01234567" format
                for (int i = 0; i < 8; i ++) {
                    byteSend[idx ++] = (byte) offsetString.charAt(i);
                }

                // File name
                for (int i = 0; i < fileName.length(); i ++) {
                    byteSend[idx ++] = (byte) fileName.charAt(i);
                }
                System.arraycopy(byteBuffer, 0, byteSend, idx, bytesRead);
                
                int attempt = 0;
                do {
                    this.clean();
                    this.command = new DIANMINGPackage(this.destinationAddress, this.sourceAddress, commandHead, ArrayUtils.toObject(byteSend));
                    ok = sendPackage();
                    if (! ok) {
                        attempt ++;
                        Thread.sleep(1500);
                    }
                } while ((! ok) && (attempt < 3));

                byteSend = null;
                if (ok) {
                    offset += bytesRead;
                }
            }
        } while (ok && (bytesRead == DM_PACKAGE_SIZE));

        return ok;
    }
    
    //--------------------------------------------------------------------------

    boolean sendBMP(int index, byte[] image) throws InterruptedException {
        return sendFile(41, BMPName(index), new ByteArrayInputStream(image));
    }

    //--------------------------------------------------------------------------

    boolean sendAll(List<String> playlist, List<byte[]> images) throws InterruptedException {
        boolean ok    = true;
        int     index = 0;
    
        try {
            for (byte[] image : images) {
                ok &= sendBMP(index ++, image);
                if (! ok) {
                    break;
                }
            }

            if (ok) {
                setPlaylist(0, playlist);
            }
        } catch (Exception ex) {
            ok = false;
        }
        
        return ok;
    }
    
    //--------------------------------------------------------------------------

    public void turnOnVMS() {
	Byte[] frame = new Byte[1];
        frame[0] = (byte) 0x01;
        
	this.clean();
        this.command = new DIANMINGPackage(this.destinationAddress, this.sourceAddress, 11, frame);
        this.sendPackage();
    }

    public void turnOffVMS() {
	Byte[] frame = new Byte[1];
        frame[0] = (byte) 0x00;
        
	this.clean();
        this.command = new DIANMINGPackage(this.destinationAddress, this.sourceAddress, 11, frame);
        this.sendPackage();
    }
    
    public void rebootVMS() {
	this.clean();
        this.command = new DIANMINGPackage(this.destinationAddress, this.sourceAddress, 67, null);
        this.sendPackage();
    }
    
    public boolean setPlaylist(Integer number, List<String> contents) {
        String playlistSend = "";
        int    numItems     = contents.size();
        int    i            = 0;
        
        playlistSend = playlistSend.concat("[PLAYLIST]" + DM_NEWLINE);
        playlistSend = playlistSend.concat("ITEM_NO=" + VMSUtils.ZeroPad(numItems, 3) + DM_NEWLINE);
        for (String playlistString : contents) {
            playlistSend = playlistSend.concat("ITEM" + VMSUtils.ZeroPad(i, 3) + "=" + playlistString);
            if (i < numItems - 1) {
                playlistSend = playlistSend.concat(DM_NEWLINE);
            }
            i ++;
        }
        
        try {
            // 71: Sends playlist to VMS
            return sendFile(71, playlistName(number), new ByteArrayInputStream(playlistSend.getBytes()));
        } catch (Exception ex) {
            return false;
        }
    }
    
    public void getPlaylist(Integer numPlaylist) {
	String name      = playlistName(numPlaylist);
        Byte[] nameBytes = new Byte[name.length()];

	for (int i = 0; i < name.length(); i ++) {
            nameBytes[i] = (byte) name.charAt(i);
	}

	// 47: Shows stored playlist
	this.clean();
        this.command = new DIANMINGPackage(this.destinationAddress, this.sourceAddress, 47, nameBytes);
        this.sendPackage();
    }
    
    public void setBrightness(Integer brightness) {
        Byte[] brightnessBytes  = new Byte[6];
        String brightnessString = "FF";

	if (brightness < 32) {
            brightnessString = VMSUtils.ZeroPad(brightness, 2);
	}
	for (int i = 0; i < 3; i ++) {
            brightnessBytes[(i * 2) + 0] = (byte) brightnessString.charAt(0);
            brightnessBytes[(i * 2) + 1] = (byte) brightnessString.charAt(1);
	}

	// 23: Set brightness command
        this.clean();
        this.command = new DIANMINGPackage(this.destinationAddress, this.sourceAddress, 23, brightnessBytes);
        this.sendPackage();
    }
    
    public DIANMINGBrightness getBrightness() {
	// 21: Get brightness command
	this.clean();
        this.command = new DIANMINGPackage(this.destinationAddress, this.sourceAddress, 21, null);
        this.sendPackage();
        
        Byte[] responseBytes      = new Byte[this.getResponse().size()];
        this.getResponse().toArray(responseBytes);
        String brightnessResponse = new String(ArrayUtils.toPrimitive(responseBytes), 7, 8);
        
        DIANMINGBrightness brightness      = new DIANMINGBrightness();
        String             redBrightness   = brightnessResponse.substring(0, 2);
        String             greenBrightness = brightnessResponse.substring(2, 4);
        String             blueBrightness  = brightnessResponse.substring(4, 6);
        String             panelBrightness = brightnessResponse.substring(6, 8);
        if (redBrightness.compareTo("FF") != 0) {
            if ((redBrightness.compareTo(greenBrightness) == 0) && (greenBrightness.compareTo(blueBrightness) == 0)) {
                brightness.setValue(Integer.valueOf(redBrightness));
                brightness.setAutomatic(false);
            }
        } else {
            brightness.setValue(Integer.valueOf(panelBrightness));
            brightness.setAutomatic(true);
        }

	return brightness;
    }

    public List<DIANMINGInfo> getDetailedStatus() {
	// 03: Get status command
	this.clean();
        this.command = new DIANMINGPackage(this.destinationAddress, this.sourceAddress, 3, null);
        
        this.sendPackage();

        Byte[] responseBytes = new Byte[this.getResponse().size()];
        responseBytes = this.getResponse().toArray(responseBytes);

        int numCabinets = VMSUtils.BytesToInt(responseBytes, 7);
        int index       = 9;
        
        List<DIANMINGInfo> cabinets = new ArrayList();
        for (int i = 0; i < numCabinets; i ++, index += 32) {
            DIANMINGInfo cabinetInfo = new DIANMINGInfo();
            cabinetInfo.setAll(responseBytes, index);
            cabinets.add(cabinetInfo);
        }
        
        return cabinets;
    }
    
    public Integer getBadPixels() {
	// 69: Bad pixels
	this.clean();
        this.command = new DIANMINGPackage(this.destinationAddress, this.sourceAddress, 69, null);
        this.sendPackage();

        Integer numPixels;
        try {
            Byte[] bytes = new Byte[6];
            int    countFF = 0;
            for (int i = 0; i < 6; i ++) {
                bytes[i] = this.getResponse().get(i + 7);
                if (bytes[i] == (byte) 0xFF) {
                    countFF ++;
                }
            }
            
            if (countFF != 6) {
                numPixels = VMSUtils.BytesToInt(bytes, 0, 6);
            } else {
                numPixels = -1;
            }
        } catch (Exception ex) {
            numPixels = null;
        }
        
	return numPixels;
    }
    
    public boolean getCommunicationStatus() {
        // 29: Communication status
        this.clean();
        this.command = new DIANMINGPackage(this.destinationAddress, this.sourceAddress, 29, null);
        this.sendPackage();

        return (this.getResponse().get(7) == (byte) 0x31);
    }

    public boolean sendMessage(SignModel sign, MessageModel message) {
        int     j, k;
        boolean sent = true;
        
        try {
//            VMSDAO dao = new VMSDAO();
            
            List<byte[]> images = new ArrayList();
            List<String> items  = new ArrayList();

            items.clear();
            MessageImage mi = new MessageImageModel(message.getId());
                
            List<byte[]> bytes = mi.getImageBytes(DM_SEGMENT_WIDTH);
            images.addAll(bytes);

            String sItem  = "1,0,0,0,0,";
            for (k = 0, j = 0; k < bytes.size(); k ++, j += DM_SEGMENT_WIDTH) {
                sItem += "\\C" + VMSUtils.ZeroPad(j, 3) + "000" + "\\B" + VMSUtils.ZeroPad(k, 3);
            }
            items.add(sItem);
            
            sent &= sendAll(items, images);
            this.socket.close();
        } catch (Exception ex) {
            sent = false;
        }
        
        return sent;
    }
    
    public boolean sendMessage(VMSViewModel sign, MessageImage mi) {
        int     j, k;
        boolean sent;
        
        try {
            List<byte[]> images = new ArrayList();
            List<String> items  = new ArrayList();

            items.clear();
                
            List<byte[]> bytes = mi.getImageBytes(DM_SEGMENT_WIDTH);
            images.addAll(bytes);

            String sItem  = "1,0,0,0,0,";
            for (k = 0, j = 0; k < bytes.size(); k ++, j += DM_SEGMENT_WIDTH) {
                sItem += "\\C" + VMSUtils.ZeroPad(j, 3) + "000" + "\\B" + VMSUtils.ZeroPad(k, 3);
            }
            items.add(sItem);
            
            sent = sendAll(items, images);
            this.socket.close();
        } catch (Exception ex) {
            sent = false;
        }
        
        return sent;
    }
    
    public boolean sendSequence(Sign sign, Sequence sequence) {
        int     j, k;
        int     counter = 0, prevCounter = 0;
        boolean bEnviado = false;
        
        try {
            VMSDAO dao = new VMSDAO();
            List<SequenceMessage> sequenceMessages = dao.getSequenceMessages(sequence);
            List<byte[]>          images           = new ArrayList();
            List<String>          items            = new ArrayList();

            items.clear();
            for (SequenceMessage sequenceMessage : sequenceMessages) {
                MessageModel      message = dao.getMessage(sequenceMessage.getMessage().getId());
                MessageImage mi      = new MessageImage(message.getId());
                
                List<byte[]> bytes = mi.getImageBytes(DM_SEGMENT_WIDTH);
                images.addAll(bytes);
                prevCounter = counter;
                counter    += bytes.size();
                
                String sItem  = sequenceMessage.getTime().toString() + ",0,0,0,0,";
                for (k = prevCounter, j = 0; k < counter; k ++, j += DM_SEGMENT_WIDTH) {
                    sItem += "\\C" + VMSUtils.ZeroPad(j, 3) + "000" + "\\B" + VMSUtils.ZeroPad(k, 3);
                }
                items.add(sItem);
            }
            
            sendAll(items, images);
        } catch (Exception ex) {
        }
        
        return bEnviado;
    }

    @Override
    public String toString() {
        return "DIANMING{" + "urlServidor=" + serverURL + ", puerto=" + portURL + ", socket=" + socket + ", direccionObjetivo=" + destinationAddress + ", direccionFuente=" + sourceAddress + ", modoConexion=" + connectionMode + '}';
    }
    
}
