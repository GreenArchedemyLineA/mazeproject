package Maze;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Arrow extends JLabel implements Movable {

	private ImageIcon arrowL, arrowR, arrowU, arrowD;

	public Arrow() {
		initData();
		setInitLayout();
	}

	private void initData() {
		arrowL = new ImageIcon("images/arrowL.png");
		arrowR = new ImageIcon("images/arrowR.png");
		arrowU = new ImageIcon("images/arrowU.png");
		arrowD = new ImageIcon("images/arrowD.png");

	}

	private void setInitLayout() {
		setSize(90, 80);

	}

	@Override
	public void right() {
		setIcon(arrowR);
	}

	@Override
	public void left() {
		setIcon(arrowL);

	}

	@Override
	public void up() {
		setIcon(arrowU);

	}

	@Override
	public void down() {
		setIcon(arrowD);

	}
}
