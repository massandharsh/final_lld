package dev.harsh.tictactoe.factories;

import dev.harsh.tictactoe.models.GameLevel;
import dev.harsh.tictactoe.strategies.playing.PlayingStrategy;
import dev.harsh.tictactoe.strategies.playing.RandomBotPlayingStrategy;

public class PlayingStrategyFactory {
    public static PlayingStrategy getPlayingStrategyInstance(GameLevel gameLevel){
        return new RandomBotPlayingStrategy();
    }
}
