package cl.auter.VMSAPI.model;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Base64;
import javax.imageio.ImageIO;
import javax.xml.bind.DatatypeConverter;


public class SideImage {
    private BufferedImage image;
    private String        imageB64;
    private Integer       verticalAlign;

    public SideImage() {
        image         = null;
        imageB64      = null;
        verticalAlign = null;
    }

    public SideImage(SideImageModel sideImage) {
    	if (sideImage != null) {
	    	setImageB64(sideImage.getImagen_b64());
	    	setVerticalAlign(sideImage.getUbicacion_vrt());
    	} else {
            image         = null;
            imageB64      = null;
            verticalAlign = null;
    	}
    }

    public BufferedImage getImage() {
        return image;
    }

    public void setImage(BufferedImage image) {
        this.image = image;

        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ImageIO.write((RenderedImage) image, "bmp", bos);
            this.imageB64 = Base64.getEncoder().encodeToString(bos.toByteArray());
        } catch (Exception ex) {
            this.imageB64 = "";
        }
        
    }

    public String getImageB64() {
    	if(imageB64 == null) {
        	return "";
        }
        return imageB64;
    }

    public void setImageB64(String imageB64) {
        this.imageB64 = imageB64;
        
        try {
            byte[] bytesImagen = DatatypeConverter.parseBase64Binary(this.imageB64);
            this.image = ImageIO.read(new ByteArrayInputStream(bytesImagen));
        } catch (Exception ex) {
            this.image = null;
        }
    }

    public Integer getVerticalAlign() {
        return verticalAlign;
    }

    public void setVerticalAlign(Integer verticalAlign) {
        this.verticalAlign = verticalAlign;
    }

    @Override
    public String toString() {
        return "SideImage{" + "image=" + image + ", imageB64=" + imageB64 + ", verticalAlign=" + verticalAlign + '}';
    }
    
}
