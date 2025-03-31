/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farisa_entity;

import farisa_bullet.Bullet;
import static farisa_bullet.Bullet.bulletWidth;
import farisa_display.Display;
import farisa_manager.gameManager;
import farisa_setUp.gameSetUp;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Mehedi Hasan Akash
 */

public class Player implements KeyListener {
    private int x;
    private int y;
    public static final int playerWidth = 30;
    public static final int playerHeight = 30;
    private boolean left;
    private boolean right;
    private boolean fire;
    private long current;
    private long delay;
    
    private List<Integer> lives;
    private static Color playerColour = Color.red;
    private boolean exit = false;
    
    public Player(int x, int y){
        this.x = x;
        this.y = y;
        
        lives = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            lives.add(i);
        }
    }
    
    public void init(){
        Display.frame.addKeyListener(this);
        current = System.nanoTime();
        delay = 100;
    }

    public void tick() {
        if (hasLives()) {
            if (left && x >= 55) {
                x -= 5;
            }
            if (right && x <= 445 - playerWidth) {
                x += 5;
            }
            if (fire) {
                long breaks = (System.nanoTime() - current) / 1000000;
                if (breaks > delay) {
                    gameManager.bullet.add(new Bullet(x + (playerWidth / 2) - (bulletWidth / 2), y));
                }
                current = System.nanoTime();
            }
        }
    }

    public void render(Graphics g) {
        if (hasLives()) {
            g.setColor(playerColour);
            g.fillRect(x, y, playerWidth, playerHeight);
        }
    }

    public void keyPressed(KeyEvent e) {
        int source = e.getKeyCode();
        if (source == KeyEvent.VK_LEFT || source == KeyEvent.VK_A) {
            left = true;
        }
        if (source == KeyEvent.VK_RIGHT || source == KeyEvent.VK_D) {
            right = true;
        }
        if (source == KeyEvent.VK_SPACE) {
            fire = true;
        }
        if (source == KeyEvent.VK_ESCAPE) {
            exit = true;
        }
    }

    public void keyReleased(KeyEvent e) {
        int source = e.getKeyCode();
        if (source == KeyEvent.VK_LEFT || source == KeyEvent.VK_A) {
            left = false;
        }
        if (source == KeyEvent.VK_RIGHT || source == KeyEvent.VK_D) {
            right = false;
        }
        if (source == KeyEvent.VK_SPACE) {
            fire = false;
        }
    }

    public void keyTyped(KeyEvent e) {}

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Iterator<Integer> getLivesIterator() {
        return lives.iterator();
    }

    public void loseLife() {
        if (hasLives()) {
            lives.remove(lives.size() - 1);
        }
        System.out.println("Lives remaining: " + lives.size());
    }

    public boolean hasLives() {
        return !lives.isEmpty();
    }

    public void setLives(List<Integer> lives) {
        this.lives = new ArrayList<>(lives);
    }

    public static void setPlayerColour(Color c) {
        playerColour = c;
    }

    public boolean getExit() {
        return exit;
    }
}
