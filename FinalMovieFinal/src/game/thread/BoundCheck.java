package game.thread;

import game.Game;

public class BoundCheck extends Thread {
	private Game gg;
	private int xlo;
	private int ylo;
	private boolean flag;

	public int getXlo() {
		return xlo;
	}

	public void setXlo(int xlo) {
		this.xlo = xlo;
	}

	public int getYlo() {
		return ylo;
	}

	public void setYlo(int ylo) {
		this.ylo = ylo;
	}

	public BoundCheck(Game gg, int xlo, int ylo) {
		this.gg = gg;
		this.xlo = xlo;
		this.ylo = ylo;
	}

	@Override
	public void run() {
		flag = true;
		while (flag) {
			checkBounds();
		}
	}

	public void checkBounds() {
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ma = gg.xLoc;
		int may = gg.getMyY();
		if (ma + 85 >= xlo && xlo > ma) {
			if (ylo > 100) {
			}
		}

	}

}
