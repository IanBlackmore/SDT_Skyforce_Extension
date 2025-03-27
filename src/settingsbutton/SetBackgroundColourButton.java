package settingsbutton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;

import settings.Settings;

public class SetBackgroundColourButton implements SettingsButton {
	private JButton button;
	private ArrayList <Color> colourList = new ArrayList <Color>();
	private int currentColour;
	
	
	
	public SetBackgroundColourButton() {
		button = new JButton("Set Background Colour");
		currentColour = 0;
		colourList.add(Color.blue);
		colourList.add(Color.cyan);
		colourList.add(Color.black);
		colourList.add(Color.white);
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
		Settings.setBackgroundColour(colourList.get(currentColour%colourList.size()));
		System.out.println("Current background colour: " + colourList.get(currentColour%colourList.size()).toString());
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
