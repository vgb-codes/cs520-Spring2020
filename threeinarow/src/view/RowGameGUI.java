package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import model.RowGameModel;
import controller.RowGameController;

/**
* RowGameGUI class that depicts the gameboard. This class acts as the observer to the subject/observable RowGameModel.
 */

public class RowGameGUI implements RowGameView
{
    /**
    * JFrame object for GUI.
     */
    private JFrame gui = new JFrame("Three in a Row/Tic Tac Toe Game");

    /**
    * RowGameBoardView object.
     */
    private RowGameBoardView gameBoardView;

    /**
    * JButton that resets the board.
     */
    private JButton reset = new JButton("Reset");

    /**
    * RowGameStatusView object.
     */
    private RowGameStatusView gameStatusView;

    /**
    * RowGameController object.
     */
    private RowGameController gameController;

    /**
    * RowGameModel object.
     */
    private RowGameModel gameModel;

    /**
    * Number of rows in gameboard.
     */
    private int rows;

    /**
    * Number of columns in gameboard.
     */
    private int cols;


    /**
     * Creates a new game initializing the GUI.
     * @param gameController RowGameController object.
     * @param gameModel RowGameModel object.
     * @param rows Integer value for number of rows.
     * @param cols Integer value for number of columns.
     */
    public RowGameGUI(RowGameController gameController, RowGameModel gameModel, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
	    this.gameController = gameController;
        this.gameModel = gameModel;
        // Register this object as observer.
        this.gameModel.addObserver(this);
	
        gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gui.setSize(new Dimension(this.rows*50, this.cols*50));
        gui.setResizable(true);

	    gameBoardView = new RowGameBoardView(this.gameController, this.rows, this.cols);
        JPanel gamePanel = gameBoardView.getGamePanel();

        JPanel options = new JPanel(new FlowLayout());
        options.add(reset);

	    gameStatusView = new RowGameStatusView(this.gameController);
        JPanel messages = gameStatusView.messages;

        gui.add(gamePanel, BorderLayout.NORTH);
        gui.add(options, BorderLayout.CENTER);
        gui.add(messages, BorderLayout.SOUTH);

        reset.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                gameController.resetGame();
            }
        });
    }

    /**
     * Updates the game view after the game model
     * changes state.
     *
     * @param gameModel The current game model
     */
    public void update(RowGameModel gameModel) {
        gameBoardView.update(gameModel);
        gameStatusView.update(gameModel);
    }

    /**
    * Getter method for RowGameBoardView object.
    * @return RowGameBoardView object.
     */
    public RowGameBoardView getGameBoardView() {
        return this.gameBoardView;
    }

    /**
    * Getter method for RowGameController object.
    * @return RowGameController object.
     */
    public RowGameController getRowGameController() {
        return this.gameController;
    }

    /**
    * Getter method for RowGameStatusView object.
    * @return RowGameStatusView object.
     */
    public RowGameStatusView getStatusView() {
        return this.gameStatusView;
    }

    /**
    * Getter method for JFrame object.
    * @return JFrame object.
     */
    public JFrame getGUI(){
        return this.gui;
    }
}
