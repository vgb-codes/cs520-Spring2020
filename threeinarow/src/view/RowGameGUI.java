package view;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.*;
import java.awt.event.*;

import model.RowGameModel;
import controller.RowGameController;


public class RowGameGUI implements RowGameView
{
    private JFrame gui = new JFrame("Three in a Row");
    private RowGameBoardView gameBoardView;
    private JButton reset = new JButton("Reset");
    private RowGameStatusView gameStatusView;
    private RowGameController gameController;
    private RowGameModel gameModel;
    private int rows;
    private int cols;


    /**
     * Creates a new game initializing the GUI.
     */
    public RowGameGUI(RowGameController gameController, RowGameModel gameModel, int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
	    this.gameController = gameController;
        this.gameModel = gameModel;
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

    public RowGameBoardView getGameBoardView() {
        return this.gameBoardView;
    }

    public RowGameController getRowGameController() {
        return this.gameController;
    }

    public RowGameStatusView getStatusView() {
        return this.gameStatusView;
    }

    public JFrame getGUI(){
        return this.gui;
    }
}
