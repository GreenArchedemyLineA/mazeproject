package Maze;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Movable {

	private final int DELAY = 100;
	private int x;
	private int y;
	
	private int playerWidth;
	private int playerHeight;

	private boolean isLeft;
	private boolean isRight;
	private boolean space; // 포털타기
	private final int Speed = 3; // 캐릭터 이동 속도
	private final int Jumpspeed = 2; // 캐릭터 점프 속도

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
		Image resizeL = imageL.getScaledInstance(iconL.getIconWidth()/5, iconL.getIconHeight()/7, Image.SCALE_SMOOTH);
		Image resizeR = imageR.getScaledInstance(iconR.getIconWidth()/5, iconR.getIconHeight()/7, Image.SCALE_SMOOTH);
		ImageIcon resizeIconL = new ImageIcon(resizeL);
		ImageIcon resizeIconR = new ImageIcon(resizeR);
		
		this.playerL = resizeIconL;
		this.playerR = resizeIconR;
		
		this.isLeft = false;
		this.isRight = false;
		
		this.space = false;
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
		// 유주씨가 만들어놓은 어떠한 이벤트
		boolean success = this.playerLocationService.move(number);
		if(success) {
			switch(number) {
			case 1:
				this.x -= 180;
				setLocation(this.x, this.y);
				break;
			case 2:
				this.y -= 90;
				setLocation(this.x, this.y);
				break;
			case 3:
				this.x += 180;
				setLocation(this.x, this.y);
				break;
			case 4:
				this.y += 90;
				setLocation(this.x, this.y);
				break;
			}
		}
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
}
