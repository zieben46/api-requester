package game;

import EntityBehaviors.*;

public class MyShipUpgrades {

	MyShip myShip;

	public MyShipUpgrades(MyShip myShip) {
		this.myShip=myShip;
	}

	public void setUpgrades(int level) {
		switch (level) {
		case 1:
			myShip.setBulletBehavior(new SingleBulletBehavior());
			myShip.setGunSpeedUps(0);
			myShip.setMissileBehavior(new NullMissileBehavior());
			myShip.setSpeedUps(0);
			myShip.setBulletSpeedUps(0);
			break;
		case 2:
			myShip.setBulletBehavior(new SingleBulletBehavior());
			myShip.setGunSpeedUps(1);
			myShip.setMissileBehavior(new NullMissileBehavior());
			myShip.setSpeedUps(1);
			myShip.setBulletSpeedUps(1);
			break;
		case 3:
			myShip.setBulletBehavior(new SingleBulletBehavior());
			myShip.setGunSpeedUps(2);
			myShip.setMissileBehavior(new NullMissileBehavior());
			myShip.setSpeedUps(2);
			myShip.setBulletSpeedUps(2);
			break;
		case 4:
			myShip.setBulletBehavior(new SingleBulletBehavior());
			myShip.setGunSpeedUps(3);
			myShip.setMissileBehavior(new NullMissileBehavior());
			myShip.setSpeedUps(3);
			myShip.setBulletSpeedUps(3);
			break;
		case 5:
			myShip.setBulletBehavior(new DuoBulletBehavior());
			myShip.setGunSpeedUps(4);
			myShip.setMissileBehavior(new NullMissileBehavior());
			myShip.setSpeedUps(4);
			myShip.setBulletSpeedUps(4);
			break;
		case 6:
			myShip.setBulletBehavior(new DuoBulletBehavior());
			myShip.setGunSpeedUps(5);
			myShip.setMissileBehavior(new NullMissileBehavior());
			myShip.setSpeedUps(5);
			myShip.setBulletSpeedUps(5);
			break;
		case 7:
			myShip.setBulletBehavior(new DuoBulletBehavior());
			myShip.setGunSpeedUps(6);
			myShip.setMissileBehavior(new SingleMissileBehavior());
			myShip.setSpeedUps(6);
			myShip.setBulletSpeedUps(5);
			break;
		case 8:
			myShip.setBulletBehavior(new DuoBulletBehavior());
			myShip.setGunSpeedUps(7);
			myShip.setMissileBehavior(new SingleMissileBehavior());
			myShip.setSpeedUps(6);
			myShip.setBulletSpeedUps(5);
			break;	
		case 9:
			myShip.setBulletBehavior(new DuoBulletBehavior());
			myShip.setGunSpeedUps(8);
			myShip.setMissileBehavior(new SingleMissileBehavior());
			myShip.setSpeedUps(6);
			myShip.setBulletSpeedUps(5);
			break;
		case 10:
			myShip.setBulletBehavior(new DuoBulletBehavior());
			myShip.setGunSpeedUps(9);
			myShip.setMissileBehavior(new SingleMissileBehavior());
			myShip.setSpeedUps(6);
			myShip.setBulletSpeedUps(5);
			break;	
		case 11:
			myShip.setBulletBehavior(new DuoBulletBehavior());
			myShip.setGunSpeedUps(10);
			myShip.setMissileBehavior(new SingleMissileBehavior());
			myShip.setSpeedUps(6);
			myShip.setBulletSpeedUps(5);
			break;
		case 12:
			myShip.setBulletBehavior(new DuoBulletBehavior());
			myShip.setGunSpeedUps(11);
			myShip.setMissileBehavior(new SingleMissileBehavior());
			myShip.setSpeedUps(6);
			myShip.setBulletSpeedUps(5);
			break;
		case 13:
			myShip.setBulletBehavior(new SpreadBulletBehavior());
			myShip.setGunSpeedUps(12);
			myShip.setMissileBehavior(new SingleMissileBehavior());
			myShip.setSpeedUps(6);
			myShip.setBulletSpeedUps(5);
			break;
		case 14:
			myShip.setBulletBehavior(new SpreadBulletBehavior());
			myShip.setGunSpeedUps(13);
			myShip.setMissileBehavior(new SingleMissileBehavior());
			myShip.setSpeedUps(6);
			myShip.setBulletSpeedUps(5);
			break;
		case 15:
			myShip.setBulletBehavior(new SpreadBulletBehavior());
			myShip.setGunSpeedUps(14);
			myShip.setMissileBehavior(new SingleMissileBehavior());
			myShip.setSpeedUps(6);
			myShip.setBulletSpeedUps(5);
			break;
		case 16:
			myShip.setBulletBehavior(new SpreadBulletBehavior());
			myShip.setGunSpeedUps(14);
			myShip.setMissileBehavior(new SingleMissileBehavior());
			myShip.setSpeedUps(6);
			myShip.setBulletSpeedUps(5);
			break;
		case 17:
			myShip.setBulletBehavior(new SpreadBulletBehavior());
			myShip.setGunSpeedUps(14);
			myShip.setMissileBehavior(new SingleMissileBehavior());
			myShip.setSpeedUps(6);
			myShip.setBulletSpeedUps(5);
			break;
		case 18:
			myShip.setBulletBehavior(new SpreadBulletBehavior());
			myShip.setGunSpeedUps(14);
			myShip.setMissileBehavior(new SingleMissileBehavior());
			myShip.setSpeedUps(6);
			myShip.setBulletSpeedUps(5);
			break;
		case 19:
			myShip.setBulletBehavior(new SpreadBulletBehavior());
			myShip.setGunSpeedUps(14);
			myShip.setMissileBehavior(new SingleMissileBehavior());
			myShip.setSpeedUps(6);
			myShip.setBulletSpeedUps(5);
			break;
			
		}
	}
}
