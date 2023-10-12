package dev.harsh.tictactoe.models;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

@Getter
@Setter
public class Board {
    private List<List<BoardCell>> cells;
    private int size;

    public Board(int size){
        this.size = size;
        this.cells = new ArrayList<>();
        //Based on size initialize the board
        IntStream.range(0,size).forEach((row)->{
            cells.add(new ArrayList<>());
            IntStream.range(0,size).forEach((col)->{
                cells.get(row).add(new BoardCell(row,col));
            });
        });
    }

    public void print(){
        //Print the board later !!!
        System.out.print("   ");
        for(int i=0;i<size;i++)
            System.out.print(i+"   ");
        System.out.println();
        for (int i = 0; i < size; i++) {
            System.out.print(i + "  ");
            for (int j = 0; j < size; j++) {
                Optional<GameSymbol> gameSymbol = this.cells.get(i).get(j).getSymbol();
                System.out.print((gameSymbol.map(GameSymbol::getSymbol).orElse('-')) + " | ");
            }
            System.out.println();
            for (int k = 0; k <= size; k++)
                System.out.print("----");
            System.out.println();
        }
    }

    public void setSymbol(GameSymbol gameSymbol,int row,int col){
        cells.get(row).get(col).setSymbol(Optional.of(gameSymbol));
    }

    public boolean isEmpty(int row,int col){
        return this.getCells().get(row).get(col).getSymbol().isEmpty();
    }

    public boolean isFull(){
        Board board = this;
        for(List<BoardCell> boardCells : board.getCells()){
            for(BoardCell boardCell : boardCells){
                if(boardCell.getSymbol().isEmpty()){
                    return false;
                }
            }
        }
        return true;
    }

    public BoardCell getCell(int row,int col){
        return this.getCells().get(row).get(col);
    }

    public List<BoardCell> getEmptyCells(){
        return cells.stream()
                .flatMap(Collection::stream)
                .filter((cell)->cell.getSymbol().isEmpty())
                .toList();
    }

}
