package io.github.sojant.tictactoe;

import io.github.sojant.tictactoe.util.BoardRotation;
import io.github.sojant.tictactoe.util.StringBoardParser;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sojant on 2017-10-24.
 */


public class BoardRotationTest {

    @Test
    public void rotateTest(){


        String [][] originalBoardState;
        String [][] rotatedBoardState;

        String state =
                "|X  |"+
                "|X  |"+
                "|X  |";

        originalBoardState = StringBoardParser.parseString(state);

        StringBoardParser.printBoard(originalBoardState);

        rotatedBoardState= BoardRotation.rotateCW(originalBoardState);
        StringBoardParser.printBoard(rotatedBoardState);
        System.out.println();

        rotatedBoardState= BoardRotation.rotateCW(rotatedBoardState);
        StringBoardParser.printBoard(rotatedBoardState);
        System.out.println();

        rotatedBoardState= BoardRotation.rotateCW(rotatedBoardState);
        StringBoardParser.printBoard(rotatedBoardState);
        System.out.println();

        rotatedBoardState= BoardRotation.rotateCW(rotatedBoardState);
        StringBoardParser.printBoard(rotatedBoardState);
        System.out.println();

        // After 4 rotations boardState must be the same as the original
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                Assert.assertTrue(originalBoardState[row][col].equals(rotatedBoardState[row][col]));
            }
        }

    }



}
