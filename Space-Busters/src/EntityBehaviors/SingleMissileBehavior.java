package EntityBehaviors;

import java.util.ArrayList;

import classes.ProjectileEntity;
import classes.ProjectileEntity.Team;
import game.Missile;
import game.Sound;
import game.Sound.soundEnum;

public class SingleMissileBehavior implements MissileBehavior {

	@Override
	public ArrayList<ProjectileEntity> shootMissile(int x, int y, int Vx, int Vy, Team team) {
		ArrayList<ProjectileEntity> missiles=new ArrayList<>();
		missiles.add(new Missile(x, y, Vx, 0, team));
		Sound.playSound(soundEnum.MULTISHOOT);
		return missiles;
	}
}
