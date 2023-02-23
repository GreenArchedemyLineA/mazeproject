package Maze;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WholeFrame extends JFrame {

	private PlayerLocationService playerLocationService;
	private JLabel backgroundMap;
	private JLabel[] arrows = new JLabel[25];

	private int backgroundMapWidth;
	private int backgroundMapHeight;
	//private Player player; // ===배진석 작성=== 

	public WholeFrame() {
		this.playerLocationService = new PlayerLocationService();
//		this.playerLocationService.getMazeArr();
		// =========김유주 작성===========

		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		// ===========이현서 작성 ========================
		ImageIcon icon = new ImageIcon("images/background.png");
		Image backgroundimage = icon.getImage();
		this.backgroundMapWidth = icon.getIconWidth() / 2;
		this.backgroundMapHeight = icon.getIconHeight() / 2;
		Image changeScaleImage = backgroundimage.getScaledInstance(this.backgroundMapWidth, this.backgroundMapHeight,
				Image.SCALE_SMOOTH);
		ImageIcon changeScaleIcon = new ImageIcon(changeScaleImage);
		backgroundMap = new JLabel(changeScaleIcon);
		// ============================================

		// ===================김유주 작성==================
		int arrowX = 30;
		int arrowY = 30;
		for (int i = 0; i < 25; i++) {
			arrows[i] = new JLabel(new ImageIcon("images/arrow.png"));
			arrows[i].setSize(100, 100);
			backgroundMap.add(arrows[i]);
			arrows[i].setLocation(arrowX, arrowY);
			arrowX += 180;
			if(i>=6) {
				arrowX=30;
				arrows[i].setLocation(arrowX,arrowY+90);
				arrowX+=180;
				i++;
			}
			
		}
//		for (int i = 5; i < 10; i++) {
//			arrows[i] = new JLabel(new ImageIcon("images/arrow.png"));
//			arrows[i].setSize(100, 100);
//			backgroundMap.add(arrows[i]);
//			arrows[i].setLocation(arrowX, arrowY+40);
//			
//			
//		}

		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(backgroundMap);
		setSize(this.backgroundMapWidth, this.backgroundMapHeight);

	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

	}
	// ====================================//

	public void addEventListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				// TODO Auto-generated method stub
				super.keyReleased(e);
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					playerLocationService.move(1);
					break;
				}
				
			}
		});
	}

	public static void main(String[] args) {
		new WholeFrame();
	}
}
