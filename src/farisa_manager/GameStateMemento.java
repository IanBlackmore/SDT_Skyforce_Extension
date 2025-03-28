package farisa_manager;

//package farisa_setUp;

import farisa_bullet.Bullet;
import farisa_enemies.Enemy;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameStateMemento {
    private final List<Integer> playerLives;
    private final ArrayList<Bullet> savedBullets;
    private final ArrayList<Enemy> savedEnemies;

    public GameStateMemento(Iterator<Integer> iterator, ArrayList<Bullet> bullets, ArrayList<Enemy> enemies) {
        this.playerLives = new ArrayList<>();
        while (iterator.hasNext()) {
            this.playerLives.add(iterator.next());
        }
        this.savedBullets = new ArrayList<>(bullets); // Deep copy bullets
        this.savedEnemies = new ArrayList<>(enemies); // Deep copy enemies
    }

    public List<Integer> getPlayerLives() { return new ArrayList<>(playerLives); }
    public ArrayList<Bullet> getSavedBullets() { return new ArrayList<>(savedBullets); }
    public ArrayList<Enemy> getSavedEnemies() { return new ArrayList<>(savedEnemies); }
}

