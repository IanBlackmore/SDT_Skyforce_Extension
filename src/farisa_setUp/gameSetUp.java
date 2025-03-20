/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farisa_setUp;

import farisa_display.Display;
import settings.Settings;
import farisa_graphics.loadImage;
import farisa_manager.gameManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Mehedi Hasan Akash
 */
public class gameSetUp implements Runnable{
    
    private String title;
    private int width;
    private int height;
    private Thread thread;
    private boolean running;
    private Display display;
    private BufferStrategy buffer;
    private Graphics g;//to draw something
    private gameManager manager;
    
    private static Color backgroundColour = Color.black;
    
    //private int y;
    //private boolean start;
    public static final int gameWidth = 400;
    public static final int gameHeight = 400;
    
    
    public gameSetUp(String title, int width, int height){ //sets up the game screen
        
        this.title = title;
        this.width = width;
        this.height = height;
        
    }
    public void init(){//initialize
    	
    	Settings.render();
        display = new Display(title,width,height);
        loadImage.init();
        manager = new gameManager();
        manager.init();
        //start = true;  
        Settings.render();
        
        
        
        
    }
    
    public synchronized void start(){//to start a thread for game
        if (running)
            return;
        running = true;
        if(thread == null){
        thread = new Thread(this);
        thread.start();
        }
    }
    public synchronized void stop(){
    	
        if(!(running))
            return;
        running = false;
        try {
            thread.join(200);//stops the thread
            
        } catch (InterruptedException ex) {
            Logger.getLogger(gameSetUp.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        g.dispose();
        System.exit(0);
    }
    public void tick(){//for game screen update
        manager.tick();
        if (manager.getExit()) {
        	stop();
        }
    }
    public void renderGame(){//draw stuffs for game screen
        buffer = display.getCanvas().getBufferStrategy();
        if(buffer == null){
            display.getCanvas().createBufferStrategy(3);
            return;
        }
        
        g = buffer.getDrawGraphics();
        g.clearRect(0, 0, width, height);//x, y, width, height
        //draw
        
        g.setColor(backgroundColour);//new color(R,G,B)//set middle frame color
        g.fillRect(50, 50, gameWidth, gameHeight);
        //g.drawImage(loadImage.image, 50, 50, gameWidth, gameHeight,null);
        manager.render(g);//after the creation of rectangle so entity shows on the rectangle
        
        //end of draw
        buffer.show();
        g.dispose();
    }
    
    @Override
    public void run(){//needs to make class Runnable
        init();
        
        int fps = 50; //how many frame per second
        double timePerTick = 1000000000/fps;
        double delta = 0;//time between two frames or break
        long current = System.nanoTime();
        
        while(running){
            
            delta = delta + (System.nanoTime()-current)/timePerTick;
            current = System.nanoTime();
            if(delta>=1){
            tick();
            renderGame();
            delta--;
            }
        }
    }
    public static void setBackgroundColour(Color c) {
    	backgroundColour = c;
    }
    
}
