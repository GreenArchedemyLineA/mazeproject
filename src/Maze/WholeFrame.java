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
	private ImageIcon arrowIcon;
	private JLabel[] arrows = new JLabel[25];

	private int backgroundMapWidth;
	private int backgroundMapHeight;
	private Player player; // ===배진석 작성===

	public WholeFrame() {
		this.playerLocationService = new PlayerLocationService();
		// =========김유주 작성===========

		initData();
		setInitLayout();
		addEventListener();
	}

	private void initData() {
		ImageIcon icon = new ImageIcon("images/background.png");
		Image backgroundImage = icon.getImage();
		this.backgroundMapWidth = icon.getIconWidth() / 2;
		this.backgroundMapHeight = icon.getIconHeight() / 2;
		Image changeScaleImage = backgroundImage.getScaledInstance(this.backgroundMapWidth, this.backgroundMapHeight,
				Image.SCALE_SMOOTH);
		ImageIcon changeScaleIcon = new ImageIcon(changeScaleImage);
		ImageIcon arrowOriginIcon = new ImageIcon("images/arrow.png");
		Image arrowImage = arrowOriginIcon.getImage();

		Image changeScaleArrowImage = arrowImage.getScaledInstance(arrowOriginIcon.getIconWidth() / 2,
				arrowOriginIcon.getIconHeight() / 2, Image.SCALE_SMOOTH);

		this.backgroundMap = new JLabel(changeScaleIcon);
		this.arrowIcon = new ImageIcon(changeScaleArrowImage);
		this.player = new Player(this.playerLocationService);
		this.backgroundMap.add(player);
		int arrowX = 30;
		int arrowY = 30;
		for (int i = 0; i < 25; i++) {
			arrows[i] = new JLabel(this.arrowIcon);
			arrows[i].setSize(90, 80);
			backgroundMap.add(arrows[i]);
			arrows[i].setLocation(arrowX, arrowY);
			arrowX += 180;
			if (i % 5 == 4) {
				arrowX = 30;
				arrowY += 90;

			}
		}
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

	public void addEventListener() {
		this.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_RIGHT:
					player.setRight(false);
					break;
				case KeyEvent.VK_LEFT:
					player.setLeft(false);
					break;
				}
			}

			@Override
			public void keyPressed(KeyEvent e) {
				switch (e.getKeyCode()) {
				case KeyEvent.VK_UP:
					player.space();
					break;
				case KeyEvent.VK_RIGHT:
					if (!player.isRight()) {
						player.right();
					}
					break;
				case KeyEvent.VK_LEFT:
					if (!player.isLeft()) {
						player.left();
					}
					break;
				}

			}
		});
	}

	public static void main(String[] args) {
		new WholeFrame();
	}
}
