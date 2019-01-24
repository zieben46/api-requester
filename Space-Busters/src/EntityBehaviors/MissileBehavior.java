package EntityBehaviors;

import java.util.ArrayList;

import classes.ProjectileEntity;
import classes.ProjectileEntity.Team;

public interface MissileBehavior {

	public ArrayList<ProjectileEntity> shootMissile(int x, int y, int Vx, int Vy, Team team);
}
