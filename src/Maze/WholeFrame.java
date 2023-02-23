package Maze;

import java.awt.Image;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class WholeFrame extends JFrame {

	private PlayerLocationService playerLocationService;
	private JLabel backgroundMap;
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
		// ===========이현서 작성 ========================
		ImageIcon icon = new ImageIcon("images/background.png");
		Image backgroundimage = icon.getImage();
		this.backgroundMapWidth = icon.getIconWidth() / 2;
		this.backgroundMapHeight = icon.getIconHeight() / 2;
		Image changeScaleImage = backgroundimage.getScaledInstance(this.backgroundMapWidth, this.backgroundMapHeight,
				Image.SCALE_SMOOTH);
		ImageIcon changeScaleIcon = new ImageIcon(changeScaleImage);
//		backgroundMap = new JLabel(new ImageIcon("images/background.png"));
		backgroundMap = new JLabel(changeScaleIcon);
		// ============================================
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setContentPane(backgroundMap);
		setSize(this.backgroundMapWidth, this.backgroundMapHeight);
		//==================배진석 작성=========================
		player = new Player();
		

	}

	private void setInitLayout() {
		setLayout(null);
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);
		add(player);
		

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
					System.out.println("위");
					player.up();
					break;
				case KeyEvent.VK_DOWN:
					System.out.println("아래");
					player.down();
					break;
				case KeyEvent.VK_RIGHT:
					System.out.println("오른쪽");
					player.right();
					break;
				case KeyEvent.VK_LEFT:
					System.out.println("왼쪽");
					player.left();
					break;
				}
			}
		});
	}

	public static void main(String[] args) {
		new WholeFrame();
	}
}
