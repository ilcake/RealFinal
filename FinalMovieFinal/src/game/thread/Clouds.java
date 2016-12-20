package game.thread;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Game;

public class Clouds extends Thread {
	private Game gg;
	public int xLoc;
	public int yLoc;
	private JLabel lb_cloud;
	private int speed;
	private BoundCheck bc;

	public Clouds(Game gg) {
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

			makeCloud();
			moving();
		}

	}

	public void makeCloud() {
		xLoc = 610;
		lb_cloud = new JLabel();
		yLoc = 50;
		lb_cloud.setBounds(xLoc, yLoc, 100, 70);
		lb_cloud.setIcon(new ImageIcon("img/game/cloud.png"));
		gg.gmPanle.add(lb_cloud);
		gg.revalidate();
		gg.repaint();
	}

	public void moving() {
		while (xLoc > -120) {
			try {
				Thread.sleep(40);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			xLoc -= 8;
			lb_cloud.setBounds(xLoc, yLoc, 100, 70);
			lb_cloud.repaint();
			gg.repaint();
			gg.revalidate();
		}
	}

}