package io.github.sojant.tictactoe.controller;

import io.github.sojant.tictactoe.logic.GameLogic;
import io.github.sojant.tictactoe.logic.KeyboardMapper;
import io.github.sojant.tictactoe.model.Point;
import io.github.sojant.tictactoe.view.BoardView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Scanner;


/**
 * Created by Sojant on 2017-10-23.
 */
public class GameController {

    Logger LOG = LogManager.getLogger(GameController.class);
    Scanner in = new Scanner(System.in);

    private BoardView board;
    KeyboardMapper keyboardMapper= new KeyboardMapper();

    private boolean xTurn = true;

    private String playerName;
    private String opponentName;
    private String difficulty;
    private GameLogic gameLogic;

    public GameController(BoardView board, GameLogic logic){
        this.board=board;
        this.gameLogic=logic;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public String getOpponentName() {
        return opponentName;
    }

    public void setOpponentName(String opponentName) {
        this.opponentName = opponentName;
    }

    public String getCurrentPlayer(){
        return  xTurn ? playerName : opponentName;
    }

    public String getCurrentMark(){
        return  xTurn ? "X": "O";
    }


    public void printNextMoveInfo(){
        System.out.print((String.format("\n%s's Turn (%s):",getCurrentPlayer(),getCurrentMark())));
    }

    public void announceNextMove(){
        System.out.println("\n\n\n------------------------------------");
        board.printBoardOnScreen();
        printNextMoveInfo();
    }

    public void getNextMove(){
        if(xTurn){
            String nextPlayerMove = in.nextLine();
            Point p = keyboardMapper.getPointFromKeyboard(nextPlayerMove);
            if(!board.isEmptySpace(p)) LOG.warn("Illegal Move!!");
            board.makeMove("X",p);
        }else{
            Point p = gameLogic.makeNextMove(board);
            if(!board.isEmptySpace(p)){
                LOG.error("Illegal Move from Machine "+p);
                board.printBoardOnScreen();
                throw new IllegalStateException("Illegal Move from Machine");
            }
            board.makeMove("O",p);
        }

    }

    public void startGame(){

        do{
            announceNextMove();
            getNextMove();
        } while(!checkFinalGameStatus());



//        for (int i = 0; i < 20; i++) {
//            announceNextMove();
//            getNextMove();
//            boolean finalStatus = checkFinalGameStatus();
//
//            if(finalStatus) break;
//
//            xTurn=!xTurn;
//        }

    }

    public boolean checkFinalGameStatus(){
        if(board.checkForWinner(xTurn?"X":"O")){
            System.out.println("\n\n\n------------------------------------");
            LOG.info("\nWinner is "+ getCurrentPlayer()+"!!");
            board.printBoardOnScreen();
            return true;
        } else if(board.isFull()){
            System.out.println("\n\n\n------------------------------------");
            LOG.info("It's a Draw!!");
            board.printBoardOnScreen();
            return true;
        }

        //Change player turn
        xTurn=!xTurn;
        return false;
    }


}
