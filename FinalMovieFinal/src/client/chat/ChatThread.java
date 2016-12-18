package client.chat;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import datas.Data;

public class ChatThread implements Runnable {
	private Socket sk;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;
	private boolean flag;
	private ArrayList<String> userlist;
	private ChatGUI gui;

	public ChatThread(Socket sk, ObjectOutputStream oos, ObjectInputStream ois, ChatGUI gui) {
		this.sk = sk;
		this.oos = oos;
		this.ois = ois;
		this.gui = gui;
		flag = true;
		userlist = new ArrayList<>();
	}

	@Override
	public void run() {
		while (flag) {
			try {
				Object[] obj = (Object[]) ois.readObject();
				int proto = (int) obj[0];

				switch (proto) {
				case Data.CHATLOGIN:
					System.out.println(gui.getId() + "//ct :  유저 리스트를 갱신합니다");
					userlist = (ArrayList<String>) obj[1];
					gui.setUserList(userlist);
					break;

				case Data.CHATLOGOUT:
					userlist = (ArrayList<String>) obj[1];
					break;

				case Data.CHATMESSAGE:
					gui.talk((String) obj[1]);
					break;
				}
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				flag = false;
			}
		}

	}

}
