package collisionsprite;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;

public abstract class CollisionSprite {
	BufferedImage images;
	static BufferedImage[] imageArray;
	protected int width=50;
	protected int height=50;
	private int frame;
	private int x;
	private int y;

	public CollisionSprite(Point point) {
		this.x=(int) point.getX();
		this.y=(int) point.getY();
		setImage();
	}
	
	abstract void setImage();
	
	public void render(Graphics2D g) {
		g.drawImage(imageArray[frame], x-25, y-25, null);
		frame++;
	}
	
	public boolean animating() {
		return frame<46;
	}
}
