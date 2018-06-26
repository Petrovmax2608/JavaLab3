package GUI;

import Game.TicTacToe;
import Game.Winner;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;

public class CanvasPainter {

    private GraphicsContext context;
    private static final int CELL_SIZE = Main.CELL_SIZE;
    public static int[][] matrix;
    private TicTacToe ticTacToe;
    private static final int CELL_SHIFT = 15;

    CanvasPainter(Canvas canvas, int cellCount) {
        context = canvas.getGraphicsContext2D();
        context.setStroke(Color.GRAY);
        context.setLineWidth(3);
        matrix = new int[cellCount][cellCount];
        ticTacToe = new TicTacToe();
        paintField(cellCount);
    }

    private void paintField(int cellCount) {
        context.setFill(Color.YELLOWGREEN);
        context.fillRect(0, 0, cellCount * CELL_SIZE, cellCount * CELL_SIZE);
        for (int i = 0; i < cellCount; i++) {
            for (int j = 0; j < cellCount; j++) {
                matrix[i][j] = -10;
                context.strokeRect(i * CELL_SIZE, j * CELL_SIZE, CELL_SIZE, CELL_SIZE);
            }
        }
    }

    private void paintStep(int xIndex, int yIndex, boolean isX) {
        if (matrix[xIndex][yIndex] != -10) return;
        if (isX) {
            context.setStroke(Color.RED);
            matrix[xIndex][yIndex] = 2;
            context.moveTo(xIndex * CELL_SIZE + CELL_SHIFT, yIndex * CELL_SIZE + CELL_SHIFT);
            context.lineTo((xIndex + 1) * CELL_SIZE - CELL_SHIFT, (yIndex + 1) * CELL_SIZE - CELL_SHIFT);
            context.moveTo((xIndex + 1) * CELL_SIZE - CELL_SHIFT, yIndex * CELL_SIZE + CELL_SHIFT);
            context.lineTo(xIndex * CELL_SIZE + CELL_SHIFT, (yIndex + 1) * CELL_SIZE - CELL_SHIFT);
            context.stroke();
        } else {
            context.setStroke(Color.BLUE);
            matrix[xIndex][yIndex] = 1;
            context.strokeOval(xIndex * CELL_SIZE + CELL_SHIFT, yIndex * CELL_SIZE + CELL_SHIFT,
                    CELL_SIZE - CELL_SHIFT * 2, CELL_SIZE - CELL_SHIFT * 2);
        }
        ticTacToe.changeStep();
        Winner winner = ticTacToe.whoWin(xIndex, yIndex);
        if (winner == Winner.X || winner == Winner.O) {
            winWindow(winner);
        }
    }

    private void winWindow(Winner winner) {
        Alert alert = new Alert(Alert.AlertType.NONE);
        alert.setContentText("Победители " + (winner == Winner.X ? "крестики!" : "нолики!"));
        alert.getDialogPane().getButtonTypes().add(ButtonType.OK);
        alert.show();
    }

    void listener(MouseEvent event) {
        int xIndex = (int) event.getX() / CELL_SIZE;
        int yIndex = (int) event.getY() / CELL_SIZE;
        paintStep(xIndex, yIndex, ticTacToe.isX());
    }

}
