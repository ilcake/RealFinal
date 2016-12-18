package client;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Loading extends JLabel {
	ImageIcon loading;

	public Loading() {
		loading = new ImageIcon("img/loading2.gif");
		this.setIcon(loading);
		this.setBounds(28, 25, 440, 458);
	}

}