package game.thread;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import game.Game;

public class MovingFlower extends Thread {
	private Game gg;
	public int xLoc;
	public int yLoc;
	private JLabel lb_rock;
	private int speed;
	private BoundCheck bc;

	public MovingFlower(Game gg) {
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

			makeRock();
			moving();
		}

	}

	public void makeRock() {
		xLoc = 610;
		lb_rock = new JLabel();
		yLoc = 225;
		lb_rock.setBounds(xLoc, yLoc, 50, 40);
		lb_rock.setIcon(new ImageIcon("img/game/flower.png"));
		gg.gmPanle.add(lb_rock);
		gg.revalidate();
		gg.repaint();
	}

	public void moving() {
		// speed = (int) (Math.random() * 20 + 5);
		// System.out.println("speed " + speed);
		while (xLoc > -100) {
			try {
				Thread.sleep(30);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			xLoc -= 10;
			lb_rock.setBounds(xLoc, 225, 50, 40);
			gg.repaint();
			gg.revalidate();
		}
	}

}
