package io.github.sojant.tictactoe.model;

/**
 * Created by Sojant on 2017-10-23.
 */
public class Point {

    public int row, col;

    public Point(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public String toString() {
        return String.format("Point[%s,%s]",row,col);
    }
}
