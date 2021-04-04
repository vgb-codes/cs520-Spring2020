import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import model.*;
import controller.*;
import view.*;

/**
 * An example test class, which merely shows how to write JUnit tests.
 */
public class TestExample {
    private RowGameModel gameModel;
    private RowGameController gameController;
    private RowGameRulesStrategy gameStrategy;

    // Test initial conditions
    @Test
    public void initialConditionCheck() {
        gameController = new RowGameController(6, 6, "ThreeInARow");
        gameModel = gameController.getModel();
        int moves = gameModel.getMovesLeft();
        String player = gameModel.getPlayer();
        assertEquals(moves, 36);
        assertEquals(player, "1");
    }

    // Illegal number of rows
    @Test (expected = IllegalArgumentException.class)
    public void testIllegalRowsAndColumns() {
        gameController = new RowGameController(-1, 3, "ThreeInARow");
    }

    // Illegal strategy
    @Test (expected = IllegalArgumentException.class)
    public void testIllegalStrategy() {
        gameController = new RowGameController(4, 5, "threeinarow");
    }

    // Test moves left update
    @Test
    public void testMovesLeftUpdate() {
        gameController = new RowGameController(3, 3, "TicTacToe");
        gameStrategy = gameController.getGameStrategy();
        gameModel = gameController.getModel();
        gameStrategy.move(gameModel, 0, 0);
        assertEquals(gameModel.getMovesLeft(), 8);
    }

    // Test if reset works for tictactoe
    @Test
    public void testResetTicTacToe() {
        gameController = new RowGameController(3, 3, "TicTacToe");
        gameStrategy = gameController.getGameStrategy();
        gameModel = gameController.getModel();
        gameStrategy.move(gameModel, 0, 0);
        gameStrategy.reset(gameModel);
        boolean legal = gameModel.getBlocksData()[0][0].getIsLegalMove();
        assertEquals(legal, true); 
    }

    // Test if already played block is legal for tictactoe
    @Test
    public void testIfLegalPlayedTicTacToe() {
        gameController = new RowGameController(3, 3, "TicTacToe");
        gameStrategy = gameController.getGameStrategy();
        gameModel = gameController.getModel();
        gameStrategy.move(gameModel, 0, 0);
        boolean legal = gameModel.getBlocksData()[0][0].getIsLegalMove();
        assertEquals(legal, false);
    }

    // Test if unplayed block is legal for tictactoe
    public void testIfLegalUnplayedTicTacToe() {
        gameController = new RowGameController(3, 3, "TicTacToe");
        gameStrategy = gameController.getGameStrategy();
        gameModel = gameController.getModel();
        gameStrategy.move(gameModel, 0, 0);
        boolean legal = gameModel.getBlocksData()[0][1].getIsLegalMove();
        assertEquals(legal, true);
    }

    // Test win condition for player 1 for tictactoe
    @Test
    public void testPlayer1WinTicTacToe() {
        gameController = new RowGameController(3, 3, "TicTacToe");
        gameStrategy = gameController.getGameStrategy();
        gameModel = gameController.getModel();
        // Player 1 - X
        gameStrategy.move(gameModel, 0, 0);
        // Player 2 - O
        gameStrategy.move(gameModel, 0, 1);
        // Player 1 - X
        gameStrategy.move(gameModel, 1, 0);
        // Player 2 - O
        gameStrategy.move(gameModel, 1, 1);
        // Player 1 - X
        gameStrategy.move(gameModel, 2, 0);
        assertEquals(gameStrategy.isWin(gameModel, 2, 0), true);
    }

    // Test win condition for player 2 for tictactoe
    @Test
    public void testPlayer2WinTicTacToe() {
        gameController = new RowGameController(3, 3, "TicTacToe");
        gameStrategy = gameController.getGameStrategy();
        gameModel = gameController.getModel();
        // Player 1 - X
        gameStrategy.move(gameModel, 0, 0);
        // Player 2 - O
        gameStrategy.move(gameModel, 0, 1);
        // Player 1 - X
        gameStrategy.move(gameModel, 1, 0);
        // Player 2 - O
        gameStrategy.move(gameModel, 1, 1);
        // Player 1 - X
        gameStrategy.move(gameModel, 0, 2);
        // Player 2 - O
        gameStrategy.move(gameModel, 2, 1);
        assertEquals(gameStrategy.isWin(gameModel, 2, 1), true);       
    }

    // Test tie condition for tictactoe
    @Test
    public void testTieConditionTicTacToe() {
        gameController = new RowGameController(3, 3, "TicTacToe");
        gameStrategy = gameController.getGameStrategy();
        gameModel = gameController.getModel();
        // Player 1 - X
        gameStrategy.move(gameModel, 0, 0);
        // Player 2 - O
        gameStrategy.move(gameModel, 1, 0);
        // Player 1 - X
        gameStrategy.move(gameModel, 2, 0);
        // Player 2 - O
        gameStrategy.move(gameModel, 0, 2);
        // Player 1 - X
        gameStrategy.move(gameModel, 0, 1);
        // Player 2 - O
        gameStrategy.move(gameModel, 1, 1);
        // Player 1 - X
        gameStrategy.move(gameModel, 1, 2);
        // Player 2 - O
        gameStrategy.move(gameModel, 2, 1);
        // Player 1 - X
        gameStrategy.move(gameModel, 2, 2);
        assertEquals(gameStrategy.isTie(gameModel), true);
    }

    // Test if reset works for threeinarow
    @Test
    public void testResetThreeInARow() {
        gameController = new RowGameController(3, 3, "ThreeInARow");
        gameStrategy = gameController.getGameStrategy();
        gameModel = gameController.getModel();
        gameStrategy.move(gameModel, 2, 0);
        gameStrategy.reset(gameModel);
        boolean legal = gameModel.getBlocksData()[2][0].getIsLegalMove();
        assertEquals(legal, true); 
    }

    // Test if already played block is legal for threeinarow
    @Test
    public void testIfLegalPlayedThreeInARow() {
        gameController = new RowGameController(3, 3, "ThreeInARow");
        gameStrategy = gameController.getGameStrategy();
        gameModel = gameController.getModel();
        gameStrategy.move(gameModel, 2, 0);
        boolean legal = gameModel.getBlocksData()[2][0].getIsLegalMove();
        assertEquals(legal, false);
    }

    // Test if unplayed block is legal for threeinarow
    public void testIfLegalUnplayedThreeInARow() {
        gameController = new RowGameController(3, 3, "ThreeInARow");
        gameStrategy = gameController.getGameStrategy();
        gameModel = gameController.getModel();
        gameStrategy.move(gameModel, 2, 0);
        boolean legal = gameModel.getBlocksData()[2][1].getIsLegalMove();
        assertEquals(legal, true);
    }

    // Test win condition for player 1 for threeinarow
    @Test
    public void testPlayer1WinThreeInARow() {
        gameController = new RowGameController(3, 3, "ThreeInARow");
        gameStrategy = gameController.getGameStrategy();
        gameModel = gameController.getModel();
        // Player 1 - X
        gameStrategy.move(gameModel, 2, 0);
        // Player 2 - O
        gameStrategy.move(gameModel, 2, 1);
        // Player 1 - X
        gameStrategy.move(gameModel, 1, 0);
        // Player 2 - O
        gameStrategy.move(gameModel, 1, 1);
        // Player 1 - X
        gameStrategy.move(gameModel, 0, 0);
        assertEquals(gameStrategy.isWin(gameModel, 0, 0), true);
    }

    // Test win condition for player 2 for tictactoe
    @Test
    public void testPlayer2WinThreeInARow() {
        gameController = new RowGameController(3, 3, "ThreeInARow");
        gameStrategy = gameController.getGameStrategy();
        gameModel = gameController.getModel();
        // Player 1 - X
        gameStrategy.move(gameModel, 2, 0);
        // Player 2 - O
        gameStrategy.move(gameModel, 2, 1);
        // Player 1 - X
        gameStrategy.move(gameModel, 1, 0);
        // Player 2 - O
        gameStrategy.move(gameModel, 1, 1);
        // Player 1 - X
        gameStrategy.move(gameModel, 2, 2);
        // Player 2 - O
        gameStrategy.move(gameModel, 0, 1);
        assertEquals(gameStrategy.isWin(gameModel, 0, 1), true);       
    }

    // Test tie condition for tictactoe
    @Test
    public void testTieConditionThreeInARow() {
        gameController = new RowGameController(3, 3, "ThreeInARow");
        gameStrategy = gameController.getGameStrategy();
        gameModel = gameController.getModel();
        // Player 1 - X
        gameStrategy.move(gameModel, 2, 0);
        // Player 2 - O
        gameStrategy.move(gameModel, 2, 1);
        // Player 1 - X
        gameStrategy.move(gameModel, 2, 2);
        // Player 2 - O
        gameStrategy.move(gameModel, 1, 0);
        // Player 1 - X
        gameStrategy.move(gameModel, 1, 2);
        // Player 2 - O
        gameStrategy.move(gameModel, 0, 2);
        // Player 1 - X
        gameStrategy.move(gameModel, 1, 1);
        // Player 2 - O
        gameStrategy.move(gameModel, 0, 0);
        // Player 1 - X
        gameStrategy.move(gameModel, 0, 1);
        assertEquals(gameStrategy.isTie(gameModel), true);
    }
}
