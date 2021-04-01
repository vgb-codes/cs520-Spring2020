package controller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import model.RowGameModel;
import view.RowGameGUI;


/**
 * Java implementation of the 3 in a row game, using the Swing framework.
 *
 * This quick-and-dirty implementation violates a number of software engineering
 * principles and needs a thorough overhaul to improve readability,
 * extensibility, and testability.
 */
public class RowGameController {
    public static final String GAME_END_NOWINNER = "Game ends in a draw";
	private RowGameModel gameModel;
    private RowGameGUI gameView;
	private int rows;
	private int cols;
	private RowGameRulesStrategy gameStrategy;

    /**
     * Creates a new game initializing the GUI.
     */
    public RowGameController(int rows, int cols) {		
		this.rows = rows;
		this.cols = cols;
		gameModel = new RowGameModel(this.rows, this.cols);
		gameView = new RowGameGUI(this, this.rows, this.cols);
		gameStrategy = new ThreeInARowStrategy(rows, cols);


		resetGame();
    }

    public RowGameModel getModel() {
		return this.gameModel;
    }

    public RowGameGUI getView() {
		return this.gameView;
    }

    public void startUp() {
		gameView.getGUI().setVisible(true);
    }

    /**
     * Moves the current player into the given block.
     *
     * @param block The block to be moved to by the current player
     */
    public void move(JButton block) {
	gameModel.setMovesLeft(gameModel.getMovesLeft() - 1);

	String player = gameModel.getPlayer();
	int movesLeft = gameModel.getMovesLeft();
	if(player.equals("1")) {
	    // Check whether player 1 won
	    if(block==gameView.getGameBoardView().getBlocks()[0][0]) {
		gameModel.getBlocksData()[0][0].setContents("X");
		gameModel.getBlocksData()[0][0].setIsLegalMove(false);
		gameModel.setPlayer("2");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[0][1].getContents()) &&
			gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[0][2].getContents())) ||
		       (gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[1][0].getContents()) &&
			gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[2][0].getContents())) ||
		       (gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[2][2].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.getGameBoardView().getBlocks()[0][1]) {
		gameModel.getBlocksData()[0][1].setContents("X");
		gameModel.getBlocksData()[0][1].setIsLegalMove(false);
		gameModel.setPlayer("2");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[0][0].getContents()) &&
			gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[0][2].getContents())) ||
		       (gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[2][1].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.getGameBoardView().getBlocks()[0][2]) {
		gameModel.getBlocksData()[0][2].setContents("X");
		gameModel.getBlocksData()[0][2].setIsLegalMove(false);
		gameModel.setPlayer("2");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[0][1].getContents()) &&
			gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[0][0].getContents())) ||
		       (gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[1][2].getContents()) &&
			gameModel.getBlocksData()[1][2].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[2][0].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.getGameBoardView().getBlocks()[1][0]) {
		gameModel.getBlocksData()[1][0].setContents("X");
		gameModel.getBlocksData()[1][0].setIsLegalMove(false);
		// Enable the space on top of this one
		gameModel.getBlocksData()[0][0].setIsLegalMove(true);
		gameModel.setPlayer("2");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[1][2].getContents())) ||
		       (gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[0][0].getContents()) &&
			gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[2][0].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.getGameBoardView().getBlocks()[1][1]) {
		gameModel.getBlocksData()[1][1].setContents("X");
		gameModel.getBlocksData()[1][1].setIsLegalMove(false);
		// Enable the space on top of this one
		gameModel.getBlocksData()[0][1].setIsLegalMove(true);
		gameModel.setPlayer("2");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[1][0].getContents()) &&
			gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[1][2].getContents())) ||
		       (gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][1].getContents()) &&
			gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[2][1].getContents())) ||
		       (gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][0].getContents()) &&
			gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][2].getContents()) &&
			gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[2][0].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.getGameBoardView().getBlocks()[1][2]) {
		gameModel.getBlocksData()[1][2].setContents("X");
		gameModel.getBlocksData()[1][2].setIsLegalMove(false);
		// Enable the space on top of this one
		gameModel.getBlocksData()[0][2].setIsLegalMove(true);
		gameModel.setPlayer("2");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[1][2].getContents().equals(gameModel.getBlocksData()[0][2].getContents()) &&
			gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[1][2].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[1][0].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.getGameBoardView().getBlocks()[2][0]) {
		gameModel.getBlocksData()[2][0].setContents("X");
		gameModel.getBlocksData()[2][0].setIsLegalMove(false);
		// Enable the space on top of this one
		gameModel.getBlocksData()[1][0].setIsLegalMove(true);
		gameModel.setPlayer("2");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[2][0].getContents().equals(gameModel.getBlocksData()[2][1].getContents()) &&
			gameModel.getBlocksData()[2][1].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[2][0].getContents().equals(gameModel.getBlocksData()[1][0].getContents()) &&
			gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[0][0].getContents())) ||
		       (gameModel.getBlocksData()[2][0].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][2].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(movesLeft==0) {
		       gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.getGameBoardView().getBlocks()[2][1]) {
		gameModel.getBlocksData()[2][1].setContents("X");
		gameModel.getBlocksData()[2][1].setIsLegalMove(false);
		// Enabled the space on top of this one
		gameModel.getBlocksData()[1][1].setIsLegalMove(true);
		gameModel.setPlayer("2");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[2][1].getContents().equals(gameModel.getBlocksData()[2][0].getContents()) &&
			gameModel.getBlocksData()[2][0].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[2][1].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][1].getContents()))) {
		        gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.getGameBoardView().getBlocks()[2][2]) {
		gameModel.getBlocksData()[2][2].setContents("X");
		gameModel.getBlocksData()[2][2].setIsLegalMove(false);
		// Enable the space on top of this one
		gameModel.getBlocksData()[1][2].setIsLegalMove(true);
		gameModel.setPlayer("2");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[2][2].getContents().equals(gameModel.getBlocksData()[2][1].getContents()) &&
			gameModel.getBlocksData()[2][1].getContents().equals(gameModel.getBlocksData()[2][0].getContents())) ||
		       (gameModel.getBlocksData()[2][2].getContents().equals(gameModel.getBlocksData()[1][2].getContents()) &&
			gameModel.getBlocksData()[1][2].getContents().equals(gameModel.getBlocksData()[0][2].getContents())) ||
		       (gameModel.getBlocksData()[2][2].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][0].getContents()))) {
			gameModel.setFinalResult("Player 1 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    }
	} else {
	    // Check whether player 2 won
	    if(block==gameView.getGameBoardView().getBlocks()[0][0]) {
		gameModel.getBlocksData()[0][0].setContents("O");
		gameModel.getBlocksData()[0][0].setIsLegalMove(false);
		gameModel.setPlayer("1");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[0][1].getContents()) &&
			gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[0][2].getContents())) ||
		       (gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[1][0].getContents()) &&
			gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[2][0].getContents())) ||
		       (gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[2][2].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.getGameBoardView().getBlocks()[0][1]) {
		gameModel.getBlocksData()[0][1].setContents("O");
		gameModel.getBlocksData()[0][1].setIsLegalMove(false);
		gameModel.setPlayer("1");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[0][0].getContents()) &&
			gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[0][2].getContents())) ||
		       (gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[2][1].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.getGameBoardView().getBlocks()[0][2]) {
		gameModel.getBlocksData()[0][2].setContents("O");
		gameModel.getBlocksData()[0][2].setIsLegalMove(false);
		gameModel.setPlayer("1");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[0][1].getContents()) &&
			gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[0][0].getContents())) ||
		       (gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[1][2].getContents()) &&
			gameModel.getBlocksData()[1][2].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[2][0].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.getGameBoardView().getBlocks()[1][0]) {
		gameModel.getBlocksData()[1][0].setContents("O");
		gameModel.getBlocksData()[1][0].setIsLegalMove(false);
		// Enable the space on top of this one
		gameModel.getBlocksData()[0][0].setIsLegalMove(true);
		gameModel.setPlayer("1");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[1][2].getContents())) ||
		       (gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[0][0].getContents()) &&
			gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[2][0].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.getGameBoardView().getBlocks()[1][1]) {
		gameModel.getBlocksData()[1][1].setContents("O");
		gameModel.getBlocksData()[1][1].setIsLegalMove(false);
		// Enable the space on top of this one
		gameModel.getBlocksData()[0][1].setIsLegalMove(true);
		gameModel.setPlayer("1");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[1][0].getContents()) &&
			gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[1][2].getContents())) ||
		       (gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][1].getContents()) &&
			gameModel.getBlocksData()[0][1].getContents().equals(gameModel.getBlocksData()[2][1].getContents())) ||
		       (gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][0].getContents()) &&
			gameModel.getBlocksData()[0][0].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][2].getContents()) &&
			gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[2][0].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.getGameBoardView().getBlocks()[1][2]) {
		gameModel.getBlocksData()[1][2].setContents("O");
		gameModel.getBlocksData()[1][2].setIsLegalMove(false);
		// Enable the space on top of this one
		gameModel.getBlocksData()[0][2].setIsLegalMove(true);
		gameModel.setPlayer("1");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[1][2].getContents().equals(gameModel.getBlocksData()[0][2].getContents()) &&
			gameModel.getBlocksData()[0][2].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[1][2].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[1][0].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.getGameBoardView().getBlocks()[2][0]) {
		gameModel.getBlocksData()[2][0].setContents("O");
		gameModel.getBlocksData()[2][0].setIsLegalMove(false);
		// Enable the space on top of this one
		gameModel.getBlocksData()[1][0].setIsLegalMove(true);
		gameModel.setPlayer("1");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[2][0].getContents().equals(gameModel.getBlocksData()[2][1].getContents()) &&
			gameModel.getBlocksData()[2][1].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[2][0].getContents().equals(gameModel.getBlocksData()[1][0].getContents()) &&
			gameModel.getBlocksData()[1][0].getContents().equals(gameModel.getBlocksData()[0][0].getContents())) ||
		       (gameModel.getBlocksData()[2][0].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][2].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.getGameBoardView().getBlocks()[2][1]) {
		gameModel.getBlocksData()[2][1].setContents("O");
		gameModel.getBlocksData()[2][1].setIsLegalMove(false);
		// Enable the space on top of this one
		gameModel.getBlocksData()[1][1].setIsLegalMove(true);
		gameModel.setPlayer("1");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[2][1].getContents().equals(gameModel.getBlocksData()[2][0].getContents()) &&
			gameModel.getBlocksData()[2][0].getContents().equals(gameModel.getBlocksData()[2][2].getContents())) ||
		       (gameModel.getBlocksData()[2][1].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][1].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    } else if(block==gameView.getGameBoardView().getBlocks()[2][2]) {
		gameModel.getBlocksData()[2][2].setContents("O");
		gameModel.getBlocksData()[2][2].setIsLegalMove(false);
		// Enable the space on top of this one
		gameModel.getBlocksData()[1][2].setIsLegalMove(true);
		gameModel.setPlayer("1");
		if(movesLeft<7) {
		    if((gameModel.getBlocksData()[2][2].getContents().equals(gameModel.getBlocksData()[2][1].getContents()) &&
			gameModel.getBlocksData()[2][1].getContents().equals(gameModel.getBlocksData()[2][0].getContents())) ||
		       (gameModel.getBlocksData()[2][2].getContents().equals(gameModel.getBlocksData()[1][2].getContents()) &&
			gameModel.getBlocksData()[1][2].getContents().equals(gameModel.getBlocksData()[0][2].getContents())) ||
		       (gameModel.getBlocksData()[2][2].getContents().equals(gameModel.getBlocksData()[1][1].getContents()) &&
			gameModel.getBlocksData()[1][1].getContents().equals(gameModel.getBlocksData()[0][0].getContents()))) {
			gameModel.setFinalResult("Player 2 wins!");
			endGame();
		    } else if(movesLeft==0) {
			gameModel.setFinalResult(GAME_END_NOWINNER);
		    }
		}
	    }
	}

	gameView.update(gameModel);
    }

    /**
     * Ends the game disallowing further player turns.
     */
    public void endGame() {
	for(int row = 0;row<this.rows;row++) {
	    for(int column = 0;column<this.cols;column++) {
		this.gameModel.getBlocksData()[row][column].setIsLegalMove(false);
	    }
	}

	gameView.update(gameModel);
    }

    /**
     * Resets the game to be able to start playing again.
     */
    public void resetGame() {
        // for(int row = 0;row<this.rows;row++) {
        //     for(int column = 0;column<this.cols;column++) {
        //         gameModel.getBlocksData()[row][column].reset();
		// // Enable the bottom row
	    //     gameModel.getBlocksData()[row][column].setIsLegalMove(row == 2);
        //     }
        // }
		// gameModel.setPlayer("1");
		// gameModel.setMovesLeft(this.rows*this.cols);
		// gameModel.setFinalResult(null);
		gameStrategy.reset(gameModel);
		gameView.update(gameModel);
    }
}
