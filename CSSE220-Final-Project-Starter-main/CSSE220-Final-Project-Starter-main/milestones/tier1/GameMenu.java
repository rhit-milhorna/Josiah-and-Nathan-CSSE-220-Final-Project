package tier1;

import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Menu class for the game.
 * Handles menu buttons.
 */

public class GameMenu extends JPanel{
	private JButton leftButton;
	private JButton rightButton;
	private JButton centerButton;
	
	public GameMenu() {
		this.setLayout(new FlowLayout(FlowLayout.CENTER, 8, 6));
		this.leftButton = new JButton("Left");
		this.rightButton = new JButton("Right");
		this.centerButton = new JButton("Center");

		this.add(this.leftButton);
		this.add(this.rightButton);
		this.add(this.centerButton);
		

	
	// TODO (Step 1): Add ActionListener to each button
	// - Print event info (getSource, getActionCommand)
	// - Show a JOptionPane message when clicked
		
	}
	
	public JButton getLeftButton() {
		return this.leftButton;
	}

	public JButton getRightButton() {
		return this.rightButton;
	}

	public JButton getCenterButton() {
		return this.centerButton;
	}
	

}
