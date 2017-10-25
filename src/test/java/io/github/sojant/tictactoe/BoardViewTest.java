package io.github.sojant.tictactoe;

import io.github.sojant.tictactoe.logic.HardGameLogic;
import io.github.sojant.tictactoe.model.Point;
import io.github.sojant.tictactoe.view.BoardView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

/**
 * Created by Sojant on 2017-10-24.
 */

public class BoardViewTest {

    Logger LOG = LogManager.getLogger(BoardViewTest.class);

    @org.junit.Test
    public void testWinRowGame(){

        BoardView board = new BoardView();
        String [][]boardState = board.getBoardState();

        boardState[0][0]="X";boardState[0][1]="X";boardState[0][2]="X";
        Assert.assertTrue(board.checkForWinner("X"));

        board.initializeEmptyBoard();

        boardState[1][0]="X";boardState[1][1]="X";boardState[1][2]="X";
        Assert.assertTrue(board.checkForWinner("X"));

        board.initializeEmptyBoard();

        boardState[2][0]="X";boardState[2][1]="X";boardState[2][2]="X";
        Assert.assertTrue(board.checkForWinner("X"));

    }

    @org.junit.Test
    public void testWinColGame(){

        BoardView board = new BoardView();
        String [][]boardState = board.getBoardState();

        boardState[0][0]="X";
        boardState[1][0]="X";
        boardState[2][0]="X";
        Assert.assertTrue(board.checkForWinner("X"));

        board.initializeEmptyBoard();

        boardState[0][1]="X";
        boardState[1][1]="X";
        boardState[2][1]="X";
        Assert.assertTrue(board.checkForWinner("X"));

        board.initializeEmptyBoard();

        boardState[0][2]="X";
        boardState[1][2]="X";
        boardState[2][2]="X";
        Assert.assertTrue(board.checkForWinner("X"));

    }

    @org.junit.Test
    public void testDiagonalColGame(){

        BoardView board = new BoardView();
        String [][]boardState = board.getBoardState();

        boardState[0][0]="X";
        boardState[1][1]="X";
        boardState[2][2]="X";
        Assert.assertTrue(board.checkForWinner("X"));

        board.initializeEmptyBoard();

        boardState[0][2]="X";
        boardState[1][1]="X";
        boardState[2][0]="X";
        Assert.assertTrue(board.checkForWinner("X"));


    }




}
