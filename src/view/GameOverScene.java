package view;

import controller.Controller;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class GameOverScene extends Scene {

	private Label gameOver;
	private Label time;
	private Button restart;
	private Controller controller;

	public GameOverScene(double width, double height, Controller controller) {
		super(new Pane(), width, height);
		this.controller = controller;
		initLabels();
		
		restart = new Button("Restart");
		restart.setOnMouseClicked(e -> restart());
		
		
		initView(width, height);
		
		

	}

	private void initLabels() {
		gameOver = new Label("Game Over");
		gameOver.setFont(new Font("Arial Black", 50));
		gameOver.setAlignment(Pos.CENTER);
		time = new Label();
		time.setFont(new Font("Arial Black", 20));
		time.setTextFill(Color.WHITE);;
		time.setAlignment(Pos.CENTER);
	}

	private void initView(double height , double width) {
		
		BorderPane pane = new BorderPane();
		pane.setPrefSize(width, height);
		pane.setMinSize(width, height);
		pane.setMaxSize(width, height);
		pane.setBackground(new Background(new BackgroundFill(Color.RED, null, null)));
		
		VBox v = new VBox();
		v.getChildren().addAll(gameOver, time, restart);
		v.setAlignment(Pos.TOP_CENTER);
		v.setPadding(new Insets(200));
		
		pane.setCenter(v);
		BorderPane.setAlignment(v, Pos.CENTER);
		
		setRoot(pane);
		
	}
	
	private void restart() {
	}
	
	// Getters

	public Label getTime() {
		return time;
	}

}
