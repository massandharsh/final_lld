package dev.harsh.tictactoe.strategies.winning;

import dev.harsh.tictactoe.models.Board;
import dev.harsh.tictactoe.models.BoardCell;

public interface WinningStrategy {
    boolean checkWin(Board board, BoardCell boardCell);
}
