package io.github.sojant.tictactoe.logic;

import io.github.sojant.tictactoe.model.Point;
import io.github.sojant.tictactoe.view.BoardView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Created by Sojant on 2017-10-25.
 */
public class HumanGameLogic implements GameLogic {

    Logger LOG = LogManager.getLogger(HumanGameLogic.class);

    Scanner in = new Scanner(System.in);
    KeyboardMapper keyboardMapper= new KeyboardMapper();

    public Point makeNextMove(BoardView board, String mark) {
        boolean validSpace = false;
        Point p=null;

        while(!validSpace){
            String nextPlayerMove = in.nextLine();
            System.out.println("\n");
            p = keyboardMapper.getPointFromKeyboard(nextPlayerMove);
            if(p==null || !board.isEmptySpace(p) ){
                LOG.info("That's an Illegal move, try another: ");
            }else{
                validSpace=true;
            }
        }

        return p;
    }
}
