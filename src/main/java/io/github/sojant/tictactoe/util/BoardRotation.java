package io.github.sojant.tictactoe.util;

/**
 * Created by Sojant on 2017-10-24.
 */
public class BoardRotation {

    public static String[][] rotateCW(String[][] boardState) {
        final int M = boardState.length;
        final int N = boardState[0].length;
        String[][] ret = new String[N][M];
        for (int row = 0; row < M; row++) {
            for (int col = 0; col < N; col++) {
                ret[col][M-1-row] = boardState[row][col];
            }
        }
        return ret;
    }
}
