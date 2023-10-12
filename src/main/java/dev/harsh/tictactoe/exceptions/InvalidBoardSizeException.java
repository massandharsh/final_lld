package dev.harsh.tictactoe.exceptions;

public class InvalidBoardSizeException extends RuntimeException {
    public InvalidBoardSizeException(String message) {
        super(message);
    }
}
