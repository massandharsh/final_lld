import dev.harsh.tictactoe.factories.WinningStrategyFactory;
import dev.harsh.tictactoe.models.Board;
import dev.harsh.tictactoe.models.BoardCell;
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
}
