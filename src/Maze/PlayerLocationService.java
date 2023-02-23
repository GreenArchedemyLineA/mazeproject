package Maze;

public class PlayerLocationService implements Movable {
	private final int MAPSIZE = 5;
	private int playerX, playerY;
	private int[][] mazeArr = new int[MAPSIZE][MAPSIZE];
	private boolean gameClear;

	public PlayerLocationService() {
		this.playerX = 0;
		this.playerY = 0;
		this.gameClear = false;
		mazeInitial(this.mazeArr);
	}

	public int[][] mazeInitial(int[][] mazeArr) {
		for (int i = 0; i < mazeArr.length; i++) {
			for (int j = 0; j < mazeArr[0].length; j++) {
				mazeArr[i][j] = 0;
			}
		}
		mazeArr[this.playerX][this.playerY] = 1;
		return mazeArr;
	}

	/**
	 *  로직 테스트하는 코드(Test Code)
	 */
	public void testMazeArr() {
		for (int i = 0; i < this.mazeArr.length; i++) {
			for (int j = 0; j < this.mazeArr[0].length; j++) {
				System.out.print(this.mazeArr[i][j]);
			}
			System.out.println();
		}
		System.out.println("===============================");
		move(2);
		
		for (int i = 0; i < this.mazeArr.length; i++) {
			for (int j = 0; j < this.mazeArr[0].length; j++) {
				System.out.print(this.mazeArr[i][j]);
			}
			System.out.println();
		}
	}

	public void setMazeArr(int[][] mazeArr) {
		this.mazeArr = mazeArr;
	}

	// MOVE
	public void move(int directionState) {
		switch (directionState) {
		case 1:
			left();
			break;
		case 2:
			right();
			break;
		case 3:
			up();
			break;
		case 4:
			down();
			break;
		}
	}

	@Override
	public void left() {
		this.mazeArr[this.playerY][this.playerX] = 0;
		this.playerX -= 1;
		try {
			this.mazeArr[this.playerY][this.playerX] = 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			this.playerX = 0;
			this.mazeArr[this.playerY][this.playerX] = 1;
		} finally {
			this.gameClear = gameWinCheck(this.playerX, this.playerY);
		}
	}

	@Override
	public void right() {
		this.mazeArr[this.playerY][this.playerX] = 0;
		this.playerX += 1;
		try {
			this.mazeArr[this.playerY][this.playerX] = 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			this.playerX = MAPSIZE - 1;
			this.mazeArr[this.playerY][this.playerX] = 1;
		} finally {
			this.gameClear = gameWinCheck(this.playerX, this.playerY);
		}
	}

	@Override
	public void up() {
		this.mazeArr[this.playerY][this.playerX] = 0;
		this.playerY -= 1;
		try {
			this.mazeArr[this.playerY][this.playerX] = 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			this.playerY = 0;
			this.mazeArr[this.playerY][this.playerX] = 1;
		}
	}

	@Override
	public void down() {
		this.mazeArr[this.playerY][this.playerX] = 0;
		this.playerY += 1;
		try {
			this.mazeArr[this.playerY][this.playerX] = 1;
		} catch (ArrayIndexOutOfBoundsException e) {
			this.playerY = MAPSIZE - 1;
			this.mazeArr[this.playerY][this.playerX] = 1;
		} finally {
			this.gameClear = gameWinCheck(this.playerX, this.playerY);
		}
	}

	public boolean gameWinCheck(int playerX, int playerY) {
		if (playerX == MAPSIZE && playerY == MAPSIZE) {
			return true;
		} else {
			return false;
		}
	}
}
