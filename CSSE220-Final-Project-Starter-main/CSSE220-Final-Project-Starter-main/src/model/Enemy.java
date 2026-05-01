package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Enemy {
	private int x,y;
	private int startX;
	private int startY;
	BufferedImage sprite;
	public Enemy(int x, int y) {
		this.x=x;
		this.y = y;
		this.startX = x;
		this.startY = y;
		
		try {
			sprite = ImageIO.read(Enemy.class.getResource("zombie.png"));
		} catch (IOException | IllegalArgumentException e) {
			sprite = null;
		}
	}
	
	public void draw(Graphics2D g2) {
		if (sprite != null) {
			g2.drawImage(sprite,x,y,null);
		} else {
			g2.setColor(Color.RED);
			g2.fillOval(x, y, 100, 200);
		}
	}
	public void move() {
		Random direction = new Random();
	}
}
