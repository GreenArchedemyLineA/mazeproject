package Maze;

import java.io.File;
import java.io.IOException;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineEvent;
import javax.sound.sampled.LineListener;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class BGM {
	private AudioInputStream bgm;
	private File musicFile;
	private Clip bgmClip;

	public BGM(String filePath) {
		try {
			this.musicFile = new File(filePath);
			this.bgm = AudioSystem.getAudioInputStream(this.musicFile);
			playSound();
		} catch (UnsupportedAudioFileException e1) {
			System.out.println("지원하지 않는 파일 형식입니다.");
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public void playSound() {
		try {
			this.bgmClip = AudioSystem.getClip();
			bgmClip.addLineListener(new LineListener() {
				@Override
				public void update(LineEvent event) {
					if (event.getType() == LineEvent.Type.STOP) {
						bgmClip.close();
					}
				}
			});
			bgmClip.open(this.bgm);
			bgmClip.start();
		} catch (LineUnavailableException e1) {
			e1.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		}
	}

	public void playStop() {
		bgmClip.stop();
	}
}
