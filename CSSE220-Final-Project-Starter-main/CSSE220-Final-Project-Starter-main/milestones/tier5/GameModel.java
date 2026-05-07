package tier5;

import java.awt.Graphics2D;

/**
 * Represents the core game state (the "model").
 *
 * Responsibilities:
 *   - Store all game data (ball, walls, and other objects)
 *   - Load a level from a text file
 *   - Update the game state over time (movement, collisions)
 *   - Provide data for drawing (but does NOT handle graphics directly)
 */
 
public class GameModel {
	// What game objects do we need to store (walls, enemies, player, coins?
	// TODO: store multiple balls
    // private ArrayList<Ball> balls;
	

	public GameModel() {
		// TODO: load a level file (e.g., "level1.txt")
	}
	
	/**
	 * Loads a level from a text file. 
	 * Reads file and throws exceptions if invalid
	 *
	 * @param filename name of the level file
	 * @throws IllegalStateException if the level is invalid
	 */
	public void loadLevel(String filename) {
	    // TODO: read file and build game objects
	}
	
	public void update() {
        // TODO: update each ball
    }

    public void draw(Graphics2D g2) {
        // TODO: draw each ball
    }
	
}
