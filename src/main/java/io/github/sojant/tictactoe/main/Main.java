package io.github.sojant.tictactoe.main;

import io.github.sojant.tictactoe.controller.GameController;
import io.github.sojant.tictactoe.logic.EasyGameLogic;
import io.github.sojant.tictactoe.logic.GameLogic;
import io.github.sojant.tictactoe.logic.HardGameLogic;
import io.github.sojant.tictactoe.view.BoardView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;

/**
 * Created by Sojant on 2017-10-23.
 */
public class Main {

    private static Logger LOG = LogManager.getLogger(Main.class);



    public static void main(String[] args) {

        LOG.info("Welcome to TicTacToe");

        LOG.info("What's your name?:");
        Scanner in = new Scanner(System.in);
        String name = in.nextLine();

        LOG.info("Select level of difficulty:");
        LOG.info("  a) Easy:");
        LOG.info("  b) Super Hard:");

        String difficulty = in.nextLine();

        BoardView board = new BoardView();

        GameLogic gl = "a".equals(difficulty) ? new EasyGameLogic() : new HardGameLogic("O");
        GameController gc = new GameController(board, gl);


        gc.setPlayerName(name);
        gc.setOpponentName(gl.getClass().getSimpleName()+" Machine");

        gc.startGame();

    }

}
