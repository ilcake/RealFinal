package vos;

import java.io.Serializable;

public class MovieBoxInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4521787287364820813L;
	private String movieNm;
	private String movieCd;
	private String director;
	private String openDt;

	public MovieBoxInfo(String movieNm, String movieCd, String director, String openDt) {
		super();
		this.movieNm = movieNm;
		this.movieCd = movieCd;
		this.director = director;
		this.openDt = openDt;
	}

	public String getMovieNm() {
		return movieNm;
	}

	public void setMovieNm(String movieNm) {
		this.movieNm = movieNm;
	}

	public String getMovieCd() {
		return movieCd;
	}

	public void setMovieCd(String movieCd) {
		this.movieCd = movieCd;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public String getOpenDt() {
		return openDt;
	}

	public void setOpenDt(String openDt) {
		this.openDt = openDt;
	}

	@Override
	public String toString() {
		return "MovieBoxInfo [movieNm=" + movieNm + ", movieCd=" + movieCd + ", director=" + director + ", openDt="
				+ openDt + "]";
	}

}
