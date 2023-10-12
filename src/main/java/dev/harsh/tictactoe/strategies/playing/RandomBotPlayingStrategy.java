package dev.harsh.tictactoe.strategies.playing;

import dev.harsh.tictactoe.models.Board;
import dev.harsh.tictactoe.models.BoardCell;

import java.util.List;

public class RandomBotPlayingStrategy implements PlayingStrategy{

    @Override
    public BoardCell makeMove(Board board) {
        //Find the empty cells
        List<BoardCell> emptyCells = board.getEmptyCells();
        //Get some empty cell randomly
        int randomCellIndex = (int)(Math.random() * emptyCells.size());
        BoardCell boardCell = emptyCells.get(randomCellIndex);
        //Place symbol any one the cell randomly
        return new BoardCell(boardCell.getRow(), boardCell.getCol());
    }
}
