package EntityBehaviors;
import java.util.ArrayList;

import javax.sound.sampled.LineUnavailableException;

import classes.ProjectileEntity;
import classes.ProjectileEntity.Team;
import game.Bullet;
import game.Sound;
import game.Sound.soundEnum;
import game.Sound;

public class SpreadBulletBehavior implements BulletBehavior{


	@Override
	public ArrayList<ProjectileEntity> shootBullet(int x, int y, int Vx, int Vy, Team team) {
		ArrayList<ProjectileEntity> bullets=new ArrayList<>();
		bullets.add(new Bullet(x, y, 1, Vy, team));
		bullets.add(new Bullet(x, y, 2, Vy, team));
		bullets.add(new Bullet(x, y, -1, Vy, team));
		bullets.add(new Bullet(x, y, -2, Vy, team));
		Sound.playSound(soundEnum.MULTISHOOT);
		
		return bullets;
	}
}
