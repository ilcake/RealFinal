package game.thread;

import game.Game;

public class BoundCheck extends Thread {
	private Game gg;
	private int xlo;
	private int ylo;
	private boolean flag;
	private int eney;

	public int getEney() {
		return eney;
	}

	public void setEney(int eney) {
		this.eney = eney;
	}

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
		while (gg != null) {
			checkBounds();
		}
	}

	public void checkBounds() {
		try {
			Thread.sleep(30);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int ma = gg.xLoc;
		int may = gg.getMyY();
		if (ma + 60 >= xlo && xlo + 10 > ma) {
			if (may + 128 >= eney + 20 && may < eney + 20) {
				// if (may < eney + 40 || may + 128 > eney - 10) {
				int now = gg.getHp() - 5;
				gg.setHp(now);
				gg.getProgressBar().setValue(now);
			}
		}
		if (gg.getHp() < 0) {
			flag = false;
		}

	}

}
