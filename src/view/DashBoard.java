package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Slider;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.paint.Color;

public class DashBoard extends BorderPane {

	private Controller controller;

	private Button pauseButton;
	private Button exitButton;
	private Slider speedSlider;
	private Label playTimeLabel;

	private final int BTNWIDTH = 100;
	private final int BTNHEIGHT = 40;
	private final int INSET = 20;

	public DashBoard(Controller controller) {

		this.controller = controller;
		setBackground(new Background(new BackgroundFill(Color.DARKGRAY, null, null)));

		initButtons();
		initSlider();
		playTimeLabel = new Label();
		playTimeLabel.setPrefSize(BTNWIDTH, BTNHEIGHT);

		HBox h = new HBox(INSET);
		h.getChildren().addAll(pauseButton, exitButton, speedSlider, playTimeLabel);

		HBox.setHgrow(pauseButton, Priority.ALWAYS);
		HBox.setHgrow(exitButton, Priority.ALWAYS);
		HBox.setHgrow(speedSlider, Priority.ALWAYS);
		HBox.setHgrow(playTimeLabel, Priority.ALWAYS);

		HBox.setMargin(pauseButton, new Insets(INSET));
		HBox.setMargin(exitButton, new Insets(INSET));
		HBox.setMargin(speedSlider, new Insets(INSET));
		HBox.setMargin(playTimeLabel, new Insets(INSET));

		setBottom(h);
	}

	// SetFocusTraversable(false) so the arrowkeys don't navigate the buttons

	private void initButtons() {

		pauseButton = new Button("Start");
		pauseButton.setPrefSize(BTNWIDTH, BTNHEIGHT);
		pauseButton.setFocusTraversable(false);
		pauseButton.setOnMouseClicked(e -> controller.pause());

		exitButton = new Button("Exit");
		exitButton.setPrefSize(BTNWIDTH, BTNHEIGHT);
		exitButton.setFocusTraversable(false);
		exitButton.setOnMouseClicked(e -> controller.exit());

	}

	private void initSlider() {

		speedSlider = new Slider(1, 12, 1);
		speedSlider.setShowTickMarks(true);
		speedSlider.setShowTickLabels(true);
		speedSlider.setMajorTickUnit(1);
		speedSlider.setMinorTickCount(1);
		speedSlider.setDisable(true);
		speedSlider.setBackground(new Background(new BackgroundFill(Color.LIGHTGRAY.brighter(), null, null)));
		
	}
	
	// Setters
	
	public void SetButtonName(String name) {
		pauseButton.setText(name);
	}

	public void setSliderValue() {
		speedSlider.setValue(speedSlider.getValue() + 1);
	}
	
	// Getters

	public Label getplayTimeLabel() {
		return playTimeLabel;
	}

}
