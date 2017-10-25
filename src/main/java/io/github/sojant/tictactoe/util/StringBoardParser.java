package io.github.sojant.tictactoe.util;

import java.util.StringTokenizer;

/**
 * Created by Sojant on 2017-10-24.
 */
public class StringBoardParser {

    public static String[][] parseString(String boardString){

        String[][] boardState = new String[3][3];

        StringTokenizer st = new StringTokenizer(boardString,"\\|",false);

        int tokens = st.countTokens();
        if(tokens>3) throw new IllegalArgumentException("Malformed boardString, no more than 3 Rows are expected;");

        int i = 0;
        String rows[] = new String[3];

        while(st.hasMoreTokens()){
            rows[i]=st.nextToken();
            if(rows[i].length()!=3) throw new IllegalArgumentException("Malformed boardString, no more than 3 Columns are expected;");
            boardState[i][0]=rows[i].substring(0,1);
            boardState[i][1]=rows[i].substring(1,2);
            boardState[i][2]=rows[i].substring(2,3);
            i++;
        }

        return boardState;
    }

}
