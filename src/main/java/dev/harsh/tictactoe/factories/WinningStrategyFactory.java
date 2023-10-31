package dev.harsh.tictactoe.factories;

import dev.harsh.tictactoe.strategies.winning.*;

public class WinningStrategyFactory {
    public static WinningStrategy getWinningStrategyInstance(WinningStrategyType winningStrategyType){
        return switch (winningStrategyType){
            case COL -> new ColumnWinningStrategy();
            case ROW -> new RowWinningStrategy();
            case DIAGONAL -> new DiagonalWinningStrategy();
        };
    }
}
