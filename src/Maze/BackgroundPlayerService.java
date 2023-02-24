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
			this.image = ImageIO.read(new File("images/backgroundService.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		System.out.println("77777");
		while (true) {
			Color leftColor = new Color(image.getRGB(player.getX(), player.getY()));
			Color rightColor = new Color(image.getRGB(player.getX(), player.getY()));
			int leftWallColor = image.getRGB(player.getX() + 20, player.getY() + 55);
			int rightWallColor = image.getRGB(player.getX() + 40, player.getY() + 55);
//			System.out.println(player.getX());
			int Colorcheck = new Color(image.getRGB(player.getX()+50, player.getY())).getRed();
			
			System.out.println(player.getY());
			System.out.println(Colorcheck);
//			if (leftWallColor + rightWallColor == -18611714) {
//				try {
//					Thread.sleep(3000);
//				} catch (InterruptedException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//				System.out.println("충돌");

		}

//			int ColorLeft = image.getRGB(player.getX(), player.getY());
//			int ColorRight = image.getRGB(player.getX() + , player.getY()  );
//			System.out.println(ColorLeft);
//			System.out.println(player.getX());

	}

}
