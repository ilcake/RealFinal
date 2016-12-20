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
		while (gg != null) {
			int time = (int) (Math.random() * 1900) + 200;
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
		lb_ene = new JLabel();
		xLoc = 610;
		yLoc = 200;
		lb_ene.setBounds(xLoc, yLoc, 50, 50);
		lb_ene.setIcon(new ImageIcon("img/game/giphy.gif"));
		gg.gmPanle.add(lb_ene);
		gg.revalidate();
		gg.repaint();
		bc = new BoundCheck(gg, xLoc, yLoc);
		new Thread(bc).start();

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
			xLoc -= 6;
			bc.setXlo(xLoc);
			bc.setEney(yLoc);
			lb_ene.setBounds(xLoc, yLoc, 50, 50);
			gg.repaint();
			gg.revalidate();
		}
	}

}
