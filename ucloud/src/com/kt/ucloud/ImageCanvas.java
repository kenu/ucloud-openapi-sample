package com.kt.ucloud;

import java.awt.*;
import java.awt.image.*;

public class ImageCanvas extends Canvas {
    Image image;
    
    public ImageCanvas(String name) {
    	MediaTracker media = new MediaTracker(this);
    	image = Toolkit.getDefaultToolkit().getImage(name);
    	media.addImage(image, 0, 150, 150);
    	//media.addImage(image, 0); // no scale
        try {
        	media.waitForID(0); 
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    public void imageChange(String name) {
    	MediaTracker media = new MediaTracker(this);
    	media.removeImage(image);
    	image = Toolkit.getDefaultToolkit().getImage(name);
    	media.addImage(image, 0, 150, 150);
    	try {
        	media.waitForID(0); 
        }
        catch (Exception e) {
        	e.printStackTrace();
        }
    	this.repaint();
    }

    public ImageCanvas(ImageProducer imageProducer) {
    	image = createImage(imageProducer);
    }

    public void paint(Graphics g) {
    	g.drawImage(image, 0,0, this);
    }

}