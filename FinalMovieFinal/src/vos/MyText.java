package vos;

import javax.swing.JTextArea;

public class MyText extends JTextArea {
	/**
	 * 
	 */
	private static final long serialVersionUID = -8074996459766320443L;
	private UserComment comment;

	public MyText(UserComment comment) {
		super();
		this.comment = comment;
	}

	public UserComment getComment() {
		return comment;
	}

	public void setComment(UserComment comment) {
		this.comment = comment;
	}

}
