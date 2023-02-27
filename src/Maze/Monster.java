package Maze;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Monster extends JLabel implements Movable {
	private int x;
	private int y;
	private boolean isLeft;
	private boolean isRight;
	private int LWidth;
	private int LHeight;
	private int RWidth;
	private int RHeight;

	private ImageIcon monsterR, monsterL;

	public Monster() {
		initData();
		setInitLayout();
		left();
	}

	public void initData() {

		ImageIcon iconL = new ImageIcon("images/monsterL.png");
		ImageIcon iconR = new ImageIcon("images/monsterR.png");
		Image imageL = iconL.getImage();
		Image imageR = iconR.getImage();
		Image resizeL = imageL.getScaledInstance(iconL.getIconWidth() / 5, iconL.getIconHeight() / 7,
				Image.SCALE_SMOOTH);
		Image resizeR = imageR.getScaledInstance(iconR.getIconWidth() / 5, iconR.getIconHeight() / 7,
				Image.SCALE_SMOOTH);
		ImageIcon resizeIconL = new ImageIcon(resizeL);
		ImageIcon resizeIconR = new ImageIcon(resizeR);
		this.monsterL = resizeIconL;
		this.monsterR = resizeIconR;
		this.isLeft = false;
		this.isRight = false;

	}

	public void setInitLayout() {
		x = 550;
		y = 550;
		setSize(120, 120);

		setLocation(500, 550);
		setIcon(monsterL);

	}

	@Override
	public void left() {
		isLeft = true;
		new Thread(() -> {
			while (isLeft) {
				x = x - 3;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	@Override
	public void right() {
		isRight = true;
		new Thread(() -> {
			while (isRight) {
				x = x + 3;
				setLocation(x, y);
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();

	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}

	public ImageIcon getMonsterR() {
		return monsterR;
	}

	public ImageIcon getMonsterL() {
		return monsterL;
	}
}

//		}

//	
//	
//	}
