package game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Random;
import static java.util.stream.Collectors.*;

import javax.swing.JOptionPane;

import classes.ProjectileEntity;
import classes.EnemyEntity;
import classes.UpgradeEntity;
import classes.UpgradeEntity.UpgradeType;
import collisionsprite.*;
import game.Sound.soundEnum;
import level.*;
import classes.Entity;
import classes.ExplosionEntity;
import classes.ProjectileEntity.Team;
import classes.ProjectileEntity;

public class Controller {

	private LinkedList<ProjectileEntity> projectileEntities = new LinkedList<ProjectileEntity>();
	private LinkedList<EnemyEntity> enemyEntities = new LinkedList<EnemyEntity>();
	private LinkedList<UpgradeEntity> upgradeEntities = new LinkedList<UpgradeEntity>();
	private LinkedList<ExplosionEntity> explosionEntities = new LinkedList<ExplosionEntity>();

	private MyShip myShip;

	private int gunTypeUpgradeSpawn=-1;
	private int gunRateUpgradeSpawn=-1;
	private int movementUpgradeSpawn=-1;
	private int healthPackSpawn;
	private Random random = new Random();
	private LevelEntity level;
	private LevelFactory levelFactory;
	private MyShipUpgrades myShipUpgrades;
	private boolean inGap;
	private Popup popup;
	private boolean paused;
	private boolean playerDead;
	private boolean explosionRendered=false;

	private final int TOTAL_LEVELS=16;
	private int levelNumber;

	private int enemiesKilled;
	private int totalEnemies;

	public Controller() {
		this(1);
	}

	public Controller(int levelNumber) {
		this.levelNumber=levelNumber;
		myShip=new MyShip(420, 600);
		levelFactory=new LevelFactory(enemyEntities, myShip);
		myShipUpgrades=new MyShipUpgrades(myShip);
		initializeLevel();
		healthPackSpawn=level.getHeathSpawnRate();
	}

	private void initializeLevel() {
		new EnemyAI(myShip);
		playerDead=false;
		explosionRendered=false;
		level=levelFactory.newLevel(levelNumber);
		totalEnemies=level.getTotalEnemies();
		enemiesKilled=0;
		myShipUpgrades.setUpgrades(levelNumber);
		StatsTracker.level=levelNumber;
		popup=new Popup(levelNumber);
	}

	public synchronized void update() {
		if (!paused) {
			myShip.update();
			//		projectileEntities.stream().forEach(e -> {
			//		projectileHit(e);
			//		projectileBounds(e)});

			for (int i=0; i<projectileEntities.size(); i++) {
				ProjectileEntity projectileEntity=projectileEntities.get(i);
				projectileHit(projectileEntity);
				projectileBounds(projectileEntity);
				checkBeam(projectileEntity);
				projectileEntity.update();
			}

			for (int i=0; i<enemyEntities.size(); i++) {
				EnemyEntity enemyEntity=enemyEntities.get(i);
				testCollision(enemyEntity);
				checkDeadEnemy(enemyEntity);			
				enemyBounds(enemyEntity);
				randomShoot(enemyEntity);
				enemyEntity.update();
			}

			for (int i=0; i<upgradeEntities.size(); i++) {
				UpgradeEntity upGradeEntity=upgradeEntities.get(i);
				pickUp(upGradeEntity);
				upgradeBounds(upGradeEntity);
				upGradeEntity.update();
			}

			for (int i=0; i<explosionEntities.size(); i++) {
				ExplosionEntity explosionEntity=explosionEntities.get(i);
				checkRendering(explosionEntity);
			}

			level.update();
			EnemyAI.update();
			checkComplete();
			inGap=level.getInGap();
			tester();

			if (myShip.isDead()) {
				if (!explosionRendered) {
					explosionEntities.add(new BigYellowExplosion(new Point
							(myShip.getX()+myShip.getWidth()/2, myShip.getY()+myShip.getHeight()/2)));
					explosionRendered=true;
				}
			}
		}
		checkKeys();
	}

	private void checkRendering(ExplosionEntity explosionEntity) {
		if (!explosionEntity.isRendering()) {
			explosionEntities.remove(explosionEntity);
		}
	}

	private void checkBeam(ProjectileEntity projectileEntity) {
		if (projectileEntity.getClass().equals(Beam.class)) {
			projectileEntities.remove(projectileEntity);
		}
	}

	public void render(Graphics2D g) {
		projectileEntities.stream()
		.forEach(e -> e.render(g));

		//		projectileEntities.stream()
		//		                  .forEach(Entity::render(g));

		enemyEntities.stream()
		.forEach(e -> e.render(g));

		upgradeEntities.stream()
		.forEach(e -> e.render(g));

		myShip.render(g);

		explosionEntities.stream()
		.forEach(e -> e.render(g));

		if (inGap) {
			popup.renderWarning(g);
		}

		if (myShip.isDead()) {
			popup.renderDeadMessage(g);
			playerDead=true;
			//			if (!explosionRendered) {
			//				explosionEntities.add(new BigYellowExplosion(new Point
			//						(myShip.getX()+myShip.getWidth()/2, myShip.getY()+myShip.getHeight()/2)));
			//				explosionRendered=true;
			//			}
		}

		if (paused) {
			popup.renderPauseMessage(g);
		}

		renderKillCounter(g);

		//g.drawLine(Game.WIDTH/2-6, 0, Game.WIDTH/2-6, 800);
	}

	private void renderKillCounter(Graphics2D g) {
		g.setFont(new Font("AR DESTINE",Font.BOLD, 45 ));
		g.setColor(Color.white);
		g.drawString(enemiesKilled+"/"+totalEnemies, Game.WIDTH-130, Game.HEIGHT-20);
	}

	private void randomShoot(EnemyEntity enemyEntity) {
		ArrayList<ProjectileEntity> newProjectiles=enemyEntity.randomBullet();
		if (newProjectiles!=null) {
			projectileEntities.addAll(newProjectiles);
		}
	}

	private void tester() {
		//		projectileEntities=new LinkedList<ProjectileEntity>(
		//				projectileEntities.stream()
		//								  .filter(Controller::isFriendly)
		//								  .filter(Controller::collision)
		//								  .collect(toCollection(LinkedList::new))
		//				);	
	}

	//
	//	private static boolean collision(ProjectileEntity projectileEntity) {
	//		return !enemyEntities.stream().anyMatch(enemy -> Physics.Collision(projectileEntity, enemy));
	//	}
	//	
	//	private static boolean isFriendly(ProjectileEntity projectileEntity) {
	//		return projectileEntities.equals(Team.FRIENDLY);
	//	}


	private void  projectileHit(ProjectileEntity projectileEntity) {    //test player hits enemy
		if (projectileEntity.team().equals(Team.FRIENDLY)) {
			for (int i=0; i<enemyEntities.size(); i++) {
				EnemyEntity enemyEntity=enemyEntities.get(i);
				if (Physics.Collision(projectileEntity, enemyEntity)) {

					enemyEntity.takeDamage(projectileEntity.getDamage());
					explosionEntities.add(new YellowExplosion(new Point
							(projectileEntity.getX()+myShip.getWidth()/2, projectileEntity.getY()+myShip.getHeight()/2)));
					Sound.playSound(soundEnum.SMALLEXPLOSION);
					projectileEntities.remove(projectileEntity);
				}
			}
		} else if (projectileEntity.team().equals(Team.ENEMY)){   //test enemy hits player
			if (Physics.Collision(myShip, projectileEntity)) {
				projectileEntities.remove(projectileEntity);
				myShip.decreaseHealth();
				explosionEntities.add(new BlueExplosion(new Point
						(myShip.getX()+myShip.getWidth()/2, myShip.getY()+myShip.getHeight()/2)));
				Sound.playSound(soundEnum.SMALLEXPLOSION);
			}
		}
	}

	private void  projectileHit2(ProjectileEntity projectileEntity) {    //test player hits enemy
		System.out.print(
				projectileEntities.stream().filter(projectile -> projectile.equals(Team.FRIENDLY))
				.count()


				//			.filter(enemy -> Physics.Collision(projectileEntity, enemy))
				//			.count();
				//.forEach(enemy -> enemyEntities.remove(enemy));
				);

		//					
		//			for (int i=0; i<enemyEntities.size(); i++) {
		//				EnemyEntity enemyEntity=enemyEntities.get(i);
		//				if (Physics.Collision(projectileEntity, enemyEntity)) {
		//
		//					renderCollision=true;
		//					enemyEntity.takeDamage(projectileEntity.getDamage());
		//					collisionSprite=new CollisionSpriteYellow(new Point
		//							(projectileEntity.getX(), projectileEntity.getY()));
		//					Sound.playSound(soundEnum.SMALLEXPLOSION);
		//					projectileEntities.remove(projectileEntity);
		//				}
		//			}
		//		} else if (projectileEntity.team().equals(Team.ENEMY)){   //test enemy hits player
		//			if (Physics.Collision(myShip, projectileEntity)) {
		//				projectileEntities.remove(projectileEntity);
		//				myShip.decreaseHealth();
		//				renderCollision=true;
		//				collisionSprite=new CollisionSpriteBlue(new Point
		//						(myShip.getX()+myShip.getWidth()/2, myShip.getY()+myShip.getHeight()/2));
		//				Sound.playSound(soundEnum.SMALLEXPLOSION);
		//			}
		//		}
	}

	//public boolean reset() {
	//	return reset;
	//}

	private void checkDeadEnemy(EnemyEntity enemyEntity) {
		if (enemyEntity.isDead()) {
			enemyEntities.remove(enemyEntity);
			enemiesKilled++;
			spawnUpgrade(enemyEntity.getX(), enemyEntity.getY());    //move?
			level.addEnemy();
		}
	}

	private void testCollision(EnemyEntity enemyEntity) {    //test for enemy player collision
		if (Physics.Collision(myShip, enemyEntity)) {
			Sound.playSound(soundEnum.SMALLEXPLOSION);
			myShip.decreaseHealth();
			Rectangle union=Physics.getIntersection(myShip, enemyEntity);
			explosionEntities.add(new BlueExplosion(new Point((int) union.getX(),(int) union.getY())));
			enemyEntities.remove(enemyEntity);
			enemiesKilled++;
			level.addEnemy();
		}
	}

	private void projectileBounds(ProjectileEntity ProjectileEntity) {
		if (ProjectileEntity.getY()<-20||ProjectileEntity.getY()>1000||
				ProjectileEntity.getX()<-100||ProjectileEntity.getX()>900) {
			projectileEntities.remove(ProjectileEntity);
		}
	}

	private void upgradeBounds(UpgradeEntity upgradeEntity) {
		if (upgradeEntity.getY()<-20||upgradeEntity.getY()>1000||
				upgradeEntity.getX()<-100||upgradeEntity.getX()>900) {
			upgradeEntities.remove(upgradeEntity);
		}
	}

	private void enemyBounds(EnemyEntity enemyEntity) {
		int x=enemyEntity.getX();
		int y=enemyEntity.getY();

		if (x>Game.WIDTH+60) {
			enemyEntity.setX(-30);
		}
		else if (x<-60) {
			enemyEntity.setX(Game.WIDTH+30);

		} else if (y>Game.HEIGHT+400) {
			enemyEntity.setNewPosition();
		}
	}

	private void checkComplete() {
		if (level.levelComplete()&&levelNumber<TOTAL_LEVELS) {
			levelNumber++;
			initializeLevel();
		}
	}

	/////////////////////////////////////////////////////////////////////////////////

	private void pickUp(UpgradeEntity upgradeEntity) {
		if ((Physics.Collision(myShip, upgradeEntity))) {
			upgradeEntities.remove(upgradeEntity);
			myShip.upgrade(upgradeEntity);
		}
	}

	private void spawnUpgrade(int x, int y) {  //move
		int r=random.nextInt(100);
		if (r<=gunTypeUpgradeSpawn&&StatsTracker.gunTypeUpgs<3) {
			upgradeEntities.add(new Upgrade(x, y, UpgradeType.gunType));
		}
		r=random.nextInt(100);
		if (r<=gunRateUpgradeSpawn&&StatsTracker.gunRateUpgs<10) {
			upgradeEntities.add(new Upgrade(x, y, UpgradeType.gunRate));
		} 
		r=random.nextInt(100);
		if (r<=movementUpgradeSpawn&&StatsTracker.movementUpgs<3) {
			upgradeEntities.add(new Upgrade(x, y, UpgradeType.movement));
		}
		r=random.nextInt(100);
		if (r<=healthPackSpawn) {
			upgradeEntities.add(new Upgrade(x, y, UpgradeType.healthPack));
		}
	}


	private void checkKeys() {
		if (Keyboard.typed(KeyEvent.VK_LEFT)&&!paused&&!playerDead) {
			myShip.moveLeft();
		}
		if (Keyboard.typed(KeyEvent.VK_RIGHT)&&!paused&&!playerDead) {
			myShip.moveRight();
		}
		if (Keyboard.typed(KeyEvent.VK_UP)&&!paused&&!playerDead) {
			myShip.moveUp();
		}
		if (Keyboard.typed(KeyEvent.VK_DOWN)&&!paused&&!playerDead) {
			myShip.moveDown();
		}
		if (Keyboard.typed(KeyEvent.VK_SPACE)&&!paused&&!playerDead) {
			ArrayList<ProjectileEntity> newProjectiles= new ArrayList<>();
			newProjectiles=myShip.shootBullet();
			if (newProjectiles!=null) {
				projectileEntities.addAll(newProjectiles);
			}
		}

		if (Keyboard.typed(KeyEvent.VK_S)&&!paused&&!playerDead) {
			ArrayList<ProjectileEntity> newProjectiles=myShip.shootMissile(-30);
			if (newProjectiles!=null) {
				projectileEntities.addAll(newProjectiles);
			}
		}

		if (Keyboard.typed(KeyEvent.VK_F)&&!paused&&!playerDead) {
			ArrayList<ProjectileEntity> newProjectiles=myShip.shootMissile(30);
			if (newProjectiles!=null) {
				projectileEntities.addAll(newProjectiles);
			}
		}

		if (Keyboard.EnterPressed()&&!paused&&playerDead) {
			myShip=new MyShip(420, 600);
			myShipUpgrades=new MyShipUpgrades(myShip);
			enemyEntities.clear();
			upgradeEntities.clear();
			projectileEntities.clear();
			healthPackSpawn=level.getHeathSpawnRate();
			initializeLevel();
		}

		if (Keyboard.PPressed()) {
			paused=true;	
		}

		if (Keyboard.RPressed()) {
			paused=false;
		}

		if (Keyboard.escapePressed()&&paused) {
			int input=JOptionPane.showConfirmDialog(null, "Exit game and return to menu?");
			if (input==0) {
				Game.state=Game.STATE.MENU;
				//Sound.stopBackgroundMusic();
			}
			Keyboard.resetEscape();
		}

		if (Keyboard.SPressed()&&paused) {
			int input=JOptionPane.showConfirmDialog(null, "Save game?");
			if (input==0) {
				LoadAndSaver.Save(levelNumber);
			}
			Keyboard.resetS();
		}
	}
}