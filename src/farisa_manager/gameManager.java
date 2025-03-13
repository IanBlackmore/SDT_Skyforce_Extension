/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farisa_manager;

import farisa_bullet.Bullet;
import static farisa_bullet.Bullet.bulletWidth;
import farisa_enemies.Enemy;
import static farisa_enemies.Enemy.enemyHeight;
import static farisa_enemies.Enemy.enemyWidth;
import farisa_entity.Player;
import static farisa_entity.Player.playerWidth;
import farisa_setUp.gameSetUp;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author Mehedi Hasan Akash
 */
public class gameManager {
    private Player player;
    public static ArrayList<Bullet> bullet;//take arrayList for multiple bullets
    private ArrayList<Enemy> enemies;//take ArrayList for multipls enemies
    private long current;//create time and delay for enemy creation
    private long delay;
    private int health;
    
    public gameManager(){
        
    }
    
    public void init(){
        player = new Player((gameSetUp.gameWidth/2)+50-(Player.playerWidth/2),(gameSetUp.gameHeight-30)+50);
        player.init();
        bullet = new ArrayList<Bullet>();//initialize bullets
        enemies = new ArrayList<Enemy>();
        current = System.nanoTime();
        delay = 800;//delay is 2 second
        health = player.getHealth();
        
    }
    
    public void tick(){
        player.tick();
        for(int i = 0; i<bullet.size(); i++){//for bullet increments depending on bullet size
        bullet.get(i).tick();
        
        
    }
        long breaks = (System.nanoTime()-current)/1000000;
        if(breaks > delay){      
        for(int i = 0; i<2; i++){//to generate enemies randomly
            Random rand = new Random();
            int randX = rand.nextInt(450);
            int randY = rand.nextInt(450);
            if(health > 0){
               enemies.add(new Enemy(randX,-randY));//y is negative to generate enemies from up above the playground 
            }
            
            
    
        }        
        current = System.nanoTime();
        }
        //to update enemies
        for(int i = 0; i<enemies.size(); i++){//for enemy increments depending on enemy size
            enemies.get(i).tick();
            
        }
        
    }
    public void render(Graphics g){
        player.render(g);
        for(int i = 0; i<bullet.size() ; i++){//i is for enemy
            bullet.get(i).render(g);
        }
        for(int i = 0; i<bullet.size(); i++){//so bullets don't go over the boundary
            if(bullet.get(i).getY() <= 50){
                bullet.remove(i);
                i--;
            }
        }
        //to draw enemies
        for(int i = 0; i<enemies.size(); i++){
            //condition to bound enemy creation inside playground box
            if(!(enemies.get(i).getX()>=450-enemyWidth || enemies.get(i).getY()<=50 || enemies.get(i).getY()>=450-enemyHeight || enemies.get(i).getX()<=50)){
            enemies.get(i).render(g);
            }
            
        }
        for(int i = 0; i<enemies.size(); i++){//loop for enemies
            int ex = enemies.get(i).getX();
            int ey = enemies.get(i).getY();
            
            int px = player.getX();
            int py = player.getY();
            
            //collution detection and remove for enemy and player
             if(px < ex + enemyWidth && px + playerWidth > ex && py < ey + enemyWidth && py + playerWidth > ey){
                   enemies.remove(i);
                   i--;
                   health--;
                   System.out.println(health);
                   if(health <= 0){
                       enemies.removeAll(enemies);
                       player.setHealth(0);
                   }
               } 
            
            for(int j = 0; j<bullet.size(); j++){//loop for bullets
               int bx = bullet.get(j).getX();
               int by = bullet.get(j).getY();
                //collution detection and remove for bullet and enemy
               if(ex < bx + bulletWidth && ex + enemyWidth > bx && ey < by + bulletWidth && ey + enemyWidth > by){
                   enemies.remove(i);
                   i--;
                   bullet.remove(j);
                   j--;
               } 
               
            }
        }
    }
    
}
