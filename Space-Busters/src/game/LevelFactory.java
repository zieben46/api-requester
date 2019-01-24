package game;

import java.util.LinkedList;
import classes.EnemyEntity;
import level.*;

public class LevelFactory {
	private LinkedList<EnemyEntity> enemyEntities;
	private MyShip myShip;

	LevelFactory(LinkedList<EnemyEntity> enemyEntities, MyShip myShip) {
		this.enemyEntities=enemyEntities;
		this.myShip=myShip;
	}


	public LevelEntity newLevel(int levelNumber) {
		switch (levelNumber) {
		case 1: 
			return new Level1(enemyEntities, myShip);
		case 2: 
			return new Level2(enemyEntities, myShip);
		case 3: 
			return new Level3(enemyEntities, myShip);
		case 4: 
			return new Level4(enemyEntities, myShip);
		case 5: 
			return new Level5(enemyEntities, myShip);
		case 6:
			return new Level6(enemyEntities, myShip);
		case 7:
			return new Level7(enemyEntities, myShip);
		case 8:
			return new Level8(enemyEntities, myShip);
		case 9:
			return new Level9(enemyEntities, myShip);
		case 10:
			return new Level10(enemyEntities, myShip);
		case 11:
			return new Level11(enemyEntities, myShip);
		case 12:
			return new Level12(enemyEntities, myShip);
		case 13:
			return new Level13(enemyEntities, myShip);
		case 14:
			return new Level14(enemyEntities, myShip);
		case 15:
			return new Level15(enemyEntities, myShip);
		case 16:
			return new Level16(enemyEntities, myShip);
		case 17:
			//return new Level17(enemyEntities, myShip);
		case 18:
			//return new Level18(enemyEntities, myShip);
		case 19:
			//return new Level19(enemyEntities, myShip);
		case 20:
			//return new Level20(enemyEntities, myShip);
			
		}
		return null;
	}
}

