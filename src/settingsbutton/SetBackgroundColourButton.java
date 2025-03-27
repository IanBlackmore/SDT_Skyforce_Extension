package settingsbutton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;

import settings.Settings;

public class SetBackgroundColourButton implements SettingsButton {
	private JButton button;
	private ArrayList <Color> colourList = new ArrayList <Color>();
	private int currentColour;
	JTextField textField;
	
	
	public SetBackgroundColourButton() {
		button = new JButton("Set Background Colour");
		currentColour = 0;
		colourList.add(Color.black);
		colourList.add(Color.blue);
		colourList.add(Color.cyan);
		
		colourList.add(Color.white);
		button.setBounds(300, 300, 100, 100);
		button.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e)
            {
                execute();
            }
        });
		textField = new JTextField("Current Background Colour");
		textField.setForeground(colourList.get(currentColour));
		textField.setEditable(false);
		Settings.addTextToPanel(textField);
	}
	
	@Override
	public void execute() {
		currentColour++;
		Settings.setBackgroundColour(colourList.get(currentColour%colourList.size()));
		System.out.println("Current background colour: " + colourList.get(currentColour%colourList.size()).toString());
		textField.setForeground(colourList.get(currentColour%colourList.size()));
		
		
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
