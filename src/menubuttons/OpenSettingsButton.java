package menubuttons;
// concrete command for settings button
import settings.Settings;

public class OpenSettingsButton implements MenuButtons {
	public void execute() {
		Settings.render(); // open settings
	}
}
