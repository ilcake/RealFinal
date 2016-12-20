package server;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import datas.Data;
import vos.UserComment;
import vos.MovieSearchInfo;
import vos.User;

public class ServerThread implements Runnable {
	private Socket sk;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	public ArrayList<ObjectOutputStream> usersList;
	public ArrayList<User> theUsers;
	private boolean flag = true;
	private ServerDBwork mg;
	private ServerGui gui;
	private String userID = "미접속";
	private ServerReceiver sr;
	private User me;

	public String getUserID() {
		return userID;
	}

	public void setUserID(String userID) {
		this.userID = userID;
	}

	public ServerThread(Socket sk, ObjectOutputStream oos, ObjectInputStream ois,
			ArrayList<ObjectOutputStream> usersList, ArrayList<User> theUsers, ServerGui gui, ServerReceiver sr) {
		this.sk = sk;
		this.oos = oos;
		this.ois = ois;
		this.usersList = usersList;
		this.theUsers = theUsers;
		this.gui = gui;
		this.sr = sr;
		mg = new ServerDBwork(gui, this);
	}

	public void run() {

		while (flag) {
			try {
				Object[] obj = (Object[]) ois.readObject();
				int protocol = (Integer) obj[0];
				process(protocol, obj);

			} catch (Exception e) {
				logOut();
				flag = false;
			}

		}

	}

	public void process(int protocol, Object[] obj) {
		try {
			switch (protocol) {

			case Data.REGISTER:
				int regRe = mg.register((User) obj[1]);
				Object[] regResult = new Object[] { Data.REGISTER, regRe };
				oos.writeUnshared(regResult);
				break;

			case Data.LOGIN:
				me = mg.login((String) obj[1], (String) obj[2]);
				if (me != null) {
					theUsers.add(me);
				}
				Object[] loginResult = new Object[] { Data.LOGIN, me, gui.getMblist() };
				oos.writeUnshared(loginResult);
				gui.setUserList(theUsers);
				break;

			case Data.LOGOUT:
				break;

			case Data.CHATLOGIN:
				ArrayList<String> nicks = new ArrayList<>();

				for (User k : theUsers)
					nicks.add(k.getId());

				Object[] CLogresult = new Object[] { Data.CHATLOGIN, nicks };
				for (ObjectOutputStream oio : usersList) {
					oio.writeUnshared(CLogresult);
					oio.flush();
				}
				gui.setUserList(theUsers);

				break;

			case Data.CHATMESSAGE:
				String message = (String) obj[1];
				Object[] MeResult = new Object[] { Data.CHATMESSAGE, message };

				for (ObjectOutputStream oio : usersList) {
					oio.writeUnshared(MeResult);
					oio.flush();
				}
				break;

			case Data.ICONCHANGE:
				mg.changeIcon(me, (String) obj[2]);
				break;

			case Data.USERLIKE:
				mg.saveUserLike((String) obj[1], (MovieSearchInfo) obj[2]);
				break;

			case Data.GETUSERLIKE_BY_CD:
				int wht = mg.getUserLikebyCD((String) obj[1], (String) obj[2]);
				Object[] gbCD = new Object[] { Data.GETUSERLIKE_BY_CD, wht };
				oos.writeUnshared(gbCD);
				break;

			case Data.GETUSERLIKE_BY_ID:
				ArrayList<MovieSearchInfo> sdList = mg.getUserLikebyID((String) obj[1]);
				Object[] gBID = new Object[] { Data.GETUSERLIKE_BY_ID, sdList };
				oos.writeUnshared(gBID);
				break;

			case Data.WRITECOMMENT:
				mg.writeComment((UserComment) obj[1]);
				break;

			case Data.GETCOMMENT:
				ArrayList<UserComment> cmList = mg.getComment((String) obj[1]);
				Object[] gComm = new Object[] { Data.GETCOMMENT, cmList };
				oos.writeUnshared(gComm);
				break;

			case Data.GETAVGGRADE:
				double avgG = mg.getMovieAvgGrade((String) obj[1]);
				Object[] avgg = new Object[] { Data.GETAVGGRADE, avgG };
				oos.writeUnshared(avgg);
				break;

			case Data.GETMYCOMMENT:
				ArrayList<UserComment> usc = mg.getMyComment((String) obj[1]);
				Object[] gmcd = new Object[] { Data.GETMYCOMMENT, usc };
				oos.writeUnshared(gmcd);
				break;

			case Data.DELCOMMENT:
				mg.delComment((String) obj[1], (String) obj[2]);
				break;

			case Data.SEARCHAPI:
				ArrayList<MovieSearchInfo> scList = mg.searchAPI((int) obj[1], (String) obj[2]);
				Object[] scc = new Object[] { Data.SEARCHAPI, scList };
				oos.writeUnshared(scc);
				break;

			case Data.SEARCHYOUTUBE:
				String ytcd = mg.searchYouTube((String) obj[1]);
				Object[] utKey = new Object[] { Data.SEARCHYOUTUBE, ytcd };
				oos.writeUnshared(utKey);
				break;

			case Data.GETNUMS:
				ArrayList<Integer> nList = mg.getNums((String) obj[1]);
				Object[] nli = new Object[] { Data.GETNUMS, nList };
				oos.writeUnshared(nli);
				break;
				
			case Data.GETBESTMOVIE:
				UserComment ucM = mg.getBestM();
				Object[] gbm = new Object[] { Data.GETBESTMOVIE, ucM };
				oos.writeUnshared(gbm);
				break;

			case Data.GETBESTCOMMENT:
				UserComment ucC = mg.getBestC();
				Object[] gbc = new Object[] { Data.GETBESTCOMMENT, ucC };
				oos.writeUnshared(gbc);
				break;
				
			case Data.GETBESTLIKE:
				UserComment ucL = mg.getBestL();
				Object[] gbl = new Object[] { Data.GETBESTLIKE, ucL };
				oos.writeUnshared(gbl);
				break;


			}

			oos.flush();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void logOut() {
		System.out.println("로그아웃~");
		usersList.remove(oos);
		theUsers.remove(me);

		ArrayList<String> nicks = new ArrayList<>();
		for (User k : theUsers) {
			nicks.add(k.getId());
		}

		if (!userID.equals("미접속")) {
			Object[] logRes = new Object[] { Data.CHATLOGOUT, nicks, (userID + "회원이 퇴장하였습니다.") };
			try {
				for (ObjectOutputStream ooos : usersList) {
					ooos.writeUnshared(logRes);
				}
			} catch (Exception e) {

			}
		}
		gui.setMessage(userID + " 회원이 프로그램을 종료하였습니다.");
		gui.setUserCount(theUsers.size());
		gui.setUserList(theUsers);

	}

}
