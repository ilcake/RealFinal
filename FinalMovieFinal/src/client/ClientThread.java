package client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import client.chat.ChatGUI;
import datas.Data;
import vos.MovieBoxInfo;
import vos.MovieSearchInfo;
import vos.User;
import vos.UserComment;

public class ClientThread extends Thread {
	private ObjectInputStream ois;
	private ClientGui gui;
	private ClientManager cm;
	private ChatGUI cgui;
	private boolean flag;

	public ClientThread(ObjectInputStream ois, ClientGui gui, ClientManager cm, ChatGUI cgui) {
		this.ois = ois;
		this.gui = gui;
		this.cm = cm;
		flag = true;
	}

	public ChatGUI getCgui() {
		return cgui;
	}

	public void setCgui(ChatGUI cgui) {
		this.cgui = cgui;
	}

	public void run() {

		while (flag) {
			try {
				Object[] obj = (Object[]) ois.readObject();
				int proto = (int) obj[0];
				action(proto, obj);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}

		}

	}

	@SuppressWarnings("unchecked")
	private void action(int proto, Object[] obj) {
		switch (proto) {

		case Data.REGISTER:
			gui.registerReaction((int) obj[1]);
			break;

		case Data.LOGIN:
			User me = (User) obj[1];
			gui.loginReaction(me);
			gui.setMovieBoxInfo((ArrayList<MovieBoxInfo>) obj[2]);
			cm.setMe(me);
			gui.setMe(me);
			break;

		case Data.CHATLOGIN:
			ArrayList<String> usersdd = (ArrayList<String>) obj[1];
			cgui.list.setListData(usersdd.toArray());
			break;

		case Data.CHATMESSAGE:
			cgui.talk((String) obj[1]);
			break;

		case Data.CHATLOGOUT:
			cgui.setUserList((ArrayList<String>) obj[1]);
			cgui.talk((String) obj[2]);
			break;

		case Data.GETUSERLIKE_BY_CD:
			gui.reactionIsItLike((int) obj[1]);
			break;

		case Data.GETUSERLIKE_BY_ID:
			gui.getLikeByIdReaction((ArrayList<MovieSearchInfo>) obj[1]);
			break;

		case Data.GETCOMMENT:
			gui.getCommentReaction((ArrayList<UserComment>) obj[1]);
			break;

		case Data.GETAVGGRADE:
			gui.reActionAvgGrade((double) obj[1]);
			break;

		case Data.GETMYCOMMENT:
			gui.getMyCommentReaction((ArrayList<UserComment>) obj[1]);
			break;

		case Data.SEARCHAPI:
			gui.setScList((ArrayList<MovieSearchInfo>) obj[1]);
			gui.setSearchTable();
			break;

		case Data.SEARCHYOUTUBE:
			gui.searchYoutubeReaction((String) obj[1]);
			break;

		case Data.GETNUMS:
			gui.setNumsReaction((ArrayList<Integer>) obj[1]);
			break;
			
		case Data.GETBESTMOVIE:
			gui.setBestM((UserComment) obj[1]);
			break;
			
		case Data.GETBESTCOMMENT:
			gui.setBestC((UserComment) obj[1]);
			break;
			
		case Data.GETBESTLIKE:
			gui.setBestL((UserComment) obj[1]);
			break;
		}

	}

}
