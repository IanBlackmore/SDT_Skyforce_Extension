/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farisa_manager;

//handle game objs

import farisa_bullet.Bullet;
import static farisa_bullet.Bullet.bulletWidth;
import farisa_enemies.Enemy;
import static farisa_enemies.Enemy.enemyHeight;
import static farisa_enemies.Enemy.enemyWidth;
import farisa_entity.Player;
import static farisa_entity.Player.playerWidth;
import static farisa_entity.Player.playerHeight;

import farisa_setUp.gameSetUp;
import settings.Settings;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Mehedi Hasan Akash
 */
//package farisa_manager;

import farisa_bullet.Bullet;
import farisa_enemies.Enemy;
import farisa_entity.Player;
//import farisa_setUp.GameStateMemento;
import java.util.ArrayList;

public class gameManager {
    private Player player;
    public static ArrayList<Bullet> bullet;
    private ArrayList<Enemy> enemies;
    private long current;//create time and delay for enemy creation
    private long delay;
    //private int health;

    public gameManager() {}

    public void init() // initializes the player, bullets, enemies, and sets up the initial timing and health.
    {
        player = new Player((gameSetUp.gameWidth/2)+50-(Player.playerWidth/2),(gameSetUp.gameHeight-30)+50);
        player.init();
        bullet = new ArrayList<>();
        enemies = new ArrayList<>();
        current = System.nanoTime();
        delay = 800;//delay is 2 second
        //health = player.getHealth();
    }

    public void tick() //updates the player, bullets, and enemies. It also handles the creation of new enemies at regular intervals.
    {
    	 player.tick();
         for(int i = 0; i<bullet.size(); i++)
         {//for bullet increments depending on bullet size
         	bullet.get(i).tick();
         }
         
         // Handle enemy creation
         long breaks = (System.nanoTime()-current)/1000000;// Calculate the time elapsed since the last enemy creation
         if(breaks > delay)// Check if the elapsed time exceeds the delay 
         {     
	         for(int i = 0; i<2; i++){//to generate enemies randomly
	             Random rand = new Random();
	             int randX = rand.nextInt(450);
	             int randY = rand.nextInt(450);
	             if(player.getLivesIterator().hasNext()){
	                enemies.add(new Enemy(randX,-randY));//y is negative to generate enemies from up above the playground 
	             }                      
     
         }        
         current = System.nanoTime();//reset
         }
         
         

         //to update enemies and check for ground collision
         for(int i = 0; i<enemies.size(); i++){//for enemy increments depending on enemy size
             enemies.get(i).tick();// Update enemy position
          
             
         }
         
      

    }

    public void render(Graphics g) //draws the player, bullets, and enemies on the screen. It also handles collision detection and removal of bullets and enemies.
    {
    	
    	// Get ground level (based on the player's y-position and height)
        int groundLevel = player.getY() + Player.playerHeight;
        
    	// Render life counter
        Iterator<Integer> livesIterator = player.getLivesIterator();
        int lifeX = 10; // Starting X position for life counter
        int lifeY = 10; // Starting Y position for life counter
        while (livesIterator.hasNext()) {
            g.setColor(Color.GREEN);
            g.fillRect(lifeX, lifeY, 20, 20); // Draw a small rectangle for each life
            lifeX += 25; // Move to the next position
            livesIterator.next();

        }
        
        
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
             if(ey >= gameSetUp.gameHeight + playerHeight || px < ex + enemyWidth && px + playerWidth > ex && py < ey + enemyWidth && py + playerWidth > ey){
                   enemies.remove(i);
                   i--;
                   //health--;
                   //player.setHealth(player.getHealth() - 1); // Decrement health and update lives
                   player.loseLife(); // Decrement lives
                   
                   if (!player.getLivesIterator().hasNext()) {
                       enemies.removeAll(enemies);
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

    // **CREATE MEMENTO (SAVE GAME STATE)**
    public GameStateMemento saveState() {
        return new GameStateMemento(player.getLivesIterator(), bullet, enemies);
    }

 // **RESTORE MEMENTO (LOAD GAME STATE)**
    public void restoreState(GameStateMemento memento) {
        if (memento != null) {
            List<Integer> restoredLives = memento.getPlayerLives();
            player.setLives(restoredLives); // Restore lives
            bullet = memento.getSavedBullets();
            enemies = memento.getSavedEnemies();
            System.out.println("Game state restored!");
        }
    }

    public boolean getExit() { return player.getExit(); }
}

