package client;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Desktop;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Vector;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import javax.swing.UIManager.LookAndFeelInfo;
import javax.swing.border.LineBorder;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import client.chat.ChatGUI;
import datas.Data;
import datas.User;
import game.Game;
import vos.UserComment;
import vos.MovieBoxInfo;
import vos.MovieSearchInfo;
import vos.MyText;
import vos.PnComment;
import vos.PnLike;
import vos.PnUserComment;
import vos.Stars;

import javax.swing.ScrollPaneConstants;
import java.awt.SystemColor;

public class ClientGui extends JFrame { //
	////
	private static final int fwidth = 900;
	private static final int fheight = 540;
	private JPanel pnLogin, pnMain, pnMovie;
	private JPanel lg2, lg1, lg2_1, lg2_2;
	private JPanel mm1_1, mm1_2, mm1_3, mm1_4, mv2_1, mv2_2, mv1, panel_6, mv2_panel, mn1, mn2;
	private JPanel pn_search, pn_info;
	private JTextField tf_id;
	private JPasswordField tf_pw;
	private JButton bt_Login;
	private JLabel goRegi;
	private CardLayout mainCard, lg2Card, mv2Card;
	private JLabel lblNewLabel_2;
	private JTextField rg_id;
	private JLabel label, label_1, label_2, lb_mvIcon, lb_mvTitle, lb_miG;
	private JTextField rg_ph, rg_ma;
	private JButton bt_rgCancel, bt_rgReg;
	private JPasswordField rg_pw;
	private ClientManager mg;
	private ClientGui myGui;
	private String whoAmI;
	private Container mainBOARD;
	private JTable mBoxTable;
	private ArrayList<MovieBoxInfo> dblist;
	private ArrayList<MovieSearchInfo> scList;
	private JScrollPane tableView;
	private JTextField mn_search;
	private JButton bt_search;
	private JComboBox<String> cb;
	private JTable tb_search;
	private JScrollPane sp_search;
	private JTextArea ta_mvStory;
	private JScrollPane mv1_jsp;
	private JButton bt_mv2Return;
	private JLabel bt_mv2Write;
	private CardLayout mn1Card;
	private JButton bt_mm1_2Return;
	private JLabel lb_miD;
	private JPanel pn_label;
	private JLabel lb_miT, lb_miA, lb_mvDirector, lb_mvGen, lb_mvShowT, lb_mvActor, lb_img2;
	private ImageIcon noImg;
	private ChatGUI chat;
	private JButton bt_chat;
	private User me;

	private JButton likeIcon;
	private JButton commentIcon;
	private JLabel pIcon;
	private JLabel searchRBack;
	private ChangeIcon changeIconGUI;
	private ImageIcon person;
	private JTextArea ta_comment;
	private JButton bt_mv2_2Return;
	private JButton bt_mv2_2Sub;
	private JLabel lb_mv2_pic;
	private JLabel lb_grade;
	private JPanel mv2;
	private String sMovieCD;
	private JLabel lblNewLabel_5;
	private JPanel pn_UserLike;
	private JButton bt_mm1_3Return;
	private JButton bt_mm1_4Return;
	private JPanel pn_UserComment;
	private JLabel bt_mv2Like;
	private JLabel lb_isLike;
	private MovieSearchInfo msi;

	private JLabel lb_mv2_stars;
	private JLabel lb_mv2_minus;
	private JLabel lb_mv2_plus;
	private Stars myStar;
	private Dimension mv2Size, ucSize;
	private JScrollPane jsp_board;
	private MouseAdapter ma;
	private JScrollPane jsp_UserLike;
	private JScrollPane jsp;
	private JScrollPane jsp_comm;
	private JButton bt_trailer;
	private ImageIcon writeBt, modifyBt, deleteBt;
	private ImageIcon likeg, cancelBt, movieLikeBt, disLike, loading;
	private JButton bt_deleteCom;
	private JLabel lb_commentNum;
	private JLabel lb_likeNum;
	private JPanel pnLoading;
	private JLabel lb_loading;
	private Game gm;

	private JLabel thumb_bestM, thumb_bestC, thumb_bestL; // 추가1

	public ArrayList<MovieSearchInfo> getScList() {
		return scList;
	}

	public void setScList(ArrayList<MovieSearchInfo> scList) {
		this.scList = scList;
	}

	public ClientGui() {////
		myGui = this;
		setImgicons();

		try {
			for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (Exception e) {
			// If Nimbus is not available, you can set the GUI to another look
			// and feel.
			try { //
				UIManager.setLookAndFeel("ch.randelshofer.quaqua.QuaquaLookAndFeel15");
				UIManager.setLookAndFeel("com.seaglasslookandfeel.SeaGlassLookAndFeel");
			} catch (Exception e1) {
				System.out.println("룩앤필 에러!");
			}
		} //

		mainBOARD = getContentPane();
		setTitle("MovieLovers");
		setSize(fwidth, fheight);
		mainCard = new CardLayout(0, 0);
		mainBOARD.setLayout(mainCard);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		setIconImage(Toolkit.getDefaultToolkit().getImage("img/mainIcon.png"));// 아이콘

		pnLogin = new JPanel();
		pnLogin.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainBOARD.add(pnLogin, "pnLogin");
		pnLogin.setLayout(new GridLayout(1, 2, 0, 0));

		ImageIcon img = new ImageIcon("img/mainL.jpg");
		lg1 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(img.getImage(), 0, 0, null); // 이미지 원래사이즈로 넣기
															// Dimension d
				Dimension d = getSize();
				g.drawImage(img.getImage(), 0, 0, d.width, d.height, null); // 컴포넌트사이즈에맞게
			}
		};

		pnLogin.add(lg1);
		lg1.setLayout(null);

		lg2 = new JPanel();
		pnLogin.add(lg2);
		lg2Card = new CardLayout(0, 0);
		lg2.setLayout(lg2Card);

		ImageIcon mainR = new ImageIcon("img/mainR.jpg");
		lg2_1 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(mainR.getImage(), 0, 0, null);
				Dimension d = getSize();
				g.drawImage(mainR.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		lg2.add(lg2_1, "lg2_1");
		lg2_1.setLayout(null);

		JLabel lblNewLabel = new JLabel("ID");
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setBounds(12, 448, 62, 18);
		lg2_1.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("Pw");
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setBounds(174, 448, 62, 18);
		lg2_1.add(lblNewLabel_1);

		tf_id = new JTextField();
		tf_id.setBounds(39, 442, 116, 27);
		lg2_1.add(tf_id);
		tf_id.setColumns(10);

		tf_pw = new JPasswordField();
		tf_pw.setBounds(201, 442, 116, 27);
		tf_pw.setEchoChar('*');
		lg2_1.add(tf_pw);

		goRegi = new JLabel("Join");
		goRegi.setForeground(Color.WHITE);
		goRegi.setBounds(365, 417, 36, 18);
		lg2_1.add(goRegi);

		bt_Login = new JButton("LogIn");
		bt_Login.setBounds(342, 440, 73, 27);
		lg2_1.add(bt_Login);

		lg2_2 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(mainR.getImage(), 0, 0, null);
				Dimension d = getSize();
				g.drawImage(mainR.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		lg2.add(lg2_2, "lg2_2");
		lg2_2.setLayout(null);

		lblNewLabel_2 = new JLabel("\uC544\uC774\uB514");
		lblNewLabel_2.setForeground(Color.WHITE);
		lblNewLabel_2.setBounds(122, 118, 62, 18);
		lg2_2.add(lblNewLabel_2);

		rg_id = new JTextField();
		rg_id.setBounds(224, 115, 116, 24);
		lg2_2.add(rg_id);
		rg_id.setColumns(10);

		rg_pw = new JPasswordField();
		rg_pw.setBounds(224, 161, 116, 24);
		rg_pw.setEchoChar('*');
		lg2_2.add(rg_pw);

		rg_ma = new JTextField();
		rg_ma.setColumns(10);
		rg_ma.setBounds(224, 211, 116, 24);
		lg2_2.add(rg_ma);

		rg_ph = new JTextField();
		rg_ph.setColumns(10);
		rg_ph.setBounds(224, 257, 116, 24);
		lg2_2.add(rg_ph);

		label = new JLabel("\uBE44\uBC00\uBC88\uD638");
		label.setForeground(Color.WHITE);
		label.setBounds(122, 164, 62, 18);
		lg2_2.add(label);

		label_1 = new JLabel("\uC804\uD654\uBC88\uD638");
		label_1.setForeground(Color.WHITE);
		label_1.setBounds(122, 260, 62, 18);
		lg2_2.add(label_1);

		label_2 = new JLabel("\uC774\uBA54\uC77C");
		label_2.setForeground(Color.WHITE);
		label_2.setBounds(122, 214, 62, 18);
		lg2_2.add(label_2);

		bt_rgCancel = new JButton("CANCEL");
		bt_rgCancel.setBounds(110, 331, 107, 27);
		lg2_2.add(bt_rgCancel);

		bt_rgReg = new JButton("REGISTER");
		bt_rgReg.setBounds(243, 331, 107, 27);
		lg2_2.add(bt_rgReg);

		pnMain = new JPanel();
		pnMain.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainBOARD.add(pnMain, "pnMain");
		pnMain.setLayout(new GridLayout(1, 2, 0, 0));

		mn1 = new JPanel();
		// mn1.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnMain.add(mn1);
		mn1Card = new CardLayout(0, 0);
		mn1.setLayout(mn1Card);

		ImageIcon boxOfficeL = new ImageIcon("./img/boxOfficeL.png");
		mm1_1 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(boxOfficeL.getImage(), 0, 0, this);
				Dimension d = getSize();
				g.drawImage(boxOfficeL.getImage(), 0, 0, d.width, d.height, this);
			}
		};
		mn1.add(mm1_1, "mm1_1");
		mm1_1.setLayout(null);

		tableView = new JScrollPane();
		tableView.setBounds(43, 155, 360, 190); /// 바꿈 + img추가!
		mm1_1.add(tableView);

		mBoxTable = new JTable();
		tableView.setViewportView(mBoxTable);

		// 추가2 + img들!
		ImageIcon bestM = new ImageIcon("img/bestM.png");
		JLabel lb_bestM = new JLabel("bestM");
		lb_bestM.setBounds(33, 355, 40, 40);
		Image bestMImg = bestM.getImage();
		bestMImg = bestMImg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		bestM.setImage(bestMImg);
		lb_bestM.setIcon(bestM);
		mm1_1.add(lb_bestM);

		ImageIcon bestC = new ImageIcon("img/bestC.png");
		JLabel lb_bestC = new JLabel("bestC");
		lb_bestC.setBounds(167, 355, 40, 40);
		Image bestCImg = bestC.getImage();
		bestCImg = bestCImg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		bestC.setImage(bestCImg);
		lb_bestC.setIcon(bestC);
		mm1_1.add(lb_bestC);

		ImageIcon bestL = new ImageIcon("img/bestL.png");
		JLabel lb_bestL = new JLabel("bestL");
		lb_bestL.setBounds(300, 355, 40, 40);
		Image bestLImg = bestL.getImage();
		bestLImg = bestLImg.getScaledInstance(40, 40, Image.SCALE_SMOOTH);
		bestL.setImage(bestLImg);
		lb_bestL.setIcon(bestL);
		mm1_1.add(lb_bestL);

		thumb_bestM = new JLabel();
		thumb_bestM.setBounds(56, 371, 70, 84);
		mm1_1.add(thumb_bestM);

		thumb_bestC = new JLabel();
		thumb_bestC.setBounds(190, 371, 70, 84);
		mm1_1.add(thumb_bestC);

		thumb_bestL = new JLabel();
		thumb_bestL.setBounds(324, 371, 70, 84);
		mm1_1.add(thumb_bestL);
		/*
		 * JPanel panel = new JPanel(); panel.setBorder(new LineBorder(new
		 * Color(0, 0, 0))); panel.setBounds(44, 374, 360, 78);
		 * mm1_1.add(panel); panel.setLayout(new GridLayout(0, 1, 0, 0));
		 * 
		 * JLabel lb_img1 = new JLabel(""); lb_img1.setIcon(new
		 * ImageIcon("./img/boLogo.png")); panel.add(lb_img1);
		 * lb_img1.setHorizontalAlignment(SwingConstants.CENTER);
		 * 
		 * JPanel panel_1 = new JPanel(); panel_1.setBorder(new LineBorder(new
		 * Color(0, 0, 0))); panel_1.setBounds(44, 27, 360, 78);
		 * mm1_1.add(panel_1); panel_1.setLayout(new GridLayout(0, 1, 0, 0));
		 * 
		 * lb_img2 = new JLabel(""); lb_img2.setIcon(new
		 * ImageIcon("./img/boLogo.png"));
		 * lb_img2.setHorizontalAlignment(SwingConstants.CENTER);
		 * panel_1.add(lb_img2);
		 */

		// ImageIcon searchL = new ImageIcon("/img/searchL.jpg");
		// /////////////////////
		// mm1_2 = new JPanel() {
		// public void paintComponent(Graphics g) {
		// g.drawImage(searchL.getImage(), 0, 0, null);
		// Dimension d = getSize();
		// g.drawImage(searchL.getImage(), 0, 0, d.width, d.height, null);
		// }
		// };
		////
		ImageIcon SL = new ImageIcon("./img/searchL.png"); /////////////////////
		mm1_2 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(SL.getImage(), 0, 0, this);
				Dimension d = getSize();
				g.drawImage(SL.getImage(), 0, 0, d.width, d.height, this);
			}
		};

		mn1.add(mm1_2, "mm1_2");
		mm1_2.setLayout(null);

		pn_search = new JPanel();
		pn_search.setBackground(new Color(255, 255, 255));
		pn_search.setBounds(30, 140, 381, 228);
		mm1_2.add(pn_search);
		pn_search.setLayout(new GridLayout(1, 0, 0, 0));

		sp_search = new JScrollPane();
		sp_search.setBackground(new Color(255, 255, 255));
		pn_search.add(sp_search);

		tb_search = new JTable();
		sp_search.setViewportView(tb_search);

		// JPanel panel_5 = new JPanel();
		// panel_5.setBounds(30, 299, 381, 77);
		// mm1_2.add(panel_5);

		ImageIcon backBt = new ImageIcon("img/backBt.png");
		bt_mm1_2Return = new JButton("뒤로가기") {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(backBt.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		bt_mm1_2Return.setBounds(30, 424, 103, 27);
		mm1_2.add(bt_mm1_2Return);

		ImageIcon likeItL = new ImageIcon("img/likeItL.png");
		mm1_3 = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(likeItL.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		mn1.add(mm1_3, "mm1_3");
		mm1_3.setLayout(null);

		jsp_UserLike = new JScrollPane();
		jsp_UserLike.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
		jsp_UserLike.setBounds(62, 157, 323, 240);
		mm1_3.add(jsp_UserLike);

		pn_UserLike = new JPanel();
		jsp_UserLike.setViewportView(pn_UserLike);
		pn_UserLike.setBackground(Color.WHITE);

		bt_mm1_3Return = new JButton("뒤로가기") {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(backBt.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		bt_mm1_3Return.setBounds(30, 424, 103, 27);
		mm1_3.add(bt_mm1_3Return);

		// lblNewLabel_3 = new JLabel("좋아요");
		// lblNewLabel_3.setBounds(195, 80, 62, 18);
		// mm1_3.add(lblNewLabel_3);

		ImageIcon commentL = new ImageIcon("img/commentL.png");
		mm1_4 = new JPanel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(commentL.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		mn1.add(mm1_4, "mm1_4");
		mm1_4.setLayout(null);

		jsp = new JScrollPane();
		jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp.setBounds(30, 141, 385, 300);
		mm1_4.add(jsp);

		pn_UserComment = new JPanel();
		ucSize = new Dimension(370, 280);
		pn_UserComment.setPreferredSize(ucSize);
		jsp.setViewportView(pn_UserComment);
		pn_UserComment.setBackground(Color.WHITE);

		bt_mm1_4Return = new JButton("뒤로가기") {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(backBt.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		bt_mm1_4Return.setBounds(30, 450, 103, 27);
		mm1_4.add(bt_mm1_4Return);

		// lblNewLabel_6 = new JLabel("코멘트");
		// lblNewLabel_6.setBounds(195, 31, 62, 18);
		// mm1_4.add(lblNewLabel_6);

		ImageIcon boxOfficeR = new ImageIcon("./img/boxOfficeR.png");
		mn2 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(boxOfficeR.getImage(), 0, 0, this);
				Dimension d = getSize();
				g.drawImage(boxOfficeR.getImage(), 0, 0, d.width, d.height, this);
			}
		}; // 여기 mn2에 레이블추가해서 그림 넣기
		mn2.setLayout(null);

		ImageIcon sRBack = new ImageIcon("img/searchR.png");

		cb = new JComboBox<String>();
		cb.setBounds(85, 185, 99, 26);
		mn2.add(cb);
		cb.addItem("=====");
		cb.addItem("제목검색");
		cb.addItem("감독검색");

		mn_search = new JTextField();
		mn_search.setBounds(200, 185, 150, 24);
		mn2.add(mn_search);
		mn_search.setColumns(10);

		ImageIcon searchBt = new ImageIcon("img/searchBt.png");

		lb_likeNum = new JLabel("0");
		lb_likeNum.setForeground(SystemColor.activeCaption);
		lb_likeNum.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_likeNum.setFont(new Font("함초롬돋움", Font.BOLD, 26));
		lb_likeNum.setBounds(143, 385, 58, 50);
		mn2.add(lb_likeNum);

		lb_commentNum = new JLabel("0");
		lb_commentNum.setForeground(SystemColor.activeCaption);
		lb_commentNum.setHorizontalAlignment(SwingConstants.RIGHT);
		lb_commentNum.setFont(new Font("함초롬돋움", Font.BOLD, 26));
		lb_commentNum.setBounds(275, 385, 58, 48);
		mn2.add(lb_commentNum);
		bt_search = new JButton("SEARCH") {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(searchBt.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		bt_search.setFont(new Font("굴림", Font.BOLD, 15));
		bt_search.setBounds(243, 260, 107, 31);
		mn2.add(bt_search);

		ImageIcon chatBt = new ImageIcon("img/chatBt.png");
		bt_chat = new JButton("CHAT") {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(chatBt.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		bt_chat.setFont(new Font("굴림", Font.BOLD, 15));
		bt_chat.setBounds(85, 260, 107, 31);
		mn2.add(bt_chat);

		ImageIcon likeBt = new ImageIcon("img/likeIcon.png");
		likeIcon = new JButton() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(likeBt.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		likeIcon.setBounds(85, 360, 75, 75);
		mn2.add(likeIcon);

		ImageIcon commentBt = new ImageIcon("img/commentIcon.png");
		commentIcon = new JButton() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(commentBt.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		commentIcon.setBounds(220, 360, 75, 75);
		mn2.add(commentIcon);

		// person = new ImageIcon("img/p1.png"); // 여기서_로그인된_사용자로_프로필_바꿔주기
		pIcon = new JLabel();
		// Image image = person.getImage();
		// Image reSized = image.getScaledInstance(100, 100,
		// Image.SCALE_SMOOTH);
		// person.setImage(reSized);
		// pIcon.setIcon(person);
		pIcon.setBounds(170, 40, 100, 100);
		mn2.add(pIcon);

		// mn2.setBorder(new LineBorder(new Color(0, 0, 0))); //볼더할까말까
		pnMain.add(mn2);
		searchRBack = new JLabel() {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(sRBack.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		searchRBack.setBounds(0, 0, 446, 510);
		searchRBack.setVisible(false);
		mn2.add(searchRBack);

		pnMovie = new JPanel();
		pnMovie.setBorder(new LineBorder(new Color(0, 0, 0)));
		mainBOARD.add(pnMovie, "pnMovie");
		pnMovie.setLayout(new GridLayout(1, 2, 0, 0));

		ImageIcon infoL = new ImageIcon("img/infoL.png");
		mv1 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(infoL.getImage(), 0, 0, null);
				Dimension d = getSize();
				g.drawImage(infoL.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		mv1.setBorder(new LineBorder(new Color(0, 0, 0)));
		pnMovie.add(mv1);
		mv1.setLayout(null);

		lb_isLike = new JLabel("");
		lb_isLike.setHorizontalAlignment(SwingConstants.CENTER);
		lb_isLike.setIcon(new ImageIcon("C:\\Users\\kita\\Downloads\\bmo.gif"));
		lb_isLike.setBounds(162, 234, 50, 50);
		mv1.add(lb_isLike);

		lb_mvIcon = new JLabel("", new ImageIcon("C:\\Users\\kita\\Downloads\\bmo.gif"), SwingConstants.CENTER);
		lb_mvIcon.setSize(new Dimension(170, 215));
		lb_mvIcon.setBounds(31, 49, 181, 235);
		mv1.add(lb_mvIcon);

		lb_mvTitle = new JLabel();
		lb_mvTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lb_mvTitle.setFont(new Font("Dialog", Font.PLAIN, 21));
		lb_mvTitle.setBounds(236, 49, 181, 67);
		mv1.add(lb_mvTitle);

		mv1_jsp = new JScrollPane();
		mv1_jsp.setBounds(31, 307, 386, 102);
		mv1.add(mv1_jsp);

		ta_mvStory = new JTextArea();
		mv1_jsp.setViewportView(ta_mvStory);
		ta_mvStory.setLineWrap(true);
		ta_mvStory.setEditable(false);

		pn_label = new JPanel();
		pn_label.setBounds(237, 164, 64, 120);
		mv1.add(pn_label);
		pn_label.setLayout(new GridLayout(0, 1, 0, 0));

		lb_miD = new JLabel("감독");
		pn_label.add(lb_miD);

		lb_miG = new JLabel("장르");
		pn_label.add(lb_miG);

		lb_miT = new JLabel("상영시간");
		pn_label.add(lb_miT);

		lb_miA = new JLabel("주연배우");
		pn_label.add(lb_miA);

		pn_info = new JPanel();
		pn_info.setBounds(304, 164, 113, 120);
		mv1.add(pn_info);
		pn_info.setLayout(new GridLayout(0, 1, 0, 0));

		lb_mvDirector = new JLabel("감독");
		lb_mvDirector.setHorizontalAlignment(SwingConstants.CENTER);
		pn_info.add(lb_mvDirector);

		lb_mvGen = new JLabel("장르");
		lb_mvGen.setHorizontalAlignment(SwingConstants.CENTER);
		pn_info.add(lb_mvGen);

		lb_mvShowT = new JLabel("상영시간");
		lb_mvShowT.setHorizontalAlignment(SwingConstants.CENTER);
		pn_info.add(lb_mvShowT);

		lb_mvActor = new JLabel("주연배우");
		lb_mvActor.setHorizontalAlignment(SwingConstants.CENTER);
		pn_info.add(lb_mvActor);

		JPanel pn_grade = new JPanel();
		pn_grade.setBounds(236, 120, 181, 40);
		mv1.add(pn_grade);
		pn_grade.setLayout(null);

		JLabel lblNewLabel_4 = new JLabel("별점:");
		lblNewLabel_4.setBounds(12, 11, 38, 16);
		pn_grade.add(lblNewLabel_4);

		lb_grade = new JLabel("별L");
		lb_grade.setBounds(54, 7, 115, 25);
		pn_grade.add(lb_grade);

		bt_trailer = new JButton("트레일러");
		bt_trailer.setBounds(31, 435, 107, 27);
		mv1.add(bt_trailer);

		JLabel lblNewLabel_3 = new JLabel("@Copywrite by Park, Choe  ");
		lblNewLabel_3.setFont(new Font("굴림", Font.PLAIN, 12));
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNewLabel_3.setBounds(182, 435, 235, 27);
		mv1.add(lblNewLabel_3);

		mv2 = new JPanel();
		pnMovie.add(mv2);
		mv2.setBorder(new LineBorder(new Color(0, 0, 0)));
		mv2Card = new CardLayout(0, 0);
		mv2.setLayout(mv2Card);

		ImageIcon infoR = new ImageIcon("img/infoR.png");
		mv2_1 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(infoR.getImage(), 0, 0, null);
				Dimension d = getSize();
				g.drawImage(infoR.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		mv2.add(mv2_1, "mv2_1");
		mv2_1.setLayout(null);
		mv2Size = new Dimension(370, 350);
		jsp_board = new JScrollPane();
		jsp_board.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
		jsp_board.setBounds(34, 43, 385, 365);
		mv2_1.add(jsp_board);

		mv2_panel = new JPanel();
		jsp_board.setViewportView(mv2_panel);
		mv2_panel.setBackground(UIManager.getColor("Button.background"));
		mv2_panel.setPreferredSize(mv2Size);

		bt_mv2Return = new JButton("뒤로가기") {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(backBt.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		bt_mv2Return.setBounds(314, 435, 103, 27);
		;
		mv2_1.add(bt_mv2Return);

		writeBt = new ImageIcon("img/writeBt.png");
		Image image5 = writeBt.getImage();
		image5 = image5.getScaledInstance(107, 33, Image.SCALE_SMOOTH);
		writeBt = new ImageIcon(image5);
		bt_mv2Write = new JLabel("글쓰기");
		bt_mv2Write.setIcon(writeBt);

		/*
		 * { public void paintComponent(Graphics g) { Dimension d = getSize();
		 * g.drawImage(writeBt.getImage(), 0, 0, d.width, d.height, null); } };
		 */
		bt_mv2Write.setOpaque(false);
		bt_mv2Write.setBounds(34, 435, 107, 33);
		mv2_1.add(bt_mv2Write);

		bt_mv2Like = new JLabel("좋아요");
		bt_mv2Like.setOpaque(false);
		bt_mv2Like.setBounds(153, 435, 107, 33);
		mv2_1.add(bt_mv2Like);

		ImageIcon writingR = new ImageIcon("img/writingR.png");
		mv2_2 = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(writingR.getImage(), 0, 0, null);
				Dimension d = getSize();
				g.drawImage(writingR.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		mv2.add(mv2_2, "mv2_2");
		mv2_2.setLayout(null);

		jsp_comm = new JScrollPane();
		jsp_comm.setBounds(48, 173, 351, 163);
		mv2_2.add(jsp_comm);

		ta_comment = new JTextArea();
		jsp_comm.setViewportView(ta_comment);
		ta_comment.setLineWrap(true);

		bt_mv2_2Return = new JButton("back") {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(backBt.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		bt_mv2_2Return.setBounds(314, 435, 103, 27);
		mv2_2.add(bt_mv2_2Return);

		ImageIcon submitBt = new ImageIcon("img/submitBt.png");
		bt_mv2_2Sub = new JButton("SUBMIT") {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(submitBt.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		bt_mv2_2Sub.setBounds(292, 349, 107, 27);
		mv2_2.add(bt_mv2_2Sub);

		lb_mv2_pic = new JLabel("pic");
		lb_mv2_pic.setBounds(48, 78, 85, 85);
		mv2_2.add(lb_mv2_pic);

		lblNewLabel_5 = new JLabel("평점주기 :");
		lblNewLabel_5.setBounds(147, 110, 75, 18);
		mv2_2.add(lblNewLabel_5);

		lb_mv2_stars = new JLabel("별");
		lb_mv2_stars.setBounds(250, 107, 115, 25);
		mv2_2.add(lb_mv2_stars);

		lb_mv2_minus = new JLabel("마이너스");
		lb_mv2_minus.setBounds(220, 107, 25, 25);
		ImageIcon minus = new ImageIcon("img/minus.png");
		Image image = minus.getImage();
		image = image.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		minus.setImage(image);
		lb_mv2_minus.setIcon(minus);
		mv2_2.add(lb_mv2_minus);

		lb_mv2_plus = new JLabel("플러스");
		lb_mv2_plus.setBounds(370, 107, 25, 25);
		ImageIcon plus = new ImageIcon("img/plus.png");
		Image image2 = plus.getImage();
		image2 = image2.getScaledInstance(25, 25, Image.SCALE_SMOOTH);
		plus.setImage(image2);
		lb_mv2_plus.setIcon(plus);
		mv2_2.add(lb_mv2_plus);

		ImageIcon delt = new ImageIcon("img/deleteBBt.png");
		bt_deleteCom = new JButton("Delete") {
			public void paintComponent(Graphics g) {
				Dimension d = getSize();
				g.drawImage(delt.getImage(), 0, 0, d.width, d.height, null);
			}
		};
		bt_deleteCom.setBounds(48, 349, 107, 27);
		// bt_deleteCom.setIcon(deleteBt);
		mv2_2.add(bt_deleteCom);

		pnLoading = new JPanel();
		pnLoading.setBackground(UIManager.getColor("CheckBox.focus"));
		getContentPane().add(pnLoading, "nowLoading");

		lb_loading = new JLabel("");
		lb_loading.setBounds(0, 0, 894, 510);

		loading = new ImageIcon("img/loading.gif");
		// Image nlo = loading.getImage();
		// Image reno = nlo.getScaledInstance(900, 550, Image.SCALE_SMOOTH);
		// loading = new ImageIcon(reno);
		pnLoading.setLayout(null);
		lb_loading.setIcon(loading);
		pnLoading.add(lb_loading);

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int locWidth = (int) ((d.getWidth() - fwidth) / 2);
		int locHeight = (int) ((d.getHeight() - fheight) / 2);
		setLocation(locWidth, locHeight);
		setResizable(false);
		setVisible(true);
		ma = new mcl();
		addListeners();
		mg = new ClientManager(this);
		// sb = new SearchBy();
		// sg = new StringGiver();

	}

	public User getMe() {
		return me;
	}

	public void setMe(User me) {
		this.me = me;
	}

	public void addListeners() {
		WIndowAd wa = new WIndowAd();
		goRegi.addMouseListener(ma);
		bt_rgReg.addMouseListener(ma);
		bt_rgCancel.addMouseListener(ma);
		bt_Login.addMouseListener(ma);
		bt_search.addMouseListener(ma);
		bt_mv2Return.addMouseListener(ma);
		bt_mm1_2Return.addMouseListener(ma);
		bt_mm1_3Return.addMouseListener(ma);
		bt_mm1_4Return.addMouseListener(ma);
		bt_chat.addMouseListener(ma);
		pIcon.addMouseListener(ma);
		bt_mv2Write.addMouseListener(ma); // 글쓰기
		bt_mv2_2Return.addMouseListener(ma);
		bt_mv2_2Sub.addMouseListener(ma);
		bt_deleteCom.addMouseListener(ma);
		likeIcon.addMouseListener(ma);
		commentIcon.addMouseListener(ma);
		bt_mv2Like.addMouseListener(ma);
		lb_mv2_minus.addMouseListener(ma);
		lb_mv2_plus.addMouseListener(ma);
		bt_trailer.addMouseListener(ma);
		this.addComponentListener(wa);
	}

	public class mcl extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == goRegi) {
				lg2Card.show(lg2, "lg2_2");
			} else if (e.getSource() == bt_rgReg) {
				register();

			} else if (e.getSource() == bt_rgCancel) {
				lg2Card.show(lg2, "lg2_1");
			} else if (e.getSource() == bt_Login) {
				login();
			} else if (e.getSource() == mBoxTable) {
				int wh = mBoxTable.getSelectedRow();
				if (wh != -1) {
					String gC = dblist.get(wh).getMovieCd();
					String searchString = (dblist.get(wh).getMovieNm() + ", 영화, 공식 트레일러");
					mainCard.show(mainBOARD, "nowLoading");
					mg.searchYouTube(searchString);

					//////////////////////////////////////////
				} /////////////////////////////////

			} else if (e.getSource() == tb_search) {
				int who = tb_search.getSelectedRow();
				if (who != -1) {
					sMovieCD = scList.get(who).getMvCode();
					setMovieInfoPage(sMovieCD);
					mg.getComments(sMovieCD);
				}
				mainCard.show(mainBOARD, "pnMovie");
			} else if (e.getSource() == bt_mv2Return) {
				mainCard.show(mainBOARD, "pnMain");
				mv2_panel.removeAll();
			} else if (e.getSource() == bt_search) {
				searchByAction();
			} else if (e.getSource() == bt_mm1_2Return) {
				mn1Card.show(mn1, "mm1_1");
				searchRBack.setVisible(false);
			} else if (e.getSource() == bt_chat) {
				chat.windowOn();
			} else if (e.getSource() == pIcon) {// 캐릭터 바꾸기
				changeIconGUI = new ChangeIcon(myGui, mg); // 생성
			} else if (e.getSource() == bt_mv2Write) {
				// 글쓰기 버튼..
				mv2Card.show(mv2, "mv2_2");
				myStar = new Stars(0);
				setStars(myStar, lb_mv2_stars);
			} else if (e.getSource() == bt_mv2_2Return) {// 뒤로가기
				mv2Card.show(mv2, "mv2_1");
				myStar = new Stars(0);
				setStars(myStar, lb_mv2_stars);
				ta_comment.setText("");
			} else if (e.getSource() == bt_mv2_2Sub) {// 코멘트 올리기
				writeComment();
				// mv2Card.show(mv2, "mv2_1");
			} else if (e.getSource() == bt_mm1_3Return) {
				mn1Card.show(mn1, "mm1_1");
			} else if (e.getSource() == bt_mm1_4Return) {
				mn1Card.show(mn1, "mm1_1");
			} else if (e.getSource() == likeIcon) {
				mn1Card.show(mn1, "mm1_3");
				searchRBack.setVisible(false);
				///// 내가 좋아요 누른 영화들 확인
				mg.getUserLikebyID(whoAmI);
			} else if (e.getSource() == commentIcon) {
				mn1Card.show(mn1, "mm1_4");
				mg.getMyComment(whoAmI);
				searchRBack.setVisible(false);
				///// 내가 남긴 코멘트 확인
			} else if (e.getSource() == bt_mv2Like) {
				////////// 좋아요눌렀을때?
				actionLike();
			} else if (e.getSource() == lb_mv2_minus) {// minus
				if (myStar.getStars() == 0) {
					JOptionPane.showMessageDialog(null, "별점은 0~5점입니다.");
				} else {
					myStar.minus();
					setStars(myStar, lb_mv2_stars);
				}
			} else if (e.getSource() == lb_mv2_plus) {// plus
				if (myStar.getStars() == 10) {
					JOptionPane.showMessageDialog(null, "별점은 0~5점입니다.");
				} else {
					myStar.plus();
					setStars(myStar, lb_mv2_stars);
				}
			} else if (e.getSource() == bt_deleteCom) {
				mg.delMyComment(whoAmI, msi.getMvCode());
				mg.getComments(msi.getMvCode());
				mv2Card.show(mv2, "mv2_1");
				// }else if (e.getSource() instanceof PnComment) {
				// PnComment uc = (PnComment) e.getSource();
				// UserComment c = uc.getC();
				// // System.out.println(c.getUserText());
				//
				// } else if (e.getSource() instanceof PnLike) {
				// PnLike p = (PnLike) e.getSource();
				// MovieSearchInfo mc = p.getMs();
				// System.out.println(mc.getMvTitle());
			} else if (e.getSource() instanceof MyText) {
				MyText itsMe = (MyText) e.getSource();
				// System.out.println(itsMe.getComment()); // 여기야여기!!지혜야 여기봐라!!!
				CommentDetail cd = new CommentDetail(itsMe.getComment());
			} else if (e.getSource() == bt_trailer) {
				String searchString = (msi.getMvTitle() + ", 영화, 공식 트레일러, 예고편");
				mg.searchYouTube(searchString);
			}
		}

	}

	public void register() {
		String id = rg_id.getText();
		String pw = rg_pw.getText();
		String mail = rg_ma.getText();
		String phN = rg_ph.getText();

		User nUser = new User(id, pw, mail, phN, "img/p1.png");
		mg.register(nUser);
	}

	public void login() {
		String id = tf_id.getText();
		String pw = tf_pw.getText();
		if (id.equals("")) {
			JOptionPane.showMessageDialog(null, "아이디를 입력해 주십시오", "로그인 에러", JOptionPane.ERROR_MESSAGE);
			tf_id.grabFocus();
			return;
		} else if (pw.equals("")) {
			JOptionPane.showMessageDialog(null, "비밀번호를 입력해 주십시오", "로그인 에러", JOptionPane.ERROR_MESSAGE);
			tf_pw.grabFocus();
			return;
		}
		mg.login(id, pw);
	}

	public void setMovieBoxInfo(ArrayList<MovieBoxInfo> malist) {
		dblist = malist;
		Vector<String> column = new Vector<String>();
		column.addElement("순위");
		column.addElement("제목");
		column.addElement("감독");
		column.addElement("개봉일");

		Vector<String> elements = null;
		Vector<Vector> rowData1 = new Vector<Vector>();
		for (int i = 0; i < dblist.size(); i++) {
			MovieBoxInfo h = dblist.get(i);
			elements = new Vector<String>();
			elements.addElement((i + 1) + "");
			elements.addElement(h.getMovieNm());
			elements.addElement(h.getDirector());
			elements.addElement(h.getOpenDt());
			rowData1.addElement(elements);
		}

		// mBoxTable = new JTable(rowData1, column);
		// 테이블 내용 수정 불가------------------------
		DefaultTableModel dtf = new DefaultTableModel(rowData1, column) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		mBoxTable = new JTable(dtf);
		// -----------------------------------------------

		mBoxTable.getTableHeader().setReorderingAllowed(false);
		mBoxTable.getColumnModel().getColumn(0).setPreferredWidth(4);
		mBoxTable.getColumnModel().getColumn(0).setResizable(false);
		DefaultTableCellRenderer celAlignCenter = new DefaultTableCellRenderer();
		celAlignCenter.setHorizontalAlignment(JLabel.CENTER);
		mBoxTable.getColumnModel().getColumn(0).setCellRenderer(celAlignCenter);

		mBoxTable.setDragEnabled(false);
		tableView.setViewportView(mBoxTable);
		mBoxTable.addMouseListener(new mcl());
	}

	public void searchByAction() {
		int type = cb.getSelectedIndex();
		String what = mn_search.getText();
		if (what.equals("")) {
			JOptionPane.showMessageDialog(null, "검색값을 입력해 주십시오.", "검색 에러", JOptionPane.ERROR_MESSAGE);
			mn_search.grabFocus();
			return;
		}
		if (type == 0) {
			JOptionPane.showMessageDialog(null, "검색 타입을 선택하여 주십시오", "검색 에러", JOptionPane.ERROR_MESSAGE);
			cb.grabFocus();
			return;
		}
		//////////////////
		mainCard.show(mainBOARD, "nowLoading");
		gm = new Game();
		mg.searchMovie(type, what);

		// Thread th = new Thread(this);
		// th.start();

	}

	public void setSearchTable() {
		gm.dispose();
		mainCard.show(mainBOARD, "pnMain");

		if (scList.size() == 0) {
			JOptionPane.showMessageDialog(null, "검색 결과가 없습니다.", "검색 실패", JOptionPane.ERROR_MESSAGE);
			mn_search.grabFocus();
			return;
		}

		mn1Card.show(mn1, "mm1_2");
		searchRBack.setVisible(true);
		mm1_1.revalidate();

		Vector<String> column = new Vector<String>();
		column.addElement("제목");
		column.addElement("장르");
		column.addElement("감독");
		column.addElement("주연배우");

		Vector<String> elements = null;
		Vector<Vector<String>> rowData1 = new Vector<Vector<String>>();
		for (int i = 0; i < scList.size(); i++) {
			MovieSearchInfo h = scList.get(i);
			elements = new Vector<String>();
			elements.addElement(h.getMvTitle());
			elements.addElement(h.getMvGenre());
			elements.addElement(h.getMvDirector());
			elements.addElement(h.getMvActor());
			rowData1.addElement(elements);
		}

		// 테이블 내용 수정 불가------------------------
		DefaultTableModel dtf = new DefaultTableModel(rowData1, column) {
			public boolean isCellEditable(int rowIndex, int mColIndex) {
				return false;
			}
		};
		tb_search = new JTable(dtf);
		// -----------------------------------------------

		tb_search.getTableHeader().setReorderingAllowed(false);
		tb_search.setPreferredSize(new Dimension(pn_search.getSize()));
		tb_search.getColumnModel().getColumn(0).setPreferredWidth(100);
		tb_search.setDragEnabled(false);
		sp_search.setViewportView(tb_search);
		tb_search.addMouseListener(new mcl());

	}

	public void setMovieInfoPage(String movieCode) {
		mg.getIsLike(whoAmI, movieCode);
		mg.getAvgGrade(movieCode);

		MovieSearchInfo m = null;
		for (int i = 0; i < scList.size(); i++) {
			if (scList.get(i).getMvCode().equals(movieCode)) {
				m = scList.get(i);
			}
		}
		msi = m;
		String iconURL = m.getMvThumb();
		String title = m.getMvTitle();
		String genre = m.getMvGenre();
		String showTm = m.getMvTm();
		String actor = m.getMvActor();
		String director = m.getMvDirector();
		String story = m.getMvStory();

		if (iconURL.equals("")) {
			Image image = noImg.getImage();
			Image reSized = image.getScaledInstance(170, 215, Image.SCALE_SMOOTH);
			noImg = new ImageIcon(reSized);
			lb_mvIcon.setIcon(noImg);
		} else {
			try {
				ImageIcon imgicon = new ImageIcon(ImageIO.read(new URL(iconURL)));
				Image image = imgicon.getImage();
				Image reSized = image.getScaledInstance(170, 215, Image.SCALE_SMOOTH);
				imgicon = new ImageIcon(reSized);
				lb_mvIcon.setIcon(imgicon);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

		//
		// lb_mvIcon.setSize(new Dimension(170, 215));
		// lb_mvIcon.setBounds(31, 49, 171, 222);
		lb_mvTitle.setText("");
		lb_mvTitle.setText(title);
		int fontSize = 30;
		int titleLength = title.length();
		if (titleLength > 8) {
			fontSize -= 3;
		} else if (titleLength > 10) {
			fontSize -= 5;
		} else if (titleLength > 20) {
			fontSize -= 10;
		} else if (titleLength > 30) {
			fontSize -= 20;
		}
		lb_mvTitle.setHorizontalAlignment(SwingConstants.CENTER);
		lb_mvTitle.setFont(new Font("Dialog", Font.PLAIN, fontSize));
		lb_mvTitle.setBounds(216, 49, 191, 80);

		lb_mvDirector.setHorizontalAlignment(SwingConstants.CENTER);
		lb_mvDirector.setText(director);

		lb_mvGen.setHorizontalAlignment(SwingConstants.CENTER);
		lb_mvGen.setText(genre);

		lb_mvShowT.setHorizontalAlignment(SwingConstants.CENTER);
		lb_mvShowT.setText(showTm);

		lb_mvActor.setHorizontalAlignment(SwingConstants.CENTER);
		lb_mvActor.setText(actor);

		ta_mvStory.setText("");
		String[] sArr = story.split("\r\n");
		for (int i = 0; i < sArr.length; i++) {
			ta_mvStory.append(sArr[i] + "\n");
		}
		ta_mvStory.setCaretPosition(0);
		//////////////////

		lb_mvIcon.setSize(new Dimension(170, 215));
		lb_mvIcon.setBounds(31, 49, 181, 235);
		mv1.repaint();
		mv1.revalidate();

		// 여기에서 평균별점 넣어주기------------------------------------
		// double avg = 9.9;// 여기서 영화코드로 구한 평균더블값을 넣어주자
		// Stars avgStar = new Stars(avg);
		// setStars(avgStar, lb_grade);
		this.revalidate();
	}

	public void setChatGUI(ChatGUI chat) {
		this.chat = chat;
	}

	public class WIndowAd extends ComponentAdapter {

		@Override
		public void componentMoved(ComponentEvent e) {
			// TODO Auto-generated method stub
			super.componentMoved(e);
			if (chat != null) {
				chat.setThisLocation();
			}
		}

		@SuppressWarnings("deprecation")
		@Override
		public void componentShown(ComponentEvent e) {
			// TODO Auto-generated method stub
			super.componentShown(e);
			if (chat != null) {
				chat.setVisible(true);
			}
		}

		@SuppressWarnings("deprecation")
		@Override
		public void componentHidden(ComponentEvent e) {
			// TODO Auto-generated method stub
			super.componentHidden(e);
			if (chat != null) {
				chat.setVisible(false);
			}
		}
	}

	public void registerReaction(int reaction) {
		switch (reaction) {
		case Data.FAIL:
			JOptionPane.showMessageDialog(null, "중복된 아이디가 이미 존재합니다.", "회원가입 에러", JOptionPane.ERROR_MESSAGE);
			rg_id.setText("");
			break;
		case Data.RG_SUCCESS:
			JOptionPane.showMessageDialog(null, "회원가입이 완료되었습니다!", "환영합니다", JOptionPane.INFORMATION_MESSAGE);
			lg2Card.show(lg2, "lg2_1");
			break;
		}
	}

	public void loginReaction(User result) {
		if (result != null) {
			JOptionPane.showMessageDialog(null, "로그인이 완료되었습니다!", "환영합니다", JOptionPane.INFORMATION_MESSAGE);
			mainCard.show(mainBOARD, "pnMain");
			whoAmI = result.getId();
			me = result;
			mg.setUserId(result.getId());
			chat = new ChatGUI(mg, this);
			chat.setId(whoAmI);
			String iconURL = result.getPic();
			// System.out.println("loginReactino" + iconURL);
			setPersonIcon(iconURL);
			mg.setCgui(chat);
			mg.startChat();

			mg.getNums(whoAmI);

			mg.getBestM();// 여기가 맞나? 그리고
			mg.getBestC();
			mg.getBestL();
			// mg.getMovieBoxInfo();

			///////////////////////// 프로필바꾸기
			// changeIconGUI = new ChangeIcon(this); // 생성
			// changeIconGUI.setVisible(false);
			/////////////////////////

		} else {
			JOptionPane.showMessageDialog(null, "아이디 혹은 패스워드가 일치하지않습니다.", "로그인 에러", JOptionPane.ERROR_MESSAGE);
			tf_id.grabFocus();
			/////
		}
	}

	public void setPersonIcon(String url) { // 프로필수정해주기
		// System.out.println(url);
		person = new ImageIcon(url);
		Image image = person.getImage();
		Image reSized = image.getScaledInstance(100, 100, Image.SCALE_SMOOTH);
		person.setImage(reSized);
		pIcon.setIcon(person);

		Image reSized2 = image.getScaledInstance(85, 85, Image.SCALE_SMOOTH);
		ImageIcon pp = new ImageIcon();
		pp.setImage(reSized2);
		lb_mv2_pic.setIcon(pp);
	}

	public String getPersonIcon() { // 지금프로필받아오기
		String url = "";
		url += pIcon.getIcon();
		return url;
	}

	public void actionLike() {
		///////////// who am i 랑 msi 를 보내주면 저장된다.
		////////////////// 일단 좋아하는지 안좋아하는지 가져와서 아직 좋아요가 아니면 등록하면서 아이콘 등록해주고
		// 원래 좋아하던거면 없애주기???
		mg.userLike(whoAmI, msi);
		mg.getIsLike(whoAmI, msi.getMvCode());

		mg.getBestL();
	}

	public void setImgicons() {
		noImg = new ImageIcon("img/noImg.jpg");

		likeg = new ImageIcon("img/like.png");
		Image image = likeg.getImage();
		Image reSized = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		likeg = new ImageIcon(reSized);

		cancelBt = new ImageIcon("img/cancelBt.png");
		Image cancel = cancelBt.getImage();
		Image rescancel = cancel.getScaledInstance(107, 33, Image.SCALE_SMOOTH);
		cancelBt = new ImageIcon(rescancel);

		movieLikeBt = new ImageIcon("img/movieLikeBt.png");
		Image like = movieLikeBt.getImage();
		Image liked = like.getScaledInstance(107, 33, Image.SCALE_SMOOTH);
		movieLikeBt = new ImageIcon(liked);

		disLike = new ImageIcon("./img/dislike.png");
		Image image5 = disLike.getImage();
		Image reSized5 = image5.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
		disLike = new ImageIcon(reSized5);

		modifyBt = new ImageIcon("img/modifyBt.png");
		Image ldgg = modifyBt.getImage();
		Image reldgg = ldgg.getScaledInstance(107, 33, Image.SCALE_SMOOTH);
		modifyBt = new ImageIcon(reldgg);

		deleteBt = new ImageIcon("img/deleteBt.png");
		Image ldggg = deleteBt.getImage();
		Image ggldggg = ldggg.getScaledInstance(107, 27, Image.SCALE_SMOOTH);
		deleteBt = new ImageIcon(ggldggg);

	}

	public void reactionIsItLike(int reaction) {
		if (reaction == Data.USERLIKETHIS) {
			lb_isLike.setIcon(likeg);
			bt_mv2Like.setIcon(cancelBt);
		} else {
			lb_isLike.setIcon(disLike);
			bt_mv2Like.setIcon(movieLikeBt);
		}
	}

	public void setStars(Stars stars, JLabel label) {
		double s = stars.getStars();
		if (s < 0) {
			label.setIcon(setStarLabel("img/noStar.png"));
		} else if (s >= 0 && s < 1) {
			label.setIcon(setStarLabel("img/0.png"));
		} else if (s >= 1 && s < 2) {
			label.setIcon(setStarLabel("img/0_5.png"));
		} else if (s >= 2 && s < 3) {
			label.setIcon(setStarLabel("img/1.png"));
		} else if (s >= 3 && s < 4) {
			label.setIcon(setStarLabel("img/1_5.png"));
		} else if (s >= 4 && s < 5) {
			label.setIcon(setStarLabel("img/2.png"));
		} else if (s >= 5 && s < 6) {
			label.setIcon(setStarLabel("img/2_5.png"));
		} else if (s >= 6 && s < 7) {
			label.setIcon(setStarLabel("img/3.png"));
		} else if (s >= 7 && s < 8) {
			label.setIcon(setStarLabel("img/3_5.png"));
		} else if (s >= 8 && s < 9) {
			label.setIcon(setStarLabel("img/4.png"));
		} else if (s >= 9 && s < 10) {
			label.setIcon(setStarLabel("img/4_5.png"));
		} else if (s >= 10) {
			label.setIcon(setStarLabel("img/5.png"));
		}
	}

	public ImageIcon setStarLabel(String url) {
		ImageIcon starLabel = new ImageIcon(url);
		Image image = starLabel.getImage();
		image = image.getScaledInstance(115, 25, Image.SCALE_SMOOTH);
		starLabel.setImage(image);
		return starLabel;
	}

	public void writeComment() {
		String userID = me.getId();
		String userText = ta_comment.getText();
		Double grade = myStar.getStars();
		String movieCD = msi.getMvCode();
		String userPic = me.getPic();
		String thumb = msi.getMvThumb();
		String title = msi.getMvTitle();

		if (userText.equals("")) {
			JOptionPane.showMessageDialog(null, "내용을 입력해 주십시오");
			return;
		} else if (grade == 0) {
			JOptionPane.showMessageDialog(null, "평점을 설정해 주십시오");
			return;
		}

		UserComment uc = new UserComment(userID, userText, grade, movieCD, userPic, thumb, title);
		mg.writeComments(uc);
		ta_comment.setText("");
		myStar = new Stars(0);
		mg.getComments(movieCD);
		setStars(myStar, lb_mv2_stars);
		mv2Card.show(mv2, "mv2_1");

		mg.getNums(whoAmI);
	}

	////////////////////////////////////
	public void getCommentReaction(ArrayList<UserComment> cCList) {
		mv2_panel.removeAll();

		bt_deleteCom.setVisible(false);
		bt_deleteCom.setEnabled(false);

		bt_mv2Write.setIcon(writeBt);
		bt_mv2Write.setBounds(34, 435, 107, 33);
		ta_comment.setText("");
		mv2_panel.setPreferredSize(mv2Size);
		Dimension d = new Dimension(mv2Size.getSize());
		int size = cCList.size();
		int height = 102;
		if (size == 0)
			return;
		UserComment c = null;
		for (int i = 0; i < size; i++) {
			c = cCList.get(i);
			if (c.getUserID().equals(whoAmI)) {
				bt_mv2Write.setIcon(modifyBt);
				bt_deleteCom.setVisible(true);
				bt_deleteCom.setEnabled(true);
				ta_comment.setText(c.getUserText());
			}
			PnComment pn = new PnComment(c);
			setPreferredSize(new Dimension((int) (mv2Size.getWidth() - 65), 80));
			if (i > 3) {
				d.setSize(d.getWidth(), d.getHeight() + height);
				mv2_panel.setPreferredSize(d);
			}
			// setCommentML(pn);
			pn.getTa_Te().addMouseListener(ma);
			mv2_panel.add(pn);
		}
		mv2_panel.revalidate();
		this.revalidate();

		mg.getBestM();// 여기가 맞나? 그리고
		mg.getBestC();
	}

	////////////////////////////////////
	public void getMyCommentReaction(ArrayList<UserComment> usc) {
		pn_UserComment.removeAll();
		pn_UserComment.setPreferredSize(ucSize);
		Dimension d = new Dimension(ucSize.getSize());
		int size = usc.size();
		int height = 100;
		if (size == 0)
			return;
		UserComment c = null;
		for (int i = 0; i < size; i++) {
			c = usc.get(i);
			PnUserComment pn = new PnUserComment(c);
			setPreferredSize(new Dimension((int) (ucSize.getWidth() - 65), 80));
			if (i > 3) {
				d.setSize(d.getWidth(), d.getHeight() + height);
				pn_UserComment.setPreferredSize(d);
			}
			pn.getTa_Te().addMouseListener(ma);
			pn_UserComment.add(pn);
		}
		pn_UserComment.revalidate();
		this.revalidate();
	}

	public void setCommentML(JPanel pn) {
		pn.addMouseListener(ma);
	}

	public void setLikeML(JLabel lb) {
		lb.addMouseListener(ma);
	}

	public void addMouseAdd(JComponent thd) {
		thd.addMouseListener(ma);

	}

	public void reActionAvgGrade(double avg) {
		Stars avgStar = new Stars(avg);
		setStars(avgStar, lb_grade);
		mv1.repaint();
		mv1.revalidate();
	}

	public void getLikeByIdReaction(ArrayList<MovieSearchInfo> lkList) {
		int size = lkList.size();
		pn_UserLike.removeAll();
		PnLike p = null;
		if (size == 0)
			return;
		for (MovieSearchInfo dd : lkList) {
			p = new PnLike(dd);
			pn_UserLike.add(p);
			setLikeML(p);
		}
		pn_UserLike.revalidate();

		mg.getNums(whoAmI);
	}

	public void searchYoutubeReaction(String utb) {
		mainCard.show(mainBOARD, "pnMain");

		try {
			Desktop.getDesktop().browse(new URI(utb));

		} catch (IOException e) {
			// TODO Auto-generated catch block
			// e.printStackTrace();
		} catch (URISyntaxException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
		}
	}

	public void setNumsReaction(ArrayList<Integer> nLi) {
		if (nLi.size() == 2) {
			lb_commentNum.setText(nLi.get(1) + "");
			lb_likeNum.setText(nLi.get(0) + "");
		}
	}

	public void setBestM(UserComment uc) {
		if (uc == null) {
			thumb_bestM.setIcon(noImg);
			thumb_bestM.setToolTipText("가장 별점이 높은 영화가 아직 없습니다.");
		} else {
			try {
				ImageIcon bestM = new ImageIcon(ImageIO.read(new URL(uc.getThumb())));
				System.out.println("ClientGUI.setBestsM : " + uc.getThumb());
				Image image = bestM.getImage();
				image = image.getScaledInstance(70, 84, Image.SCALE_SMOOTH);
				bestM = new ImageIcon(image);
				thumb_bestM.setIcon(bestM);
				thumb_bestM.setToolTipText("가장 별점이 높은 영화 : " + uc.getTitle());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	public void setBestC(UserComment uc) {
		if (uc == null) {
			thumb_bestC.setIcon(noImg);
			thumb_bestC.setToolTipText("코멘트가 달린 영화가 아직 없습니다.");
		} else {
			try {
				ImageIcon bestC = new ImageIcon(ImageIO.read(new URL(uc.getThumb())));
				System.out.println("ClientGUI.setBestsC : " + uc.getThumb());
				Image image = bestC.getImage();
				image = image.getScaledInstance(70, 84, Image.SCALE_SMOOTH);
				bestC = new ImageIcon(image);
				thumb_bestC.setIcon(bestC);
				thumb_bestC.setToolTipText("가장 많은 코멘트가 달린 영화 : " + uc.getTitle());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setBestL(UserComment uc) {
		if (uc == null) {
			thumb_bestL.setIcon(noImg);
			thumb_bestL.setToolTipText("좋아요된 영화가 아직 없습니다.");
		} else {
			try {
				ImageIcon bestL = new ImageIcon(ImageIO.read(new URL(uc.getThumb())));
				System.out.println("ClientGUI.setBestsL : " + uc.getThumb());
				Image image = bestL.getImage();
				image = image.getScaledInstance(70, 84, Image.SCALE_SMOOTH);
				bestL = new ImageIcon(image);
				thumb_bestL.setIcon(bestL);
				thumb_bestL.setToolTipText("가장 많은 좋아요를 받은 영화 : " + uc.getTitle());
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
