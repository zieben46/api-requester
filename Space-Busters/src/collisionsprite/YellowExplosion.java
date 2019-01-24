package collisionsprite;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import game.ImageLoader;

public class YellowExplosion extends Explosion {

	public YellowExplosion(Point point) {
		super(point);
	}

	@Override
	void setImage() {
		int width=50;
		int height=50;
		imageArray=new BufferedImage[46];
		images = ImageLoader.yellowSprite;
		
		int counter=0;
		for (int y=251; y>=1; y=y-height) {
			for (int x=251; x>=1; x=x-width) {
				imageArray[counter]=images.getSubimage(x, y, 49, 49);
				counter++;
			}
		}
	}
}
