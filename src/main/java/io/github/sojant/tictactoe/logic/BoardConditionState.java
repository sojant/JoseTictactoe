package io.github.sojant.tictactoe.logic;

/**
 * Created by Sojant on 2017-10-25.
 */
public class BoardConditionState {

    Integer winMoveCount = null;
    Integer markCount = null;
    Integer adversaryCount = null;
    Integer targetPointAvailable = null;

    public Integer getWinMoveCount() {
        return winMoveCount;
    }

    public void setWinMoveCount(Integer winMoveCount) {
        this.winMoveCount = winMoveCount;
    }

    public Integer getMarkCount() {
        return markCount;
    }

    public void setMarkCount(Integer markCount) {
        this.markCount = markCount;
    }

    public Integer getAdversaryCount() {
        return adversaryCount;
    }

    public void setAdversaryCount(Integer adversaryCount) {
        this.adversaryCount = adversaryCount;
    }

    public Integer getTargetPointAvailable() {
        return targetPointAvailable;
    }

    public void setTargetPointAvailable(Integer targetPointAvailable) {
        this.targetPointAvailable = targetPointAvailable;
    }
}
