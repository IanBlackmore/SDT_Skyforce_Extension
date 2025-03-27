package settings;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import farisa_bullet.Bullet;
import farisa_enemies.Enemy;
import farisa_entity.Player;
import farisa_setUp.gameSetUp;
import settingsbutton.*;

public class Settings {
	static Color playerColour = Color.red;
	static Color backgroundColour = Color.black;
	static Color bulletColour = Color.blue;
	static Color enemyColour = Color.cyan;
	public static final int gameWidth = 400;
    public static final int gameHeight = 400;
    
    private static JFrame frame;
	static boolean exitSettings = false;
	private static ArrayList <SettingsButton> buttonList;
	private static JPanel panel;
    
	public static void setPlayerColour(Color c) {
		playerColour = c;
	}

	public static void setBackgroundColour(Color c) {
		backgroundColour = c;
	}
	
	public static void setBulletColour(Color c) {
		bulletColour = c;
	}
	
	public static void setEnemyColour(Color c) {
		enemyColour = c;
	}
	
	public static Color getPlayerColour() {
		return playerColour;
	}
	
	public static Color getBackgroundColour() {
		return backgroundColour;
	}
	
	public static Color getBulletColour() {
		return bulletColour;
	}

	public static Color getEnemyColour() {
		return enemyColour;
	}
	
	public static void applySettings() {
		Player.setPlayerColour(playerColour);
		gameSetUp.setBackgroundColour(backgroundColour);
		Enemy.setColour(enemyColour);
		Bullet.setColour(bulletColour);
	}
	
	
	public static void init() {
		buttonList = new ArrayList<SettingsButton>();
		SettingsButton playerColour = new SetPlayerColourButton();
		SettingsButton bulletColour = new SetBulletColourButton();
		SettingsButton backgroundColour = new SetBackgroundColourButton();
		SettingsButton enemyColour = new SetEnemyColourButton();
		buttonList.add(playerColour);
		buttonList.add(bulletColour);
		buttonList.add(backgroundColour);
		buttonList.add(enemyColour);
		panel = new JPanel();
	}
	
	public static void render() {
		
		frame = new JFrame("Settings");
		JButton exitButton = new JButton("Exit Settings Menu");
		
        exitButton.setBounds(100, 100, 200, 100);
        exitButton.addActionListener(new ActionListener() {
        	@Override
            public void actionPerformed(ActionEvent e)
            {
                exitButtonClicked(e);
            }
        });
        
  
        
		addAllButtonsToFrame();
		panel.add(exitButton);
    
    
        initFrame();
		
	}
	
	private static void initFrame() {
		
        frame.setSize(500,500);
        frame.setVisible(true);//makes sure the visibility of the frame
        frame.setLocationRelativeTo(null);//makes the frame appear in the middle of the screen
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//application closes if the frame is closed
        frame.add(panel);
        
        /*Canvas canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(500,500));
        canvas.setBackground(Color.gray);//Set full frame color
        canvas.setFocusable(false); 
        frame.add(canvas);*/
        
        
	}
	
	private static void exitButtonClicked(ActionEvent e) {
		applySettings();
		frame.setVisible(false);
		frame.removeAll();
		frame.dispose();
		panel.removeAll();
	}
	
	public static void addButton(SettingsButton s) {
		buttonList.add(s);

	}
	
	private static void addAllButtonsToFrame() {
		
		for (int i = 0; i < buttonList.size(); i++) {
			panel.add(buttonList.get(i).getButton());
		}
	}
	
	public static void addTextToPanel(JTextField t) {
		panel.add(t);
	}
	
	
	
}
