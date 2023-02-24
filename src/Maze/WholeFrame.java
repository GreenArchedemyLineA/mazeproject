package Maze;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class WholeFrame extends JFrame {

	private PlayerLocationService playerLocationService;
	private JLabel backgroundMap;
	private JLabel[][] arrows = new JLabel[5][5];
	private int[][] records = new int[5][5];
	private Player player;
	private Arrow arrow;


	private int backgroundMapWidth;
	private int backgroundMapHeight;

	public WholeFrame() {
		this.playerLocationService = new PlayerLocationService();
		// =========김유주 작성===========

		initData();
		setInitLayout();
		addEventListener();
		new Thread(new BackgroundPlayerService(player)).start();
	}

	private void initData() {
		Random rand = new Random();
		this.arrow = new Arrow();
		ImageIcon icon = new ImageIcon("images/backgroundService.png");
		Image backgroundImage = icon.getImage();
		this.backgroundMapWidth = icon.getIconWidth() / 2;
		this.backgroundMapHeight = icon.getIconHeight() / 2;
		Image changeScaleImage = backgroundImage.getScaledInstance(this.backgroundMapWidth, this.backgroundMapHeight,
				Image.SCALE_SMOOTH);
		ImageIcon changeScaleIcon = new ImageIcon(changeScaleImage);

		backgroundMap = new JLabel(changeScaleIcon);

		// ============================================

//		this.backgroundMap = new JLabel(changeScaleIcon);
		this.player = new Player(this.playerLocationService);
		this.backgroundMap.add(player);
		int arrowX = 30;
		int arrowY = 30;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				int randomNumber = rand.nextInt(4);

				arrows[j][i] = new JLabel(this.arrow.getArrowImages(randomNumber));
				records[j][i] = randomNumber;
				arrows[j][i].setSize(90, 80);
				backgroundMap.add(arrows[j][i]);
				switch (randomNumber) {
				// left
				case 0:
					arrows[j][i].setLocation(arrowX, arrowY);
					break;
				// right
				case 1:
					arrows[j][i].setLocation(arrowX, arrowY-20);
					break;
				// up
				case 2:
					arrows[j][i].setLocation(arrowX-10, arrowY-10);
					break;
				// down
				case 3:
					arrows[j][i].setLocation(arrowX+10, arrowY-10);
					break;
				}
				arrowX += 180;
				if (j == 4) {
					arrowX = 30;
					arrowY += 90;

				}
			}
		}

//		backgroundMap = new JLabel(new ImageIcon("images/background.png"));
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
					playerLocationService.testMazeArr();
					int playerX = playerLocationService.getPlayerX();

					int playerY = playerLocationService.getPlayerY();				
					player.space(records[playerY][playerX]+1);
					arrows[0][0].setIcon(arrow.getArrowImages(0));
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