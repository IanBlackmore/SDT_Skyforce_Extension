/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package farisa_setUp;

import farisa_display.Display;
import settings.Settings;
import farisa_graphics.loadImage;
import farisa_manager.GameStateCaretaker;
import farisa_manager.gameManager;
import menu.Menu;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.util.logging.Level;
import java.util.logging.Logger;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 *
 * @author Mehedi Hasan Akash
 */
//package farisa_setUp;

import farisa_display.Display;
import farisa_manager.gameManager;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class gameSetUp implements Runnable, KeyListener {
    private String title;
    private int width, height;
    private boolean running, paused;
    private Thread thread;
    private Display display;
    private BufferStrategy buffer;
    private Graphics g;
    private gameManager manager;
    private GameStateCaretaker caretaker; //Caretaker to store game state

    public static final int gameWidth = 400, gameHeight = 400;
    private static Color backgroundColour = Color.black;
    
    //for run()
    int fps = 50;
    double timePerTick = 1000000000 / fps, delta = 0;

    public gameSetUp(String title, int width, int height) {
        this.title = title;
        this.width = width;
        this.height = height;
        caretaker = new GameStateCaretaker(); // Initialize caretaker
    }

    //The init() method sets up the display and initializes the game manager.
    public void init() 
    {
        display = new Display(title, width, height);
        display.getFrame().addKeyListener(this); // Register key listener
        manager = new gameManager();
        manager.init();
    }

    public synchronized void start() {
        if (running) return;
        running = true;
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }

    public synchronized void stop() {
        if (!running) return;
        running = false;
        try { thread.join(200); } 
        catch (InterruptedException e) { e.printStackTrace(); }
        g.dispose();
        System.exit(0);
    }

    public void togglePause() {
        paused = !paused;
        if (paused) {
            caretaker.saveState(manager.saveState()); // Save game state
        } else {
            manager.restoreState(caretaker.restoreState()); // Restore game state
        }
    }

    public void tick() {
        if (!paused) manager.tick();
        if (manager.getExit()) stop();
    }

    public void renderGame()//running 
    {
        buffer = display.getCanvas().getBufferStrategy();
        if (buffer == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = buffer.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        g.setColor(backgroundColour);
        g.fillRect(50, 50, gameWidth, gameHeight);
        manager.render(g);
        buffer.show();
        g.dispose();
    }

    private void renderPauseScreen() //displays a pause screen when the game is paused.
    {
        buffer = display.getCanvas().getBufferStrategy();
        if (buffer == null) {
            display.getCanvas().createBufferStrategy(3);
            return;
        }

        g = buffer.getDrawGraphics();
        g.clearRect(0, 0, width, height);
        g.setColor(new Color(0, 0, 0, 150));
        g.fillRect(50, 50, gameWidth, gameHeight);
        g.setColor(Color.WHITE);
        g.drawString("Game Paused", width / 2 - 40, height / 2 - 10);
        g.drawString("Press P to Resume", width / 2 - 50, height / 2 + 10);
        buffer.show();
        g.dispose();
    }

    
    @Override
    public void run() {
        init();
        long current = System.nanoTime(); // initializes the current variable with the current time in nanoseconds.

        while (running) 
        {
        	//This line calculates the time passed since the last frame.
        	//System.nanoTime() - current gives the time elapsed in nanoseconds.
        	//Dividing by timePerTick converts this elapsed time into the number of frames that should have passed.
        	//delta accumulates this value, representing the total number of frames that should have been processed.
            delta += (System.nanoTime() - current) / timePerTick;
            current = System.nanoTime();
            if (!paused) {
            	//This condition checks if enough time has passed to process at least one frame.
            	//If true, tick() is called to update the game logic, and renderGame() is called to draw the game.
                if (delta >= 1) {
                    tick();
                    renderGame();
                    delta--;//delta-- decrements delta by 1, indicating that one frame has been processed.
                }
            } else {
            	delta=0;//reset when paused
                renderPauseScreen();
            }
        }
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_P) togglePause();
    }
    @Override public void keyReleased(KeyEvent e) {}
    @Override public void keyTyped(KeyEvent e) {}

	public static void setBackgroundColour(Color backgroundColour2) {
		// TODO Auto-generated method stub
	    backgroundColour = backgroundColour2;  // Set the static background color to the new color

	}

}

