package farisa_entity;

import java.util.List;

public class PlayerLivesIterator implements LivesIterator {
    private List<Integer> lives;
    private int position;

    public PlayerLivesIterator(List<Integer> lives) {
        this.lives = lives;
        this.position = 0;
    }

    @Override
    public boolean hasNext() {
        return position < lives.size();
    }

    @Override
    public Integer next() {
        if (hasNext()) {
            return lives.get(position++);
        }
        return null; // Or throw an exception
    }
}
