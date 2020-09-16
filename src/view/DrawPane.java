package view;

import java.io.InputStream;
import java.util.ArrayList;

import javafx.scene.Node;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.BodyPart;
import model.Spot;

public class DrawPane extends Pane {

	private Canvas canvas;
	private GridPane gridpane;
	private Pane imgPane;

	private final int WIDTH = 760;
	private final int HEIGHT = 600;
	private final int SQRSIZE = 40;
	private final int MAXROW = 15;
	private final int MAXCOL = 19;

	public DrawPane() {

		setPrefSize(WIDTH, HEIGHT);
		gridpane = new GridPane();
		gridpane.setPrefSize(WIDTH, HEIGHT);
		fillGridPane();

	}

	// Draws the snake and the spots

	public void draw(ArrayList<BodyPart> snake, ArrayList<Spot> spots) {

		Node temp = null;
		Node temp2 = null;

		// First the old canvas and spots pane are found and deleted

		for (Node a : getChildren()) {
			if (a instanceof Canvas) {
				temp = a;
			}
			if (a instanceof Pane) {
				temp2 = a;
			}
		}

		getChildren().remove(temp);
		getChildren().remove(temp2);

		// New pane and canvas are created

		imgPane = new Pane();
		imgPane.getChildren().add(gridpane);
		canvas = new Canvas(WIDTH, HEIGHT);
		GraphicsContext gc = canvas.getGraphicsContext2D();

		// The snake is drawn on the canvas

		for (int i = 0; i < snake.size(); i++) {

			Color color = Color.ORANGE;

			// Checks if the Bodypart is the snakes head

			if (i == 0) {
				color = Color.RED;
			}
			gc.setFill(color);
			gc.fillOval(snake.get(i).getxPos(), snake.get(i).getyPos(), SQRSIZE, SQRSIZE);
		}

		// Creates the spots on the pane

		for (int i = 0; i < spots.size(); i++) {

			InputStream is;

			switch (spots.get(i).getMarker().getMarker()) {

			case "bear":
				is = this.getClass().getResourceAsStream("/img/bear.png");
				break;
			case "fire":
				is = this.getClass().getResourceAsStream("/img/fire.png");
				break;
			case "mouse":
				is = this.getClass().getResourceAsStream("/img/mouse.png");
				break;
			default:
				is = this.getClass().getResourceAsStream("/img/bear.png");
				break;
			}

			Image img = new Image(is, SQRSIZE, SQRSIZE, true, true);
			ImageView imageview = new ImageView();
			imageview.setImage(img);
			imageview.setLayoutX(spots.get(i).getxPos());
			imageview.setLayoutY(spots.get(i).getyPos());
			imgPane.getChildren().add(imageview);

		}

		getChildren().addAll(imgPane, canvas);

	}

	// fills the gridpane with squares of different colors

	public void fillGridPane() {

		for (int x = 0; x < MAXCOL; x++) {
			for (int y = 0; y < MAXROW; y++) {

				Color color = Color.BLACK;

				if (x % 2 == 1 && y % 2 == 1) {
					color = Color.BLACK.brighter().brighter().brighter().brighter();
				} else if (x % 2 == 1 && y % 2 == 0 || x % 2 == 0 && y % 2 == 1) {
					color = Color.BLACK.brighter().brighter().brighter();
				} else if (x % 2 == 0 && y % 2 == 0) {
					color = Color.BLACK.brighter();
				}

				Square sqr = new Square(SQRSIZE, SQRSIZE, color);
				GridPane.setConstraints(sqr, x, y);
				gridpane.getChildren().add(sqr);
			}
		}
	}
}
