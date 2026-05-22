package ui;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Handles the start screen for the game
 * @author Josiah Layman
 */

public class StartPanel extends JPanel{
	
	private JLabel title = new JLabel("Main Menu");
	public JButton start = new JButton("Start Game");
	
	public StartPanel() {
		
		this.setLayout(new BorderLayout());
		add(title, BorderLayout.NORTH);
		add(start, BorderLayout.CENTER);
	}

}
