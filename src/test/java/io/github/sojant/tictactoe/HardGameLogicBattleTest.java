package io.github.sojant.tictactoe;

import io.github.sojant.tictactoe.controller.GameController;
import io.github.sojant.tictactoe.logic.EasyGameLogic;
import io.github.sojant.tictactoe.logic.HardGameLogic;
import io.github.sojant.tictactoe.model.Player;
import io.github.sojant.tictactoe.view.BoardView;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by Sojant on 2017-10-25.
 */
public class HardGameLogicBattleTest {

    @Test
    public void testHardAIvsHardAI(){
        BoardView board = new BoardView();
        GameController gc = new GameController(board);
        gc.setPrintOnScreen(false);
        Player player1 = new Player("player1",new HardGameLogic());
        Player player2 = new Player("player2",new HardGameLogic());
        gc.setXPlayer(player1);
        gc.setOPlayer(player2);
        gc.startGame();

        //Test it is a Draw
        Assert.assertNull(gc.getWinner());
    }

    @Test
    public void testHardAIvsEasyAI(){
        BoardView board = new BoardView();
        GameController gc = new GameController(board);
        gc.setPrintOnScreen(false);
        Player player1 = new Player("player1",new HardGameLogic());
        Player player2 = new Player("player2",new EasyGameLogic());
        gc.setXPlayer(player1);
        gc.setOPlayer(player2);
        gc.startGame();

        //Test it is a Draw
        Assert.assertTrue(player1 == gc.getWinner());
    }

}
