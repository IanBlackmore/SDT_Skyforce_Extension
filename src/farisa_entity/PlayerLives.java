package farisa_entity;

import java.util.ArrayList;
import java.util.List;

public class PlayerLives implements LivesAggregate {
    private List<Integer> lives;

    public PlayerLives() {
        lives = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            lives.add(i);
        }
    }

    public void loseLife() {
        if (!lives.isEmpty()) {
            lives.remove(lives.size() - 1);
        }
    }

    public int getRemainingLives() {
        return lives.size();
    }

    @Override
    public LivesIterator createIterator() {
        return new PlayerLivesIterator(lives);
    }
}
