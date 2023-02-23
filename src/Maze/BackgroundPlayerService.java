package Maze;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class BackgroundPlayerService {
	
	private BufferedImage image;
	private Player player;
	public BackgroundPlayerService(Player player) {
		this.player = player;
		try {
			this.image = ImageIO.read(new File("images/backgroundService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
