package Maze;

public interface Movable {

	/**
	 * 우측으로 이동할 때 어떻게 구현하실지에 대해 작성하시면됩니다~
	 */
	default public void right() {
	};

	/**
	 * 좌측으로 이동할 때 어떻게 구현하실지에 대해 작성하시면됩니다~
	 */
	default public void left() {
	};

	/**
	 * 위로 이동할 때 어떻게 구현하실지에 대해 작성하시면됩니다~
	 */
	default public void up() {
	};

	/**
	 * 아래로 이동할 때 어떻게 구현하실지에 대해 작성하시면됩니다~
	 */
	default public void down() {
	};
}
