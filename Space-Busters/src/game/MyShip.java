package game;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;

import EntityBehaviors.*;

import classes.*;
import game.Sound.soundEnum;

public class MyShip extends GameObject implements Entity {
	protected BufferedImage[] shipImage;
	protected int Vx;
	protected int Vy;
	protected double animeCount=6.0;
	private BulletBehavior bulletBehavior;
	private MissileBehavior missileBehavior;
	private boolean dead=false;

	private double firedTime;
	private double coolDownTime;  //half second
	private int bulletSpeed;
	//private double coolDownTime=.05*1000;

	private int speedUps=0;
	private int gunSpeedUps=0;
	private int bulletSpeedUps;
	private int health=200;
	private BufferedImage explosions[];
	private int explosionCounter;

	public MyShip(int x, int y) { 
		super(x, y);
		coolDownTime=0.5*1000;  //half second
		bulletBehavior=new SingleBulletBehavior();
		missileBehavior=new NullMissileBehavior();
		shipImage=new BufferedImage[6];
		Vx=4;
		Vy=4;
		getImage();
		super.setHeight(shipImage[3].getHeight());
		super.setWidth(shipImage[3].getWidth());
		StatsTracker.playerHealth=health;
	}

	private void getImage() {
		shipImage[0]= ImageLoader.myShip0;
		shipImage[1]= ImageLoader.myShip1;
		shipImage[2]= ImageLoader.myShip2;
		shipImage[3]= ImageLoader.myShip3;
		shipImage[4]= ImageLoader.myShip4;
		shipImage[5]= ImageLoader.myShip5;
	}

	public void render(Graphics2D g) {
		if (!dead) {
			g.drawImage(shipImage[(int) animeCount%6], x, y, null);
			animeCount+=.05;
		}
	}

	public void update() {
		if (x>=Space.WIDTH-50&&!dead) {
			x=Space.WIDTH-50;
		}
		if (x<=0) {
			x=0;
		}
		if (y>=Space.HEIGHT-50&&!dead) {
			y=Space.HEIGHT-50;
		}
		if (y<=0) {
			y=0;
		}
		if (health<=0) {
			dead=true;
		}
	}

	public ArrayList<ProjectileEntity> shootBullet() {
		double currentTime=System.currentTimeMillis();
		if (currentTime-firedTime>coolDownTime) {
			firedTime=currentTime;
			return bulletBehavior.shootBullet(x+20, y, -Vx, -Vy-bulletSpeed, ProjectileEntity.Team.FRIENDLY);
		}
		return null;
	}

	public ArrayList<ProjectileEntity> shootMissile(int Vx) {
		double currentTime=System.currentTimeMillis();
		if (currentTime-firedTime>=coolDownTime) {
			firedTime=currentTime;
			return missileBehavior.shootMissile(x+20, y, Vx, -Vy, ProjectileEntity.Team.FRIENDLY);
		}
		return null;
	}

//	public void increaseShootBehavior(BulletBehavior bulletBehavior) {
//		this.bulletBehavior=bulletBehavior;
//	}

	public void setGunSpeedUps(int gunSpeedUps) {
		this.gunSpeedUps=gunSpeedUps;
		coolDownTime=0.5*1000*Math.pow(0.85, gunSpeedUps);
		StatsTracker.gunRateUpgs=gunSpeedUps;
	}

	public void setBulletSpeedUps(int bulletSpeedUps) {
		this.bulletSpeedUps=bulletSpeedUps;
		bulletSpeed=4+this.bulletSpeedUps;
	}

	public void setSpeedUps(int speedUps) {
		this.speedUps=speedUps;
		Vx=4+this.speedUps;
		Vy=4+this.speedUps;
		StatsTracker.movementUpgs=this.speedUps;
	}

	public void increaseHealth() {
		if (health<200) {
			health+=20;
			StatsTracker.playerHealth=health;
		}
	}

	public void decreaseHealth() {
		if (health>0) {
			health-=20;
			StatsTracker.playerHealth=health;
		} else {
			dead=true;
		}
	}

	public boolean isDead() {
		return health<=0;
	}

	public void upgrade(UpgradeEntity upgradeEntity) {	
		switch(upgradeEntity.upgradeType()) {
		case gunType:
			//increaseShootBehavior();
			break;
		case gunRate:
			gunSpeedUps++;
			break;
		case movement:
			speedUps++;
			break;
		case healthPack:
			increaseHealth();
		}
	}

	public void setBulletBehavior(BulletBehavior bulletBehavior) {
		this.bulletBehavior=bulletBehavior;
	}

	public void setMissileBehavior(MissileBehavior missileBehavior) {
		this.missileBehavior=missileBehavior;
	}

	public void moveLeft() {
		x-=Vx;
	}
	public void moveRight() {
		x+=Vx;
	}
	public void moveUp() {
		y-=Vy;
	}
	public void moveDown() {
		y+=Vy;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x=x;
	}
	
	public void setY(int y) {
		this.y=y;
	}

	public Rectangle getBounds() {
		return new Rectangle (x, y, shipImage[3].getWidth(), shipImage[3].getHeight());
	}

	public int getWidth() {
		return super.getWidth();
	}

	public int getHeight() {
		return super.getHeight();
	}
}