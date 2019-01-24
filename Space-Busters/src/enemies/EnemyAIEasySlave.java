package enemies;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

import javax.imageio.ImageIO;

import EntityBehaviors.DuoBulletBehavior;
import classes.EnemyEntity;
import classes.ProjectileEntity;
import classes.ProjectileEntity.Team;
import game.EnemyAI;
import game.ImageLoader;

public class EnemyAIEasySlave extends EnemyAIEasy implements EnemyEntity {
	private Random random=new Random();
	protected boolean canRandomMove=true;
	protected double movedTime;
	protected double moveCoolDownTime=.2*1000;
	private int rapidFireCoolDownTime=6*1000;
	private int rapidFireCount;
	private double rapidFiredTime;

	public EnemyAIEasySlave() {
		super();
		Vx=random.nextInt(10)-5;
		Vy=5;
		health=10;
		bulletBehavior=new DuoBulletBehavior();
		bulletSpeed=5;
		bulletCoolDownTime=0.01*1000;
	}

	@Override
	public void getImage() {
		shipImage = ImageLoader.EnemyAIEasySlave;
	}

	@Override
	public void randomMove() {
		double deltaY=EnemyAI.getPlayerY()-y;
		if (deltaY<100) {
			Vy=-4;
		} else if (deltaY>300){
			Vy=3;
		}

		double currentTime=System.currentTimeMillis();
		if ((currentTime-movedTime)>=moveCoolDownTime) {
			int deltaX=EnemyAI.getPlayerX()-x;
			if (deltaX>0) {
				Vx=random.nextInt(5)+5;
			} else {
				Vx=random.nextInt(5)-5;
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
