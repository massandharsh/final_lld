package dev.harsh.tictactoe.models;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

import java.util.Optional;
import java.util.Scanner;


@Setter
@Getter
@SuperBuilder
@NoArgsConstructor
@Accessors(fluent = true,chain = true)
public class HumanPlayer extends Player{
    private User user;
    @Builder.Default
    private Scanner scanner = new Scanner(System.in);
    public HumanPlayer(GameSymbol gameSymbol,User user){
        super(gameSymbol);
        this.user = user;
    }
    @Override
    public BoardCell makeMove(Board board) {
        System.out.println("!!Enter row and col!!");
        int row = scanner.nextInt();
        int col = scanner.nextInt();
        BoardCell boardCell = new BoardCell(row,col);
        return boardCell.setSymbol(Optional.of(this.getGameSymbol()));
    }
}
