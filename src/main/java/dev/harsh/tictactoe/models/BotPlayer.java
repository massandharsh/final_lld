package dev.harsh.tictactoe.models;

import dev.harsh.tictactoe.factories.PlayingStrategyFactory;
import dev.harsh.tictactoe.strategies.playing.PlayingStrategy;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.util.Optional;

@Getter
@Setter
@SuperBuilder
public class BotPlayer extends Player{
    private GameLevel gameLevel;
    private PlayingStrategy playingStrategy;
    public BotPlayer(GameSymbol gameSymbol,GameLevel gameLevel)
    {
        super(gameSymbol);
        this.gameLevel = gameLevel;
        this.playingStrategy = PlayingStrategyFactory.getPlayingStrategyInstance(gameLevel);
    }
    @Override
    public BoardCell makeMove(Board board) {
        BoardCell boardCell = this.playingStrategy.makeMove(board);
        return boardCell.setSymbol(Optional.of(this.getGameSymbol()));
    }
}
