package dev.harsh.tictactoe.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
public abstract class Player {
    private GameSymbol gameSymbol;
    public abstract BoardCell makeMove(Board board);
}
