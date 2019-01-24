package EntityBehaviors;

import java.util.ArrayList;

import classes.ProjectileEntity;
import classes.ProjectileEntity.Team;

public class NullBulletBehavior implements BulletBehavior {

	@Override
	public ArrayList<ProjectileEntity> shootBullet(int x, int y, int Vx, int Vy, Team team) {
		return null;
	}
}
