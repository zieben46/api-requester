package enemies;

import java.io.File;
import java.io.IOException;
import java.util.Random;
import javax.imageio.ImageIO;
import EntityBehaviors.SingleBulletBehavior;
import classes.EnemyEntity;
import game.ImageLoader;

public class EnemyEasy1 extends Enemy implements EnemyEntity {
	int health=30;
	Random random=new Random();
	
	public EnemyEasy1() {
		super();
		Vx=0;
		Vy=random.nextInt(3)+2;
		health=10;
		bulletBehavior=new SingleBulletBehavior();
		bulletCoolDownTime=1*1000;
		bulletSpeed=10;
	}

	@Override
	void getImage() {
		shipImage = ImageLoader.EnemyEasy1;
	}

}
