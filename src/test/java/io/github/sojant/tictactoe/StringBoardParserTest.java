package io.github.sojant.tictactoe;

import io.github.sojant.tictactoe.util.StringBoardParser;
import io.github.sojant.tictactoe.view.BoardView;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sojant on 2017-10-24.
 */
public class StringBoardParserTest {

    @Test
    public void testParseString(){

        String state =
                "|X  |"+
                "| O |"+
                "|X  |";

        String[][] boardState = StringBoardParser.parseString(state);

       StringBoardParser.printBoard(boardState);

        Assert.assertTrue("X".equals(boardState[0][0]));
        Assert.assertTrue("O".equals(boardState[1][1]));
        Assert.assertTrue("X".equals(boardState[2][0]));
        Assert.assertTrue(" ".equals(boardState[0][2]));

    }

}
