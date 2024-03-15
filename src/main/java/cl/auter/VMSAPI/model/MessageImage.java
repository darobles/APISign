package cl.auter.VMSAPI.model;

import java.awt.image.BufferedImage;
import java.awt.image.RenderedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Iterator;
import java.util.List;
import javax.imageio.ImageIO;

import cl.auter.VMSAPI.model.view.MessageViewModel;
import cl.auter.VMSAPI.model.view.SignTypeViewModel;
import cl.auter.VMSAPI.protocol.DIANMING;
import cl.auter.util.Constants;

public class MessageImage {
		
    private MessageViewModel message;
    private List<Symbol>     symbols = new ArrayList<Symbol>();
    private BufferedImage    image = null;
    private String           customText = null;
    private Integer          segmentWidth;
    
    // JPérez 2024.02.21 :: Constructor for previewing unattached messages   
    public MessageImage(SignTypeViewModel signTypeViewModel, Integer alignmentId, Integer colour, Integer spacing, String message) {
        this.message = new MessageViewModel();
        this.message.setProtocol(signTypeViewModel.getProtocolId());
        this.message.setSignTypeHeight(signTypeViewModel.getHeight());
        this.message.setSignTypeWidth(signTypeViewModel.getWidth());
        this.message.setGrain(signTypeViewModel.getGrain());
        this.message.setMaxSymbolHeight(signTypeViewModel.getSymbolMaxHeight());
        this.message.setLineSpacing(signTypeViewModel.getLineSpacing());
        this.message.setAlignmentId(alignmentId);
        this.message.setColour(colour);
        this.message.setMessage(message);
        this.message.setSpacing(spacing);
    }

    public MessageImage(MessageViewModel messageId) {    	
        this.message = messageId;
    }
    
	public void setSymbols(List<SymbolModel> symbolsModel, SideImage leftImage, SideImage rightImage) {
        if (message != null) {        	
        	for (SymbolModel symbolModel : symbolsModel) {
        		Symbol symb = new Symbol(symbolModel);
        		this.symbols.add(symb);
        	}
            this.segmentWidth = this.message.getProtocol() == Constants.ID_DIANMING ? DIANMING.DM_SEGMENT_WIDTH : this.message.getSignTypeWidth();
            System.out.println("test1");
            build(leftImage, rightImage);
            System.out.println("test2");
        } 
    }
    
    public MessageImage(Integer messageId, String customText) {
    	System.out.println("Correccion MessageImage");
       /* this.message    = messageService.getById(messageId);
        this.customText = customText;
        if (message != null) {
        	List<SymbolModel> symbolsModel = symbolService.getSymbolsByCharacterList(message.getGroupId(), VMSUtils.CharsAsStringList(this.customText));
        	this.symbols = new ArrayList<Symbol>();
        	for (SymbolModel symbolModel : symbolsModel) {
        		this.symbols.add(new Symbol(symbolModel));
        	}
            this.segmentWidth = this.message.getProtocol() == Constants.ID_DIANMING ? DIANMING.DM_SEGMENT_WIDTH : this.message.getSignTypeWidth();
            build();
        } else {
            this.symbols      = null;
            this.segmentWidth = null;
        } */
    }

    
    private boolean oldProtocol() {
        return (this.message.getProtocol() != Constants.ID_DVI) && (this.message.getProtocol() != Constants.ID_DIANMING);
    }

    
    private int getRGB() {
        switch (this.message.getColour()) {
            case 0 :
                return 0xFFFFFF;
            case 1 :
                return 0xE2943A;
            case 2 :
                return 0xFF0000;
        }
        return 0xFFFFFF;
    }

    
    private int applyGrain(int y) {
        if ((! oldProtocol()) || (y == 0) || (this.message.getGrain() == 1)) {
            return y;
        }
        
        if (y >= this.message.getGrain()) {
            while ((y % this.message.getGrain()) != 0) {
                y --;
            }
        } else {
            y = 0;
        }
        return y;
    }

    
    private Symbol findSymbol(Character c) {
        for(Symbol symbol: symbols)
        {
            if (symbol.getSymbol() == c) {
                return symbol;
            }
        	
        }            
        return null;
    }

    
    private int getLineWidth(String line, int spacing) {
        int width = 0;
        for (int i = 0; i < line.length(); i ++) {
            Symbol c = findSymbol(line.charAt(i));
            
            if (c != null) {
                width += c.getWidth() + spacing;
            }
        }
        return Integer.max(0, width - spacing);
    }

    
    private void build(SideImage leftImage, SideImage rightImage) {
        if (this.message != null) {
            int totalWidth  = this.message.getSignTypeWidth();
            int totalHeight = this.message.getSignTypeHeight();
            int width       = totalWidth;
            int offsetY     = 0;
            int offsetX     = 0;
            
            int numImages = totalWidth / this.segmentWidth;
            if ((totalWidth % this.segmentWidth) > 0) {
                numImages ++;
            }

            // Black background
            this.image = new BufferedImage(totalWidth, totalHeight, BufferedImage.TYPE_INT_RGB);
            for (int i = 0; i < totalWidth; i ++) {
                for (int j = 0; j < totalHeight; j ++) {
                    this.image.setRGB(i, j, 0x000000);
                }
            }
            // Side images
            if (! oldProtocol()) {
                if (leftImage.getImage() != null) {
                    int imageWidth  = leftImage.getImage().getWidth();
                    int imageHeight = leftImage.getImage().getHeight();
                    int offsetImgX  = 0;
                    int offsetImgY  = 0;
                    width -= imageWidth;
                    if (leftImage.getVerticalAlign() == 1) {
                        offsetImgY = (totalHeight - imageHeight) / 2;
                    } else if (leftImage.getVerticalAlign() == 2) {
                        offsetImgY = totalHeight - imageHeight;
                    }
                    System.out.println(" imageWidth: " + imageWidth + " imageHeight: " + imageHeight + " offsetImgX: " + offsetImgX + " offsetImgY " + offsetImgY);
                    for (int i = 0; i < leftImage.getImage().getWidth(); i ++) {
                        for (int j = 0; j < leftImage.getImage().getHeight(); j ++) {
                        	if ((offsetImgX + i >= 0) && (offsetImgX + i < this.image.getWidth()) && (offsetImgY + j >= 0) && (offsetImgY + j < this.image.getHeight())) {
                        		this.image.setRGB(offsetImgX + i, offsetImgY + j, leftImage.getImage().getRGB(i, j));
                        	}
                            
                        }
                    }
                    offsetX = leftImage.getImage().getWidth();  // Message space begins to the right side of this image
                }

                if (rightImage.getImage() != null) {
                    int imageWidth  = rightImage.getImage().getWidth();
                    int imageHeight = rightImage.getImage().getHeight();
                    int offsetImgX  = totalWidth - imageWidth;
                    int offsetImgY  = 0;

                    width -= imageWidth;
                    if (rightImage.getVerticalAlign() == 1) {
                        offsetImgY = (totalHeight - imageHeight) / 2;
                    } else if (rightImage.getVerticalAlign() == 2) {
                        offsetImgY = totalHeight - imageHeight;
                    }

                    for (int i = 0; i < imageWidth; i ++) {
                        for (int j = 0; j < imageHeight; j ++) {
                        	if ((offsetImgX + i >= 0) && (offsetImgX + i < this.image.getWidth()) && (offsetImgY + j >= 0) && (offsetImgY + j < this.image.getHeight())) {
                        		this.image.setRGB(offsetImgX + i, offsetImgY + j, rightImage.getImage().getRGB(i, j));
                        	}
                        }
                    }
                }
            }

            // Text
            String text  = (this.customText == null) ? this.message.getMessage() : this.customText;
            String[] lines = text.split(System.lineSeparator());
            int rgb = getRGB();
            for (String line : lines) {
                int lineWidth = getLineWidth(line, this.message.getSpacing());
                int x = 0;

                // Alignment
                if (this.message.getAlignmentId() == 2) {
                    x = applyGrain(width - lineWidth);
                } else if (this.message.getAlignmentId() == 3) {
                    x = applyGrain((width - lineWidth) / 2);
                }
                for (int i = 0; i < line.length(); i ++) {
                    Symbol c = findSymbol(line.charAt(i));
                    if (c != null) {
                        Iterator<Integer> iterator = c.getData().iterator();
                        while (iterator.hasNext()) {
                            int pY = iterator.next();
                            int pX = iterator.next();
                            try {
                                if ((x + pX >= 0) && (x + pX < width) && (offsetY + pY >= 0) && (offsetY + pY < totalHeight)) {
                                    this.image.setRGB(offsetX + x + pX, offsetY + pY, rgb);
                                }
                            } catch (Exception ex) {
                            	System.out.println(ex);
                            }
                        }
                        x += c.getWidth() + this.message.getSpacing();
                    }
                }

                offsetY += this.message.getMaxSymbolHeight() + this.message.getLineSpacing();
            }
            System.out.println("Ends ");
        }
    }

    public String getBase64() {
        String b64;
        try {
            b64 = Base64.getEncoder().encodeToString(this.getImageBytes());
        } catch (IOException ex) {
            b64 = "";
        }
        return b64;
    }
    
    public byte[] getImageBytes() throws IOException {
    	System.out.println("getImageBytes");
        RenderedImage         ri  = this.image;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        ImageIO.write(ri, "bmp", bos);
        return bos.toByteArray();
    }

    // Divide imagen en segmentos horizontales del ancho especificado
    public List<byte[]> getImageBytes(int width) throws IOException {
        if (this.image != null) {
            int numImages = this.image.getWidth() / width;
            if ((this.image.getWidth() % width) > 0) {
                numImages ++;
            }
            
            List<byte[]> images         = new ArrayList();
            int          remainingWidth = this.image.getWidth();
            int          x              = 0;
            for (int i = 0; i < numImages; i ++) {
                BufferedImage         segment = this.image.getSubimage(x, 0, Integer.min(remainingWidth, width), this.image.getHeight());
                RenderedImage         ri      = segment;
                ByteArrayOutputStream bos     = new ByteArrayOutputStream();

                ImageIO.write(ri, "bmp", bos);
                images.add(bos.toByteArray());
                
                remainingWidth -= width;
                x              += width;
            }
            return images;
        } else {
            return null;
        }
    }
    
    public Integer getWidth() {
        return (this.image != null) ? this.image.getWidth() : null;
    }

    public Integer getHeight() {
        return (this.image != null) ? this.image.getHeight() : null;
    }

    public Integer getPixelRGB(Integer x, Integer y) {
        Integer pixel = null;
        if (this.image != null) {
            try {
                pixel = this.image.getRGB(x, y);
            } catch (ArrayIndexOutOfBoundsException ex) {
                pixel = null;
            }
        }
        return pixel;
    }

}