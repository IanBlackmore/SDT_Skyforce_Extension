package settingsbutton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import settings.Settings;

public class SetEnemyColourButton implements SettingsButton {
	JButton button;
	ArrayList <Color> colourList = new ArrayList <Color>();
	int currentColour;
	
	public SetEnemyColourButton() {
		button = new JButton("Set Enemy Colour");
		currentColour = 0;
		colourList.add(Color.blue);
		colourList.add(Color.cyan);
		colourList.add(Color.black);
		colourList.add(Color.red);
		button.setBounds(300, 300, 100, 100);
	}
	
	@Override
	public void execute() {
		Settings.setEnemyColour(colourList.get(currentColour%colourList.size()));
		
		System.out.println("Current enemy colour: " + colourList.get(currentColour%colourList.size()).toString());
		currentColour++;
	}

	@Override
	public JButton getButton() {
		
		button.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e)
            {
                execute();
            }
        });
		return button;
	}
	@Override
	public void addButtonToSettings() {
		Settings.addButton(this);
	}
	
}
