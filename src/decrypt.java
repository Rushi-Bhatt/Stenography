import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


public class decrypt {

	String output="";
	decrypt(String ext) throws IOException
	{
		File file=new File("C:/Users/Harshin/Desktop/depcryt_pics/large2."+ext);
		BufferedImage image = ImageIO.read(file);
        int[] pixels = image.getRGB(0,0, image.getWidth(), image.getHeight(), null, 0, image.getWidth());   
        
        
        
        for(int i=0;i<pixels.length;i++)
        {		
            int red=(pixels[i] & 0x00FF0000)>> 16;        		    		
            int green=(pixels[i] & 0x0000FF00)>> 8;
       	    int blue=(pixels[i] & 0x000000FF);
       	    
       	    red=(red & 7)<<5;
       	    green=(green & 7)<<2;
       	    blue=blue & 3;
       	    
       	    int asc=red | green | blue;
       	    
       	    if((char)asc=='~')
    	    	break;
       	    
       	    output+=(char)asc;	    
        }	
	}
}
