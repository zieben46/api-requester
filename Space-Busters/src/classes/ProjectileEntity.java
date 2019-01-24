package classes;

public interface ProjectileEntity extends Entity {
	
	enum Team {
		FRIENDLY,
		ENEMY
	}
	
	abstract int getDamage();
	abstract Team team();
}
