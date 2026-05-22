package model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Gem implements Collidable{
	private int x;
	private int y;
	private int width = 80;
	private int height = 80;
	BufferedImage sprite;

	public Gem(int x, int y) {
		this.x=x;
		this.y = y;

		
		try {
			sprite = ImageIO.read(Enemy.class.getResource("pngtree-glossy-pixel.png"));
		} catch (IOException | IllegalArgumentException e) {
			sprite = null;
		}
	}
	
	public void draw(Graphics2D g2) {
		if (sprite != null) {
			g2.drawImage(sprite,x,y,width,height,null);
		} else {
			g2.setColor(Color.BLUE);
			g2.fillOval(x, y, width, height);
		}
	}
	
	
	
	public Rectangle getBounds() {
		return new Rectangle(x, y, width, height);
	}

	@Override
	public boolean collidesWith(Player other) {
		Player player = (Player) other;
		return this.getBounds().intersects(player.getBounds());
	}

}
