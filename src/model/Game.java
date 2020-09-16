package model;

import java.util.ArrayList;
import java.util.Random;

public class Game {

	private Snake snake;
	private ArrayList<Spot> spots = new ArrayList<Spot>();

	public Game() {
		snake = new Snake();
	}

	// Adds a spot on a random place

	public void addSpot(Marker marker) {

		Random rnd = new Random();

		int x = 0;
		int y = 0;
		boolean gotPos = false;

		// Checks if it is a valid position to place a spot

		while (!gotPos) {
			x = rnd.nextInt(19) * 40;
			y = rnd.nextInt(15) * 40;

			gotPos = checkSquare(x, y);
		}

		Spot spot = new Spot(x, y, marker);
		spots.add(spot);

	}

	// Checks if it is a valid position to place a spot

	public Boolean checkSquare(int x, int y) {

		// Checks if the position is on the edge of the playingfield.

		if (x == 0 || x == 720 || y == 0 || y == 560) {
			return false;
		}

		// Checks if the position is on a bodypart or next to a bodypart.

		for (int i = 0; i < snake.getSnakeBody().size(); i++) {

			BodyPart temp = snake.getSnakeBody().get(i);

			if (temp.getxPos() - 40 < x && x < temp.getxPos() + 40
					|| temp.getyPos() - 40 < y && y < temp.getyPos() + 40) {
				return false;
			}
		}

		// Checks if the position is on another spot.

		if (spots.size() > 0) {
			for (int i = 0; i < spots.size(); i++) {
				Spot temp = spots.get(i);
				if (temp.getxPos() == x && y == temp.getyPos()) {
					return false;
				}
			}
		}

		return true;

	}

	// Checks if you are in a gameover situation.

	public Boolean gameOver() {

		BodyPart head = snake.getSnakeBody().get(0);
		int xpos = head.getxPos();
		int ypos = head.getyPos();

		// Checks if the head is outside the playingfield

		if (xpos < 0 || xpos >= 760 || ypos < 0 || ypos >= 600) {
			return true;
		}

		// Checks if the head is on another bodypart

		for (int i = 1; i < snake.getSnakeBody().size(); i++) {
			if (snake.getSnakeBody().get(i).getxPos() == xpos && snake.getSnakeBody().get(i).getyPos() == ypos) {
				return true;
			}
		}
		
		// Checks if a head is on a spot and acts accordingly.
		
		for (int i = 0; i < spots.size(); i++) {

			Spot temp = spots.get(i);

			if (temp.getxPos() == head.getxPos() && temp.getyPos() == head.getyPos()) {

				switch (temp.getMarker().getMarker()) {

				case "bear":
					snake.half();
					spots.remove(spots.get(i));
					break;
				case "fire":
					return true;
				case "mouse":
					snake.newBodyParts(5);
					spots.remove(spots.get(i));
					return false;
				}
			}
		}
		
		// Checks if the size of the snake is smaller than 5

		if (snake.getSnakeBody().size() < 5) {
			return true;
		}

		return false;

	}
	
	// Getters

	public Snake getSnake() {
		return snake;
	}

	public ArrayList<Spot> getSpots() {
		return spots;
	}

}
