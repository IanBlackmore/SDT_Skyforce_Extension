package farisa_manager;

// Handle game objects

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
 * Game manager for handling the game logic.
 */
public class gameManager {
    private Player player;
    public static ArrayList<Bullet> bullet;
    private ArrayList<Enemy> enemies;
    private long current; // To track time for enemy creation
    private long delay; // Delay for creating new enemies

    public gameManager() {}

    public void init() {
        // Initialize the player, bullets, enemies, and set up the initial timing for enemy creation.
        player = new Player((gameSetUp.gameWidth / 2) + 50 - (Player.playerWidth / 2), (gameSetUp.gameHeight - 30) + 50);
        player.init();
        bullet = new ArrayList<>();
        enemies = new ArrayList<>();
        current = System.nanoTime();
        delay = 800; // Delay for enemy creation (2 seconds)
    }

    public void tick() {
        // Update the player, bullets, and enemies, and handle the creation of new enemies at regular intervals.
        player.tick();
        for (int i = 0; i < bullet.size(); i++) {
            bullet.get(i).tick(); // Update each bullet's position
        }

        // Handle enemy creation based on delay
        long breaks = (System.nanoTime() - current) / 1000000; // Calculate time elapsed since the last enemy creation
        if (breaks > delay) {
            for (int i = 0; i < 2; i++) {
                // Create random enemies
                Random rand = new Random();
                int randX = rand.nextInt(450); // Random X position for enemy
                int randY = rand.nextInt(450); // Random Y position for enemy
                if (player.getLivesIterator().hasNext()) {
                    enemies.add(new Enemy(randX, -randY)); // Enemies spawn from the top
                }
            }
            current = System.nanoTime(); // Reset the time for the next enemy creation
        }

        // Update the enemies' positions
        for (int i = 0; i < enemies.size(); i++) {
            enemies.get(i).tick();
        }
    }

    public void render(Graphics g) {
        // Render the player, bullets, enemies, and life counter on the screen
        // Get the ground level based on the player's position and height
        int groundLevel = player.getY() + Player.playerHeight;

        // Render life counter (each life is represented by a small green rectangle)
        Iterator<Integer> livesIterator = player.getLivesIterator();
        int lifeX = 10;
        int lifeY = 10;
        while (livesIterator.hasNext()) {
            g.setColor(Color.GREEN);
            g.fillRect(lifeX, lifeY, 20, 20); // Draw a small rectangle for each life
            lifeX += 25;
            livesIterator.next();
        }

        // Render the player
        player.render(g);

        // Render each bullet
        for (int i = 0; i < bullet.size(); i++) {
            bullet.get(i).render(g);
        }

        // Remove bullets that go off the screen (above the playground)
        for (int i = 0; i < bullet.size(); i++) {
            if (bullet.get(i).getY() <= 50) {
                bullet.remove(i);
                i--;
            }
        }

        // Render each enemy within the screen bounds
        for (int i = 0; i < enemies.size(); i++) {
            // Ensure the enemy is within the bounds of the game area
            if (!(enemies.get(i).getX() >= 450 - enemyWidth || enemies.get(i).getY() <= 50 || enemies.get(i).getY() >= 450 - enemyHeight || enemies.get(i).getX() <= 50)) {
                enemies.get(i).render(g);
            }
        }

        // Collision detection and removal of enemies when colliding with player or bullets
        for (int i = 0; i < enemies.size(); i++) {
            int ex = enemies.get(i).getX();
            int ey = enemies.get(i).getY();
            int px = player.getX();
            int py = player.getY();

            // Check if enemy collides with the player
            if (ey >= gameSetUp.gameHeight + playerHeight || (px < ex + enemyWidth && px + playerWidth > ex && py < ey + enemyWidth && py + playerWidth > ey)) {
                enemies.remove(i);
                i--;
                player.loseLife(); // Decrement player life

                // Clear enemies if player has no lives left
                if (!player.getLivesIterator().hasNext()) {
                    enemies.clear();
                }
            }

            // Check if enemy is hit by a bullet
            for (int j = 0; j < bullet.size(); j++) {
                int bx = bullet.get(j).getX();
                int by = bullet.get(j).getY();

                if (ex < bx + bulletWidth && ex + enemyWidth > bx && ey < by + bulletWidth && ey + enemyWidth > by) {
                    enemies.remove(i); // Remove enemy on collision with bullet
                    i--;
                    bullet.remove(j); // Remove the bullet
                    j--;
                }
            }
        }
    }

    // **SAVE GAME STATE (Memento)**
    public GameStateMemento saveState() {
        return new GameStateMemento(player.getLivesIterator(), bullet, enemies);
    }

    // **LOAD GAME STATE (Restore Memento)**
    public void restoreState(GameStateMemento memento) {
        if (memento != null) {
            List<Integer> restoredLives = memento.getPlayerLives();
            player.setLives(restoredLives); // Restore player lives
            bullet = memento.getSavedBullets(); // Restore bullets
            enemies = memento.getSavedEnemies(); // Restore enemies
            System.out.println("Game state restored!");
        }
    }

    // Check if the player wants to exit the game
    public boolean getExit() {
        return player.getExit();
    }
}
