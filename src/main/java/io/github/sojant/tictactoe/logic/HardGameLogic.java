package io.github.sojant.tictactoe.logic;

import io.github.sojant.tictactoe.model.Point;
import io.github.sojant.tictactoe.util.BoardRotation;
import io.github.sojant.tictactoe.util.StringBoardParser;
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

    public Point checkForNextForkMove(String mark, String[][] boardState) {

        //"T" stands for the target point to make the last Fork Move
        //"E" stands for an explicit empty space
        //"M" stands for a mark of the current player
        //" " stands for 'ignore whatever is on this spot'

        Point nextMove = null;

        String triangularForkState1 =
                "|T E|"+
                "|EM |"+
                "|M E|";

        String triangularForkState2 =
                "|M E|"+
                "|ET |"+
                "|M E|";

        String triangularForkState3 =
                "|M E|"+
                "|EM |"+
                "|T E|";

        nextMove = checkForkStateAndRotate(mark,boardState,triangularForkState1);
        if(nextMove!=null) return nextMove;

        nextMove = checkForkStateAndRotate(mark,boardState,triangularForkState2);
        if(nextMove!=null) return nextMove;

        nextMove = checkForkStateAndRotate(mark,boardState,triangularForkState3);
        if(nextMove!=null) return nextMove;

        String arrowHeadForkState1 =
                "|TME|"+
                "|M  |"+
                "|E  |";

        nextMove = checkForkStateAndRotate(mark,boardState,arrowHeadForkState1);
        if(nextMove!=null) return nextMove;


        String encirclementForkState1 =
                "|TEM|"+
                "|EE |"+
                "|M  |";

        String encirclementForkState2 =
                "|MET|"+
                "|EE |"+
                "|M  |";

        String encirclementForkState3 =
                "|MEM|"+
                "|EE |"+
                "|T  |";

        nextMove = checkForkStateAndRotate(mark,boardState,encirclementForkState1);
        if(nextMove!=null) return nextMove;

        nextMove = checkForkStateAndRotate(mark,boardState,encirclementForkState2);
        if(nextMove!=null) return nextMove;

        nextMove = checkForkStateAndRotate(mark,boardState,encirclementForkState3);
        if(nextMove!=null) return nextMove;


        return nextMove;

    }

    private Point checkForkStateAndRotate(String mark, String[][] boardState, String state) {
        String[][] triangularForkState = StringBoardParser.parseString(state);

        int winMoveCount;
        int markCount;
        boolean targetPointAvailable;
        Point targetPoint;

        for (int rotations = 1; rotations <= 4; rotations++) {

            winMoveCount=0;
            markCount=0;
            targetPointAvailable=false;
            targetPoint=null;

//            StringBoardParser.printBoard(triangularForkState);
//            System.out.println("-----");

            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if(" ".equals(triangularForkState[row][col])) continue;
                    else if("E".equals(triangularForkState[row][col])) {
                        if(" ".equals(boardState[row][col])){
                            winMoveCount++;
                        }
                    }
                    else if("M".equals(triangularForkState[row][col])){
                        if(mark.equals(boardState[row][col])){
                            markCount++;
                        }
                    }
                    else if("T".equals(triangularForkState[row][col])){
                        if(" ".equals(boardState[row][col])){
                            targetPointAvailable = true;
                            targetPoint = new Point(row,col);
                        }
                    }
                }
            }

            if(targetPointAvailable && markCount == 2 && winMoveCount>=2 ){
                //LOG.info("checkForNextForkMove at Rotation "+rotations);
                //StringBoardParser.printBoard(boardState);
                return targetPoint;
            }

            triangularForkState = BoardRotation.rotateCW(triangularForkState);

        }

//        for (int row = 0; row < 3; row++) {
//            for (int col = 0; col < 3; col++) {
//                if(" ".equals(triangularForkState[row][col])) continue;
//                else if("E".equals(triangularForkState[row][col])) {
//                    if(" ".equals(boardState[row][col])){
//                        winMoveCount++;
//                    }
//                }
//                else if("M".equals(triangularForkState[row][col])){
//                    if(mark.equals(boardState[row][col])){
//                        markCount++;
//                    }
//                }
//                else if("T".equals(triangularForkState[row][col])){
//                    if(" ".equals(boardState[row][col])){
//                        targetPointAvailable = true;
//                        targetPoint = new Point(row,col);
//                    }
//                }
//            }
//        }

//        if(targetPointAvailable && markCount == 2 && winMoveCount>=2 ){
//            return targetPoint;
//        }else{
//            return null;
//        }

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
