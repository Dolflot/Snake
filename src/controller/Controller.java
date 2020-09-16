package controller;

import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Direction;
import model.Game;
import model.Marker;
import view.DrawPane;
import view.GameOverScene;
import view.GameScene;

public class Controller extends Application {

	private GameOverScene gameOverScene;
	private GameScene gameScene;
	private Game gameModel;
	private DrawPane drawPane;
	private Boolean pause = true;
	private Stage stage;
	private TimerTask timerTask;
	private BodyPartTask bodyPartTask;
	private SpotsTask spotTask;

	private long timerDelay;
	private int speedUpper;
	private AnimationTimer timer;

	@Override
	public void start(Stage stage) {

		this.stage = stage;

		stage.show();
		stage.setTitle("PROG4 ASS Snake - Gijs Schuttelaars");
		stage.setOnCloseRequest(new ApplicationExitEventHandler());
		stage.setHeight(719);
		stage.setWidth(775);
		stage.centerOnScreen();
		stage.setResizable(false);

		// Initialize Scenes and models.

		gameOverScene = new GameOverScene(775, 719, this);
		gameScene = new GameScene(this);
		gameModel = new Game();

		// Add spots to the gameModel.

		addSpots();

		// Draws the snake and the spots onto the DrawPane.

		drawPane = gameScene.getDrawPane();
		drawPane.draw(gameModel.getSnake().getSnakeBody(), gameModel.getSpots());

		// Initialize the animationTimer

		timer = new AnimationTimerInit();
		speedUpper = 0;
		timerDelay = 1200;

		// Initialize the timer and binds the timer to the labels in DashBoard and
		// GameOverScene.

		timerTask = new TimerTask();
		gameScene.getDashBoard().getplayTimeLabel().textProperty().bind(timerTask.messageProperty());
		gameOverScene.getTime().textProperty().bind(timerTask.messageProperty());
		new Thread(timerTask).start();

		// Initialize the task that adds bodyparts at predetirmened intervals.

		bodyPartTask = new BodyPartTask(this);
		new Thread(bodyPartTask).start();

		// Initialize the task that adds spots on random places after a random amount of
		// time.

		spotTask = new SpotsTask(this);
		new Thread(spotTask).start();

		setOnKeyPressed();

		stage.setScene(gameScene);

	}
	
	

	// Adds a amount of 2-6 spots to the game as long as the amount of spots on the
	// playfield is 20 or less.

	public void addSpots() {

		if (gameModel.getSpots().size() < 21) {

			Random rnd = new Random();
			int amount = (rnd.nextInt(3) + 1) * 2;

			for (int i = 0; i < amount; i++) {

				// Determines randomly what kind of spot it's going to be.

				int temp = rnd.nextInt(3) + 1;

				switch (temp) {
				case 1:
					gameModel.addSpot(Marker.BEAR);
					break;
				case 2:
					gameModel.addSpot(Marker.FIRE);
					break;
				case 3:
					gameModel.addSpot(Marker.MOUSE);
					break;
				}
			}
		}
	}

	// Toggles the game between pause and play.

	public void pause() {

		if (pause) {

			// Starts animationtimer.

			timer.start();

			// Sets the sleep for bodyPartTask and SpotTask.

			bodyPartTask.setSleep(10000);
			spotTask.randomSleep();

			// Sleeps the main Thread so the bodyPartTask and SpotTask don't immediatly
			// fire.

			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
			}

			// Unpause the tasks.

			timerTask.setPaused(false);
			bodyPartTask.setPaused(false);
			spotTask.setPaused(false);

			// Change the text of the pause button.

			gameScene.getDashBoard().SetButtonName("Pause");
			pause = false;

		} else {

			// Stops animationtimer.

			timer.stop();

			// pauses the tasks and sets sleep period on 1ms.

			timerTask.setPaused(true);
			bodyPartTask.setSleep(1);
			bodyPartTask.setPaused(true);
			spotTask.setSleep(1);
			spotTask.setPaused(true);

			// Change the text of the pause button.

			gameScene.getDashBoard().SetButtonName("Start");
			pause = true;
		}

	}

	public void ChangeScene(Scene scene) {

		stage.setScene(scene);

	}

	// Moves the snake

	public void move() {

		// Moves the snake and adds a bodypart if necessary.

		gameModel.getSnake().move();
		gameModel.getSnake().addBodyPart();

		// Checks if after the move you're in a gameover situation and if you are takes
		// you to the gameOverScene

		if (gameModel.gameOver()) {
			timerTask.setPaused(true);
			timer.stop();
			spotTask.stop();
			bodyPartTask.stop();
			ChangeScene(gameOverScene);
		}

		// Draws the new situation after the move.

		drawPane.draw(gameModel.getSnake().getSnakeBody(), gameModel.getSpots());
	}

	// Makes sure you can move the snake with the arrow keys.

	public void setOnKeyPressed() {

		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {
			public void handle(KeyEvent ke) {
				if (ke.getCode() == KeyCode.UP) {
					gameModel.getSnake().getSnakeBody().get(0).setDirection(Direction.UP);
				} else if (ke.getCode() == KeyCode.RIGHT) {
					gameModel.getSnake().getSnakeBody().get(0).setDirection(Direction.RIGHT);
				} else if (ke.getCode() == KeyCode.DOWN) {
					gameModel.getSnake().getSnakeBody().get(0).setDirection(Direction.DOWN);
				} else if (ke.getCode() == KeyCode.LEFT) {
					gameModel.getSnake().getSnakeBody().get(0).setDirection(Direction.LEFT);
				}
			}
		});

	}

	// Stops all the tasks, the animationTimer and exits the window.

	public void exit() {
		Platform.exit();
		timer.stop();
		timerTask.stop();
		bodyPartTask.stop();
		spotTask.stop();
	}

	// Getters

	public DrawPane getDrawPane() {
		return drawPane;
	}

	public Game getGameModel() {
		return gameModel;
	}

	// AnimationTimer

	class AnimationTimerInit extends AnimationTimer {

		long lastUpdate = 0;

		@Override
		public void handle(long now) {

			if ((now - lastUpdate) < (timerDelay * 1000000)) {
				return;
			}

			lastUpdate = now;
			speedUpper += timerDelay;
			move();

			// If 3 seconds have passed and max speed has not been reached the Delay is
			// shortened by 100 and the slider is set higher

			if (speedUpper >= 3000 && timerDelay != 100) {
				speedUpper = 0;
				gameScene.getDashBoard().setSliderValue();
				timerDelay -= 100;
			}
		}

	}

	// Exithandler

	class ApplicationExitEventHandler implements EventHandler<WindowEvent> {

		public void handle(WindowEvent e) {
			Platform.exit();
			timer.stop();
			timerTask.stop();
			bodyPartTask.stop();
			spotTask.stop();
		}
	}

}
