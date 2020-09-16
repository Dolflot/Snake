package controller;

import javafx.concurrent.Task;

public class BodyPartTask extends Task<Void> {

	private Boolean running;
	private Boolean paused;
	private Controller controller;
	private int sleep;

	// Amount of bodyparts added every cycle

	private final int AMOUNT = 3;

	public BodyPartTask(Controller controller) {
		running = true;
		paused = true;
		this.controller = controller;
		sleep = 1;
	}

	@Override
	protected Void call() throws InterruptedException {

		// Checks if the task is running and if the task is paused. 
		// If the task is paused it won't add bodyparts.

		while (running) {

			Thread.sleep(sleep);
			if (!paused) {
				controller.getGameModel().getSnake().newBodyParts(AMOUNT);
			}
		}
		return null;
	}
	
	// Setters

	public void setSleep(int time) {
		sleep = time;
	}

	public void setPaused(Boolean bool) {
		paused = bool;

	}

	public void stop() {
		running = false;
	}

}
