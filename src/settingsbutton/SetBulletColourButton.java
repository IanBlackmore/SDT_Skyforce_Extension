package settingsbutton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import settings.Settings;

public class SetBulletColourButton implements SettingsButton {
	private JButton button;
	private ArrayList <Color> colourList = new ArrayList <Color>();
	private int currentColour;
	
	public SetBulletColourButton() {
		button = new JButton("Set Bullet Colour");
		currentColour = 0;
		colourList.add(Color.blue);
		colourList.add(Color.cyan);
		colourList.add(Color.black);
		colourList.add(Color.red);
		button.setBounds(300, 300, 100, 100);
		button.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e)
            {
                execute();
            }
        });
	}
	
	@Override
	public void execute() {
		Settings.setBulletColour(colourList.get(currentColour%colourList.size()));
		System.out.println("Current bullet colour: " + colourList.get(currentColour%colourList.size()).toString());
		currentColour++;
	}

	@Override
	public JButton getButton() {
		
		return button;
	}
	@Override
	public void addButtonToSettings() {
		Settings.addButton(this);
	}
	
}
