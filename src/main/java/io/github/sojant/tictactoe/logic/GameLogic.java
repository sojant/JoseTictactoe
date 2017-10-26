package io.github.sojant.tictactoe.logic;

import io.github.sojant.tictactoe.model.Point;
import io.github.sojant.tictactoe.view.BoardView;

/**
 * Created by Sojant on 2017-10-23.
 */
public interface GameLogic {

    public Point makeNextMove(BoardView  board, String mark);

}
