package game.thread;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Game;

public class Enemy extends Thread {
	private Game gg;
	public int xLoc;
	public int yLoc;
	private JLabel lb_ene;
	private int speed;
	private BoundCheck bc;

	public Enemy(Game gg) {
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

			makeEnemy();
			moving();
		}

	}

	public void makeEnemy() {
		xLoc = 610;
		lb_ene = new JLabel();
		yLoc = 210;
		lb_ene.setBounds(xLoc, yLoc, 50, 50);
		lb_ene.setIcon(new ImageIcon("img/game/giphy.gif"));
		gg.gmPanle.add(lb_ene);
		gg.revalidate();
		gg.repaint();
		bc = new BoundCheck(gg, xLoc, yLoc);
		Thread bcth = new Thread(bc);
		bcth.run();

	}

	public void moving() {
		// speed = (int) (Math.random() * 20 + 5);
		// System.out.println("speed " + speed);
		while (xLoc > -60) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			xLoc -= 7;
			bc.setXlo(xLoc);
			lb_ene.setBounds(xLoc, yLoc, 50, 40);
			gg.repaint();
			gg.revalidate();
		}
	}

}
