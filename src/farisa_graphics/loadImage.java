/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farisa_graphics;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author Mehedi Hasan Akash
 */
public class loadImage {
    public static BufferedImage image;
    
    public static void init(){
        //image = imageLoader("/eveningMode.png");
    }
    
    public static BufferedImage imageLoader(String path){
        try {
            return ImageIO.read(loadImage.class.getResource(path));
        } catch (IOException ex) {
            Logger.getLogger(loadImage.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
            
        }
        return null;
    }
    
}
