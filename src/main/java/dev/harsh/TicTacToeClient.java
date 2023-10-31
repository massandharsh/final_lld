package dev.harsh;

// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import dev.harsh.tictactoe.models.*;
import dev.harsh.tictactoe.strategies.winning.WinningStrategyType;

import java.util.*;

public class TicTacToeClient {
    private final static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {

        System.out.println("!!Let the fun begin with tic tac toe!!");
        //Take input for players
        List<Player> playerList = new ArrayList<>(getHumanPlayersList());
        playerList.addAll(getBotPlayersList());
        Game game = intialiseGame(playerList);
        //Start the game
        game.start();
        //Play the game
        while (game.isGameActive()){
            Player player = game.getNextPlayer();
            System.out.println("Next Player: " + player.getGameSymbol().getSymbol());
            game.makeMove();
            game.getBoard().print();
        }
        if(game.getWinnerPlayer().isPresent()){
            //We have a winner
            game.getBoard().print();
            System.out.println("!!Winner !!" + game.getWinnerPlayer().get().getGameSymbol());
        }
        else{
            System.out.println("!!Game ended in a draw!!");
        }
    }

    private static Game intialiseGame(List<Player> playerList) {
        System.out.println("!!Please enter the board size!!");
        int boardSize = scanner.nextInt();
        //Take winning strategies
        System.out.println("!!Choose winning strategies \\uD83D\\uDE03 !!");
        WinningStrategyType [] winningStrategyTypes = WinningStrategyType.values();
        System.out.println(Arrays.toString(winningStrategyTypes));
        System.out.println("!!Please enter number of winning strategies you want \\uD83D\\uDE03!!");
        int noOfWinningStrategies = scanner.nextInt();
        List<WinningStrategyType> winningStrategiesTypes = new ArrayList<>(noOfWinningStrategies);
        for(int i = 0 ; i < noOfWinningStrategies; ++i){
            System.out.println("!!Please enter winning strategy!! " + (i+1));
            WinningStrategyType strategyTypes = null;
            try{
                 strategyTypes = winningStrategyTypes[scanner.nextInt()];
            }
            catch (ArrayIndexOutOfBoundsException arrayIndexOutOfBoundsException){
                System.out.println("!!Please enter a valid index \uD83D\uDE29!!");
            }

            winningStrategiesTypes.add(strategyTypes);
        }
        //Build the game
        return Game.builder()
                .withSize(boardSize)
                .withPlayers(playerList)
                .withWithStrategies(winningStrategiesTypes)
                .build();

    }

    private static List<BotPlayer> getBotPlayersList() {
        //Ask input for bot player
        System.out.println("!!Enter the number of bot players!!");
        int noOfBots = scanner.nextInt();
        List<BotPlayer> botPlayers = new ArrayList<>();
        scanner.nextLine();
        for(int i = 0 ; i < noOfBots ; ++i){
            System.out.println("!!Enter the Game Level of : " + (i+1) + " bot !!" );
            GameLevel gameLevel;
            try{
                gameLevel = GameLevel.valueOf(scanner.nextLine());
            }
            catch (IllegalArgumentException exception){
                System.out.println("!!Please enter the correct game level!! \uD83D\uDE29");
                --i;
                continue;
            }
            BotPlayer botPlayer = new BotPlayer(new GameSymbol(getRandomCharacter()),gameLevel);
            botPlayers.add(botPlayer);
        }
        return botPlayers;
    }

    private static List<HumanPlayer> getHumanPlayersList() {
        //Ask input for human player
        System.out.println("!!Enter the number of human players!!");
        List<HumanPlayer> playerList = new ArrayList<>();
        int noOfHumanPlayers = scanner.nextInt();
        scanner.nextLine();
        HumanPlayer player = new HumanPlayer();

        //Ask for email,id and Symbol
        for(int i = 0 ; i < noOfHumanPlayers ; ++i){
            System.out.println("!!Enter email for player no: " + (i+1) + " !!");
            String email = scanner.nextLine();
            System.out.println("!!Enter name for player no: "  + (i+1) + " !!");
            String name = scanner.nextLine();
            System.out.println("!!Enter Symbol for player no: " + (i+1) + " !!");
            GameSymbol gameSymbol = new GameSymbol(scanner.nextLine().charAt(0));
            HumanPlayer humanPlayer = HumanPlayer.builder()
                    .user(User.builder()
                            .photo("")
                            .email(email)
                            .name(name)
                            .build())
                    .gameSymbol(gameSymbol)
                    .build();
            playerList.add(humanPlayer);
        }
        return playerList;
    }

    private static char getRandomCharacter(){
        Random random = new Random();

        // Generate a random character within the ASCII range (32 to 126)
        int minAsciiValue = 0;
        int maxAsciiValue = 500;
        int randomAsciiValue = random.nextInt(maxAsciiValue - minAsciiValue + 1) + minAsciiValue;

        // Convert the random ASCII value to a character
        return (char) randomAsciiValue;
    }

}