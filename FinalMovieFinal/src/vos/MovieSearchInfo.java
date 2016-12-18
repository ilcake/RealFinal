package vos;

import java.io.Serializable;

public class MovieSearchInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8257484628510956339L;
	private String mvCode;
	private String mvTitle;
	private String mvGenre;
	private String mvDirector;
	private String mvActor;
	private String mvThumb;
	private String mvStory;
	private String mvTm;

	public MovieSearchInfo(String mvCode, String mvTitle, String mvGenre, String mvDirector, String mvActor,
			String mvThumb, String mvStory, String mvTm) {
		super();
		this.mvCode = mvCode;
		this.mvTitle = mvTitle;
		this.mvGenre = mvGenre;
		this.mvDirector = mvDirector;
		this.mvActor = mvActor;
		this.mvThumb = mvThumb;
		this.mvStory = mvStory;
		this.mvTm = mvTm;
	}

	public String getMvCode() {
		return mvCode;
	}

	public void setMvCode(String mvCode) {
		this.mvCode = mvCode;
	}

	public String getMvTitle() {
		return mvTitle;
	}

	public void setMvTitle(String mvTitle) {
		this.mvTitle = mvTitle;
	}

	public String getMvGenre() {
		return mvGenre;
	}

	public void setMvGenre(String mvGenre) {
		this.mvGenre = mvGenre;
	}

	public String getMvDirector() {
		return mvDirector;
	}

	public void setMvDirector(String mvDirector) {
		this.mvDirector = mvDirector;
	}

	public String getMvActor() {
		return mvActor;
	}

	public void setMvActor(String mvActor) {
		this.mvActor = mvActor;
	}

	public String getMvThumb() {
		return mvThumb;
	}

	public void setMvThumb(String mvThumb) {
		this.mvThumb = mvThumb;
	}

	public String getMvStory() {
		return mvStory;
	}

	public void setMvStory(String mvStory) {
		this.mvStory = mvStory;
	}

	public String getMvTm() {
		return mvTm;
	}

	public void setMvTm(String mvTm) {
		this.mvTm = mvTm;
	}

	@Override
	public String toString() {
		return "MovieSearchInfo [mvCode=" + mvCode + ", mvTitle=" + mvTitle + ", mvGenre=" + mvGenre + ", mvDirector="
				+ mvDirector + ", mvActor=" + mvActor + ", mvThumb=" + mvThumb + ", mvStory=" + mvStory + ", mvTm="
				+ mvTm + "]";
	}

}
