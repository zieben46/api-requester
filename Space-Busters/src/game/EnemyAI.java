package game;

public class EnemyAI {
	private static int playerX;
	private static int playerY;
	private static MyShip myShip;
	
	public EnemyAI(MyShip myShip) {
		EnemyAI.myShip=myShip;
	}
	
	public static void update() {
		playerX=myShip.getX();
		playerY=myShip.getY();
	}
	
	public static int getPlayerX() {
		return playerX;
	}
	public static int getPlayerY() {
		return playerY;
	}
}
