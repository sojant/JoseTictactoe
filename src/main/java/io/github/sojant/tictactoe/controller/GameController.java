package io.github.sojant.tictactoe.controller;

import io.github.sojant.tictactoe.logic.GameLogic;
import io.github.sojant.tictactoe.logic.HumanGameLogic;
import io.github.sojant.tictactoe.logic.KeyboardMapper;
import io.github.sojant.tictactoe.model.Player;
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
    //private String difficulty;
    //private GameLogic gameLogic;
    private GameLogic gameLogicX = new HumanGameLogic();
    private Player xPlayer;
    private Player oPlayer;
    private Player currentPlayer;

    private Player winner;

    public boolean isPrintOnScreen() {
        return printOnScreen;
    }

    public void setPrintOnScreen(boolean printOnScreen) {
        this.printOnScreen = printOnScreen;
    }

    boolean printOnScreen=true;

    public GameController(BoardView board){
        this.board=board;
    }


    public void printNextMoveInfo(){
        System.out.print((String.format("\n%s's Turn (%s):",currentPlayer.getName(),currentPlayer.getMark())));
    }

    public void announceNextMove(){
        System.out.println("\n------------------------------------");
        if(printOnScreen){
            board.printBoardOnScreen();
        }
        printNextMoveInfo();
    }

    public void getNextMove(){

        Point p = currentPlayer.getGameLogic().makeNextMove(board, currentPlayer.getMark());

        if(!board.isEmptySpace(p)){throw new IllegalArgumentException("Illegal Move.");}
        board.makeMove(currentPlayer.getMark(),p);

    }

    public void startGame(){
        currentPlayer=xPlayer;
        do{
            announceNextMove();
            getNextMove();
        } while(!checkFinalGameStatus());
    }

    public boolean checkFinalGameStatus(){
        if(board.checkForWinner(xTurn?"X":"O")){
            System.out.println("\n\n\n------------------------------------");
            LOG.info("\n"+currentPlayer.getName()+" is the Winner!!");
            board.printBoardOnScreen();
            winner = currentPlayer;
            return true;
        } else if(board.isFull()){
            System.out.println("\n\n\n------------------------------------");
            LOG.info("It's a Draw!!");
            board.printBoardOnScreen();
            return true;
        }

        //Change player turn
        xTurn=!xTurn;
        currentPlayer = xTurn ? xPlayer:oPlayer;
        return false;
    }


    public void setXPlayer(Player xPlayer) {
        xPlayer.setMark("X");
        this.xPlayer = xPlayer;
    }

    public void setOPlayer(Player oPlayer) {
        oPlayer.setMark("O");
        this.oPlayer = oPlayer;
    }

    public Player getWinner() {
        return winner;
    }
}
