package dev.harsh.tictactoe.strategies.winning;

import dev.harsh.tictactoe.models.Board;
import dev.harsh.tictactoe.models.BoardCell;
import dev.harsh.tictactoe.models.GameSymbol;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

public class RowWinningStrategy implements WinningStrategy{
    List<Map<GameSymbol,Integer>> list = new ArrayList<>();
    @Override
    public boolean checkWin(Board board, BoardCell boardCell) {
        int size = board.getSize();
        int row = boardCell.getRow();
        assert boardCell.getSymbol().isPresent();
        GameSymbol gameSymbol = boardCell.getSymbol().get();
        if(list.isEmpty()){
            initializeList(size);
        }
        return isWinner(row, gameSymbol,size);
    }

    private boolean isWinner(int row, GameSymbol gameSymbol,int noOfRows) {
        Map<GameSymbol,Integer> symbolCountMap = list.get(row);
        symbolCountMap.putIfAbsent(gameSymbol,0);
        symbolCountMap.put(gameSymbol,symbolCountMap.get(gameSymbol) + 1);
        return symbolCountMap.get(gameSymbol) == noOfRows;
    }

    private void initializeList(int size){
        IntStream.range(0,size)
                .forEach((a)->list.add(new HashMap<>()));
    }
}
