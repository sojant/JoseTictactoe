package io.github.sojant.tictactoe.logic;

import io.github.sojant.tictactoe.model.Point;
import io.github.sojant.tictactoe.view.BoardView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by Sojant on 2017-10-24.
 */
public class HardGameLogic implements GameLogic{

    Logger LOG = LogManager.getLogger(HardGameLogic.class);

    String mark;
    private enum GameState{WIN,BLOCK,FORK,BLOCKFORK,CENTER,OPPOSITE_CORNER,EMPTY_CORNER,EMPTY_SIDE}

    public HardGameLogic(String mark){
        this.mark=mark;
    }

    public Point makeNextMove(BoardView board) {

        return findGameState(board);

    }

    private Point findGameState(BoardView board) {

        String[][] boardState = board.getBoardState();
        EasyGameLogic easyGameLogic = new EasyGameLogic();
        Point nextMove = null;

        nextMove = checkForNextWinMove(mark,boardState);
        if (nextMove!=null){
            LOG.info("HardGameLogic chose WIN");
            return nextMove;
        }

        nextMove = checkForNextWinMove(getOponentMark(mark),boardState);
        if (nextMove!=null){
            LOG.info("HardGameLogic chose BLOCK");
            return nextMove;
        }

        nextMove = checkForNextForkMove(mark,boardState);

        nextMove = easyGameLogic.makeNextMove(board);
        LOG.info("HardGameLogic defaulted to next open space");

        return nextMove;

    }

    private Point checkForNextForkMove(String mark, String[][] boardState) {

        String state = "|X  |"+
                       "| X |"+
                       "|X  |";


        return null;
    }

    public Point checkForNextWinMove(String mark, String[][] boardState) {

        mark=mark.toUpperCase();

        Point p = null;

        // Search por possible Win Condition in All Columns
        for (int col = 0; col < 3; col++) {

            int emptyPoints=0;
            int markPoints=0;
            for (int row = 0; row < 3; row++) {
                if(" ".equals(boardState[row][col])){
                    emptyPoints++;
                    p = new Point(row,col);
                }
                else if(mark.equals(boardState[row][col])) markPoints++;
            }
            if(emptyPoints==1 && markPoints==2){
                return p;
            }

//            int emptyPoints=0;
//            int markPoints=0;
//            for (int row = 0; row < 3; row++) {
//                if(" ".equals(boardState[row][0])){
//                    emptyPoints++;
//                    p = new Point(row,0);
//                }
//                else if(mark.equals(boardState[row][0])) markPoints++;
//            }
//            if(emptyPoints==1 && markPoints==2){
//                return p;
//            }

        }

        // Search por possible Win Condition in All Rows
        for (int col = 0; col < 3; col++) {

            int emptyPoints=0;
            int markPoints=0;
            for (int row = 0; row < 3; row++) {
                if(" ".equals(boardState[col][row])){
                    emptyPoints++;
                    p = new Point(col,row);
                }
                else if(mark.equals(boardState[col][row])) markPoints++;
            }
            if(emptyPoints==1 && markPoints==2){
                return p;
            }
        }

        return null;
    }

    public String getOponentMark(String playerMark){
        playerMark = playerMark.toUpperCase();
        return "X".equals(playerMark) ? "O":"X";
    }
}