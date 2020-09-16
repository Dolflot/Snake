package model;

public class BodyPart {

	private int xPos;
	private int yPos;
	private Direction direction;

	public BodyPart(int x, int y, Direction direction) {

		setxPos(x);
		setyPos(y);
		setDirection(direction);

	}
	
	// Getters

	public int getxPos() {
		return xPos;
	}

	public Direction getDirection() {
		return direction;
	}

	public int getyPos() {
		return yPos;
	}
	
	// Setters

	public void setyPos(int yPos) {
		this.yPos = yPos;
	}

	public void setxPos(int xPos) {
		this.xPos = xPos;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

}
