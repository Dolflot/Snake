package view;

import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;

public class Square extends Pane {
	
	public Square(int width, int height, Color color) {
		
		setPrefSize(width, height);
		setMinSize(width, height);
		setMaxSize(width, height);
		setBackground(new Background(new BackgroundFill(color, null, null)));
		
	}

}
