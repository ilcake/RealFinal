package vos;

import java.io.Serializable;

public class User implements Serializable {
	private String id;
	private String pw;
	private String mail;
	private String phN;
	private String pic;

	public User(String id, String pw, String mail, String phN, String pic) {
		super();
		this.id = id;
		this.pw = pw;
		this.mail = mail;
		this.phN = phN;
		this.pic = pic;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPw() {
		return pw;
	}

	public void setPw(String pw) {
		this.pw = pw;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public String getPhN() {
		return phN;
	}

	public void setPhN(String phN) {
		this.phN = phN;
	}

	public String getPic() {
		return pic;
	}

	public void setPic(String pic) {
		this.pic = pic;
	}

	@Override
	public String toString() {
		return "사용자 정보 [ 아이디 : " + id + ", 이메일 : " + mail + ", 전화번호 : " + phN + "]";
	}

}
