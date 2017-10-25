package io.github.sojant.tictactoe;

import io.github.sojant.tictactoe.logic.HardGameLogic;
import io.github.sojant.tictactoe.model.Point;
import io.github.sojant.tictactoe.util.StringBoardParser;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by Sojant on 2017-10-24.
 */
public class HardGameLogicTest {



    @Test
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


        //Check for Diagonal X Win

        state = "|X  |"+
                "| X |"+
                "|   |";

        boardState = StringBoardParser.parseString(state);

        p = gameLogic.checkForNextWinMove("X",boardState);
        Assert.assertNotNull(p);
        Assert.assertTrue(p.row==2 && p.col==2);

        state = "|  X|"+
                "| X |"+
                "|   |";

        boardState = StringBoardParser.parseString(state);

        p = gameLogic.checkForNextWinMove("X",boardState);
        Assert.assertNotNull(p);
        Assert.assertTrue(p.row==2 && p.col==0);

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

    @Test
    public void testNextPreWinMove(){

        String [][]boardState;

        HardGameLogic gameLogic = new HardGameLogic("X");

        String state =
                "|  X|"+
                "|   |"+
                "|   |";

        boardState = StringBoardParser.parseString(state);
        Map<String, List<Point>> mapPoints = gameLogic.checkForNextPreWinMove("X", boardState);

        for (String key: mapPoints.keySet()){
            List<Point> points = mapPoints.get(key);
            System.out.println(key+" "+points.get(0)+" "+points.get(1));
        }

        Assert.assertNotNull(mapPoints);
        Assert.assertTrue(mapPoints.keySet().size()==3);


    }

    @Test
    public void testNextForkBlock(){

        String [][]boardState;
        Point p;

        HardGameLogic gameLogic = new HardGameLogic("X");

        String state =
                "| O |"+
                "| X |"+
                "|XOO|";
        boardState = StringBoardParser.parseString(state);
        p = gameLogic.checkForNextForkBlock("O", boardState);
        System.out.println(p);
        Assert.assertNotNull(p);


    }

    @Test
    public void testNextCenterMove(){

        String [][]boardState;
        Point p;

        HardGameLogic gameLogic = new HardGameLogic("X");

        String state =
                "|XXX|"+
                "|X X|"+
                "|XXX|";

        boardState = StringBoardParser.parseString(state);
        p = gameLogic.checkForNextCenterMove("X", boardState);
        Assert.assertNotNull(p);

        Assert.assertTrue(p.row==1 && p.col ==1);

    }

    @Test
    public void testNextOppositeCorner(){

        String [][]boardState;
        Point p;
        HardGameLogic gameLogic = new HardGameLogic("X");

        String state =
                "|O  |"+
                "|   |"+
                "|   |";
        boardState = StringBoardParser.parseString(state);
        p = gameLogic.checkForNextOppositeCorner("X", boardState);
        Assert.assertNotNull(p);
        Assert.assertTrue(p.row==2 && p.col ==2);

        state =
                "|  O|"+
                "|   |"+
                "|   |";
        boardState = StringBoardParser.parseString(state);
        p = gameLogic.checkForNextOppositeCorner("X", boardState);
        Assert.assertNotNull(p);
        Assert.assertTrue(p.row==2 && p.col ==0);

        state =
                "|   |"+
                "|   |"+
                "|  O|";
        boardState = StringBoardParser.parseString(state);
        p = gameLogic.checkForNextOppositeCorner("X", boardState);
        Assert.assertNotNull(p);
        Assert.assertTrue(p.row==0 && p.col ==0);


        state =
                "|   |"+
                "|   |"+
                "|O  |";
        boardState = StringBoardParser.parseString(state);
        p = gameLogic.checkForNextOppositeCorner("X", boardState);
        Assert.assertNotNull(p);
        Assert.assertTrue(p.row==0 && p.col ==2);

    }
}


