package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

import tier7.Ball;
import tier7.Collidable;

/**
 * Enemy class deals with enemy information
 * @author Nathan Milhorn
 */

public class Enemy implements Collidable{
	private int x,y;
	private int startX;
	private int startY;
	private int direction;
	private int directionClock;
	private int width = 150;
	private int height = 150;
	BufferedImage sprite;
	public Enemy(int x, int y) {
		this.x=x;
		this.y = y;
		this.startX = x;
		this.startY = y;
		
		try {
			sprite = ImageIO.read(Enemy.class.getResource("ghost.png"));
		} catch (IOException | IllegalArgumentException e) {
			sprite = null;
		}
	}
	
	public void draw(Graphics2D g2) {
		if (sprite != null) {
			g2.drawImage(sprite,x,y,width,height,null);
		} else {
			g2.setColor(Color.RED);
			g2.fillOval(x, y, width, height);
		}
	}
	public void move() {
		Random random = new Random();
		if(this.directionClock%100 == 0) {
		this.direction = random.nextInt(0,5);
		} else {
			
		}
		if(direction == 0) {
		return;
	}
	if(direction == 1) {
		if(this.x<1900) {
			this.x += 2;
		}
		
	}
	if(direction == 2) {
		if(this.x>0) {
			this.x -= 2;
		}
		}
	
	if(direction == 3) {
		if(this.y<1000) {
			this.y += 2;
		}	}
	if(direction == 4) {
		if(this.y>0) {
			this.y -= 2;
		}	}
	if(this.x>1800) {
		this.x = 1800;
	}
	if(this.x<0) {
		this.x = 0;
	}
	if(this.y>900) {
		this.y=900;
		}
	if(this.y<0) {
		this.y = 0;
	
	}
	}
	public void update() {
		move();
		this.directionClock++;
	}
	public Rectangle getBounds() {
	    return new Rectangle(x, y, width, height);
	}
	@Override
	public boolean collidesWith(Collidable other) {
		
		Player player = (Player) other;
		return this.getBounds().intersects(player.getBounds());
		
	}
	

}
