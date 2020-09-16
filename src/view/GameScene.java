package view;

import controller.Controller;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;

public class GameScene extends Scene{
	
	private Controller controller;
	
	private DrawPane drawPane;
	private DashBoard dashBoard;
	
	private final static int WIDTH = 760;
	private final static int HEIGHT = 600;
	
	public GameScene(Controller controller) {
		super(new Pane(), WIDTH, HEIGHT);
		
		this.controller = controller;
		
		drawPane = new DrawPane();
		dashBoard = new DashBoard(this.controller);
		
		BorderPane group = new BorderPane();
		group.setCenter(drawPane);
		group.setBottom(dashBoard);
		
		setRoot(group);
		
	}
	
	// Getters

	public DrawPane getDrawPane() {
		return drawPane;
	}
	
	public DashBoard getDashBoard() {
		return dashBoard;
	}

}
