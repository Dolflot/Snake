package model;

public enum Marker {
	MOUSE ("mouse"),
	BEAR ("bear"),
	FIRE ("fire");
	
	private final String marker;
	
	Marker(String marker){
		this.marker = marker;
	}

	public String getMarker() {
		return marker;
	}

}
