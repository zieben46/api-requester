package collisionsprite;

import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import classes.ExplosionEntity;

public abstract class Explosion implements ExplosionEntity {
	BufferedImage images;
	static BufferedImage[] imageArray;
	private int frame;
	int x;
	int y;


	public Explosion (Point point) {
		this.x=(int) point.getX();
		this.y=(int) point.getY();
		setImage();
	}

	abstract void setImage();

	@Override
	public void render(Graphics2D g) {
		if (frame<imageArray.length) {
			g.drawImage(imageArray[frame], x-25, y-25, null);
			frame++;
		}
	}


	@Override
	public void update() {
	}

	@Override
	public int getX() {
		return x;
	}

	@Override
	public int getY() {
		return y;
	}

	@Override
	public Rectangle getBounds() {
		return null;
	}
	
	public boolean isRendering() {
		return frame<imageArray.length;
	}
}
