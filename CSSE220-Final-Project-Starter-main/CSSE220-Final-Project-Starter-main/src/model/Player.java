package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import ui.GameComponent;

/**
 * Player class deals with player information
 * @author Josiah Layman
 */


public class Player {
	
	private int x, y, width, height;
	private int startx, starty;
	BufferedImage sprite;
	
	public Player(int x, int y, int width, int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		
		try {
			sprite = ImageIO.read(Player.class.getResource("e"));
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
		
//		if (x <= 0) {
//			x = 0;
//		}
//		if (x + width >= GameComponent.WIDTH) {
//			x = GameComponent.WIDTH - width;
//		}
//		
//		if (y <= 0) {
//			y = 0;
//		}
//		if (y + height >= GameComponent.HEIGHT) {
//			y = GameComponent.HEIGHT - height;
//		}
	}
	

}
