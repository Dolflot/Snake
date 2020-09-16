package model;

public class Spot {
	
	private int xPos;
	private int yPos;
	private Marker marker;
	
	public Spot(int xPos, int yPos, Marker marker) {
		this.xPos = xPos;
		this.yPos = yPos;
		this.marker = marker;
	}
	
	// Getters
	
	public int getyPos() {
		return yPos;
	}
	
	public int getxPos() {
		return xPos;
	}
	
	public Marker getMarker() {
		return marker;
	}

}
