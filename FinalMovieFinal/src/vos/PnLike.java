package vos;

import java.awt.Dimension;
import java.awt.Image;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class PnLike extends JLabel {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4099723581295810769L;
	private MovieSearchInfo ms;

	public PnLike(MovieSearchInfo ms) {
		this.ms = ms;

		Dimension mySize = new Dimension(150, 200);
		setSize(mySize);
		setPreferredSize(mySize);
		String iconURL = ms.getMvThumb();
		try {
			ImageIcon imgicon = new ImageIcon(ImageIO.read(new URL(iconURL)));
			Image image = imgicon.getImage();
			Image reSized = image.getScaledInstance(150, 200, Image.SCALE_SMOOTH);
			imgicon = new ImageIcon(reSized);
			setIcon(imgicon);
		} catch (Exception e) {
			ImageIcon imgicon = new ImageIcon("./img/noImg.jpg");
			Image image = imgicon.getImage();
			Image reSized = image.getScaledInstance(170, 215, Image.SCALE_SMOOTH);
			imgicon = new ImageIcon(reSized);
			setIcon(imgicon);
		}

	}

	public MovieSearchInfo getMs() {
		return ms;
	}

	public void setMs(MovieSearchInfo ms) {
		this.ms = ms;
	}

}
