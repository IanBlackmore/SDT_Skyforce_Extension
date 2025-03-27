package settingsbutton;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JTextField;

import settings.Settings;

public class SetEnemyColourButton implements SettingsButton {
	private JButton button;
	private ArrayList <Color> colourList = new ArrayList <Color>();
	private int currentColour;
	JTextField textField;
	
	public SetEnemyColourButton() {
		button = new JButton("Set Enemy Colour");
		currentColour = 0;
		colourList.add(Color.cyan);
		colourList.add(Color.blue);
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
		textField = new JTextField("Current Enemy Colour");
		textField.setForeground(colourList.get(currentColour));
		textField.setEditable(false);
		Settings.addTextToPanel(textField);
	}
	
	@Override
	public void execute() {
		currentColour++;
		Settings.setEnemyColour(colourList.get(currentColour%colourList.size()));
		textField.setForeground(colourList.get(currentColour%colourList.size()));
		System.out.println("Current enemy colour: " + colourList.get(currentColour%colourList.size()).toString());
		
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
