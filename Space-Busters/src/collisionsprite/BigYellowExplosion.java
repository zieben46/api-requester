package collisionsprite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;

import game.Game;
import game.ImageLoader;
import game.Sound;
import game.Sound.soundEnum;

public class BigYellowExplosion extends Explosion {
	private int frame=0;

	public BigYellowExplosion(Point point) {
		super(point);

	}

	public void setImage() {
		int width=400;
		int height=400;
		imageArray=new BufferedImage[81];
		images = ImageLoader.yellowSpriteBig;
		int counter=0;	
		for (int y=3201; y>=1; y=y-height) {
			for (int x=3201; x>=1; x=x-width) {
				imageArray[counter]=images.getSubimage(x, y, 399, 399);
				counter++;
			}
		}
	}

	@Override
	public void render(Graphics2D g) {
		if (frame<imageArray.length) {
			if (frame==0) {
				Sound.playSound(soundEnum.BIGEXPLOSION);
			}
			
			if (frame<15) {
				g.setColor(Color.white);
				g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
			}

			g.drawImage(imageArray[frame], x-175, y-178, null);
			frame++;	
		}
	}
}

