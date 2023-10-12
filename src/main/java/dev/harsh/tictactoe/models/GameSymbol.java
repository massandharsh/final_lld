package dev.harsh.tictactoe.models;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GameSymbol {
    private char symbol;
    public GameSymbol(char symbol){
        this.symbol = symbol;
    }
}
