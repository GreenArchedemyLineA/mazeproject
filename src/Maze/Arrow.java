package Maze;

import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;


// 왼쪽: 0, 우측:1, 위:2, 아래:3

public class Arrow extends JLabel{

	private ImageIcon[] arrowImages = new ImageIcon[4];
	private String[] imageName = {"images/arrowL.png", "images/arrowR.png", "images/arrowU.png", "images/arrowD.png"};
	
	public Arrow() {
		initData();
//		setInitLayout();
	}

	private void initData() {
		for(int i = 0; i<imageName.length; i++) {
//			System.out.println("images/arrow+"+ imageName[i] +".png");
			ImageIcon arrowOriginIcon = new ImageIcon(imageName[i]);
			Image arrowImage = arrowOriginIcon.getImage();

			Image changeScaleArrowImage = arrowImage.getScaledInstance(arrowOriginIcon.getIconWidth() / 2,
					arrowOriginIcon.getIconHeight() / 2, Image.SCALE_SMOOTH);
			ImageIcon arrowIcon = new ImageIcon(changeScaleArrowImage);
			arrowImages[i] = arrowIcon;
		}		
	}

	private void setInitLayout() {
		setSize(90, 80);
	}

	public ImageIcon getArrowImages(int number) {
		return arrowImages[number];
	}
	
	public void setArrowImages(int number) {
		setIcon(arrowImages[number]);
	}
}
