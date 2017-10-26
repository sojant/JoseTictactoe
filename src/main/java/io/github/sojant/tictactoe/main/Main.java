package io.github.sojant.tictactoe.main;

import io.github.sojant.tictactoe.controller.GameController;
import io.github.sojant.tictactoe.logic.EasyGameLogic;
import io.github.sojant.tictactoe.logic.GameLogic;
import io.github.sojant.tictactoe.logic.HardGameLogic;
import io.github.sojant.tictactoe.logic.HumanGameLogic;
import io.github.sojant.tictactoe.model.Player;
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

        Scanner in = new Scanner(System.in);

        String gameTitle="";
        gameTitle +="     ____.                     ___________.__     ___________           ___________            \n";
        gameTitle +="    |    | ____  ______ ____   \\__    ___/|__| ___\\__    ___/____    ___\\__    ___/___   ____  \n";
        gameTitle +="    |    |/  _ \\/  ___// __ \\    |    |   |  |/ ___\\|    |  \\__  \\ _/ ___\\|    | /  _ \\_/ __ \\ \n";
        gameTitle +="/\\__|    (  <_> )___ \\\\  ___/    |    |   |  \\  \\___|    |   / __ \\\\  \\___|    |(  <_> )  ___/ \n";
        gameTitle +="\\________|\\____/____  >\\___  >   |____|   |__|\\___  >____|  (____  /\\___  >____| \\____/ \\___  >\n";
        gameTitle +="                    \\/     \\/                     \\/             \\/     \\/                  \\/ \n";

        LOG.info(gameTitle);

        LOG.info("Who will play 'X' ?");
        LOG.info("  a) Human");
        LOG.info("  b) C3PO (Easy AI)");
        LOG.info("  c) GladOS (Hard AI)");
        LOG.info("> ");
        String input = in.nextLine().toLowerCase();

        Player player1;
        if("a".equals(input)){
            LOG.info("What's the player name? :");
            input=in.nextLine();
            player1= new Player(input, new HumanGameLogic());
        }else if("b".equals(input)){
            player1= new Player("C3PO", new EasyGameLogic());
        }else{
            player1= new Player("GladOS", new HardGameLogic());
        }

        LOG.info("Who will play 'O' ?");
        LOG.info("  a) Human");
        LOG.info("  b) Wall-E (Easy AI)");
        LOG.info("  c) Cortana (Hard AI)");
        LOG.info("> ");
        input = in.nextLine().toLowerCase();


        LOG.info("");LOG.info("");

        Player player2;
        if("a".equals(input)){
            LOG.info("What's the player name? :");
            input=in.nextLine();
            player2= new Player(input, new HumanGameLogic());
        }else if("b".equals(input)){
            player2= new Player("Wall-E", new EasyGameLogic());
        }else{
            player2= new Player("Cortana", new HardGameLogic());
        }

        String humanKeys = " Q | W | E \n";
        humanKeys += "------------\n";
        humanKeys += " A | S | D \n";
        humanKeys += "------------\n";
        humanKeys += " Z | X | C \n";
        LOG.info("For human players, use the following keymap:");
        LOG.info(humanKeys);

        BoardView board = new BoardView();
        GameController gc = new GameController(board);

        gc.setXPlayer(player1);
        gc.setOPlayer(player2);

        gc.startGame();

    }

}
