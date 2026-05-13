package model;

import java.util.ArrayList;

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
	private ArrayList<Gem> gems;
	private ArrayList<Enemy> zombies;
	private int totalgems;
	private int gemscollected;
	
	public GameModel() {
		player = new Player(50, 50, 80, 80);
	}
	public void Update() {
		for (Gem gem: gems) {
			if (gem.collidesWith(player)) {
				if(totalgems> gemscollected) {
					gemscollected++;
					gems.remove(gem);
				}
				
			}
		}
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
	
	public boolean isGameOver() {
		if (player.getLives() == 0) return true;
		else return false;
	}

}
