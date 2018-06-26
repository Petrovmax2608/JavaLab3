package GUI;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class Main extends Application {

    private static Stage gameStage;
    static final int CELL_SIZE = 80;

    @Override
    public void start(Stage primaryStage) {
        gameStage = primaryStage;
        createGameWindow(CELL_SIZE * 3);
    }

    private void createGameWindow(int size) {
        GridPane gridPane = new GridPane();
        Scene scene = new Scene(gridPane, size, size + 30);

        Label label = new Label("Размер:");
        TextField textField = new TextField();
        Button button = new Button("Ок");

        button.setOnMouseClicked(event -> {
            if (!textField.getText().isEmpty() && textField.getText().matches("\\d+")) {
                int newSize = Integer.parseInt(textField.getText());
                createGameWindow(newSize * CELL_SIZE);
            }
        });

        Canvas canvas = new Canvas(scene.getHeight(), scene.getWidth());
        CanvasPainter canvasPainter = new CanvasPainter(canvas, size / CELL_SIZE);
        canvas.setOnMouseClicked(canvasPainter::listener);

        gridPane.add(label, 0, 0);
        gridPane.add(textField, 1, 0);
        gridPane.add(button, 2, 0);
        gridPane.add(canvas, 0, 1, 3, 1);
        gridPane.setVgap(3);
        gridPane.setHgap(3);

        gameStage.setScene(scene);
        gameStage.show();
    }
}
