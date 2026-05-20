package tier8;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;

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
	public static final int TILE_SIZE = 40;
	// What game objects do we need to store (walls, enemies, player, coins?
	// TODO: store multiple balls
    private ArrayList<Ball> balls;
    private Ball player;
	//private Ball ball;
    private int totalBalls;
    private int ballsLost;
    private char[][] grid;

	public GameModel() {
		balls = new ArrayList<>();
		// TODO: load a level file (e.g., "level1.txt")
		loadLevel("level1.txt");
		// TODO: get total balls
		totalBalls = balls.size();
	    ballsLost = 0;
	}
	
	/**
	 * Loads a level from a text file. 
	 * Reads file and throws exceptions if invalid
	 *
	 * @param filename name of the level file
	 * @throws IllegalStateException if the level is invalid
	 */
	public void loadLevel(String filename) {
		 

		InputStream stream = GameModel.class.getResourceAsStream(filename);
		
		if (stream == null) {
			throw new IllegalStateException("Level file not found: " + filename);
		}
		Scanner scanner = new Scanner(stream);
		
		ArrayList<String> lines = new ArrayList<>();
		
		while (scanner.hasNextLine()) {
			lines.add(scanner.nextLine());
		}
		
		grid = new char[lines.size()][];
		
		for (int row = 0; row < lines.size(); row++) {
			String line = lines.get(row);
			grid[row] = line.toCharArray();
		
		
			for (int col = 0; col < line.length(); col++) {
				char ch = grid[row][col];
				if (ch == 'P') player = new Ball(row*TILE_SIZE, col*TILE_SIZE, 10, row, col);
				else if (ch == 'B') balls.add(new Ball(row*TILE_SIZE, col*TILE_SIZE, 10, row, col));
			}
		}
	    
		scanner.close();	
	}
	
	public void update() {
		player.update();
		
		for (Ball b : balls) {
		    b.update();
		}
		    for (int i = 0; i < balls.size(); i++) {
		        for (int j = i + 1; j < balls.size(); j++) {
		            Ball a = balls.get(i);
		            Ball b = balls.get(j);

		            if (a.collidesWith(b)) {
		                a.reverse();
		                b.reverse();
		            }
		        }
		    }
		    
		    for (int i = balls.size() - 1; i >= 0; i--) {

		        if (player.collidesWith(balls.get(i))) {
		            balls.remove(i); // safe
		         // TODO: increment the count of lost balls
		            ballsLost++;
		        }
		    }
    }

    public void draw(Graphics2D g2) {
    	for (int row = 0; row < grid.length; row++) {
    		for (int col = 0; col < grid[row].length; col++) {
    			if (grid[row][col] == '*') {
    				g2.fillRect(col*TILE_SIZE, row*TILE_SIZE, TILE_SIZE, TILE_SIZE);
    			}
    		}
    	}
        // TODO: draw each ball
    	for (Ball b : balls) {
    	    b.draw(g2);
    	}
    	player.draw(g2);
    }
    
    public void movePlayer(int dx) {
        if (player != null) {
        	if (grid[player.getRow() + 1][player.getCol() + 1] != '*') {
        		player.shift(dx);
        	}
        }
    }
    
    public void resetPlayer() {
        if (player != null) {
        	player.reset();
        }
    }	
    
    // TODO: we need pass this count to GameComponent
    
    public int getTotalBalls() {
        return totalBalls;
    }

    public int getBallsEliminated() {
        return ballsLost;
    }
    
 // TODO: When to stop game
    public boolean isGameOver() {
        return balls.isEmpty();
    }
    
}
