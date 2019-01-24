package enemies;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

import EntityBehaviors.SingleBeamBehavior;
import classes.EnemyEntity;
import classes.ProjectileEntity;
import game.ImageLoader;

public class EnemyBeamEasy extends Enemy implements EnemyEntity {
	int health=30;
	Random random=new Random();
	private ArrayList<ProjectileEntity> tempBeam;
	
	public EnemyBeamEasy() {
		super();
		y=-random.nextInt(200)-400;
		y=-400;
		Vx=0;
		Vy=random.nextInt(2)+5;
		health=20;
		bulletBehavior=new SingleBeamBehavior();
		bulletCoolDownTime=.005*1000;
		bulletSpeed=10;
	}

	@Override
	void getImage() {
		shipImage = ImageLoader.enemyBeamEasy;
	}
	
	@Override
	protected boolean wantsTo() {
		return true;
	}
}
