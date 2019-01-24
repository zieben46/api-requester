package enemies;

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import EntityBehaviors.*;
import classes.EnemyEntity;
import game.ImageLoader;

public class EnemyEasy3 extends Enemy implements EnemyEntity {
	
	public EnemyEasy3() {
		super();
		Vx=random.nextInt(6)-3;
		Vy=random.nextInt(1)+4;
		health=10;
		bulletBehavior=new SpreadBulletBehavior();
		bulletSpeed=6;
		bulletCoolDownTime=1*1000;
	}
	
	@Override
	void getImage() {
		shipImage = ImageLoader.EnemyEasy2;  //??
	}
}
