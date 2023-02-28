package Maze;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
import javax.swing.JPanel;

public class WholeFrame extends JFrame {

	private PlayerLocationService playerLocationService;
	private ImageIcon changeScaleIcon;
	private JLabel backgroundMap;
	private JLabel[][] arrows = new JLabel[5][5];
	private int[][] records = new int[5][5];
	private Player player;
	private Arrow arrow;
	private Monster monster;

	private KeyService keyService;

	// RedKey:시계, BlueKey:반시계
	private RedKey redkey;
	private BlueKey bluekey;

	private StatePanel statePanel;
	private int backgroundMapWidth;
	private int backgroundMapHeight;
	private BGM backgroundMusicService;
	JButton button;

	/**
	 * 화살표 관련 설정 0: 왼쪽 1: 위쪽 2: 오른쪽 3: 아래쪽
	 */

	public WholeFrame() {
		initData();
		setInitLayout();
		Thread playerService = new Thread(new BackgroundPlayerService(player));
		Thread monsterService = new Thread(new BackgroundMonsterService(monster));
		playerService.start();
		monsterService.start();
		addEventListener(playerService, monsterService);
		managerThread();
	}

	private void initData() {
		this.playerLocationService = new PlayerLocationService();
		this.keyService = new KeyService();
		backgroundSetImageIcon();
		playerSetImageIcon();
		monsterSetImageIcon();
		gameArrowRandomSetting();
		userSetKey();
		setStatePanel();
		setMusicService();
		super.setDefaultCloseOperation(EXIT_ON_CLOSE);
		super.setContentPane(backgroundMap);
		super.setSize(this.backgroundMapWidth, this.backgroundMapHeight);
	}

	private void setInitLayout() {
		// 게임 클리어시 추가
		gameClearButton();		super.add(this.statePanel);
		super.setLayout(null);
		super.setResizable(false);
		super.setLocationRelativeTo(null);
		super.setVisible(true);
	}

	private void addEventListener(Thread playerThread, Thread monsterThread) {
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
					setArrowImageIcon(playerX, playerY, keyService.getDirectionService(redkey));
					statePanel.setKeyCount(redkey);
					repaint();
					break;
				case KeyEvent.VK_2:
					setArrowImageIcon(playerX, playerY, keyService.getDirectionService(bluekey));
					statePanel.setKeyCount(bluekey);
					repaint();
					break;
				case KeyEvent.VK_UP:
					if (playerX == 4 && playerY == 4 && records[playerY][playerX] == 3) {
						playerLocationService.setGameClear(true);
						player.setEnd(); // 끝난건알겠는데 뭔가 끝난걸 설정함
					} else {
						player.space(records[playerY][playerX]); // 이동하다 (부분적 의미)
					}
					break;
				case KeyEvent.VK_RIGHT:
					if (!player.isRight() && !player.isRightWallCrash()) {
						player.right();
					}
					break;
				case KeyEvent.VK_LEFT:
					if (!player.isLeft() && !player.isLeftWallCrash()) {
						player.left();
					}
					break;
				case KeyEvent.VK_5:
					reset();
					break;
				}
			}

			// 리셋
			public void reset() {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				backgroundMusicService.playStop();
				playerThread.interrupt();
				monsterThread.interrupt();
				dispose();
				new WholeFrame();
			}
		});

		button.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
	}

	// 설정하는 메소드
	private void backgroundSetImageIcon() {
		ImageIcon icon = new ImageIcon("images/background.png");
		Image backgroundImage = icon.getImage();
		this.backgroundMapWidth = icon.getIconWidth() / 2;
		this.backgroundMapHeight = icon.getIconHeight() / 2;
		Image changeScaleImage = backgroundImage.getScaledInstance(this.backgroundMapWidth, this.backgroundMapHeight,
				Image.SCALE_SMOOTH);
		this.changeScaleIcon = new ImageIcon(changeScaleImage);
		this.backgroundMap = new JLabel(changeScaleIcon);
	}

	private void playerSetImageIcon() {
		this.player = new Player(this.playerLocationService);
		this.backgroundMap.add(player);
	}

	private void monsterSetImageIcon() {
		this.monster = new Monster();
		this.backgroundMap.add(monster);
	}

	private void gameArrowRandomSetting() {
		this.arrow = new Arrow();
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

	private void userSetKey() {
		this.redkey = new RedKey();
		this.bluekey = new BlueKey();
	}

	private void setStatePanel() {
		this.statePanel = new StatePanel(this.redkey, this.bluekey);
		this.statePanel.setSize(200, 200);
		this.statePanel.setLocation(0, this.backgroundMapHeight - 200);
	}

	private void setMusicService() {
		this.backgroundMusicService = new BGM("backgroundmusic2.wav");
		this.button = new JButton("Game Clear!");
	}

	private void setArrowImageIcon(int playerLocationX, int playerLocationY, int direction) {
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

	private void gameClearButton() {
		if (playerLocationService.isGameClear()) {
			button.setBackground(Color.CYAN);
			button.setFocusable(false);
			button.setFont(new Font("맑은 고딕", Font.BOLD, 40));
			button.setSize(300, 150);
			button.setLocation(300, 250);
			add(button, 0);
		}
	}

	public void managerThread() {
		new Thread(() -> {
			while (!playerLocationService.isGameClear()) {
				try {
					Thread.sleep(10);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					break;
				}
			}
			setInitLayout();
		}).start();
	}

	public static void main(String[] args) {
		new WholeFrame();
	}
}