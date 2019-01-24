package game;
import java.awt.Rectangle;

import classes.Entity;

public class Physics {

	public static boolean Collision(Entity ent1, Entity ent2) {
		return (ent1.getBounds().intersects(ent2.getBounds()));
	}
	
	public static Rectangle getIntersection(Entity ent1, Entity ent2) {
		Rectangle r1=ent1.getBounds();
		Rectangle r2=ent2.getBounds();
		return r1.intersection(r2);
	}
}
