package dev.harsh.tictactoe.factories;

import dev.harsh.tictactoe.strategies.winning.RowWinningStrategy;
import dev.harsh.tictactoe.strategies.winning.WinningStrategy;
import dev.harsh.tictactoe.strategies.winning.WinningStrategyType;

public class WinningStrategyFactory {
    public static WinningStrategy getWinningStrategyInstance(WinningStrategyType winningStrategyType){
        return new RowWinningStrategy();
    }
}
