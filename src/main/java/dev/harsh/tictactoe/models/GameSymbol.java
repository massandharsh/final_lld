package dev.harsh.tictactoe.models;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class GameSymbol {
    private char symbol;
    public GameSymbol(char symbol){
        this.symbol = symbol;
    }
}
