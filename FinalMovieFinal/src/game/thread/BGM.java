package game.thread;

import java.applet.Applet;
import java.applet.AudioClip;
import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class BGM extends Thread {
	public BGM() {

	}

	public void run() {
		while (true) {
			try {
				Clip clip = AudioSystem.getClip();
				clip.open(AudioSystem.getAudioInputStream(new File("c://path.wav")));
				clip.start();

				// clip.start();
				while (!clip.isRunning())
					Thread.sleep(10);
				while (clip.isRunning())
					Thread.sleep(10);
				clip.close();
			} catch (Exception exc) {
				exc.printStackTrace(System.out);
			}
		}
	}
}
