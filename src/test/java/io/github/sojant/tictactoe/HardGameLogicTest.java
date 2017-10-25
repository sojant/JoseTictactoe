package io.github.sojant.tictactoe;

import io.github.sojant.tictactoe.logic.HardGameLogic;
import io.github.sojant.tictactoe.model.Point;
import io.github.sojant.tictactoe.util.StringBoardParser;
import io.github.sojant.tictactoe.view.BoardView;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sojant on 2017-10-24.
 */
public class HardGameLogicTest {



    @org.junit.Test
    //** Validates that the Next is a Winner state, also the retorning Point, must match with the empty Space found.
    public void testNextWinMove(){

        String [][]boardState;
        Point p;

        HardGameLogic gameLogic = new HardGameLogic("X");

        //Check for Col X Win

        String state =
                "|X  |"+
                "|   |"+
                "|X  |";
        boardState = StringBoardParser.parseString(state);

        p = gameLogic.checkForNextWinMove("X",boardState);
        Assert.assertNotNull(p);
        Assert.assertTrue(p.row==1 && p.col==0);

        //Check for Row O Win

        state = "|OO |"+
                "|   |"+
                "|   |";

        boardState = StringBoardParser.parseString(state);

        p = gameLogic.checkForNextWinMove("O",boardState);
        Assert.assertNotNull(p);
        Assert.assertTrue(p.row==0 && p.col==2);

    }

    @Test
    public void testNextForkMove(){

        String [][]boardState;
        Point p;

        HardGameLogic gameLogic = new HardGameLogic("X");

        String state =
                "| O |"+
                "| XO|"+
                "|XO |";
        boardState = StringBoardParser.parseString(state);
        p = gameLogic.checkForNextForkMove("X", boardState);
        Assert.assertNotNull(p);

        state = "| O |"+
                "|OXO|"+
                "|  X|";
        boardState = StringBoardParser.parseString(state);
        p = gameLogic.checkForNextForkMove("X", boardState);
        Assert.assertNotNull(p);

        state = "| OX|"+
                "|O O|"+
                "| OX|";
        boardState = StringBoardParser.parseString(state);
        p = gameLogic.checkForNextForkMove("X", boardState);
        Assert.assertNotNull(p);

        state = "|  X|"+
                "|OXO|"+
                "| O |";
        boardState = StringBoardParser.parseString(state);
        p = gameLogic.checkForNextForkMove("X", boardState);
        Assert.assertNotNull(p);

        //Arrowhead 1
        state = "| X |"+
                "|XOO|"+
                "| OO|";
        boardState = StringBoardParser.parseString(state);
        p = gameLogic.checkForNextForkMove("X", boardState);
        Assert.assertNotNull(p);

        //Arrowhead 1 Rotated 45
        state = "| X |"+
                "|XOO|"+
                "| OO|";
        boardState = StringBoardParser.parseString(state);
        p = gameLogic.checkForNextForkMove("X", boardState);
        Assert.assertNotNull(p);

        //Arrowhead 1 Rotated 180
        state = "|OO |"+
                "|OOX|"+
                "| X |";
        boardState = StringBoardParser.parseString(state);
        p = gameLogic.checkForNextForkMove("X", boardState);
        Assert.assertNotNull(p);

        //Encirclement 1
        state = "|  X|"+
                "|  O|"+
                "|XOO|";
        boardState = StringBoardParser.parseString(state);
        p = gameLogic.checkForNextForkMove("X", boardState);
        Assert.assertNotNull(p);





    }

}

