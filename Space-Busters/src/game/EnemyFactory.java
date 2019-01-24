package game;

import classes.EnemyEntity;
import classes.EnemyEntity.EnemyType;
import enemies.*;

public class EnemyFactory {
	
	public EnemyEntity addEnemy(EnemyType enemyType) {
		switch (enemyType) {
		case Easy1:
			return new EnemyEasy1();
		case Easy2:
			return new EnemyEasy2();
		case Easy3:
			return new EnemyEasy3();
		case EasySuicide:
			return new EnemySuicide(6, 10);
		case Medium1:
			return new EnemyMedium1();	
		case Medium3:
			return new EnemyMedium3();
		case MediumSuicide:
			return new EnemySuicide(6, 10);
		case AIEasy:
			return new EnemyAIEasy();
		case AIEasySlave:
			return new EnemyAIEasySlave();
		case CarrierEasy:
			return new EnemyCarrierEasy();
		case AIMediumSlave:
			return new EnemyAIMediumSlave();
		case BeamEasy:
			return new EnemyBeamEasy();
			
		default:
			return new EnemyEasy1();
		}
	}
}


