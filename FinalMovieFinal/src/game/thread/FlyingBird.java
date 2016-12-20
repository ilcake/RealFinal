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
		int where = (int) (Math.random() * 500);
		if (where >= 250) {
			yLoc = 80;
		} else {
			yLoc = 205;
		}
		xLoc = 610;
		lb_bird = new JLabel();
		lb_bird.setBounds(xLoc, yLoc, 50, 50);
		lb_bird.setIcon(new ImageIcon("img/game/batt.gif"));
		gg.gmPanle.add(lb_bird);
		gg.revalidate();
		gg.repaint();
		bc = new BoundCheck(gg, xLoc, yLoc);
		new Thread(bc).start();
	}

	public void moving() {
		speed = (int) (Math.random() * 15 + 10);
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
			bc.setEney(yLoc);
			lb_bird.repaint();
			gg.repaint();
			gg.revalidate();
		}
	}

}