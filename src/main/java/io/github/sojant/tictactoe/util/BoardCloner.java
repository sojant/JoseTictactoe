package io.github.sojant.tictactoe.util;

/**
 * Created by Sojant on 2017-10-25.
 */
public class BoardCloner {

    public static String[][] cloneBoardState(String[][] input) {
        if (input == null)
            return null;
        String[][] result = new String[input.length][];
        for (int r = 0; r < input.length; r++) {
            result[r] = input[r].clone();
        }
        return result;
    }

}
