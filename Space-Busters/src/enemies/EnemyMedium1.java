package enemies;

import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import EntityBehaviors.NullBulletBehavior;
import classes.EnemyEntity;
import game.ImageLoader;

public class EnemyMedium1 extends Enemy implements EnemyEntity {
	int health=30;
	
	public EnemyMedium1() {
		super();
		Vx=0;
		Vy=random.nextInt(5)+14;
		health=10;
		bulletBehavior=new NullBulletBehavior();
		bulletSpeed=0;
		bulletCoolDownTime=10000*1000;
	}

	@Override
	void getImage() {
		shipImage = ImageLoader.enemyMedium1;
	}
}
