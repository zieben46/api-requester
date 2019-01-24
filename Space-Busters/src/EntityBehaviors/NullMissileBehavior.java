package EntityBehaviors;

import java.util.ArrayList;

import classes.ProjectileEntity;
import classes.ProjectileEntity.Team;

public class NullMissileBehavior implements MissileBehavior {

	@Override
	public ArrayList<ProjectileEntity> shootMissile(int x, int y, int Vx, int Vy, Team team) {
		return null;
	}
}
