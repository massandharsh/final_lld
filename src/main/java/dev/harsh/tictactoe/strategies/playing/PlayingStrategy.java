package dev.harsh.tictactoe.strategies.playing;

import dev.harsh.tictactoe.models.Board;
import dev.harsh.tictactoe.models.BoardCell;

public interface PlayingStrategy {
    BoardCell makeMove(Board board);
}
