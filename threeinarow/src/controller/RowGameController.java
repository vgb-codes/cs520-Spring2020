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
* This version has been improved by inclusion of modularity, encapsulation, documentation, and extension to row*columns gameboard. Additionally, this package demonstrates the application of Strategy Pattern.
 */
public class RowGameController {
	/**
	* Tie messsage.
	 */
    private static final String GAME_END_NOWINNER = "Game ends in a draw";

	/**
	* Player 1 win message.
	 */
	private static final String GAME_END_PLAYER_ONE = "Player 1 Wins!";

	/**
	* Player 2 win message.
	 */
	private static final String GAME_END_PLAYER_TWO = "Player 2 Wins!";

	/**
	* RowGameModel object.
	 */
	private RowGameModel gameModel;

	/**
	* RowGameGUI object.
	 */
    private RowGameGUI gameView;

	/**
	* Number of rows in gameboard.
	 */
	private int rows;

	/**
	* Number of columns in gameboard.
	 */
	private int cols;

	/**
	* RowGameRulesStrategy object.
	 */
	private RowGameRulesStrategy gameStrategy;

    /**
     * Creates a new game initializing the GUI.
	 *
	 * @param rows Rows of game board.
	 * @param cols Columns of game board.
	 * @param strategy Strategy of game board.
	 * @throws IllegalArgumentException when strategy choice does not match 'ThreeInARow' or 'TicTacToe' or if rows and cols are not equal or if rows or cols are less than 3 (in order for threeinarow or tictactoe to work).
     */
    public RowGameController(int rows, int cols, String strategy) throws IllegalArgumentException {	

		if (rows <3 || cols <3) {
			throw new IllegalArgumentException("ARGUMENT ERROR: Rows and Columns must not be less than 3.");
		}

		this.rows = rows;
		this.cols = cols;
		gameModel = new RowGameModel(this.rows, this.cols);
		gameView = new RowGameGUI(this, gameModel, this.rows, this.cols);

		if (strategy.equals("ThreeInARow")){
			gameStrategy = new ThreeInARowStrategy(rows, cols);
		} else if(strategy.equals("TicTacToe")) {
			gameStrategy = new TicTacToeStrategy(rows, cols);
		} else {
			throw new IllegalArgumentException("Strategy choice can only be 'ThreeInARow' or 'TicTacToe'");
		}
		
		resetGame();
    }

	/**
	* Getter method for RowGameRulesStrategy object
	* @return RowGameRulesStrategy object
	 */
	 public RowGameRulesStrategy getGameStrategy() {
		 return this.gameStrategy;
	 }

	/**
	* Getter method for RowGameModel object
	* @return RowGameModel object.
	 */
    public RowGameModel getModel() {
		return this.gameModel;
    }

	/**
	* Getter method for RowGameGUI object
	* @return RowGameGUI object.
	 */
    public RowGameGUI getView() {
		return this.gameView;
    }

	/**
	* Makes the GUI visible
	 */
    public void startUp() {
		gameView.getGUI().setVisible(true);
    }

    /**
     * Moves the current player into the given block.
     *
     * @param block The block to be moved to by the current player
     */
    public void move(JButton block) {
		JButton[][] buttonBlocks = gameView.getGameBoardView().getBlocks();

		for (int row=0; row<this.rows; row++) {
			for (int col=0; col<this.cols; col++) {
				if (buttonBlocks[row][col] == block) {
					gameStrategy.move(gameModel, row, col);

					if (gameStrategy.isWin(gameModel, row, col)) {
						System.out.println("Win Condition satified at row "+row+" and column "+col);

						if (gameModel.getBlocksData()[row][col].getContents().equals("X")) {
							gameModel.setFinalResult(GAME_END_PLAYER_ONE);
							endGame();
						} else {
							gameModel.setFinalResult(GAME_END_PLAYER_TWO);
							endGame();
						}

					} else if (gameStrategy.isTie(gameModel)) {
						gameModel.setFinalResult(GAME_END_NOWINNER);
						endGame();
					}
				}
			}
		}
		// No longer used due to observer pattern being applied
		//gameView.update(gameModel);
	}
	

    /**
     * Ends the game disallowing further player turns.
     */
    public void endGame() {
		for(int row = 0;row<this.rows;row++) {
			for(int column = 0;column<this.cols;column++) {
				gameModel.getBlocksData()[row][column].setIsLegalMove(false);
				gameModel.stateChanged();
			}
		}
		// No longer used due to observer pattern being applied
		//gameView.update(gameModel);
    }

    /**
     * Resets the game to be able to start playing again.
     */
    public void resetGame() {

		gameStrategy.reset(gameModel);
		gameModel.stateChanged();
		// No longer used due to observer pattern being applied
		//gameView.update(gameModel);
    }
}
