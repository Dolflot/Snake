package controller;

import javafx.concurrent.Task;

public class TimerTask extends Task<Void> {

	private int time;
	private String timeString;
	private Boolean running;
	private Boolean paused;

	public TimerTask() {
		running = true;
		paused = true;
		time = 0;
		timeString = "00:00:000";
		updateMessage(timeString);
	}

	@Override
	protected Void call() throws InterruptedException {

		// Checks if the task is running and if the task is paused.
		// If the task is not paused it updates the time every 10ms.

		while (running) {
			if (!paused) {

				time += 10;
				formatTime();
				updateMessage(timeString);
			}

			Thread.sleep(10);
		}
		return null;
	}
	
	// formats the int into a time representation

	private void formatTime() {

		int minute;
		int second;
		int milsec;

		minute = time / 60000;
		second = time / 1000 - (minute * 60);
		milsec = time % 1000;

		timeString = String.format("%02d:%02d:%03d", minute, second, milsec);

	}
	
	// Setters

	public void setPaused(Boolean bool) {
		paused = bool;
	}

	public void stop() {
		running = false;
	}

}
