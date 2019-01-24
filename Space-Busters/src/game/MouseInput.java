package game;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

import game.Sound.soundEnum;

public class MouseInput implements MouseListener, MouseMotionListener  {

	private Game game;
	private boolean inStart;
	private boolean inLoad;
	private boolean inQuit;

	private boolean makeSound;

	public MouseInput(Game game) {
		this.game=game;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {


	}

	@Override
	public void mouseExited(MouseEvent e) {
	}

	@Override
	public void mousePressed(MouseEvent e) {

		if (Game.state.equals(Game.STATE.MENU)) {
			int x=e.getX();
			int y=e.getY();
			if (x>100&&x<200&&y>150&&y<200) {
				Sound.playSound(soundEnum.BUTTONSELECT);
				game.startLevels(1);
				Game.state=Game.STATE.GAME;
				Sound.playBackgroundMusic();
			}

			if (x>100&&x<200&&y>250&&y<300) {
				Sound.playSound(soundEnum.BUTTONSELECT);
				int levelNumber=LoadAndSaver.Load();
				game.startLevels(levelNumber);
				Game.state=Game.STATE.GAME;
				Sound.playBackgroundMusic();
			}

			if (x>100&&x<200&&y>350&&y<400) {
				Sound.playSound(soundEnum.BUTTONSELECT);
				try {
					Thread.sleep(400);
				} catch (InterruptedException e1) {
					e1.printStackTrace();
				}
				System.exit(1);
			}
		}
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseDragged(MouseEvent e) {
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		if (Game.state.equals(Game.STATE.MENU)) {
			int x=e.getX();
			int y=e.getY();

			if (x>100&&x<200&&y>150&&y<200) {
				if (inStart==false) {
					Sound.playSound(soundEnum.BUTTONHOVER);
				}
				inStart=true;
			} else {
				inStart=false;
			}

			if (x>100&&x<200&&y>250&&y<300) {
				if (inLoad==false) {
					Sound.playSound(soundEnum.BUTTONHOVER);
				}
				inLoad=true;
			} else {
				inLoad=false;
			}

			if (x>100&&x<200&&y>350&&y<400) {
				if (inQuit==false) {
					Sound.playSound(soundEnum.BUTTONHOVER);
				}
				inQuit=true;
			} else {
				inQuit=false;
			}

		}
	}

	public boolean getInStart() {
		return inStart;
	}

	public boolean getInLoad() {
		return inLoad;
	}

	public boolean getInQuit() {
		return inQuit;

	}
}
