package client;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import vos.MyText;
import vos.Stars;
import vos.UserComment;
import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.Color;

public class CommentDetail extends JDialog {

	private UserComment userComment;
	private JLabel lb_thumb;
	private JPanel background;
	private String userID, userText, movieCD, userPic, thumb, title;
	private double grade;
	private JLabel lb_stars;
	private JLabel lb_userCm;
	private ClientManager mg;
	private JLabel lb_userLk;

	public CommentDetail(UserComment userComment, ClientManager mg) {
		this.mg = mg;

		this.userID = userComment.getUserID();
		this.userText = userComment.getUserText();
		this.grade = userComment.getGrade();
		this.movieCD = userComment.getMovieCD();
		this.userPic = userComment.getUserPic();
		this.thumb = userComment.getThumb();
		this.title = userComment.getTitle();

		setTitle(userID + "'s.. Comment about " + title);
		setIconImage(Toolkit.getDefaultToolkit().getImage(userPic));// 아이콘
		setBounds(0, 0, 332, 382);
		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		setView();
		int xlo = (int) ((d.getWidth() - 400) / 2);
		int ylo = (int) ((d.getHeight() - 500) / 2);

		setLocation(xlo, ylo);
		setVisible(true);
		setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
	}

	public void setView() {
		ImageIcon infoL = new ImageIcon("img/detailBack.png");
		background = new JPanel() {
			public void paintComponent(Graphics g) {
				g.drawImage(infoL.getImage(), 0, 0, null); // 이미지 원래사이즈로 넣기
				// Dimension d
				Dimension d = getSize();
				g.drawImage(infoL.getImage(), 0, 0, d.width, d.height, null); // 컴포넌트사이즈에맞게
			}
		};
		getContentPane().add(background);
		background.setLayout(null);

		lb_thumb = new JLabel("");
		lb_thumb.setBounds(12, 10, 93, 111);
		background.add(lb_thumb);
		setImg();

		JLabel lb_title = new JLabel();
		lb_title.setHorizontalAlignment(SwingConstants.CENTER);
		lb_title.setFont(new Font("Dialog", Font.BOLD, 21));
		lb_title.setBounds(121, 10, 180, 67);
		background.add(lb_title);
		lb_title.setText(title);

		lb_stars = new JLabel("stars");
		lb_stars.setBounds(143, 87, 136, 34);
		background.add(lb_stars);
		setStars(grade, lb_stars);

		JLabel lb_userID = new JLabel("id");
		lb_userID.setBounds(22, 135, 98, 15);
		lb_userID.setText("작성자 : " + userID);
		background.add(lb_userID);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(12, 156, 289, 156);
		background.add(scrollPane);

		JTextArea ta_comment = new JTextArea();
		scrollPane.setViewportView(ta_comment);
		ta_comment.setText(userText);
		ta_comment.setEditable(false);
		ta_comment.setLineWrap(true);

		lb_userCm = new JLabel("다른 코멘트..");
		lb_userCm.setForeground(new Color(0, 0, 153));
		lb_userCm.setBounds(208, 320, 93, 18);
		background.add(lb_userCm);

		lb_userLk = new JLabel("다른 좋아요..");
		lb_userLk.setForeground(new Color(0, 0, 153));
		lb_userLk.setBounds(12, 320, 93, 18);
		background.add(lb_userLk);

		lb_userCm.addMouseListener(new mcl());
		lb_userLk.addMouseListener(new mcl());

	}

	public JLabel getLb_userCm() {
		return lb_userCm;
	}

	public void setLb_userCm(JLabel lb_userCm) {
		this.lb_userCm = lb_userCm;
	}

	public void setImg() {
		if (thumb.equals("")) {
			ImageIcon noImg = new ImageIcon("img/noImg.jpg");
			Image image = noImg.getImage();
			image = image.getScaledInstance(93, 111, Image.SCALE_SMOOTH);
			noImg = new ImageIcon(image);
			lb_thumb.setIcon(noImg);
		} else {
			try {
				ImageIcon imgicon = new ImageIcon(ImageIO.read(new URL(thumb)));
				Image image = imgicon.getImage();
				Image reSized = image.getScaledInstance(93, 111, Image.SCALE_SMOOTH);
				imgicon = new ImageIcon(reSized);
				lb_thumb.setIcon(imgicon);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setStars(double s, JLabel label) {
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
		image = image.getScaledInstance(136, 34, Image.SCALE_SMOOTH);
		starLabel.setImage(image);
		return starLabel;
	}

	public class mcl extends MouseAdapter {

		@Override
		public void mousePressed(MouseEvent e) {
			if (e.getSource() == lb_userCm) {
				System.out.println("!!");
				mg.getMyComment(userID);
			} else if (e.getSource() == lb_userLk) {
				System.out.println("??");
				mg.getUserLikebyID(userID);
			}
		}

	}
}
