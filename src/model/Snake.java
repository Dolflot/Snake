package model;

import java.util.ArrayList;

public class Snake {

	private ArrayList<BodyPart> snake = new ArrayList<BodyPart>();
	private ArrayList<BodyPart> newBodyparts = new ArrayList<BodyPart>();

	private final int STARTX = 240;
	private final int STARTY = 80;
	private final int SQRSIZE = 40;

	// Makes a new snake on the start position

	public Snake() {

		snake.add(new BodyPart(STARTX, STARTY, Direction.RIGHT));

		for (int i = 0; i < 4; i++) {
			snake.add(new BodyPart((5 - i) * SQRSIZE, STARTY, Direction.RIGHT));
		}

	}

	// Adds new bodyparts to the newBodyparts array that will be added to the snake
	// every move

	public void newBodyParts(int amount) {

		BodyPart temp = snake.get(snake.size() - 1);

		for (int i = 0; i < amount; i++) {
			BodyPart add = new BodyPart(temp.getxPos(), temp.getyPos(), temp.getDirection());
			newBodyparts.add(add);
		}

	}

	// Moves a bodypart form the newBodyparts array to the snake array

	public void addBodyPart() {

		if (newBodyparts.size() > 0) {
			snake.add(newBodyparts.get(0));
			newBodyparts.remove(newBodyparts.get(0));
		}
	}

	// Halfs the snake

	public void half() {

		int remove = snake.size() / 2;

		for (int i = 0; i < remove; i++) {
			snake.remove(snake.size() - 1);
		}

		BodyPart temp = snake.get(snake.size() - 1);

		// Updates the position and direction of the newBodyParts to the new last
		// bodypart of the snake

		for (int a = 0; a < newBodyparts.size(); a++) {
			newBodyparts.get(a).setxPos(temp.getxPos());
			newBodyparts.get(a).setyPos(temp.getyPos());
			newBodyparts.get(a).setDirection(temp.getDirection());
		}

	}

	// Moves the snake

	public void move() {

		// Updates the location of each bodypart based on it's direction

		for (int i = 0; i < snake.size(); i++) {

			BodyPart temp = snake.get(i);

			if (temp.getDirection().getDirection().equals("right")) {
				temp.setxPos(temp.getxPos() + SQRSIZE);
			} else if (temp.getDirection().getDirection().equals("down")) {
				temp.setyPos(temp.getyPos() + SQRSIZE);
			} else if (temp.getDirection().getDirection().equals("left")) {
				temp.setxPos(temp.getxPos() - SQRSIZE);
			} else if (temp.getDirection().getDirection().equals("up")) {
				temp.setyPos(temp.getyPos() - SQRSIZE);
			}
		}

		// Updates the direction of each bodypart except the head to the direction of
		// the bodypart before it

		for (int i = 1; i < snake.size(); i++) {
			int total = snake.size();

			snake.get(total - i).setDirection(snake.get(total - i - 1).getDirection());
		}

	}
	
	// Getters

	public ArrayList<BodyPart> getSnakeBody() {
		return snake;
	}

}
