package game;
import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Menu {
	private BufferedImage background;
	private MouseInput mouseInput;
	
	private boolean mouseInStart=false;
	private boolean mouseInLoad=false;
	private boolean mouseInQuit=false;

	public RoundRectangle2D startButton = new RoundRectangle2D.Float(100, 150, 100, 50, 10, 10);
	public RoundRectangle2D loadButton = new RoundRectangle2D.Float(100, 250, 100, 50, 10, 10);
	public RoundRectangle2D quitButton = new RoundRectangle2D.Float(100, 350, 100, 50, 10, 10);
	
	public Menu(Game game, MouseInput mouseInput) {
		this.mouseInput=mouseInput;
		background = ImageLoader.menuBackground;
	}

	public void render(Graphics2D g) {
		g.drawImage(background, 0, 0, null);
		GradientPaint gradient = new GradientPaint(100, 150,Color.red, 800, 200, Color.black);
		g.setPaint(gradient);
		g.setFont(new Font("AR DESTINE",Font.BOLD, 80));
		g.drawString("SPACE BUSTERS", 50, 90);
		
		gradient = new GradientPaint(100, 150, setStartColor(), 200, 200, Color.gray);
		g.setPaint(gradient);
		g.fill(startButton);
		
		gradient = new GradientPaint(100, 250, setLoadColor(),200, 300, Color.gray);
		g.setPaint(gradient);
		g.fill(loadButton);
		
		gradient = new GradientPaint(100, 350,setQuitColor(), 200, 400, Color.gray);
		g.setPaint(gradient);
		g.fill(quitButton);

		g.setFont(new Font("AR DESTINE",Font.BOLD, 24));
		g.setColor(Color.black);
		g.drawString("START", 113, 182);
		g.drawString("LOAD", 120, 282);
		g.drawString("QUIT", 125, 382);
		g.draw(startButton);
		g.draw(loadButton);
		g.draw(quitButton);
	}
	
	

	public Color setStartColor() {
		if (mouseInStart) {
			return Color.red;
		} else {
			return Color.white;
		}
	}

	public Color setLoadColor() {
		if (mouseInLoad) {
			return Color.red;
		} else {
			return Color.white;
		}
	}

	public Color setQuitColor() {
		if (mouseInQuit) {
			return Color.red;
		} else {
			return Color.white;
		}
	}
	
	public void update() {
		mouseInStart=mouseInput.getInStart();
		mouseInLoad=mouseInput.getInLoad();
		mouseInQuit=mouseInput.getInQuit();	
	}
}
