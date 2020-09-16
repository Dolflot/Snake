package controller;

import java.util.Random;

import javafx.concurrent.Task;

public class SpotsTask extends Task<Void> {

	private Boolean running;
	private Boolean paused;
	private Controller controller;
	private int sleep;
	private Random rnd;

	public SpotsTask(Controller controller) {
		running = true;
		paused = true;
		this.controller = controller;
		sleep = 1;
		rnd = new Random();
	}

	@Override
	protected Void call() throws InterruptedException {

		// Checks if the task is running and if the task is paused.
		// If the task is paused it won't add spots.

		while (running) {

			Thread.sleep(sleep);

			if (!paused) {
				controller.addSpots();
				randomSleep();
			}

		}
		return null;
	}

	// Sets the sleep to a random amount of 10s plus an amount between 0 and 10s

	public void randomSleep() {
		sleep = 10000 + (rnd.nextInt(11) * 1000);
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
