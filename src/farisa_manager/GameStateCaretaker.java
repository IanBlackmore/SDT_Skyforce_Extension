package farisa_manager;

//package farisa_setUp;

import java.util.Stack;

public class GameStateCaretaker {
    private Stack<GameStateMemento> savedStates = new Stack<>();

    public void saveState(GameStateMemento memento) {
        savedStates.push(memento);
    }

    public GameStateMemento restoreState() {
        return savedStates.isEmpty() ? null : savedStates.pop();
    }
}

