package classes;
import java.util.ArrayList;

public interface EnemyEntity extends Entity {
	abstract void takeDamage(int damage);
	abstract boolean isDead();
	public enum EnemyType {
		Easy1,
		Easy2,
		Easy3,
		EasySuicide,
		Medium1,
		Medium3,
		MediumSuicide,
		AIEasy,
		AIEasySlave,
		CarrierEasy,
		AIMediumSlave,
		BeamEasy
	}

	abstract ArrayList<ProjectileEntity> randomBullet();
	abstract void setNewPosition();
	abstract void setX(int x);
}