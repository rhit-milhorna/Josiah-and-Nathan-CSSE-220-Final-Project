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
	private int direction;
	private int directionClock;
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
			g2.drawImage(sprite,x,y,x+100,y+100,null);
		} else {
			g2.setColor(Color.RED);
			g2.fillOval(x, y, 100, 200);
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
	}
	public void update() {
		move();
		this.directionClock++;
	}
}
