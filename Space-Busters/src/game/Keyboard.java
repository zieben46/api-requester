package game;
import java.awt.event.KeyEvent;

public class Keyboard {
	
	public static boolean[] pressed=new boolean[256];
	public static boolean[] prev=new boolean[256];
	
	private Keyboard() {} 
	
	public static void update() {
			prev[KeyEvent.VK_LEFT]=pressed[KeyEvent.VK_LEFT];
			prev[KeyEvent.VK_RIGHT]=pressed[KeyEvent.VK_RIGHT];
			prev[KeyEvent.VK_UP]=pressed[KeyEvent.VK_UP];
			prev[KeyEvent.VK_DOWN]=pressed[KeyEvent.VK_DOWN];
			prev[KeyEvent.VK_SPACE]=pressed[KeyEvent.VK_SPACE];
			prev[KeyEvent.VK_S]=pressed[KeyEvent.VK_S];
			prev[KeyEvent.VK_F]=pressed[KeyEvent.VK_F];
			prev[KeyEvent.VK_ENTER]=pressed[KeyEvent.VK_ENTER];
			prev[KeyEvent.VK_P]=pressed[KeyEvent.VK_P];
			prev[KeyEvent.VK_S]=pressed[KeyEvent.VK_S];
			prev[KeyEvent.VK_R]=pressed[KeyEvent.VK_R];
			prev[KeyEvent.VK_ESCAPE]=pressed[KeyEvent.VK_ESCAPE];
	}

	public static void keyPressed(KeyEvent e) {
		pressed[e.getKeyCode()]=true;
	}
	
	public static void keyReleased(KeyEvent e) {
		pressed[e.getKeyCode()]=false;	
	}
	
	public static boolean typed(int key) {
		return pressed[key]&&prev[key];	
	}
	
	public static boolean EnterPressed() {
		return pressed[KeyEvent.VK_ENTER]&&!prev[KeyEvent.VK_ENTER];
	}
	
	public static boolean PPressed() {
		return pressed[KeyEvent.VK_P]&&!prev[KeyEvent.VK_P];
	}
	
	public static boolean SPressed() {
		return pressed[KeyEvent.VK_S]&&!prev[KeyEvent.VK_S];
	}
	
	//JOption pane messing with this
	public static void resetS() {
		pressed[KeyEvent.VK_S]=false;
	}

	public static boolean RPressed() {
		return pressed[KeyEvent.VK_R]&&!prev[KeyEvent.VK_R];
	}

	public static boolean escapePressed() {
		return pressed[KeyEvent.VK_ESCAPE]&&!prev[KeyEvent.VK_ESCAPE];
	}

	public static void resetEscape() {
		pressed[KeyEvent.VK_ESCAPE]=false;
	}
}

