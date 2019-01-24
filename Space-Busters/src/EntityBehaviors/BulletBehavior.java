package EntityBehaviors;
import java.util.ArrayList;
import classes.ProjectileEntity;
import classes.ProjectileEntity.Team;

public interface BulletBehavior {
	public ArrayList<ProjectileEntity> shootBullet(int x, int y, int Vx, int Vy, Team Team);
}