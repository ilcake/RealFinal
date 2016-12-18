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
	private BoundCheck bc;

	public LoadingWord(Game gg) {
		this.gg = gg;

	}

	@Override
	public void run() {
		while (true) {
			int time = (int) (Math.random() * 1500) + 200;
			System.out.println(time);
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
		lb_word = new JLabel("Now Loading....");
		yLoc = 10;
		lb_word.setFont(new Font("Arial", Font.PLAIN, 27));
		lb_word.setBounds(xLoc, yLoc, 324, 47);
		gg.gmPanle.add(lb_word);
		gg.revalidate();
		gg.repaint();
		bc = new BoundCheck(gg, xLoc, yLoc);
		new Thread(bc).start();
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
			bc.setXlo(xLoc);
			bc.setYlo(yLoc);
			lb_word.repaint();
			gg.repaint();
			gg.revalidate();
		}
	}

}