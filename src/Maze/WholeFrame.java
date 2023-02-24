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
	private ImageIcon changeScaleIcon;
	private JLabel backgroundMap;
	private JLabel[][] arrows = new JLabel[5][5];
	private int[][] records = new int[5][5];
	private Player player;
	private Arrow arrow;

	private KeyService keyService;

	// RedKey:시계, BlueKey:반시계
	private RedKey redkey;
	private BlueKey bluekey;

	private StatePanel statePanel;
	private int backgroundMapWidth;
	private int backgroundMapHeight;

	public WholeFrame() {
		initData();
		setInitLayout();
		addEventListener();
		mangagerThread();
		new Thread(new BackgroundPlayerService(player)).start();
	}

	private void initData() {
		this.playerLocationService = new PlayerLocationService();
		setSizeImageIcon(); // 복잡해서 함수로 따로빼냄
		this.arrow = new Arrow();
		this.backgroundMap = new JLabel(changeScaleIcon);
		this.player = new Player(this.playerLocationService);
		this.backgroundMap.add(player);
		this.keyService = new KeyService();
		this.redkey = new RedKey();
		this.bluekey = new BlueKey();
		arrowRandomSetting(); // 복잡해서 함수로 따로빼냄2
		this.statePanel = new StatePanel(this.redkey, this.bluekey);
		this.statePanel.setSize(200, 200);
		this.statePanel.setLocation(0, this.backgroundMapHeight - 200);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(backgroundMap);
		setSize(this.backgroundMapWidth, this.backgroundMapHeight);
	}

	private void setInitLayout() {
		add(this.statePanel);
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
				int playerX = playerLocationService.getPlayerX();
				int playerY = playerLocationService.getPlayerY();
				switch (e.getKeyCode()) {
				case KeyEvent.VK_1:
					setImageIcon(playerX, playerY, keyService.getDirectionService(redkey));
					statePanel.setKeyCount(redkey);
					repaint();
					break;
				case KeyEvent.VK_2:
					setImageIcon(playerX, playerY, keyService.getDirectionService(bluekey));
					statePanel.setKeyCount(bluekey);
					repaint();
					break;
				case KeyEvent.VK_UP:
					if(playerX == 4 && playerY == 4 && records[playerY][playerX] == 3) {
						player.setEnd();
						playerLocationService.setGameClear(true);
					}else {						
						player.space(records[playerY][playerX] + 1);
					}
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
				case KeyEvent.VK_5:
					reset();
					break;
				}
			}
			public void reset() {
				initData();
				setInitLayout();
			}
		});
	}

	public void setSizeImageIcon() {
		ImageIcon icon = new ImageIcon("images/background.png");
		Image backgroundImage = icon.getImage();
		this.backgroundMapWidth = icon.getIconWidth() / 2;
		this.backgroundMapHeight = icon.getIconHeight() / 2;
		Image changeScaleImage = backgroundImage.getScaledInstance(this.backgroundMapWidth, this.backgroundMapHeight,
				Image.SCALE_SMOOTH);
		this.changeScaleIcon = new ImageIcon(changeScaleImage);
	}

	public void arrowRandomSetting() {
		Random rand = new Random();
		int arrowX = 30;
		int arrowY = 30;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				int randomNumber = rand.nextInt(4);
				arrows[i][j] = new JLabel(this.arrow.getArrowImages(randomNumber));
				records[i][j] = randomNumber;
				arrows[i][j].setSize(90, 80);
				backgroundMap.add(arrows[i][j]);
				switch (randomNumber) {
				case 0:
					arrows[i][j].setLocation(arrowX, arrowY);
					break;
				case 1:
					arrows[i][j].setLocation(arrowX - 10, arrowY - 10);
					break;
				case 2:
					arrows[i][j].setLocation(arrowX, arrowY - 20);
					break;
				case 3:
					arrows[i][j].setLocation(arrowX + 10, arrowY - 10);
					break;
				}
				arrowX += 180;
				if (j == 4) {
					arrowX = 30;
					arrowY += 90;
				}
			}
		}
	}

	public void setImageIcon(int playerLocationX, int playerLocationY, int direction) {
		if (direction == 0) {
			return;
		} else {
			if (direction < 0) {
				int number = records[playerLocationY][playerLocationX] += direction;
				switch (number) {
				case -1:
					records[playerLocationY][playerLocationX] = 3;
					arrows[playerLocationY][playerLocationX].setLocation(
							arrows[playerLocationY][playerLocationX].getX() + 10,
							arrows[playerLocationY][playerLocationX].getY() - 10);
					arrows[playerLocationY][playerLocationX]
							.setIcon(arrow.getArrowImages(records[playerLocationY][playerLocationX]));
					return;
				case 0:
					arrows[playerLocationY][playerLocationX].setLocation(
							arrows[playerLocationY][playerLocationX].getX() + 10,
							arrows[playerLocationY][playerLocationX].getY() + 10);
					arrows[playerLocationY][playerLocationX]
							.setIcon(arrow.getArrowImages(records[playerLocationY][playerLocationX]));
					return;
				case 1:
					arrows[playerLocationY][playerLocationX].setLocation(
							arrows[playerLocationY][playerLocationX].getX() - 10,
							arrows[playerLocationY][playerLocationX].getY() + 10);
					arrows[playerLocationY][playerLocationX]
							.setIcon(arrow.getArrowImages(records[playerLocationY][playerLocationX]));
					return;
				case 2:
					arrows[playerLocationY][playerLocationX].setLocation(
							arrows[playerLocationY][playerLocationX].getX() - 10,
							arrows[playerLocationY][playerLocationX].getY() - 10);
					arrows[playerLocationY][playerLocationX]
							.setIcon(arrow.getArrowImages(records[playerLocationY][playerLocationX]));
					return;
				}
			} else {
				int number = records[playerLocationY][playerLocationX] += direction;
				switch (number) {
				case 1:
					arrows[playerLocationY][playerLocationX].setLocation(
							arrows[playerLocationY][playerLocationX].getX() - 10,
							arrows[playerLocationY][playerLocationX].getY() - 10);
					arrows[playerLocationY][playerLocationX]
							.setIcon(arrow.getArrowImages(records[playerLocationY][playerLocationX]));
					return;
				case 2:
					arrows[playerLocationY][playerLocationX].setLocation(
							arrows[playerLocationY][playerLocationX].getX() + 10,
							arrows[playerLocationY][playerLocationX].getY() - 10);
					arrows[playerLocationY][playerLocationX]
							.setIcon(arrow.getArrowImages(records[playerLocationY][playerLocationX]));
					return;
				case 3:
					arrows[playerLocationY][playerLocationX].setLocation(
							arrows[playerLocationY][playerLocationX].getX() + 10,
							arrows[playerLocationY][playerLocationX].getY() + 10);
					arrows[playerLocationY][playerLocationX]
							.setIcon(arrow.getArrowImages(records[playerLocationY][playerLocationX]));
					return;
				case 4:
					records[playerLocationY][playerLocationX] = 0;
					arrows[playerLocationY][playerLocationX].setLocation(
							arrows[playerLocationY][playerLocationX].getX() - 10,
							arrows[playerLocationY][playerLocationX].getY() + 10);
					arrows[playerLocationY][playerLocationX]
							.setIcon(arrow.getArrowImages(records[playerLocationY][playerLocationX]));
					return;
				}
			}
		}

	}

	public void mangagerThread() {
		new Thread(() -> {
			while (playerLocationService.isGameClear()) {
					try {
						Thread.sleep(100);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			}
			System.out.println("겜 클리어~!!");
		}).start();
	}

	public static void main(String[] args) {
		new WholeFrame();
	}
}