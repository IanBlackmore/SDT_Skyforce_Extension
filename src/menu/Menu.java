package menu;
// menu screen
import javax.swing.*;

import menubuttons.ExitGameButton;
import menubuttons.OpenSettingsButton;
import menubuttons.StartGameButton;

import java.awt.*;

public class Menu {
    private JFrame frame;
    private JPanel mainPanel;
    private CardLayout cardLayout;
    public Menu() {
        frame = new JFrame("SkyForce");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 500);
        frame.setLocationRelativeTo(null);

        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        initializeMainMenu();
    }

    private void initializeMainMenu() {
    	
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));
        
        // title
        JLabel titleLabel = new JLabel("SkyForce", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        
        // start
        JButton startButton = new JButton("Start Game");
        startButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        startButton.addActionListener(e -> new StartGameButton(frame).execute());

        // settings
        JButton settingsButton = new JButton("Settings");
        settingsButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        settingsButton.addActionListener(e -> new OpenSettingsButton().execute());

        // exit
        JButton exitButton = new JButton("Exit Game");
        exitButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        exitButton.addActionListener(e -> new ExitGameButton().execute());

        menuPanel.add(titleLabel);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(startButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(settingsButton);
        menuPanel.add(Box.createRigidArea(new Dimension(0, 10)));
        menuPanel.add(exitButton);

        mainPanel.add(menuPanel, "Main Menu");
        cardLayout.show(mainPanel, "Main Menu");
        frame.add(mainPanel);
    }

    public void show() {
        frame.setVisible(true);
    }
}

