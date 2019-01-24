package game;

import game.Game.STATE;


public class Sound {
	private static AudioClip multiShoot;
	private static AudioClip bigExplosion;
	private static AudioClip smallExplosion;
	private static AudioClip buttonHover;
	private static AudioClip buttonSelect;
	private static AudioClip backgroundMusic;

	public static Thread thread;

	public static void load() {
		multiShoot=AudioClip.createAudioClip("multiShoot.wav");
		bigExplosion=AudioClip.createAudioClip("bigExplosion.wav");
		smallExplosion=AudioClip.createAudioClip("smallExplosion.wav");
		buttonHover=AudioClip.createAudioClip("buttonHover.wav");
		buttonSelect=AudioClip.createAudioClip("buttonSelect.wav");
		backgroundMusic=AudioClip.createAudioClip("backgroundMusic.wav");
		thread=new Thread();
	}

	public enum soundEnum {
		MULTISHOOT,
		BIGEXPLOSION,
		SMALLEXPLOSION,
		BUTTONHOVER,
		BUTTONSELECT
	}

	public static void playSound(soundEnum se) {
		AudioClip presentClip = null;
		switch (se) {
		case MULTISHOOT:
			presentClip=multiShoot;
			break;
		case BIGEXPLOSION:
			presentClip=bigExplosion;
			break;
		case SMALLEXPLOSION:
			presentClip=smallExplosion;
			break;
		case BUTTONHOVER:
			presentClip=buttonHover;
			break;	
		case BUTTONSELECT:
			presentClip=buttonSelect;
			break;	
		}
		playClip(presentClip);
	}

	//	private static void playClip(AudioClip presentClip) {
	//		//presentClip.play();
	//		try{
	//			new Thread(){ public void run(){ presentClip.play();
	//			}
	//			}.start();
	//		}catch(Throwable e){
	//			e.printStackTrace();
	//		}
	//	}

	private static void playClip(AudioClip presentClip) {
		//presentClip.play();
		try {
			
			new Thread(() -> presentClip.play()		
			).start();
			
		}catch(Throwable e){
			e.printStackTrace();
		}
	}

	public static void playBackgroundMusic() {
		if (Game.state.equals(STATE.GAME)) {
			try{
				new Thread("MusicThread"){ public void run(){
					backgroundMusic.loop(0);
				}
				}.start();
			}catch(Throwable e){
				e.printStackTrace();
			}
		}
	}


	@SuppressWarnings("deprecation")
	public static void stopBackgroundMusic() {
		//			//backgroundMusic.stop();
		//	
		//			for (Thread th: Thread.getAllStackTraces().keySet()) {
		//	
		//				if (th.getName().equals("MusicThread")) {
		//					try {
		//						th.wait();
		//					} catch (InterruptedException e) {
		//						// TODO Auto-generated catch block
		//						e.printStackTrace();
		//					}
		//				}
		//			}
	}
}



