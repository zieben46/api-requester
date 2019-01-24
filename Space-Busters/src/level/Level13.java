package level;

import java.util.LinkedList;
import java.util.Random;

import classes.EnemyEntity;
import classes.EnemyEntity.EnemyType;
import game.MyShip;

public class Level13 extends Level {
	private Random random;
	private int enemyTypes=8;

	public Level13(LinkedList<EnemyEntity> enemyEntities, MyShip myShip) {
		super(enemyEntities, myShip);
		totalEnemies=550;
		enemyRate=25;
		random=new Random();
	}

	@Override
	public void addEnemy() {
		if (enemiesAdded<totalEnemies) {
			int r=random.nextInt(enemyTypes)+1;
			switch (r) {
			case 1:
				enemyEntities.add(enemyFactory.addEnemy(EnemyType.AIEasy));
				break;
			case 2:
				enemyEntities.add(enemyFactory.addEnemy(EnemyType.AIEasy));
				break;
			case 3:
				enemyEntities.add(enemyFactory.addEnemy(EnemyType.Easy1));
				break;
			case 4:
				enemyEntities.add(enemyFactory.addEnemy(EnemyType.Easy2));
				break;
			case 5:
				enemyEntities.add(enemyFactory.addEnemy(EnemyType.Easy3));
				break;
			case 6:
				enemyEntities.add(enemyFactory.addEnemy(EnemyType.Medium1));
				break;
			case 7:
				enemyEntities.add(enemyFactory.addEnemy(EnemyType.Medium1));
				break;
			case 8:
				enemyEntities.add(enemyFactory.addEnemy(EnemyType.Medium1));
				break;
			}
			enemiesAdded++;
		}
	}
}
