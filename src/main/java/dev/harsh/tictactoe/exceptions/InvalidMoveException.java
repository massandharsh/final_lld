package dev.harsh.tictactoe.exceptions;

public class InvalidMoveException extends RuntimeException {
    public InvalidMoveException(String movePlayedIsInvalid) {
        super(movePlayedIsInvalid);
    }
}
