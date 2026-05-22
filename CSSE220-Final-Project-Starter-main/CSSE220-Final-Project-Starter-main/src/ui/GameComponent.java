package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

import app.MainApp;
import model.Enemy;
import model.GameModel;
import model.Gem;
import model.Wall;

/**
 * GameComponent draws all objects on screen and handles player input
 * @author Nathan Milhorn, Josiah Layman
 */

public class GameComponent extends JComponent {

	
	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;
	public static final Color BG = Color.BLACK;
	public static final Color FG = Color.BLACK;
	BufferedImage background;
	private Timer timer;
	private GameModel model;

	private Enemy zombie1 = new Enemy(250, 250);
	private boolean upButton = false;
	private boolean downButton = false;
	private boolean leftButton = false;
	private boolean rightButton = false;

	public GameComponent(GameModel model) {
	this.model = model;
	this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
	this.setBackground(BG); // note this only works with JPanels and will not work with JComponents
	this.setOpaque(true); // we want our own background here. If false - it will be see-through

	try {
		background = ImageIO.read(this.getClass().getResource("background.png"));
	} catch (IOException | IllegalArgumentException e) {
		background = null;
	}
	timer = new Timer(30,e->{
		if (!model.isGameOver()) {
		model.Update();
		this.handleMovement();
		//player.update();
		}
		repaint();
	});
	
	
	this.setFocusable(true);
	this.requestFocusInWindow();
	
	this.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (!model.isGameOver()) {
				if(!model.isWin()) {
				if (key == KeyEvent.VK_W) {
					upButton = true;
					//model.movePlayerUp();
					//repaint();
				}
				else if (key == KeyEvent.VK_S) {
					downButton = true;
					//model.movePlayerDown();
					//repaint();
				}
				else if (key == KeyEvent.VK_A) {
					leftButton = true;
					//model.movePlayerLeft();
					//repaint();
				}
				else if (key == KeyEvent.VK_D) {
					rightButton = true;
					//model.movePlayerRight();
					//repaint();
				}
			}}
			if (model.isGameOver() | model.isWin()) {
				if (key == KeyEvent.VK_ENTER) {
					model.resetGame();
				}
			}
			
		}

		@Override
		public void keyReleased(KeyEvent e) {
			// TODO Auto-generated method stub
			int key = e.getKeyCode();
			if (!model.isGameOver()) {
				if(!model.isWin()) {
				if (key == KeyEvent.VK_W) {
					upButton = false;
					//model.movePlayerUp();
					//repaint();
				}
				else if (key == KeyEvent.VK_S) {
					downButton = false;
					//model.movePlayerDown();
					//repaint();
				}
				else if (key == KeyEvent.VK_A) {
					leftButton = false;
					//model.movePlayerLeft();
					//repaint();
				}
				else if (key == KeyEvent.VK_D) {
					rightButton = false;
					//model.movePlayerRight();
					//repaint();
				}
			}}
			if (model.isGameOver() | model.isWin()) {
				if (key == KeyEvent.VK_ENTER) {
					model.resetGame();
				}
			}
		}
		
	});
	}
	
	public void handleMovement() {
		if (upButton | downButton | leftButton | rightButton) {
			model.movePlayer(upButton, downButton, leftButton, rightButton);
			repaint();
		}
		else return;
	}
	


	@Override
	protected void paintComponent(Graphics g) {
	super.paintComponent(g);
	Graphics2D g2 = (Graphics2D) g;
	if (background!= null) {
		g2.drawImage(background, 0, 0, WIDTH, HEIGHT, null);
	} else {
	g2.setColor(FG);
	}
	// Minimal placeholder to test  it’s running
	Font font = new Font("Ariel", Font.BOLD, 20);
	g2.setFont(font);
	g2.drawString("Lives: " + model.getPlayer().getLives(), 20, 30);
	g2.drawString("Gems collected: " + model.getGemsCollected(), 120, 30);
	g2.drawString("Gems Remaining:" + model.gems.size(), 350, 30);
	g2.drawString("level: " + (model.getlevel() + 1), 550, 30);
	for (Enemy zombie:model.zombies) {
		zombie.draw(g2);
	}
	for (Gem gem:model.gems) {
		gem.draw(g2);
	}
	for (Wall wall:model.walls) {
		wall.draw(g2);
	}
	model.getExit().draw(g2);
	model.getPlayer().draw(g2);
	
	
	if (model.isGameOver()) {
		Font gameOverFont = new Font("Ariel", Font.BOLD, 50);
		g2.setFont(gameOverFont);
		g2.setColor(Color.RED);
		g2.drawString("Game Over!", WIDTH / 2, HEIGHT / 2);
		g2.setColor(Color.BLACK);
		g2.setFont(new Font("Ariel", Font.PLAIN, 25));
		g2.drawString("Press Enter to restart", WIDTH / 2, (HEIGHT / 2) + 25);
	}
	if (model.isWin()) {
		Font Winfont = new Font("Ariel", Font.BOLD, 50);
		g2.setColor(Color.BLACK);
		g2.setFont(Winfont);
		g2.drawString("Press Enter to restart", WIDTH / 2, (HEIGHT / 2) + 25);
	}
	}
	
	public void startTimer() {
		timer.start();
	}

	// TODO: draw based on model state

	public void updateBackground(String filename) {
		try {
			background = ImageIO.read(this.getClass().getResource(filename));
		} catch (IOException | IllegalArgumentException e) {
			background = null;
		}
	}
}
