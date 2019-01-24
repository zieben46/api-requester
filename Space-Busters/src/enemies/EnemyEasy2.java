package enemies;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

import EntityBehaviors.DuoBulletBehavior;

import classes.EnemyEntity;
import game.ImageLoader;

public class EnemyEasy2 extends Enemy implements EnemyEntity {
	public EnemyEasy2() {
		super();
		Vx=random.nextInt(5)-3;
		Vy=random.nextInt(1)+3;
		health=10;
		bulletBehavior=new DuoBulletBehavior();
		bulletSpeed=5;
		bulletCoolDownTime=0.9*1000;
	}
	
	@Override
	void getImage() {
		shipImage = ImageLoader.EnemyEasy2;
	}
}
