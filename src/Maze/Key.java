package Maze;

public class Key {
	private int direction;
	private int count;
	public Key() {
		this.count = 6;
	}
	
	public boolean useKey() {
		if(count == 0) {
			return false;
		}
		this.count -= 1;
		return true;
	}
	public int getDirection() {
		return this.direction;
	}
	
	public void setDirection(int direction) {
		this.direction = direction;
	}
	
	public int getCount() {
		return this.count;
	}
}
