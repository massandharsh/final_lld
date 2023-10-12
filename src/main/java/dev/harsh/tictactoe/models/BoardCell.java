package dev.harsh.tictactoe.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Optional;

@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
public class BoardCell {
    private int row;
    private int col;
    private Optional<GameSymbol> symbol = Optional.empty();
    public BoardCell(int row,int col){
        this.row = row;
        this.col = col;
    }

}
