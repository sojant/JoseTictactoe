package io.github.sojant.tictactoe;

import io.github.sojant.tictactoe.logic.HardGameLogic;
import io.github.sojant.tictactoe.model.Point;
import io.github.sojant.tictactoe.view.BoardView;
import org.junit.Assert;

/**
 * Created by Sojant on 2017-10-24.
 */
public class HardGameLogicTest {

    @org.junit.Test
    //** Validates that the Next is a Winner state, also the retorning Point, must match with the empty Space found.
    public void testNextWinMove(){

        BoardView board = new BoardView();
        String [][]boardState = board.getBoardState();
        Point p = null;

        HardGameLogic gameLogic = new HardGameLogic("X");

        boardState[0][0]="X";
        boardState[1][0]=" ";
        boardState[2][0]="X";

        p = gameLogic.checkForNextWinMove("X",boardState);
        Assert.assertNotNull(p);
        Assert.assertTrue(p.row==1 && p.col==0);

        board.initializeEmptyBoard();

        boardState[0][0]="X";boardState[0][1]="X";boardState[0][2]=" ";

        p = gameLogic.checkForNextWinMove("X",boardState);
        Assert.assertNotNull(p);
        Assert.assertTrue(p.row==0 && p.col==2);

    }

}
