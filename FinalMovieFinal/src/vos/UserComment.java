package vos;

import java.io.Serializable;

public class UserComment implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -5357305326865145002L;
	private String userID;
	private String userText;
	private Double grade;
	private String movieCD;
	private String userPic;
	private String thumb;
	private String title;

	public UserComment(String userID, String userText, Double grade, String movieCD, String userPic, String thumb,
			String title) {
		super();
		this.userID = userID;
		this.userText = userText;
		this.grade = grade;
		this.movieCD = movieCD;
		this.userPic = userPic;
		this.thumb = thumb;
		this.title = title;
	}

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public String getUserText() {
		return userText;
	}

	public void setUserText(String userText) {
		this.userText = userText;
	}

	public Double getGrade() {
		return grade;
	}

	public void setGrade(Double grade) {
		this.grade = grade;
	}

	public String getMovieCD() {
		return movieCD;
	}

	public void setMovieCD(String movieCD) {
		this.movieCD = movieCD;
	}

	public String getUserPic() {
		return userPic;
	}

	public void setUserPic(String userPic) {
		this.userPic = userPic;
	}

	public String getThumb() {
		return thumb;
	}

	public void setThumb(String thumb) {
		this.thumb = thumb;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "UserComment [userID=" + userID + ", userText=" + userText + ", grade=" + grade + ", movieCD=" + movieCD
				+ ", userPic=" + userPic + ", thumb=" + thumb + ", title=" + title + "]";
	}

}
