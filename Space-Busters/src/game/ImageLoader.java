package game;
import java.awt.image.BufferedImage;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;



final public class ImageLoader {

	public static BufferedImage blueSprite;
	public static BufferedImage yellowSprite;
	public static BufferedImage enemyAIEasy;
	public static BufferedImage EnemyAIEasySlave;
	public static BufferedImage EnemyAIMediumSlave;
	public static BufferedImage enemyBeamEasy;
	public static BufferedImage enemyCarrierEasy;
	public static BufferedImage EnemyEasy1;
	public static BufferedImage EnemyEasy2;
	public static BufferedImage enemyEasy3;
	public static BufferedImage enemyMedium1;
	public static BufferedImage enemyMedium3;
	public static BufferedImage enemySuicide;
	public static BufferedImage beamDown;
	public static BufferedImage bulletUp;
	public static BufferedImage bulletDown;
	public static BufferedImage purpImageSmall;
	public static BufferedImage yellowImageSmall;
	public static BufferedImage greenImageSmall;
	public static BufferedImage menuBackground;
	public static BufferedImage missileLeft;
	public static BufferedImage missileRight;
	public static BufferedImage myShip0;
	public static BufferedImage myShip1;
	public static BufferedImage myShip2;
	public static BufferedImage myShip3;
	public static BufferedImage myShip4;
	public static BufferedImage myShip5;
	public static BufferedImage spaceBackground;
	public static BufferedImage yellowUpgrade;
	public static BufferedImage purpleUpgrade;
	public static BufferedImage greenUpgrade;
	public static BufferedImage healthPack;
	
	public static BufferedImage yellowSpriteBig;
	public static BufferedImage yellowSpriteReallyBig;
	public static BufferedImage yellowSpriteAll;
	
//	public static BufferedImage exp1;
//	public static BufferedImage exp2;
//	public static BufferedImage exp3;
//	public static BufferedImage exp4;
//	public static BufferedImage exp5;
//	public static BufferedImage exp6;
//	public static BufferedImage exp7;
//	public static BufferedImage exp8;
//	public static BufferedImage exp9;
//	public static BufferedImage exp10;
//	public static BufferedImage exp11;
//	public static BufferedImage exp12;
//	public static BufferedImage exp13;
//	public static BufferedImage exp14;
//	public static BufferedImage exp15;
//	public static BufferedImage exp16;
//	public static BufferedImage exp17;


	public static InputStream load(String path) {
		InputStream input = ImageLoader.class.getResourceAsStream(path);
		if (input==null) {
			input=ImageLoader.class.getResourceAsStream("/"+path);
		}
		return input;
	}

	public static void loadImages() {
		try {
			blueSprite= ImageIO.read(ImageLoader.load("explosionSheetBlueShort.png"));
			yellowSprite= ImageIO.read(ImageLoader.load("explosionSheetYellowShort.png"));
			enemyAIEasy = ImageIO.read(ImageLoader.load("enemyAIEasy.png"));
			EnemyAIEasySlave= ImageIO.read(ImageLoader.load("enemyAIEasySlave.png"));
			EnemyAIMediumSlave= ImageIO.read(ImageLoader.load("enemyAIMediumSlave.png"));
			enemyBeamEasy= ImageIO.read(ImageLoader.load("enemyBeamEasy.png"));
			enemyCarrierEasy= ImageIO.read(ImageLoader.load("carrierEasy.png"));
			EnemyEasy1= ImageIO.read(ImageLoader.load("enemyType1.png")); 
			EnemyEasy2= ImageIO.read(ImageLoader.load("enemyType2.png"));
			enemyEasy3= ImageIO.read(ImageLoader.load("enemyType2.png")); 
			enemyMedium1= ImageIO.read(ImageLoader.load("enemyMedium1.png"));
			enemyMedium3= ImageIO.read(ImageLoader.load("enemyMedium3.png"));
			enemySuicide= ImageIO.read(ImageLoader.load("enemyTypeSuicide.png"));
			beamDown= ImageIO.read(ImageLoader.load("beam.png"));
			bulletUp= ImageIO.read(ImageLoader.load("bulletUp.png")); 
			bulletDown= ImageIO.read(ImageLoader.load("bulletDown.png"));
			purpImageSmall= ImageIO.read(ImageLoader.load("upgradePurpleSmall.png"));
			yellowImageSmall = ImageIO.read(ImageLoader.load("upgradeYellowSmall.png"));
			greenImageSmall= ImageIO.read(ImageLoader.load("upgradeGreenSmall.png"));
			menuBackground= ImageIO.read(ImageLoader.load("menuBackground.png"));
			missileLeft= ImageIO.read(ImageLoader.load("bulletLeft.png"));
			missileRight= ImageIO.read(ImageLoader.load("bulletRight.png"));
			myShip0= ImageIO.read(ImageLoader.load("myShipAnim1.png"));
			myShip1= ImageIO.read(ImageLoader.load("myShipAnim2.png"));
			myShip2= ImageIO.read(ImageLoader.load("myShipAnim3.png"));
			myShip3= ImageIO.read(ImageLoader.load("myShipAnim4.png"));
			myShip4= ImageIO.read(ImageLoader.load("myShipAnim5.png"));
			myShip5= ImageIO.read(ImageLoader.load("myShipAnim6.png"));
			spaceBackground= ImageIO.read(ImageLoader.load("background.png"));
			yellowUpgrade= ImageIO.read(ImageLoader.load("upgradeYellow.png"));
			purpleUpgrade= ImageIO.read(ImageLoader.load("upgradePurple.png"));
			greenUpgrade= ImageIO.read(ImageLoader.load("upgradeGreen.png"));
			healthPack= ImageIO.read(ImageLoader.load("healthPack.png"));
			//yellowSpriteBig= ImageIO.read(ImageLoader.load("explosionSheetYellowShortBig.png"));
			//yellowSpriteReallyBig= ImageIO.read(ImageLoader.load("explosionSheetYellowShortBigger.png"));
			yellowSpriteBig= ImageIO.read(ImageLoader.load("shipExplosion.png"));
			
			
//			exp1=ImageIO.read(ImageLoader.load("exp1.png"));
//			exp2=ImageIO.read(ImageLoader.load("exp2.png"));
//			exp3=ImageIO.read(ImageLoader.load("exp3.png"));
//			exp4=ImageIO.read(ImageLoader.load("exp4.png"));
//			exp5=ImageIO.read(ImageLoader.load("exp5.png"));
//			exp6=ImageIO.read(ImageLoader.load("exp6.png"));
//			exp7=ImageIO.read(ImageLoader.load("exp7.png"));
//			exp8=ImageIO.read(ImageLoader.load("exp8.png"));
//			exp9=ImageIO.read(ImageLoader.load("exp9.png"));
//			exp10=ImageIO.read(ImageLoader.load("exp10.png"));
//			exp11=ImageIO.read(ImageLoader.load("exp11.png"));
//			exp12=ImageIO.read(ImageLoader.load("exp12.png"));
//			exp13=ImageIO.read(ImageLoader.load("exp13.png"));
//			exp14=ImageIO.read(ImageLoader.load("exp14.png"));
//			exp15=ImageIO.read(ImageLoader.load("exp15.png"));
//			exp16=ImageIO.read(ImageLoader.load("exp16.png"));
//			exp17=ImageIO.read(ImageLoader.load("exp17.png"));
		
	
			
			
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROR LOADING IMAGES.  CONTACT ADMIN FOR SUPPORT");
				e.printStackTrace();
		}
	}
}
