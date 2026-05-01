package model;



/**
 * Stores the current state of the game and controls the main game rules.
 * 
 * This is where the game keeps track of objects such as the player,
 * walls, gems, zombies, score, lives, and levels.
 * 
 * GameModel should update the game state, but it should not draw anything.
 * Drawing belongs in GameComponent.
 */

public class GameModel {
	
	// Work on the lab to complete GameModel and Player for initial setup
	
	private Player player;
	
	public GameModel() {
		player = new Player(50, 50, 80, 80);
	}
	
	public Player getPlayer() {
		return this.player;
	}
	
	public void movePlayerUp() {
		player.move(0, -10);
	}
	public void movePlayerDown() {
		player.move(0, 10);
	}
	public void movePlayerLeft() {
		player.move(-10, 0);
	}
	public void movePlayerRight() {
		player.move(10, 0);
	}

}
