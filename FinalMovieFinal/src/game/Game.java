package game;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import game.thread.BGM;
import game.thread.BoundCheck;
import game.thread.Jump;
import game.thread.MakingObjects;
import game.thread.MovingRock;

import java.awt.BorderLayout;
import java.awt.Rectangle;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.SystemColor;
import java.awt.Toolkit;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.JProgressBar;

public class Game extends JFrame implements KeyListener, Runnable {
	public JLabel lb_main;
	private JLabel lb_ground;
	public int yLoc;
	public static int xLoc;
	public int myY;
	private MakingObjects mr;
	public JPanel gmPanle;
	public boolean isJumped;
	public Game me;
	private JLabel lblNewLabel;
	private JLabel lblNewLabel_1;
	private JProgressBar progressBar;
	private int hp;

	public JProgressBar getProgressBar() {
		return progressBar;
	}

	public void setProgressBar(JProgressBar progressBar) {
		this.progressBar = progressBar;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getMyY() {
		return myY;
	}

	public void setMyY(int myY) {
		this.myY = myY;
	}

	public Game() {
		hp = 100;

		isJumped = false;

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(620, 360);

		gmPanle = new JPanel();
		gmPanle.setBackground(Color.WHITE);
		getContentPane().add(gmPanle, BorderLayout.CENTER);
		gmPanle.setLayout(null);

		lb_main = new JLabel("");
		lb_main.setBackground(SystemColor.activeCaptionText);
		xLoc = 110;
		yLoc = 133;
		myY = 133;
		lb_main.setBounds(xLoc, yLoc, 68, 128);
		lb_main.setIcon(new ImageIcon("img/game/dino.gif"));
		gmPanle.add(lb_main);

		progressBar = new JProgressBar();
		progressBar.setStringPainted(true);
		progressBar.setMaximum(100);
		progressBar.setValue(hp);
		progressBar.setBackground(new Color(0, 153, 51));
		progressBar.setForeground(new Color(0, 153, 51));
		progressBar.setBounds(14, 291, 196, 25);
		gmPanle.add(progressBar);
		////////////
		// JLabel lb_rockd = new JLabel();
		// lb_rockd.setBounds(600, 225, 61, 36);
		// lb_rockd.setIcon(new ImageIcon("img/rock.png"));
		// getContentPane().add(lb_rock);
		/////////////

		lb_ground = new JLabel("");
		lb_ground.setBounds(-12, 251, 646, 89);
		lb_ground.setIcon(new ImageIcon("img/game/groundBase.png"));
		gmPanle.add(lb_ground);

		setVisible(true);
		addKeyListener(this);

		mr = new MakingObjects(this);
		Thread th = new Thread(mr);
		th.start();
		me = this;

		Dimension d = Toolkit.getDefaultToolkit().getScreenSize();
		int locWidth = (int) ((d.getWidth() - 620) / 2);
		int locHeight = (int) ((d.getHeight() - 360) / 2);
		setLocation(locWidth, locHeight);

		Thread th2 = new Thread(this);
		th2.start();
		new Thread(new BGM(this)).start();

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		if (e.getKeyCode() == 32) {
			if (!isJumped) {
				new Thread(new Jump(this, lb_main, yLoc)).start();
				isJumped = true;
			}
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		boolean flag = true;
		while (flag) {
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			if (hp < 0) {
				JOptionPane.showMessageDialog(null, "게임오버~");
				flag = false;
				this.dispose();
			}
		}

	}
}
