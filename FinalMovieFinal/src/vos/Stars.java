package vos;

import java.io.Serializable;

public class Stars implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6568978268259144107L;
	private double stars;

	public Stars(double stars) {
		this.stars = stars;
	}

	public double getStars() {
		return stars;
	}

	public void minus() {
		this.stars -= 1;
		System.out.println(stars);
	}

	public void plus() {
		this.stars += 1;
		System.out.println(stars);
	}

}
