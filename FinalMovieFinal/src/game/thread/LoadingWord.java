package game.thread;

import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Game;

public class LoadingWord extends Thread {
	private Game gg;
	public int xLoc;
	public int yLoc;
	private JLabel lb_word;
	private int speed;

	public LoadingWord(Game gg) {
		this.gg = gg;

	}

	@Override
	public void run() {
		while (gg != null) {
			int time = (int) (Math.random() * 1500) + 200;
			try {
				Thread.sleep(time);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			makeBird();
			moving();
		}

	}

	public void makeBird() {
		xLoc = 620;
		lb_word = new JLabel("Thank you for watching....");
		yLoc = 10;
		lb_word.setFont(new Font("Arial", Font.PLAIN, 27));
		lb_word.setBounds(xLoc, yLoc, 324, 47);
		gg.gmPanle.add(lb_word);
		gg.revalidate();
		gg.repaint();
	}

	public void moving() {

		while (xLoc > -300) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			xLoc -= 8;
			lb_word.setBounds(xLoc, yLoc, 324, 47);
			lb_word.repaint();
			gg.repaint();
			gg.revalidate();
		}
	}

}