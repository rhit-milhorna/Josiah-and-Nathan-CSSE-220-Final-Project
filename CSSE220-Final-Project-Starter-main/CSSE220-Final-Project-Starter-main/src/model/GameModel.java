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
	public ArrayList<Gem> gems;
	public ArrayList<Enemy> zombies;
	private int totalgems;
	private int gemscollected;
	
	public GameModel() {
		zombies = new ArrayList<>();
		gems = new ArrayList<>();
		player = new Player(50, 50, 80, 80);
		zombies.add(new Enemy(250, 250));
		zombies.add(new Enemy(1500,500));
		gems.add(new Gem(500,500));
		gems.add(new Gem(800,200));
		gems.add(new Gem(200,1000));
		gems.add(new Gem(1500,600));
		totalgems = gems.size();
	}
	public void Update() {
		if(!(gems==null)){
			for (int i = gems.size() - 1; i >= 0; i--) {
			if (gems.get(i).collidesWith(player)) {
				if(totalgems> gemscollected) {
					gemscollected++;
					//gems.remove(gem);
				}
				gems.remove(gems.get(i));
			}
		}
		}
		
		
		if(!(zombies == null)) {
		for (Enemy	 zombie: zombies) {
			zombie.update();
			if (zombie.collidesWith(player)) {
				if(player.getLives()>0) {
					
					player.removeLife();
					player.reset();
				}
				
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
	
	public int getGemsCollected() {
		return this.gemscollected;
	}

}
