package Maze;

public class PlayerLocationService implements Movable {
	private final int MAPSIZE = 5;
	private int playerX, playerY;
	private int[][] mazeArr = new int[MAPSIZE][MAPSIZE];
	private boolean gameClear;
	private boolean movePossible;

	public PlayerLocationService() {
		this.playerX = 0;
		this.playerY = 0;
		this.gameClear = false;
		this.movePossible = true;
		this.mazeArr = mazeInitial();
	}

	public int[][] mazeInitial() {
		int[][] mazeArr = new int[this.MAPSIZE][this.MAPSIZE]; 
		for (int i = 0; i < mazeArr.length; i++) { // 배열가로길이
			for (int j = 0; j < mazeArr[0].length; j++) {
				mazeArr[i][j] = 0;
			}
		}
		mazeArr[this.playerX][this.playerY] = 1;
		return mazeArr;
	}

	/**
	 * 배열창 확인하는 콘솔창 화면 코드(Test Code)
	 */
	public void testMazeArr() {
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
	public boolean move(int directionState) {
		switch (directionState) {
		case 0:
			left();
			break;
		case 1:
			up();
			break;
		case 2:
			right();
			break;
		case 3:
			down();
			break;
		}
		return this.movePossible;
	}

	@Override
	public void left() {
		this.mazeArr[this.playerY][this.playerX] = 0;
		this.playerX -= 1;
		try {
			this.mazeArr[this.playerY][this.playerX] = 1;
			this.movePossible = true;
		} catch (ArrayIndexOutOfBoundsException e) {
			this.playerX = 0;
			this.mazeArr[this.playerY][this.playerX] = 1;
			this.movePossible = false;
		}
	}

	@Override
	public void right() {
		this.mazeArr[this.playerY][this.playerX] = 0;
		this.playerX += 1;
		try {
			this.mazeArr[this.playerY][this.playerX] = 1;
			this.movePossible = true;
		} catch (ArrayIndexOutOfBoundsException e) {
			this.playerX -= 1;
			this.mazeArr[this.playerY][this.playerX] = 1;
			this.movePossible = false;
		}
	}

	@Override
	public void up() {
		this.mazeArr[this.playerY][this.playerX] = 0;
		this.playerY -= 1;
		try {
			this.mazeArr[this.playerY][this.playerX] = 1;
			this.movePossible = true;
		} catch (ArrayIndexOutOfBoundsException e) {
			this.playerY = 0;
			this.mazeArr[this.playerY][this.playerX] = 1;
			this.movePossible = false;
		}
	}

	@Override
	public void down() {
		this.mazeArr[this.playerY][this.playerX] = 0;
		this.playerY += 1;
		try {
			this.mazeArr[this.playerY][this.playerX] = 1;
			this.movePossible = true;
		} catch (ArrayIndexOutOfBoundsException e) {
			this.playerY = MAPSIZE - 1;
			this.mazeArr[this.playerY][this.playerX] = 1;
			this.movePossible = false;
		}
	}

	public int getPlayerX() {
		return playerX;
	}

	public int getPlayerY() {
		return playerY;
	}

	public boolean isGameClear() {
		return this.gameClear;
	}

	public void setGameClear(boolean gameClear) {
		this.gameClear = gameClear;
	}
}
