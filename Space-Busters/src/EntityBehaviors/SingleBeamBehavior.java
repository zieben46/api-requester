package EntityBehaviors;
import java.util.ArrayList;

import classes.ProjectileEntity;
import classes.ProjectileEntity.Team;
import game.Beam;
import game.Bullet;

public class SingleBeamBehavior implements BulletBehavior {

	@Override
	public ArrayList<ProjectileEntity> shootBullet(int x, int y, int Vx, int Vy, Team team) {
		ArrayList<ProjectileEntity> beam=new ArrayList<>();
		beam.add(new Beam(x, y, 0, Vy, team));
		return beam;
	}
}