package level;

import java.util.LinkedList;

import classes.EnemyEntity;
import classes.EnemyEntity.EnemyType;
import game.MyShip;

public class Level15 extends Level {

	public Level15(LinkedList<EnemyEntity> enemyEntities, MyShip myShip) {
		super(enemyEntities, myShip);
		totalEnemies=150;
		enemyRate=6;
	}

	@Override
	public void addEnemy() {
		if (enemiesAdded<totalEnemies) {
			enemyEntities.add(enemyFactory.addEnemy(EnemyType.Medium3));
		}
		enemiesAdded++;
	}
}
