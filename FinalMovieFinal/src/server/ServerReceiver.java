package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import vos.User;

public class ServerReceiver {
	private ServerSocket ss;
	private boolean flag;
	public static ArrayList<ObjectOutputStream> usersList = new ArrayList<ObjectOutputStream>();
	public static ArrayList<ObjectOutputStream> chatusersList = new ArrayList<ObjectOutputStream>();
	public static ArrayList<String> userNicks = new ArrayList<String>();
	public static ArrayList<User> theUsers = new ArrayList<User>();
	private ServerGui gui;

	public ServerReceiver(ServerGui gui) {
		flag = true;
		this.gui = gui;

		serverOn();
		while (flag) {
			connection();
		}
	}

	public void serverOn() {
		try {
			ss = new ServerSocket(17771);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void connection() {
		try {
			Socket sk = ss.accept();
			ObjectOutputStream oos = new ObjectOutputStream(sk.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(sk.getInputStream());

			usersList.add(oos);
			gui.setUserCount(usersList.size());
			gui.setMessage(sk.getInetAddress() + "/ 접속하였습니다.");
			ServerThread sth = new ServerThread(sk, oos, ois, usersList, theUsers, gui, this);
			Thread th = new Thread(sth);
			th.start();

		} catch (Exception e) {
			e.printStackTrace();
			flag = false;
		}
	}

}
