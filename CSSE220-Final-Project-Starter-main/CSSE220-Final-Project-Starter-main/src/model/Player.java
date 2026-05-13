package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import tier7.Ball;
import tier7.Collidable;
import ui.GameComponent;

/**
 * Player class deals with player information
 * @author Josiah Layman
 */


public class Player implements Collidable{
	
	private int x, y, width, height;
	private int startx, starty;
	BufferedImage sprite;
	private int lives;
	
	public Player(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.startx=  x;
		this.starty = y;
		this.width = width;
		this.height = height;
		this.lives = 3;
		
		try {
			sprite = ImageIO.read(Player.class.getResource("adventurer picture.png"));
		} catch (IOException | IllegalArgumentException e) {
			sprite = null;
		}
	}
	
	public void draw(Graphics2D g2) {
		if (sprite != null) {
			g2.drawImage(sprite, x, y, width, height, null);
		} else {
		g2.setColor(Color.RED);
		g2.fillOval(x, y, width, height);
	}
	}
	
	public void move(int dx, int dy) {
		this.x += dx;
		this.y += dy;
		
		if (x <= 0) {
			x = 0;
		}
		if (x + width >= GameComponent.WIDTH) {
			x = GameComponent.WIDTH - width;
		}
		
		if (y <= 0) {
			y = 0;
		}
		if (y + height >= GameComponent.HEIGHT) {
			y = GameComponent.HEIGHT - height;
		}
	}
	public void reset() {
		this.x = this.startx;
		this.y = this.starty;
	}
	
	public int getLives() {
		return this.lives;
	}
	
	public Rectangle getBounds() {
	    return new Rectangle(x, y, width, height);
	}
	
	public void removeLife() {
		this.lives --;
	}
	
	public void resetLives() {
		this.lives = 3;
	}
	
	public boolean collidesWithGem(Gem gem) {
		return this.getBounds().intersects(gem.getBounds());
	
	}
	
	@Override
	public boolean collidesWith(Collidable other) {
		Enemy enemy = (Enemy) other;
		return this.getBounds().intersects(enemy.getBounds());
	}
	
	
	
}
