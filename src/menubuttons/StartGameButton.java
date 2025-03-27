package menubuttons;
// concrete command for start game
import farisa_setUp.gameSetUp;
import javax.swing.*;

public class StartGameButton implements MenuButtons {
	private JFrame frame;
	
	public StartGameButton(JFrame frame) {
		this.frame = frame;
	}
	
	public void execute() {
		System.out.println("Start button clicked");
		frame.dispose(); // close menu window
		gameSetUp game = new gameSetUp("SkyForce", 500, 600);
		game.start(); // launch game
	}

}
