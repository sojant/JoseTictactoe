package io.github.sojant.tictactoe.view;

import io.github.sojant.tictactoe.model.Point;

/**
 * Created by Sojant on 2017-10-23.
 */
public class BoardView {

    public void setBoardState(String[][] boardState) {
        this.boardState = boardState;
    }

    private String[][] boardState = new String[3][3];

    public BoardView(){
        initializeEmptyBoard();
    }

    public void initializeEmptyBoard(){
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                boardState[i][j]=" ";
            }
        }
    }

    public void printBoardOnScreen(){

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                System.out.print(" "+boardState[row][col]+" ");
                if(col<2) System.out.print("|");
            }
            if(row<2) System.out.println("\n------------");
        }
        System.out.println();

    }

    public String[][] getBoardState() {
        return boardState;
    }

    public boolean isEmptySpace(Point p){
        return (" ".equals(boardState[p.row][p.col]));
    }

    public void makeMove(String mark, Point p) {
        boardState[p.row][p.col] = mark;
    }

    public boolean isFull() {
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if(" ".equals(boardState[row][col]))
                    return false;
            }
        }
        return true;
    }

    public boolean checkForWinner(String mark) {

        mark=mark.toUpperCase();
        String token = mark+mark+mark;

        for (int row = 0; row < 3; row++) {
            if (token.equals(boardState[row][0]+boardState[row][1]+boardState[row][2])){
                return true;
            }
        }

        for (int col = 0; col < 3; col++) {
            if (token.equals(boardState[0][col]+boardState[1][col]+boardState[2][col])){
                return true;
            }
        }

        if (token.equals(boardState[0][0]+boardState[1][1]+boardState[2][2])){
            return true;
        }

        if (token.equals(boardState[0][2]+boardState[1][1]+boardState[2][0])){
            return true;
        }



        return false;
    }
}
