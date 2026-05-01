package ui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JComponent;
import javax.swing.Timer;

import model.Enemy;
import model.GameModel;

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
		zombie1.update();
		//player.update();
		repaint();
	});
	timer.start();
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
	g2.drawString("Final Project Starter: UI is running ✅", 20, 30);
	zombie1.draw(g2);
	zombie1.update();
	}

	// TODO: draw based on model state

	
}
