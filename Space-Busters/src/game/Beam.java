package game;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import classes.ProjectileEntity;

public class Beam extends Bullet implements ProjectileEntity {

	public Beam(int x, int y, int Vx, int Vy, Team team) {
		super(x, y, Vx, Vy, team);
	}

	@Override
	protected void getImage() {
		projectileImage = ImageLoader.beamDown;

	}

	public void update() {
		//do nothing
	}
}
