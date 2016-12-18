package vos;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Image;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PnUserComment extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8372446897918235615L;
	private UserComment c;
	private MyText ta_Te;

	public MyText getTa_Te() {
		return ta_Te;
	}

	public void setTa_Te(MyText ta_Te) {
		this.ta_Te = ta_Te;
	}

	public PnUserComment(UserComment c) {
		this.c = c;
		mkComment();
	}

	public UserComment getC() {
		return c;
	}

	public void setC(UserComment c) {
		this.c = c;
	}

	public void mkComment() {
		setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		setLayout(new BorderLayout(0, 0));
		setPreferredSize(new Dimension(350, 80));
		JLabel lb_icon = new JLabel("");
		//////
		ImageIcon noImg = new ImageIcon("./img/noImg.jpg");
		String iconURL = c.getThumb();
		if (iconURL.equals("")) {
			Image image = noImg.getImage();
			Image reSized = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
			noImg = new ImageIcon(reSized);
			lb_icon.setIcon(noImg);
		} else {
			try {
				ImageIcon imgicon = new ImageIcon(ImageIO.read(new URL(iconURL)));
				Image image = imgicon.getImage();
				Image reSized = image.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
				imgicon = new ImageIcon(reSized);
				lb_icon.setIcon(imgicon);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		//////////
		add(lb_icon, BorderLayout.WEST);
		String userText = c.getUserText();
		ta_Te = new MyText(c);
		ta_Te.setLineWrap(true);

		String cutted = userText;
		String[] didi = cutted.split("\n");
		//System.out.println(didi.length);
		for (int i = 0; i < didi.length; i++) {
			ta_Te.append(didi[i]);
			if (i > 1) {
				ta_Te.append(".......");
				break;
			} else {
				ta_Te.append("\n");
			}
		}
		add(ta_Te, BorderLayout.CENTER);
		ta_Te.setEditable(false);

		JLabel lb_nick = new JLabel(c.getTitle());
		add(lb_nick, BorderLayout.NORTH);

	}
}
