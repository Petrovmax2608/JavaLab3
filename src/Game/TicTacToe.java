package Game;

import GUI.CanvasPainter;

public class TicTacToe {

    private boolean isX = true;

    public void changeStep() {
        isX = !isX;
    }

    public Winner whoWin(int xIndex, int yIndex) {
        int[][] matrix = CanvasPainter.matrix;
        for (int coordinate = 0; coordinate < matrix.length - 2; coordinate++) {
            int diagonal = checkDiagonal(matrix, xIndex, yIndex);
            int vertical = sumElements(matrix, coordinate, yIndex, 1, 0);
            int horizontal = sumElements(matrix, xIndex, coordinate, 0, 1);

            if (vertical == 6 || horizontal == 6 || diagonal == 6) return Winner.X;
            if (vertical == 3 || horizontal == 3 || diagonal == 3) return Winner.O;
        }
        return Winner.NOTHING;
    }

    private int checkDiagonal(int[][] matrix, int xIndex, int yIndex) {
        for (int x = 0; x < matrix.length; x++) {
            for (int y = 0; y < matrix.length; y++) {
                int shift = Math.min(x, y);
                if (x - y == xIndex - yIndex) {
                    return sumElements(matrix, x - shift, y - shift, 1, 1);
                }
                if (x + y == xIndex + yIndex) {
                    return sumElements(matrix, x - shift, y - shift, -1, 1);
                }
            }
        }
        return -1;
    }

    private int sumElements(int[][] matrix, int xIndex, int yIndex, int dx, int dy) {
        int total = 0;
        for (int i = 0; i < 3; i++) {
            if (xIndex < 0 || xIndex > matrix.length - 1 || yIndex < 0 || yIndex > matrix.length - 1) break;
            total += matrix[xIndex][yIndex];
            xIndex += dx;
            yIndex += dy;
        }
        return total;
    }

    public boolean isX() {
        return isX;
    }
}
