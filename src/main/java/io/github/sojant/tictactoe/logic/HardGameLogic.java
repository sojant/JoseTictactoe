package io.github.sojant.tictactoe.logic;

import io.github.sojant.tictactoe.model.Point;
import io.github.sojant.tictactoe.util.BoardRotation;
import io.github.sojant.tictactoe.util.StringBoardParser;
import io.github.sojant.tictactoe.view.BoardView;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        if (nextMove!=null){
            LOG.info("HardGameLogic chose FORK");
            return nextMove;
        }

        nextMove = checkForNextForkBlock(mark,boardState);
        if (nextMove!=null){
            LOG.info("HardGameLogic chose FORK BLOCK");
            return nextMove;
        }

        nextMove = checkForNextCenterMove(mark,boardState);
        if (nextMove!=null){
            LOG.info("HardGameLogic chose CENTER");
            return nextMove;
        }

        nextMove = checkForNextOppositeCorner(mark,boardState);
        if (nextMove!=null){
            LOG.info("HardGameLogic chose OPPOSITE CORNER");
            return nextMove;
        }


        nextMove = easyGameLogic.makeNextMove(board);
        LOG.info("HardGameLogic defaulted to next open space");


        return nextMove;

    }

    public Point checkForNextOppositeCorner(String mark, String[][] boardState) {

        String oppositeCornerState =
                "|A  |"+
                "|   |"+
                "|  T|";

        BoardConditionState condition = new BoardConditionState();
        condition.setAdversaryCount(1);
        condition.setTargetPointAvailable(1);

        Point nextMove = checkForkStateAndRotate(mark,boardState,oppositeCornerState,condition);
        if(nextMove!=null) return nextMove;

        return null;
    }

    public  Point checkForNextCenterMove(String mark, String[][] boardState) {

        if(" ".equals(boardState[1][1])){
            return new Point(1,1);
        }
        else
            return null;
    }

    public Point checkForNextForkBlock(String mark, String[][] boardState) {

        Point point = checkForNextForkMove(getOponentMark(mark), boardState);

        if(point!=null){

            Map<String, List<Point>> preWinMap = checkForNextPreWinMove(mark, boardState);

            if(preWinMap!=null){

                for(String key : preWinMap.keySet()){
                    List<Point> possiblePreWinMoves = preWinMap.get(key);
                    for(Point p : possiblePreWinMoves){

                        // Simulates the next move, to verify there won't be an opponent Fork
                        BoardView futureBoard = new BoardView();
                        futureBoard.setBoardState(boardState);
                        futureBoard.makeMove(mark,p);

                        Point opponentPossibleFork = checkForNextForkMove(getOponentMark(mark), futureBoard.getBoardState());
                        if(opponentPossibleFork==null){
                            LOG.info("HardGameLogic chose FORK BLOCK - PREWIN MOVE");
                            return p;
                        }else{
                            LOG.info("HardGameLogic discarded "+opponentPossibleFork+" It would make opponent Fork.");
                            LOG.info("FutureBoard:");
                            StringBoardParser.printBoard(futureBoard);
                        }

                    }
                }

                // Iterar la Lista y Tirar en el pimer lugar que cree una oportunidad de ganar.
                // Verificar que la jugada seleccionada no le permita Fork al oponente.
            }

            // If there is no move to be on a PreWin State, place in Opponent's fork point
            LOG.info("HardGameLogic chose FORK BLOCK - TAKE FORK");
            return point;


        }


        return null;
    }

    public Point checkForNextForkMove(String mark, String[][] boardState) {

        Point nextMove = null;

        BoardConditionState condition = new BoardConditionState();
        condition.setTargetPointAvailable(1);
        condition.setMarkCount(2);
        condition.setWinMoveCount(2);

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

        nextMove = checkForkStateAndRotate(mark,boardState,triangularForkState1,condition);
        if(nextMove!=null) return nextMove;

        nextMove = checkForkStateAndRotate(mark,boardState,triangularForkState2,condition);
        if(nextMove!=null) return nextMove;

        nextMove = checkForkStateAndRotate(mark,boardState,triangularForkState3,condition);
        if(nextMove!=null) return nextMove;

        String arrowHeadForkState1 =
                "|TME|"+
                "|M  |"+
                "|E  |";

        nextMove = checkForkStateAndRotate(mark,boardState,arrowHeadForkState1,condition);
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

        nextMove = checkForkStateAndRotate(mark,boardState,encirclementForkState1,condition);
        if(nextMove!=null) return nextMove;

        nextMove = checkForkStateAndRotate(mark,boardState,encirclementForkState2,condition);
        if(nextMove!=null) return nextMove;

        nextMove = checkForkStateAndRotate(mark,boardState,encirclementForkState3,condition);
        if(nextMove!=null) return nextMove;


        return nextMove;

    }

    private Point checkForkStateAndRotateOnlyForks(String mark, String[][] boardState, String state) {

        //"T" stands for the target point to make the last Fork Move
        //"E" stands for an explicit empty space
        //"M" stands for a mark of the current player
        //" " stands for 'ignore whatever is on this spot'
        //"A" stands for a mark of the adversary player

        String[][] triangularForkState = StringBoardParser.parseString(state);

        int winMoveCount;
        int markCount;
        int adversaryCount;
        int targetPointAvailable;
        Point targetPoint;

        for (int rotations = 1; rotations <= 4; rotations++) {

            winMoveCount=0;
            markCount=0;
            adversaryCount=0;
            targetPointAvailable=0;
            targetPoint=null;

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
                    }else if("A".equals(triangularForkState[row][col])){
                        if(getOponentMark(mark).equals(boardState[row][col])){
                            adversaryCount++;
                        }
                    }
                    else if("T".equals(triangularForkState[row][col])){
                        if(" ".equals(boardState[row][col])){
                            targetPointAvailable ++;
                            targetPoint = new Point(row,col);
                        }
                    }
                }
            }

            if(targetPointAvailable==1 && markCount == 2 && winMoveCount>=2 ){
                //LOG.info("checkForNextForkMove at Rotation "+rotations);
                //StringBoardParser.printBoard(boardState);
                return targetPoint;
            }
            triangularForkState = BoardRotation.rotateCW(triangularForkState);
        }

        return null;
    }

    private Point checkForkStateAndRotate(String mark, String[][] boardState, String state, BoardConditionState conditions) {

        //"T" stands for the target point to make the last Fork Move
        //"E" stands for an explicit empty space
        //"M" stands for a mark of the current player
        //" " stands for 'ignore whatever is on this spot'
        //"A" stands for a mark of the adversary player

        String[][] triangularForkState = StringBoardParser.parseString(state);

        int winMoveCount;
        int markCount;
        int adversaryCount;
        int targetPointAvailable;
        Point targetPoint;

        for (int rotations = 1; rotations <= 4; rotations++) {

            winMoveCount=0;
            markCount=0;
            adversaryCount=0;
            targetPointAvailable=0;
            targetPoint=null;

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
                    }else if("A".equals(triangularForkState[row][col])){
                        if(getOponentMark(mark).equals(boardState[row][col])){
                            adversaryCount++;
                        }
                    }
                    else if("T".equals(triangularForkState[row][col])){
                        if(" ".equals(boardState[row][col])){
                            targetPointAvailable ++;
                            targetPoint = new Point(row,col);
                        }
                    }
                }
            }


            if(
                    (conditions.getTargetPointAvailable() == null || targetPointAvailable>=conditions.getTargetPointAvailable()) &&
                    (conditions.getMarkCount() == null || markCount>=conditions.getMarkCount()) &&
                    (conditions.getWinMoveCount() == null || winMoveCount>=conditions.getWinMoveCount()) &&
                    (conditions.getAdversaryCount() == null || adversaryCount>=conditions.getAdversaryCount())
                    )
            {
                return targetPoint;
            }

            triangularForkState = BoardRotation.rotateCW(triangularForkState);
        }

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

        // Search por possible Win Condition in Diagonal
        int emptyPoints=0;
        int markPoints=0;
        for (int i = 0; i <3; i++) {

            if(" ".equals(boardState[i][i])){
                emptyPoints++;
                p = new Point(i,i);
            }
            else if(mark.equals(boardState[i][i])) markPoints++;
        }
        if(emptyPoints==1 && markPoints==2){
            return p;
        }


        // Search por possible Win Condition in Inverted Diagonal
        emptyPoints=0;
        markPoints=0;

        for (int i = 0; i <3; i++) {

            if(" ".equals(boardState[i][2-i])){
                emptyPoints++;
                p = new Point(i,2-i);
            }
            else if(mark.equals(boardState[i][2-i])) markPoints++;
        }
        if(emptyPoints==1 && markPoints==2){
            return p;
        }


        return null;
    }

    public Map<String,List<Point>> checkForNextPreWinMove(String mark, String[][] boardState) {

        mark=mark.toUpperCase();
        Map<String,List<Point>> mapPlays = new HashMap<String, List<Point>>();
        List<Point> posiblePlays;
        Point p = null;

        // Search por possible Win Condition in All Columns

        for (int col = 0; col < 3; col++) {

            posiblePlays = new ArrayList<Point>();
            int emptyPoints=0;
            int markPoints=0;
            for (int row = 0; row < 3; row++) {
                if(" ".equals(boardState[row][col])){
                    emptyPoints++;
                    p = new Point(row,col);
                    posiblePlays.add(p);
                }
                else if(mark.equals(boardState[row][col])) markPoints++;
            }
            if(emptyPoints==2 && markPoints==1){
                //return posiblePlays;
                mapPlays.put("COL"+col,posiblePlays);

            }
        }

        // Search por possible Win Condition in All Rows

        for (int col = 0; col < 3; col++) {

            posiblePlays = new ArrayList<Point>();
            int emptyPoints=0;
            int markPoints=0;
            for (int row = 0; row < 3; row++) {
                if(" ".equals(boardState[col][row])){
                    emptyPoints++;
                    p = new Point(col,row);
                    posiblePlays.add(p);
                }
                else if(mark.equals(boardState[col][row])) markPoints++;
            }
            if(emptyPoints==2 && markPoints==1){
                //return posiblePlays;
                mapPlays.put("ROW"+col,posiblePlays);
            }
        }

        // Search por possible Win Condition in Diagonal
        posiblePlays = new ArrayList<Point>();
        int emptyPoints=0;
        int markPoints=0;
        for (int i = 0; i <3; i++) {

            if(" ".equals(boardState[i][i])){
                emptyPoints++;
                p = new Point(i,i);
                posiblePlays.add(p);
            }
            else if(mark.equals(boardState[i][i])) markPoints++;
        }
        if(emptyPoints==2 && markPoints==1){
            //return posiblePlays;
            mapPlays.put("DIAG\\",posiblePlays);
        }


        // Search por possible Win Condition in Inverted Diagonal
        posiblePlays = new ArrayList<Point>();
        emptyPoints=0;
        markPoints=0;

        for (int i = 0; i <3; i++) {

            if(" ".equals(boardState[i][2-i])){
                emptyPoints++;
                p = new Point(i,2-i);
                posiblePlays.add(p);
            }
            else if(mark.equals(boardState[i][2-i])) markPoints++;
        }
        if(emptyPoints==2 && markPoints==1){
            //return posiblePlays;
            mapPlays.put("DIAG/",posiblePlays);
        }

        if(mapPlays.keySet().size()>0)
            return mapPlays;
        else
            return null;

    }


    public String getOponentMark(String playerMark){
        playerMark = playerMark.toUpperCase();
        return "X".equals(playerMark) ? "O":"X";
    }
}
