package game;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class Game extends JPanel implements KeyListener, Runnable {
	private static final long serialVersionUID = 1L;

	public static final int WIDTH=900;
	public static final int HEIGHT=700;
	private Thread game;
	private boolean running;
	private BufferedImage image=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage purpImage=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage yellowImage=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage greenImage=new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private Space space;
	private Menu menu;
	public static int Health=100*2;
	private MouseInput mouseInput;
	
	private int currUpdates;
	private int currFrames;

	public enum STATE {
		MENU,
		GAME
	};

	public static STATE state = STATE.MENU;

	public Game() {
		setFocusable(true);
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		addKeyListener(this);
		mouseInput=new MouseInput(this);
		addMouseListener(mouseInput);
		loadImages();
		menu=new Menu(this, mouseInput);
		this.addMouseMotionListener(mouseInput);
	}
		
	public void startLevels(int levelNumber) {
		space=new Space(WIDTH/2-Space.WIDTH/2,HEIGHT/2-Space.HEIGHT/2, levelNumber);	
	}

	private void update() {
		if (state==STATE.GAME) {
			space.update();
		} else if (state==STATE.MENU) {
			menu.update();
		}
		Keyboard.update();
	}

	private void render() {
		Graphics2D g = (Graphics2D) image.getGraphics();
		Graphics2D g2d=(Graphics2D) getGraphics();

		if (state==STATE.GAME) {
			space.render(g);
			renderHealthBar(g);
			drawFPS(g);
			drawInventory(g);

		} else if (state==STATE.MENU) {
			menu.render(g);
		}

		g2d.drawImage(image, 0, 0, this);
		g.dispose();
		g2d.dispose();
	}

	@Override
	public void run() {
		long lastTime=System.nanoTime();
		final double amountOfTicks=60.0;
		double ns=1000000000/amountOfTicks;
		double delta=0; 

		int updates=0;
		int frames=0;
		long timer=System.currentTimeMillis();

		while (running) {
			long now=System.nanoTime();
			delta +=(now-lastTime) / ns;
			lastTime=now;
			if (delta>1) {		
				update();
				delta--;
				updates++;
			}
			render();
			frames++;
			if (System.currentTimeMillis()-timer>1000) {
				timer+=1000;
				currUpdates=updates;
				currFrames=frames;
				updates=0;
				frames=0;
			}
		}
	}

	public synchronized void start() {
		if (running) return;
		running=true;
		game=new Thread(this, "game");
		state=STATE.MENU;
		game.start();
	}

	public synchronized void stop() {
		if (running) return;
		running=false;
		System.exit(0);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		Keyboard.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		Keyboard.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
	}

	private void renderHealthBar(Graphics2D g) {
		g.setColor(new Color(220,220,220, 127));
		g.fillRect(5, 5, 200, 20);
		
		Color color;
		if (StatsTracker.playerHealth>=120) {
			color=new Color(127, 255, 0, 127); //green
		}	else if (StatsTracker.playerHealth<120&&StatsTracker.playerHealth>=60) {
			color=new Color(255, 255, 0, 127); //yellow

		} else {
			color=new Color(255, 0, 0, 127); //red
		}
		g.setColor(color);
		g.fillRect(5, 5, StatsTracker.playerHealth, 20);
		g.setColor(Color.white);
		g.drawRect(5,  5, 200, 20);
	}
	
	private void drawFPS(Graphics2D g) {
		g.setFont(new Font("arial", Font.BOLD, 10));
		g.drawString("Updates: "+currUpdates+" FPS: "+currFrames, WIDTH-107, HEIGHT-5);
	}
	
	private void loadImages() {
		purpImage = ImageLoader.purpImageSmall;
		yellowImage = ImageLoader.yellowImageSmall;
		greenImage = ImageLoader.greenImageSmall;
	}
	
	private void drawInventory(Graphics2D g) {
		Color color=new Color(0, 0, 128, 127);
		g.setColor(color);
		g.fillRect(0, HEIGHT-33, 270, 70);
		g.setColor(Color.lightGray);
		
		g.setFont(new Font("arial", Font.BOLD, 12));
		g.drawString("Speed       :", 10, HEIGHT-20);
		g.drawString("Fire Rate  :", 10, HEIGHT-6);
		for (int i=1; i<=StatsTracker.movementUpgs; i++) {
			g.drawImage(yellowImage,70+12*i, HEIGHT-27, null);
		}
		
		for (int i=1; i<=StatsTracker.gunRateUpgs; i++) {
			g.drawImage(greenImage,70+12*i, HEIGHT-13, null);
		}
		g.setColor(Color.BLACK);
		g.drawRect(0, HEIGHT-33, 270, 70);
		
		g.setColor(Color.lightGray);
		g.drawString("Current Level: "+StatsTracker.level, 400, HEIGHT-10);
	}

	
}
