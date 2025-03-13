/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farisa_bullet;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Mehedi Hasan Akash
 */
public class Bullet {
    
    private int x;
    private int y;
    private int speed;
    public static final int bulletWidth = 6;
    public static final int bulletHeight = 10;
    
    public Bullet(int x, int y){
        this.x = x;
        this.y = y;
        speed = 10;
    }
    public void tick(){
        y -= speed;//as bullet goes up so decrement from y axis
        
        
    }
    public int getY(){//getter for y to access y from other classes
        return y;
    }
    public int getX(){
        return x;
    }
    public void render(Graphics g){
        g.setColor(Color.blue);//bullet color is set as blue
        g.fillRect(x, y, bulletWidth, bulletHeight);//bullet size
    }
    
}
