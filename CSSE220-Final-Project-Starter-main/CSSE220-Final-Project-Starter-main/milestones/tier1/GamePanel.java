package tier1;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.JPanel;

/**
 * Controller class for the game.
 * Handles user input (buttons + keys).
 */


public class GamePanel extends JPanel{
	private GameComponent canvas;
	private GameMenu menu;

	public GamePanel() {
		canvas = new GameComponent();
		menu = new GameMenu();
	
		this.setLayout(new BorderLayout());
		this.add(canvas,BorderLayout.CENTER);
		this.add(menu, BorderLayout.SOUTH);
		
		// TODO (Step 3): Connect ONE button first (Right)
		// - Use lambda
		// - Call canvas.move()
		
		
		// TODO (Step 5): Add Left and Center buttons
	}
	
	// TODO (Step 6): Refactor into inner class ButtonListener
	// - Handle all buttons in one place

	
	// TODO (Step 7): Add KeyListener
	// - Print key info (getKeyCode)
	// - Try pressing keys

	// TODO (Step 8): Make panel focusable
	
	// TODO (Step 9): Add key controls (VK)

	// TODO (Step 10): Fix focus issue
	// - Buttons steal focus
	

}
