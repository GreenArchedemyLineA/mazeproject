package Maze;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundMonsterService implements Runnable {

	private BufferedImage image;
	private Monster monster;

	public BackgroundMonsterService(Monster monster) {
		this.monster = monster;
		try {
			this.image = ImageIO.read(new File("images/backgroundService2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			Color rightColor = new Color(image.getRGB(monster.getX() + 80, monster.getY()));
			Color leftColor = new Color(image.getRGB(monster.getX() + 60, monster.getY()));
			
			if (leftColor.getRed() != 0 && rightColor.getRed() != 0) {
				if (monster.isLeft()) {
					monster.setIcon(monster.getMonsterR());
					monster.setLeft(false);
					monster.right();
					
				} else {
					monster.setIcon(monster.getMonsterL());
					monster.setRight(false);
					monster.left();
				}

			}

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				break;
			}
		}

	}
}
