package dev.harsh.tictactoe.models;

import lombok.*;
import lombok.experimental.Accessors;
import lombok.experimental.SuperBuilder;

@AllArgsConstructor
@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@ToString
public abstract class Player {
    private GameSymbol gameSymbol;
    public abstract BoardCell makeMove(Board board);
}
