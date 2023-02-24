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
//			System.out.println(player.getX());
//			System.out.println("RGB: " + Integer.toHexString(image.getRGB(player.getX()+100, player.getY()-20)));
			System.out.println(new Color(image.getRGB(player.getX(), player.getY())).getRed());
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
