package level;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics2D;

import game.Game;

public class Popup {
	private int levelNumber;
	private int counter;
	private boolean rendering=false;
	private double startTime;

	public Popup(int levelNumber) {
		this.levelNumber=levelNumber;
	}

	public void renderWarning(Graphics2D g) {
		GradientPaint gradient=null;
		if (counter%60<30) {
			gradient = new GradientPaint(160, 300,Color.red,500,400,new Color(106,0,0));
		} else {
			gradient = new GradientPaint(160,300,new Color(106,0,0),500,400,Color.red);
		}
		g.setPaint(gradient);
		g.setFont(new Font("AR DESTINE",Font.BOLD, 50 ));
		g.drawString("WAVE "+ levelNumber+ " APPROACHING", 162, 300);
		counter++;
		if (levelNumber==1) {
			renderIntro(g);
		}

		if (levelNumber==7) {
			renderSideBulletMessage(g);
		}
	}

	private void renderIntro(Graphics2D g) {
		GradientPaint gradient = new GradientPaint(160, 300,Color.white,500,400,Color.lightGray);
		g.setPaint(gradient);
		g.setFont(new Font("AR DESTINE",Font.BOLD, 20 ));
		g.drawString("USE SPACE BAR TO SHOOT & ARROWS TO MOVE", 195, 350);
		g.setFont(new Font("AR DESTINE",Font.BOLD, 16 ));
		g.drawString("PRESS P TO PAUSE & SAVE", 326, 380);
	}

	private void renderSideBulletMessage(Graphics2D g) {
		GradientPaint gradient = new GradientPaint(160, 300,Color.white,500,400,Color.lightGray);
		g.setPaint(gradient);
		g.setFont(new Font("AR DESTINE",Font.BOLD, 20 ));
		g.drawString("SIDE BULLETS AQUIRED", 327, 350);
		g.setFont(new Font("AR DESTINE",Font.BOLD, 16 ));
		g.drawString("USE S AND D KEYS TO FIRE SIDE BULLETS", 258, 380);
	}


	public void renderDeadMessage(Graphics2D g) {
		if (!rendering) {
			rendering=true;
			startTime=System.currentTimeMillis();
		}
		double currentTime=System.currentTimeMillis();
		if (currentTime-startTime>.75*1000) {

			g.setPaint(changingGradient());
			g.setFont(new Font("AR DESTINE",Font.BOLD, 50));
			g.drawString("YOU ARE DEAD", 249, 300);
			g.setFont(new Font("AR DESTINE",Font.BOLD, 20 ));
			g.drawString("PRESS ENTER TO CONTINUE", 305, 330);
		}
	}

	public void renderPauseMessage(Graphics2D g) {
		g.setColor(new Color(200, 200, 200, 200));
		g.fillRect(0, 0, Game.WIDTH, Game.HEIGHT);
		GradientPaint gradient = new GradientPaint(160, 300,Color.black,2500,400,Color.lightGray);
		g.setPaint(gradient);
		g.setFont(new Font("AR DESTINE",Font.BOLD, 50));
		g.drawString("GAME PAUSED", 262, 300);
		g.setFont(new Font("AR DESTINE",Font.BOLD, 20));
		g.drawString("PRESS S TO SAVE", 350, 330);
		g.drawString("PRESS R TO RESUME", 339, 360);
		g.drawString("PRESS ESCAPE TO RETURN TO MENU", 260, 390);
	}

	private GradientPaint changingGradient() {
		double currentTime=System.currentTimeMillis();
		double gapTime=currentTime-startTime;
		int creep=(int) gapTime/2;
		if (creep>1400) {
			creep=1400;
		}
		return new GradientPaint(160, 700,Color.white, 160, -1300+creep,new Color(151, 21, 21));
	}
}
