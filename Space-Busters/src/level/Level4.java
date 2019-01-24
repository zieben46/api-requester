package level;

import java.util.LinkedList;
import java.util.Random;
import classes.EnemyEntity;
import classes.EnemyEntity.EnemyType;
import game.MyShip;

public class Level4 extends Level {
	private int enemyTypes=3;
	private Random random;

	public Level4(LinkedList<EnemyEntity> enemyEntities, MyShip myShip) {
		super(enemyEntities, myShip);
		totalEnemies=15;
		enemyRate=8;
		random=new Random();
	}

	@Override
	public void addEnemy() {
		if (enemiesAdded<totalEnemies) {
			int r=random.nextInt(enemyTypes)+1;
			switch (r) {
			case 1:
				enemyEntities.add(enemyFactory.addEnemy(EnemyType.Easy1));
				break;
			case 2:
				enemyEntities.add(enemyFactory.addEnemy(EnemyType.Easy2));
				break;
			case 3:
				enemyEntities.add(enemyFactory.addEnemy(EnemyType.EasySuicide));
				break;
			default :
				enemyEntities.add(enemyFactory.addEnemy(EnemyType.Easy1));
			}
			enemiesAdded++;
		}
	}
}
