package io.github.sojant.tictactoe.model;

import io.github.sojant.tictactoe.logic.GameLogic;

/**
 * Created by Sojant on 2017-10-25.
 */
public class Player {

    String name;
    String mark;

    GameLogic gameLogic;

    public Player(String name, GameLogic gameLogic) {
        this.name = name;
        this.gameLogic = gameLogic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMark() {
        return mark;
    }

    public void setMark(String mark) {
        this.mark = mark;
    }

    public GameLogic getGameLogic() {
        return gameLogic;
    }

    public void setGameLogic(GameLogic gameLogic) {
        this.gameLogic = gameLogic;
    }
}
