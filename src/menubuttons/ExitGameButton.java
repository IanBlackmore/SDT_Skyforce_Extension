package menubuttons;
// concrete command class for exit game
public class ExitGameButton implements MenuButtons {
	public void execute() {
		System.exit(0); // exit application
	}

}
