package Maze;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class Player extends JLabel implements Movable {

	public int x;
	public int y;

	public boolean right; // 오른쪽으로
	public boolean left; // 왼쪽으로
	public boolean up; // 점프
	public boolean down; // 엎드리기
	public boolean space; // 포털타기
	public final int Speed = 3; // 캐릭터 이동 속도
	public final int Jumpspeed = 2; // 캐릭터 점프 속도

	public ImageIcon playerR, playerL;

	public Player() {
		initData();
		setInitLayout();
	}

	public void initData() {
		playerR = new ImageIcon("images/playerR.png");
		playerL = new ImageIcon("images/playerL.png");
		left = false;
		right = false;
		up = false;
		down = false;

	}

	public void setInitLayout() {
		x = 45;
		y = 40;
		setSize(50, 80); // 55 35
		setLocation(x, y);
		setIcon(playerR);
	}

	@Override
	public void right() {
		right = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (right) {
					setIcon(playerR);
					x += Speed;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} // end of while
			}
		}).start();
	}

	public void left() {
		left = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (left) {
					setIcon(playerL);
					x -= Speed;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} // end of while
			}
		}).start();

	}

	public void up() {
		up = true;
		new Thread(new Runnable() {
			@Override
			public void run() {
				while (up) {
					setIcon(playerL);
					x -= Speed;
					setLocation(x, y);
					try {
						Thread.sleep(10);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} // end of while
			}
		}).start();

	

	}

	public void down() {

	}

	public void speed() {

	}

	public void jumpspeed() {

	}

}
