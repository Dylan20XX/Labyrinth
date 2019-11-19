import java.io.File;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class MusicPlayer { // from YouTube by Max O'Didly

	// Variables for the music
	static Clip clip;
	
	public MusicPlayer(){
		
	}

	// Methods that actually create the music
	public static void loopMusic(String musicLocation) {

		try {

			// Make the method and pass the variables to the method
			File Sound = new File(musicLocation);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(Sound);
			clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.start();// start the music

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
	
	public static void playMusic(String musicLocation) {

		try {

			// Make the method and pass the variables to the method
			File Sound = new File(musicLocation);
			AudioInputStream audioInput = AudioSystem.getAudioInputStream(Sound);
			clip = AudioSystem.getClip();
			clip.open(audioInput);
			clip.start();// start the music

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

	// Method to stop the music
	public static void StopMusic() {
		// Stop the music
		clip.stop();

	}

}
