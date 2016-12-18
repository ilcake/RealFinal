package client;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JButton;

public class ChangeIcon extends JFrame implements ActionListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2322190920946201472L;
	// private String id;
	private ClientGui cGUI;
	private ImageIcon p1, p2, p3, p4, p5, p6, p7, p8, p9;
	private JLabel pl1, pl2, pl3, pl4, pl5, pl6, pl7, pl8, pl9, now;
	private JButton edit;
	private String url1, url2, url3, url4, url5, url6, url7, url8, url9;
	private ArrayList<JRadioButton> rBtList;
	private ClientManager mg;

	public ChangeIcon(ClientGui cGUI, ClientManager mg) {

		this.cGUI = cGUI;
		this.mg = mg;

		setSize(600, 500);
		setTitle("Choose Your Character");
		getContentPane().setLayout(null);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int locWidth = (int) ((d.getWidth() - 600) / 2);
		int locHeight = (int) ((d.getHeight() - 500) / 2);
		setLocation(locWidth, locHeight);

		rBtList = new ArrayList<>();
		JRadioButton rBt1 = new JRadioButton("1");
		rBt1.setBounds(69, 116, 39, 23);
		getContentPane().add(rBt1);
		rBtList.add(rBt1);

		JRadioButton rBt2 = new JRadioButton("2");
		rBt2.setBounds(207, 116, 39, 23);
		getContentPane().add(rBt2);
		rBtList.add(rBt2);

		JRadioButton rBt3 = new JRadioButton("3");
		rBt3.setBounds(345, 116, 39, 23);
		getContentPane().add(rBt3);
		rBtList.add(rBt3);

		JRadioButton rBt4 = new JRadioButton("4");
		rBt4.setBounds(69, 263, 39, 23);
		getContentPane().add(rBt4);
		rBtList.add(rBt4);

		JRadioButton rBt5 = new JRadioButton("5");
		rBt5.setBounds(207, 263, 39, 23);
		getContentPane().add(rBt5);
		rBtList.add(rBt5);

		JRadioButton rBt6 = new JRadioButton("6");
		rBt6.setBounds(345, 263, 39, 23);
		getContentPane().add(rBt6);
		rBtList.add(rBt6);

		JRadioButton rBt7 = new JRadioButton("7");
		rBt7.setBounds(69, 419, 39, 23);
		getContentPane().add(rBt7);
		rBtList.add(rBt7);

		JRadioButton rBt8 = new JRadioButton("8");
		rBt8.setBounds(207, 419, 39, 23);
		getContentPane().add(rBt8);
		rBtList.add(rBt8);

		JRadioButton rBt9 = new JRadioButton("9");
		rBt9.setBounds(345, 419, 39, 23);
		getContentPane().add(rBt9);
		rBtList.add(rBt9);

		url1 = "img/p1.png";
		p1 = new ImageIcon(url1);
		pl1 = new JLabel("1");
		p1 = imgResizing(p1);
		pl1.setIcon(p1);
		pl1.setBounds(38, 10, 100, 100);
		getContentPane().add(pl1);

		url2 = "img/p2.png";
		p2 = new ImageIcon(url2);
		pl2 = new JLabel("2");
		p2 = imgResizing(p2);
		pl2.setIcon(p2);
		pl2.setBounds(176, 10, 100, 100);
		getContentPane().add(pl2);

		url3 = "img/p3.png";
		p3 = new ImageIcon(url3);
		pl3 = new JLabel("3");
		p3 = imgResizing(p3);
		pl3.setIcon(p3);
		pl3.setBounds(314, 10, 100, 100);
		getContentPane().add(pl3);

		url4 = "img/p4.png";
		p4 = new ImageIcon(url4);
		pl4 = new JLabel("4");
		p4 = imgResizing(p4);
		pl4.setIcon(p4);
		pl4.setBounds(38, 156, 100, 100);
		getContentPane().add(pl4);

		url5 = "img/p5.png";
		p5 = new ImageIcon(url5);
		pl5 = new JLabel("5");
		p5 = imgResizing(p5);
		pl5.setIcon(p5);
		pl5.setBounds(176, 156, 100, 100);
		getContentPane().add(pl5);

		url6 = "img/p6.png";
		p6 = new ImageIcon(url6);
		pl6 = new JLabel("6");
		p6 = imgResizing(p6);
		pl6.setIcon(p6);
		pl6.setBounds(314, 156, 100, 100);
		getContentPane().add(pl6);

		url7 = "img/p7.png";
		p7 = new ImageIcon(url7);
		pl7 = new JLabel("7");
		p7 = imgResizing(p7);
		pl7.setIcon(p7);
		pl7.setBounds(38, 313, 100, 100);
		getContentPane().add(pl7);

		url8 = "img/p8.png";
		p8 = new ImageIcon(url8);
		pl8 = new JLabel("8");
		p8 = imgResizing(p8);
		pl8.setIcon(p8);
		pl8.setBounds(176, 313, 100, 100);
		getContentPane().add(pl8);

		url9 = "img/p9.png";
		p9 = new ImageIcon(url9);
		pl9 = new JLabel("9");
		p9 = imgResizing(p9);
		pl9.setIcon(p9);
		pl9.setBounds(314, 313, 100, 100);
		getContentPane().add(pl9);

		now = new JLabel("now");
		ImageIcon nowIcon = new ImageIcon(cGUI.getPersonIcon());
		nowIcon = imgResizing(nowIcon);
		now.setIcon(nowIcon);
		now.setBounds(450, 167, 100, 100);
		getContentPane().add(now);

		edit = new JButton("수정");
		edit.setBounds(464, 277, 73, 23);
		getContentPane().add(edit);
		edit.addActionListener(this);

		ButtonGroup people = new ButtonGroup();
		for (JRadioButton rBt : rBtList) {
			people.add(rBt);
			rBt.addActionListener(this);
		}

		setVisible(true);
	}

	public void setNowImage(String url) {
		getContentPane().remove(now);
		ImageIcon nowImg = new ImageIcon(url);
		now = new JLabel() {
			/**
			 * 
			 */
			private static final long serialVersionUID = 5604981293276658300L;

			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(nowImg.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		now.setBounds(450, 167, 100, 100);
		getContentPane().add(now);
	}

	public ImageIcon imgResizing(ImageIcon imgicon) {
		Image image = imgicon.getImage();
		Image reSized = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		imgicon.setImage(reSized);
		return imgicon;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("1")) {
			now.setIcon(p1);
		} else if (e.getActionCommand().equals("2")) {
			now.setIcon(p2);
		} else if (e.getActionCommand().equals("3")) {
			now.setIcon(p3);
		} else if (e.getActionCommand().equals("4")) {
			now.setIcon(p4);
		} else if (e.getActionCommand().equals("5")) {
			now.setIcon(p5);
		} else if (e.getActionCommand().equals("6")) {
			now.setIcon(p6);
		} else if (e.getActionCommand().equals("7")) {
			now.setIcon(p7);
		} else if (e.getActionCommand().equals("8")) {
			now.setIcon(p8);
		} else if (e.getActionCommand().equals("9")) {
			now.setIcon(p9);
		} else if (e.getActionCommand().equals("수정")) {
			int check = JOptionPane.showConfirmDialog(null, "프로필을 수정합니다.");
			if (check == 0) {
				cGUI.setPersonIcon("" + now.getIcon());
				mg.setIcon("" + now.getIcon());
				this.dispose();
			} else if (check == 2 || check == -1) {
				this.setVisible(false);
			}
		}
	}
}
