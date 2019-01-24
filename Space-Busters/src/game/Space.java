package game;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Space {
	public static final int WIDTH=Game.WIDTH;
	public static final int HEIGHT=Game.HEIGHT;

	private int x;
	private int y;
	private BufferedImage spaceImage;
	private BufferedImage foregroundImage;
	private Controller controller;

	public Space(int x, int y, int levelNumber) {
		this.x=x;
		this.y=y;
		spaceImage=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		foregroundImage=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		controller=new Controller(levelNumber);
		createBackground();
	}

	private void createBackground() {
		spaceImage= ImageLoader.spaceBackground;	
	}

	public void render(Graphics2D g) {
		Graphics2D g2=(Graphics2D) foregroundImage.getGraphics();
		g2.drawImage(spaceImage, 0, 0, null);		
		controller.render(g2);	
		g.drawImage(foregroundImage, x, x, null);
		g2.dispose();
	}

	public void update() {
		controller.update();
		//if (controller.reset()) {
			//controller=new Controller();
		//}
	}
}
