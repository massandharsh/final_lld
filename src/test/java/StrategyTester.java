import dev.harsh.tictactoe.factories.WinningStrategyFactory;
import dev.harsh.tictactoe.models.Board;
import dev.harsh.tictactoe.models.GameSymbol;
import dev.harsh.tictactoe.strategies.winning.WinningStrategy;
import dev.harsh.tictactoe.strategies.winning.WinningStrategyType;
import org.junit.Assert;
import org.junit.Test;

public class StrategyTester {
    private final static int BOARD_SIZE = 3;
    @Test
    public void testRowWinningStrategy(){
        WinningStrategy winningStrategy = WinningStrategyFactory.getWinningStrategyInstance(WinningStrategyType.ROW);
        Board board = new Board(BOARD_SIZE);
        GameSymbol gameSymbol = new GameSymbol('X');
        boolean isWinner = false;
        assert winningStrategy != null;
        int row = 0;
        for(int col = 0 ; col < BOARD_SIZE ; ++col){
            board.setSymbol(gameSymbol,row,col);
            isWinner = winningStrategy.checkWin(board,board.getCell(row,col));
        }
        Assert.assertTrue(isWinner);
    }

    @Test
    public void testColWinningStrategy(){
        WinningStrategy winningStrategy = WinningStrategyFactory.getWinningStrategyInstance(WinningStrategyType.COL);
        Board board = new Board(BOARD_SIZE);
        GameSymbol gameSymbol = new GameSymbol('X');
        boolean isWinner = false;
        assert winningStrategy != null;
        int col = 0;
        for(int row = 0 ; row < BOARD_SIZE ; ++row){
            board.setSymbol(gameSymbol,row,col);
            isWinner = winningStrategy.checkWin(board,board.getCell(row,col));
        }
        Assert.assertTrue(isWinner);
    }

    @Test
    public void testDiagonalWinningStrategy(){
        WinningStrategy winningStrategy = WinningStrategyFactory.getWinningStrategyInstance(WinningStrategyType.DIAGONAL);
        Board board = new Board(BOARD_SIZE);
        GameSymbol gameSymbol = new GameSymbol('X');
        boolean isWinner = false;
        assert winningStrategy != null;
        int col = 0;
        for(int row = 0 ; row < BOARD_SIZE ; ++row){
            board.setSymbol(gameSymbol,row,col);
            isWinner = winningStrategy.checkWin(board,board.getCell(row,col));
            col++;
        }
        Assert.assertTrue(isWinner);
    }

    @Test
    public void testAntiDiagonalWinningStrategy(){
        WinningStrategy winningStrategy = WinningStrategyFactory.getWinningStrategyInstance(WinningStrategyType.DIAGONAL);
        Board board = new Board(BOARD_SIZE);
        GameSymbol gameSymbol = new GameSymbol('X');
        boolean isWinner = false;
        assert winningStrategy != null;
        int col = board.getSize() - 1;
        for(int row = 0 ; row < BOARD_SIZE ; ++row){
            board.setSymbol(gameSymbol,row,col);
            isWinner = winningStrategy.checkWin(board,board.getCell(row,col));
            col--;
        }
        Assert.assertTrue(isWinner);
    }
}
