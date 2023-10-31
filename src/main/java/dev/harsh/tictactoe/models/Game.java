package dev.harsh.tictactoe.models;

import dev.harsh.tictactoe.exceptions.DuplicateSymbolException;
import dev.harsh.tictactoe.exceptions.InvalidBoardSizeException;
import dev.harsh.tictactoe.exceptions.InvalidMoveException;
import dev.harsh.tictactoe.exceptions.InvalidNumberOfPlayersException;
import dev.harsh.tictactoe.factories.WinningStrategyFactory;
import dev.harsh.tictactoe.strategies.winning.WinningStrategy;
import dev.harsh.tictactoe.strategies.winning.WinningStrategyType;
import lombok.Getter;

import java.util.*;
import java.util.stream.Collectors;

/**
 *
 * This game class will work like an orchestrator or driver class
 * we can wrap the things inside this and make a game play for
 * us
 * */
@Getter
public class Game {
    //Game will have a board
    //Players that will be playing the game
    //Winner player
    //Game status will be present with default value
    //nextPlayerIndex who will play the current move
    //List of winning strategies
    //Also some validations will be there to build the game--->think of builder here as complex object and validations

    private static final GameStatus DEFAULT_STATUS = GameStatus.IN_PROGRESS;
    private static final int MIN_BOARD_SIZE = 3;
    private static final int MIN_PLAYER = 2;
    private Board board;
    private List<Player> players;
    private Optional<Player> winnerPlayer;
    private GameStatus gameStatus;
    private int nextPlayerIndex;
    private List<WinningStrategy> winningStrategies;

    private Game(){
        players = new ArrayList<>();
        winningStrategies = new ArrayList<>();
    }

    public static class GameBuilder{
        private final Game game;
        private GameBuilder(){
            game = new Game();
        }
        public GameBuilder withSize(int size){
            game.board = new Board(size);
            return this;
        }

        public GameBuilder withPlayers(List<Player> players){
            game.players = players;
            return this;
        }
        public GameBuilder withWinningStrategy(WinningStrategyType winningStrategyType){
            game.winningStrategies.add(WinningStrategyFactory.getWinningStrategyInstance(winningStrategyType));
            return this;
        }

        public GameBuilder withWithStrategies(List<WinningStrategyType> winningStrategyTypes){
            for(WinningStrategyType winningStrategyType : winningStrategyTypes){
                game.winningStrategies.add(WinningStrategyFactory.getWinningStrategyInstance(winningStrategyType));
            }
            return this;
        }
        public Game build(){
            validate();
            //then set some other properties
            game.gameStatus = DEFAULT_STATUS;
            game.winnerPlayer = Optional.empty();
            return game;
        }

        private void validate() {
            //validate the number of player
            //validate the symbols
            if(game.players.size() < MIN_PLAYER){
                throw new InvalidNumberOfPlayersException("Number of players: " + game.players.size() +  " is less than :" + MIN_PLAYER);
            }
            Set<Character> gameSymbolSet =  game.players.stream().map((player -> player.getGameSymbol().getSymbol()))
                    .collect(Collectors.toSet());
            if(gameSymbolSet.size() != game.players.size()){
               throw new DuplicateSymbolException("Please maintain different symbols for each player");
            }
            if(game.board.getSize() < MIN_BOARD_SIZE){
                throw new InvalidBoardSizeException("Please enter min size >= " + MIN_BOARD_SIZE);
            }
        }

    }
    public static GameBuilder builder(){
        return new GameBuilder();
    }

    public Player getNextPlayer(){
        return players.get(nextPlayerIndex);
    }

    public void start(){
        //Assign random index to the playing index
        Random random = new Random();
        nextPlayerIndex = random.nextInt(0,players.size());
    }

    //Main make move method
    public void makeMove(){
        BoardCell cell = playNextMove();
        //Fill the cell with symbol
        assert cell.getSymbol().isPresent();
        board.setSymbol(cell.getSymbol().get(),cell.getRow(),cell.getCol());
        //Check for win
        if(isWin(cell)){
            winnerPlayer = Optional.of(players.get(nextPlayerIndex));
            gameStatus = GameStatus.FINISHED;
            return;
        }
        //Check for draw
        if(isDraw()){
            gameStatus = GameStatus.DRAW;
            return;
        }

        //Increase the player index
        nextPlayerIndex = (nextPlayerIndex + 1) % players.size();
    }

    private boolean isWin(BoardCell boardCell) {
        //Iterate on each winning strategy and check for winner
        for(WinningStrategy winningStrategy : winningStrategies){
            if(winningStrategy.checkWin(board,boardCell)){
                return true;
            }
        }
        return false;
    }

    private boolean isDraw() {
        return board.isFull();
    }

    private BoardCell playNextMove(){
        //Play the move
        BoardCell boardCell = players.get(nextPlayerIndex).makeMove(board);
        //Validate the move
        if(!board.isEmpty(boardCell.getRow(),boardCell.getCol())){
            throw new InvalidMoveException("Move played is invalid");
        }
        return boardCell;
    }

    public boolean isGameActive(){
        return gameStatus == GameStatus.IN_PROGRESS;
    }


}
