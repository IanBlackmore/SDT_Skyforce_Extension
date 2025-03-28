/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Mehedi Hasan Akash
 */
package farisa_display;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;

public class Display {
 
    private String title;
    private int width;
    private int height;
    
    public static JFrame frame;
    public static Canvas canvas;
    
    public Display(String title, int width, int height)
    {
            this.width = width;
            this.title = title;
            this.height = height;
            createDisplay();
    }
    
    public void createDisplay(){
        
        frame = new JFrame(title);
        frame.setSize(width,height);
        frame.setVisible(true);//makes sure the visibility of the frame
        frame.setLocationRelativeTo(null);//makes the frame appear in the middle of the screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//application closes if the frame is closed
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(width,height));
        canvas.setBackground(Color.gray);//Set full frame color
        canvas.setFocusable(false);
        frame.add(canvas);
        frame.pack();
        frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
    }
    
    public Canvas getCanvas(){//get method to use private Canvas veriable in other class
        return canvas;
    }

	public Component getFrame() {
		// TODO Auto-generated method stub
		return frame;
	}
    
}
