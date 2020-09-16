package model;

public enum Direction {
	UP ("up"),
	RIGHT ("right"),
	DOWN ("down"),
	LEFT ("left");
	
	private final String direction;
	
	Direction(String direction){
		this.direction = direction;
	}

	public String getDirection() {
		return direction;
	}

}
