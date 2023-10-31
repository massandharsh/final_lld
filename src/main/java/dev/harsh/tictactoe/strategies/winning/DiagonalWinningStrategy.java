package dev.harsh.tictactoe.strategies.winning;

import dev.harsh.tictactoe.models.Board;
import dev.harsh.tictactoe.models.BoardCell;
import dev.harsh.tictactoe.models.GameSymbol;

import java.util.HashMap;
import java.util.Map;

public class DiagonalWinningStrategy implements WinningStrategy{
    Map<GameSymbol,Integer> diagonal = new HashMap<>();
    Map<GameSymbol,Integer> antiDiagonal = new HashMap<>();
    @Override
    public boolean checkWin(Board board, BoardCell boardCell) {
        int size = board.getSize();
        int row = boardCell.getRow();
        int col = boardCell.getCol();
        assert boardCell.getSymbol().isPresent();
        GameSymbol gameSymbol = boardCell.getSymbol().get();
        //Check for both diagonal and anti-diagonal wins
        return checkDiagonalWin(row, col, gameSymbol, size) || checkAntiDiagonalWin(row, col, size, gameSymbol);
    }

    private boolean checkAntiDiagonalWin(int row, int col, int size, GameSymbol gameSymbol) {
        if(row + col == size - 1){
            antiDiagonal.putIfAbsent(gameSymbol,0);
            antiDiagonal.put(gameSymbol,antiDiagonal.get(gameSymbol) + 1);
            return antiDiagonal.get(gameSymbol) == size;
        }
        return false;
    }

    private boolean checkDiagonalWin(int row, int col, GameSymbol gameSymbol, int size) {
        if(row == col){
            diagonal.putIfAbsent(gameSymbol,0);
            diagonal.put(gameSymbol,diagonal.get(gameSymbol) + 1);
            return diagonal.get(gameSymbol) == size;
        }
        return false;
    }

}
