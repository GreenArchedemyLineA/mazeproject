package Maze;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundPlayerService implements Runnable {

	private BufferedImage image;
	private Player player;

	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			this.image = ImageIO.read(new File("images/backgroundService2.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {

			Color rightColor = new Color(image.getRGB(player.getX() + 50, player.getY()));
			Color leftColor = new Color(image.getRGB(player.getX(), player.getY()));

			if (leftColor.getRed() != 0) {
				player.setLeft(false);

			} else if (rightColor.getRed() != 0) {
				player.setRight(false);
			}

		}

	}
}
