package enemies;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import EntityBehaviors.DuoBulletBehavior;
import EntityBehaviors.NullBulletBehavior;
import classes.EnemyEntity;
import classes.ProjectileEntity;
import classes.ProjectileEntity.Team;
import game.EnemyAI;
import game.ImageLoader;

public class EnemyAIMediumSlave extends EnemyAIEasy implements EnemyEntity {
	private Random random=new Random();
	protected boolean canRandomMove=true;
	protected double movedTime;
	protected double moveCoolDownTime=.2*1000;
	private int rapidFireCoolDownTime=6*1000;
	private int rapidFireCount;
	private double rapidFiredTime;

	public EnemyAIMediumSlave() {
		super();
		Vx=random.nextInt(10)-5;
		Vy=8;
		health=100;
		bulletBehavior=new NullBulletBehavior();
		bulletSpeed=5;
		bulletCoolDownTime=10000*1000;
	}

	@Override
	public void getImage() {
		shipImage = ImageLoader.EnemyAIMediumSlave;
	}

	@Override
	public void randomMove() {
		double currentTime=System.currentTimeMillis();
		if ((currentTime-movedTime)>=moveCoolDownTime) {
			int deltaX=EnemyAI.getPlayerX()-x;
			if (deltaX>0) {
				Vx=random.nextInt(10)+10;
			} else {
				Vx=random.nextInt(10)-10;
			}
			movedTime=currentTime;
		}
	}

	@Override
	public ArrayList<ProjectileEntity> randomBullet() {
		double currentTime=System.currentTimeMillis();
		if ((currentTime-firedTime)>bulletCoolDownTime&&
				(currentTime-rapidFiredTime>rapidFireCoolDownTime)&&wantsTo()) {
			rapidFireCount++;
			firedTime=currentTime;
			if (rapidFireCount==3) {
				rapidFiredTime=currentTime;
				rapidFireCount=0;
			}
			return bulletBehavior.shootBullet(x+(getWidth()/2-5), y+getHeight(), Vx, Vy+bulletSpeed, Team.ENEMY);
		}
		return null;
	}


}
