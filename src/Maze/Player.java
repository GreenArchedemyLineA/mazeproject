package Maze;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Movable {

	private final int DELAY = 30;
	private int x;
	private int y;

	private boolean isLeft;
	private boolean isRight;
	private final int Speed = 1; // 캐릭터 이동 속도
	private boolean leftWallCrash;
	private boolean rightWallCrash;

	private ImageIcon playerR, playerL;
	private PlayerLocationService playerLocationService;

	public Player(PlayerLocationService playerLocationService) {
		this.playerLocationService = playerLocationService;
		initData();
		setInitLayout();
	}

	public void initData() {
		ImageIcon iconL = new ImageIcon("images/playerL.png");
		ImageIcon iconR = new ImageIcon("images/playerR.png");
		Image imageL = iconL.getImage();
		Image imageR = iconR.getImage();
		Image resizeL = imageL.getScaledInstance(iconL.getIconWidth() / 5, iconL.getIconHeight() / 7,
				Image.SCALE_SMOOTH);
		Image resizeR = imageR.getScaledInstance(iconR.getIconWidth() / 5, iconR.getIconHeight() / 7,
				Image.SCALE_SMOOTH);
		ImageIcon resizeIconL = new ImageIcon(resizeL);
		ImageIcon resizeIconR = new ImageIcon(resizeR);

		this.playerL = resizeIconL;
		this.playerR = resizeIconR;

		this.isLeft = false;
		this.isRight = false;
		this.leftWallCrash = false;
		this.rightWallCrash = false;
	}

	public void setInitLayout() {
		x = 45;
		y = 45;
		setSize(40, 55); // 55 35
		setLocation(x, y);
		setIcon(playerR);
	}

	@Override
	public void right() {
		isRight = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				setIcon(playerR);
				while (isRight) {
					x += Speed;
					setLocation(x, y);
					try {
						Thread.sleep(DELAY);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} // end of while
			}
		}).start();
	}

	public void left() {
		isLeft = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				setIcon(playerL);
				while (isLeft) {
					x -= Speed;
					setLocation(x, y);
					try {
						Thread.sleep(DELAY);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} // end of while
			}
		}).start();

	}

	public void space(int number) {
		boolean success = this.playerLocationService.move(number);
		if (success) {
			switch (number) {
			case 0:
				this.x -= 180;
				setLocation(this.x, this.y);
				break;
			case 1:
				this.y -= 90;
				setLocation(this.x, this.y);
				break;
			case 2:
				this.x += 180;
				setLocation(this.x, this.y);
				break;
			case 3:
				this.y += 90;
				setLocation(this.x, this.y);
				break;
			}
		}
	}

	public void setEnd() {
		this.y += 165;
		setLocation(this.x, this.y);
		setIcon(null);
	}

	public boolean isLeft() {
		return isLeft;
	}

	public void setLeft(boolean isLeft) {
		this.isLeft = isLeft;
	}

	public boolean isRight() {
		return isRight;
	}

	public void setRight(boolean isRight) {
		this.isRight = isRight;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public boolean isLeftWallCrash() {
		return leftWallCrash;
	}

	public void setLeftWallCrash(boolean leftWallCrash) {
		this.leftWallCrash = leftWallCrash;
	}

	public boolean isRightWallCrash() {
		return rightWallCrash;
	}

	public void setRightWallCrash(boolean rightWallCrash) {
		this.rightWallCrash = rightWallCrash;
	}

}
