package game;
import java.awt.Rectangle;
import java.util.Random;

public abstract class GameObject {
	protected int x;
	protected int y;
	int width;
	int height;
	Random random;

	public GameObject(int x, int y) {
		this.x=x;
		this.y=y;
	}
	
	public GameObject() {
		random=new Random();
		x=random.nextInt(Game.WIDTH);
		y=-random.nextInt(300);
		//y=-40;
	}

	public Rectangle getBounds() {
		return new Rectangle (x, y, width, height);
	}
	
	public void setWidth(int width) {
		this.width=width;
	}
	
	public void setHeight(int height) {
		this.height=height;
	}
	
	public int getWidth() {
		return width;
	}
	
	public int getHeight() {
		return height;
	}
}