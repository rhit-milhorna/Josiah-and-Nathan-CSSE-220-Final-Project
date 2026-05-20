package model;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;




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

	private int TILE_SIZE = 80;

	private Exit exit;

	private Player player;
	public ArrayList<Gem> gems;
	public ArrayList<Enemy> zombies;
	private ArrayList<String> levels;
	public ArrayList<Wall> walls;
	private int level;
	private int totalgems;
	private int gemscollected;
	
	

	
	public GameModel() {
		zombies = new ArrayList<>();
		gems = new ArrayList<>();
		walls = new ArrayList<>();
		levels = new ArrayList<>();

		/*
>>>>>>> Stashed changes
		player = new Player(50, 50, 80, 120);
		zombies.add(new Enemy(250, 250));
		zombies.add(new Enemy(1500,500));
		this.addGems();
		*/
		totalgems = gems.size();

		

		levels = new ArrayList<>();

		levels.add("level1-main.txt");
		levels.add("level2-main.txt");
		levels.add("level3-main.txt");
		levels.add("blanklevel.txt");
		

		loadLevel(levels.get(level));
	}
	
	
	
	public void loadLevel(String filename) {
		  int row = 0;

		InputStream stream = GameModel.class.getResourceAsStream(filename);
		
		if (stream == null) {
			throw new IllegalStateException("Level file not found: " + filename);
		}
		Scanner scanner = new Scanner(stream);
		
		while (scanner.hasNextLine()) {
			String line = scanner.nextLine();
			for (int col = 0; col < line.length(); col++) {
	            char ch = line.charAt(col);
	           // if (ch == 'P') {
	            //	int x = col * TILE_SIZE;
	            	//int y = row * TILE_SIZE;
	            //	player = new Ball(x,y,14);
	            	
	            //}
	            if (ch == 'P') {
	                int x = col * TILE_SIZE;
	                int y = row * TILE_SIZE;

	                player = new Player(x,y,100,120);

	                
	           
		}
	            if (ch == 'E') {
	                int x = col * TILE_SIZE;
	                int y = row * TILE_SIZE;

	                exit = new Exit(x,y);

	                
	           
		}
	            if (ch == 'Z') {
	                int x = col * TILE_SIZE;
	                int y = row * TILE_SIZE;

	                zombies.add(new Enemy(x,y));
	                

	                

		}
	            if (ch == 'G') {
	                int x = col * TILE_SIZE;
	                int y = row * TILE_SIZE;

	                gems.add(new Gem(x,y));

	                ;
	                 // stop after first ball
		}
	            if (ch == 'W') {
	                int x = col * TILE_SIZE;
	                int y = row * TILE_SIZE;

	                walls.add(new Wall(x,y));

	                ;
	                 // stop after first ball
		}
			}
			
			row++;
		}
		totalgems = gems.size();
		gemscollected = 0;
		scanner.close();
		
	    // TODO: read file and build game objects
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
				
				
				if(gems.size() == 0) {
					exit.unlockDoor();
				}
			}
		}
		}
		if (exit.collidesWith(player)) {
			if(gems.size() == 0) {
				zombies = new ArrayList<>();
				gems = new ArrayList<>();
				walls = new ArrayList<>();
				exit.lockDoor();
				level++;
				
				loadLevel(levels.get(level));
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
	public Exit getExit() {
		return this.exit;
	}
	public int getlevel() {
		return this.level;
	}

	public void movePlayerUp() {
		if(!(walls == null)) {
			for (Wall	 wall: walls) {
				if (wall.collidesWith(player)) {
					player.move(0, 21);
					return;
					}
					
				}
			
			}
		
		player.move(0, -10);
	}
	public void movePlayerDown() {
		if(!(walls == null)) {
			for (Wall	 wall: walls) {
				if (wall.collidesWith(player)) {
					player.move(0, -21);
					return;
					}
					
				}
			
			}
		
		player.move(0, 10);
	}
	public void movePlayerLeft() {
		
		if(!(walls == null)) {
			for (Wall	 wall: walls) {
				if (wall.collidesWith(player)) {
					player.move(21, 0);
					return;
					}
					
				}
			
			}
		
		player.move(-10, 0);
	}
	public void movePlayerRight() {
		if(!(walls == null)) {
			for (Wall	 wall: walls) {
				if (wall.collidesWith(player)) {
					player.move(-21, 0);
					return;
					}
					
				}
			
			}
		
		
		player.move(10, 0);
	}
	
	public boolean isGameOver() {
		if (player.getLives() == 0) return true;
		else return false;
	}
	
	public int getGemsCollected() {
		return this.gemscollected;
	}
	
	public void addGems() {
		gems.add(new Gem(500,500));
		gems.add(new Gem(800,200));
		gems.add(new Gem(200,1000));
		gems.add(new Gem(1500,600));
	}
	
	public void resetGame() {
		this.gemscollected = 0;
		this.addGems();
		player.resetLives();
	}

}
