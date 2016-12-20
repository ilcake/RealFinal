package game.thread;

import java.io.File;

import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.JLabel;

import game.Game;

public class Jump extends Thread {
	private Game gg;
	private JLabel lb_main;
	public int yLoc;

	public Jump(Game gg, JLabel lb_main, int yLoc) {
		super();
		this.gg = gg;
		this.lb_main = lb_main;
		this.yLoc = yLoc;
	}

	@Override
	public void run() {
		jump();

	}

	public void jump() {
		try {
			Clip clip = AudioSystem.getClip();
			clip.open(AudioSystem.getAudioInputStream(new File("img/game/jumping.wav")));
			clip.start();
			Thread.sleep(3);
		} catch (Exception exc) {
			exc.printStackTrace(System.out);
		}
		int count = 0;
		while (count != 10) {
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			yLoc -= 10;
			lb_main.setBounds(110, yLoc, 68, 128);
			gg.setMyY(yLoc);
			count++;
			gg.repaint();
		}
		while (count > 0) {
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			yLoc += 10;
			lb_main.setBounds(110, yLoc, 68, 128);
			gg.setMyY(yLoc);
			count--;
			gg.repaint();
		}
		gg.isJumped = false;
	}
}
