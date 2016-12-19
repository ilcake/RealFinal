package game.thread;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Game;

public class FlyingBird extends Thread {
	private Game gg;
	public int xLoc;
	public int yLoc;
	private JLabel lb_bird;
	private int speed;
	private BoundCheck bc;

	public FlyingBird(Game gg) {
		this.gg = gg;

	}

	@Override
	public void run() {
		while (true) {
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
		xLoc = 610;
		lb_bird = new JLabel();
		yLoc = 80;
		lb_bird.setBounds(xLoc, yLoc, 50, 50);
		lb_bird.setIcon(new ImageIcon("img/game/batt.gif"));
		gg.gmPanle.add(lb_bird);
		gg.revalidate();
		gg.repaint();
		bc = new BoundCheck(gg, xLoc, yLoc);
		new Thread(bc).start();
	}

	public void moving() {
		speed = (int) (Math.random() * 20 + 5);
		while (xLoc > -80) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			xLoc -= speed;
			lb_bird.setBounds(xLoc, yLoc, 50, 50);
			bc.setXlo(xLoc);
			bc.setYlo(yLoc);
			lb_bird.repaint();
			gg.repaint();
			gg.revalidate();
		}
	}

}