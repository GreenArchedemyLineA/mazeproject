package Maze;

import javax.swing.JLabel;

public class KeyService {

	public int getDirectionService(Key key) {
		boolean check = key.useKey();
		if (check) {
			return key.getDirection();
		} else {
			return 0;
		}
	}
}
