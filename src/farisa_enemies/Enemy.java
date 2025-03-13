/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farisa_enemies;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author Mehedi Hasan Akash
 */
public class Enemy {
    public int x;
    public int y;
    public static final int enemyWidth = 25;
    public static final int enemyHeight = 25;
    
    public Enemy(int x, int y){
        this.x = x;
        this.y = y;
    }
    public void tick(){
        
        y += 1;
    }
    public void render(Graphics g){
        g.setColor(Color.cyan);
        g.fillOval(x, y, enemyWidth, enemyHeight);
    }
    
    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }
    
}
