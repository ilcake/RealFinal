package game.thread;

import game.Game;

public class MakingObjects extends Thread {
	private Game gg;

	public MakingObjects(Game gg) {
		this.gg = gg;
	}

	public void run() {
		new Thread(new FlyingBird(gg)).start();
		new Thread(new MovingRock(gg)).start();
		new Thread(new Enemy(gg)).start();
		new Thread(new MovingFlower(gg)).start();
		new Thread(new Clouds(gg)).start();
		new Thread(new LoadingWord(gg)).start();
	}

}
