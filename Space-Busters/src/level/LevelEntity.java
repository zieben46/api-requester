package level;

public interface LevelEntity {
	
	abstract void initialize();
	abstract void addEnemy();
	abstract int getTotalEnemies();
	abstract boolean levelComplete();
	abstract void update();
	abstract int getHeathSpawnRate();
	abstract boolean getInGap();
}
