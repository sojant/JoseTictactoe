package io.github.sojant.tictactoe.logic;

import io.github.sojant.tictactoe.model.Point;
import io.github.sojant.tictactoe.view.BoardView;

/**
 * Created by Sojant on 2017-10-23.
 */
public class EasyGameLogic implements GameLogic {

    public Point makeNextMove(BoardView board, String mark) {

        String[][] boardPositions = board.getBoardState();
        Point p = findNextEmpySpace(boardPositions);

        return p;
    }

    private Point findNextEmpySpace( String [][] board ){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if(" ".equals(board[i][j])) return new Point(i,j);
            }
        }
        return null;
    }

}
