package EntityBehaviors;
import java.util.ArrayList;

import classes.ProjectileEntity;
import classes.ProjectileEntity.Team;
import game.Bullet;
import game.Sound;
import game.Sound.soundEnum;

public class DuoBulletBehavior implements BulletBehavior {

	@Override
	public ArrayList<ProjectileEntity> shootBullet(int x, int y, int Vx, int Vy, Team team) {
		ArrayList<ProjectileEntity> bullets=new ArrayList<>();
		bullets.add(new Bullet(x-20, y, 0, Vy, team));
		bullets.add(new Bullet(x+20, y, 0, Vy, team));
		Sound.playSound(soundEnum.MULTISHOOT);
		return bullets;
	}
}