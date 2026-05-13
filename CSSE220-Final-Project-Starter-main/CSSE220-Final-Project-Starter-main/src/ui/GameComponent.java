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

	public GameComponent(GameModel model) {
	this.model = model;
	this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
	this.setBackground(BG); // note this only works with JPanels and will not work with JComponents
	this.setOpaque(true); // we want our own background here. If false - it will be see-through

	//Player player = new Player();
	Enemy zombie1 = new Enemy(250, 250);
	try {
		background = ImageIO.read(this.getClass().getResource("background.png"));
	} catch (IOException | IllegalArgumentException e) {
		background = null;
	}
	timer = new Timer(30,e->{
		if (!model.isGameOver()) {
		model.Update();
		//player.update();
		}
		repaint();
	});
	timer.start();
	
	this.setFocusable(true);
	this.requestFocusInWindow();
	
	this.addKeyListener(new KeyAdapter() {
		@Override
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (!model.isGameOver()) {
				if (key == KeyEvent.VK_W) {
					model.movePlayerUp();
					repaint();
				}
				else if (key == KeyEvent.VK_S) {
					model.movePlayerDown();
					repaint();
				}
				else if (key == KeyEvent.VK_A) {
					model.movePlayerLeft();
					repaint();
				}
				else if (key == KeyEvent.VK_D) {
					model.movePlayerRight();
					repaint();
				}
			}
			else {
				if (key == KeyEvent.VK_ENTER) {
					model.resetGame();
				}
			}
			
		}
	});
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
	for (Enemy zombie:model.zombies) {
		zombie.draw(g2);
	}
	for (Gem gem:model.gems) {
		gem.draw(g2);
	}
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
	}

	// TODO: draw based on model state

	
}
