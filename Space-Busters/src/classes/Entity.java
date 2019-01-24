package classes;

import java.awt.Graphics2D;
import java.awt.Rectangle;

public interface Entity {
	public void update();
	public void render(Graphics2D g);
	public int getX();
	public int getY();
	public Rectangle getBounds();
}
