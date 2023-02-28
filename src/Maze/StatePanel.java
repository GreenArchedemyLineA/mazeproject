package Maze;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class StatePanel extends JPanel {
	private int redCount, blueCount;

	private Image redImage, blueImage;

	public StatePanel(RedKey redkey, BlueKey bluekey) {
		setFont(new Font("맑은 고딕", Font.BOLD, 24));
		this.redCount = redkey.getCount();
		this.blueCount = bluekey.getCount();

		this.redImage = new ImageIcon("images/redkey.png").getImage();
		this.blueImage = new ImageIcon("images/bluekey.png").getImage();
	}

	public void setKeyCount(Key key) {
		if (key instanceof RedKey) {
			if (this.redCount <= 0) {
				return;
			}
			this.redCount -= 1;
		} else {
			if (this.blueCount <= 0) {
				return;
			}
			this.blueCount -= 1;
		}
	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		if (this.redCount == 0 && this.blueCount == 0) {
			g.drawString("게임오버", 50, 60);
			g.drawString("5번을 눌러", 40, 90);
			g.drawString("게임 초기화", 35, 120);
		} else {
			g.drawImage(redImage, 10, 20, 60, 60, null);
			g.drawImage(blueImage, 10, 80, 60, 60, null);
			g.drawString(":", 100, 60);
			g.drawString(":", 100, 110);
			g.drawString(String.valueOf(redCount), 120, 60);
			g.drawString(String.valueOf(blueCount), 120, 120);
		}
	}

}
