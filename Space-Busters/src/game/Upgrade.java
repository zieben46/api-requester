package game;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import classes.UpgradeEntity;

public class Upgrade extends GameObject implements UpgradeEntity {

	protected BufferedImage upgradeImage;
	protected int Vx;
	protected int Vy;
	private UpgradeType upgradeType;

	public Upgrade(int x, int y, UpgradeType upgradeType) {
		super(x, y);
		Vx=0;
		Vy=1;
		this.upgradeType=upgradeType;
		loadImage();
		super.setHeight(upgradeImage.getHeight());
		super.setWidth(upgradeImage.getWidth());
	}

	private void loadImage() {
		switch (upgradeType) {
		case movement :
			upgradeImage= ImageLoader.yellowUpgrade;
			break;
		case gunType :
			upgradeImage= ImageLoader.purpleUpgrade;
			break;
		case gunRate :
			upgradeImage= ImageLoader.greenUpgrade;
			break;
		case healthPack :
			upgradeImage= ImageLoader.healthPack;
			break;
		}
	}

	@Override
	public void update() {
		x+=Vx;
		y+=Vy;
	}

	@Override
	public void render(Graphics2D g) {
		g.drawImage(upgradeImage, x, y, null);;

	}

	@Override
	public int getX() {
		return this.x;
	}

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public UpgradeType upgradeType() {
		return upgradeType;
	}
}
