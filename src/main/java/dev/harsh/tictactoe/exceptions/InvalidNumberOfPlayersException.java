package dev.harsh.tictactoe.exceptions;

public class InvalidNumberOfPlayersException extends RuntimeException {
    public InvalidNumberOfPlayersException(String message) {
        super(message);
    }
}
