package controller;
import model.RowGameModel;
import model.RowBlockModel;

/**
* Implementation of Tic Tac Toe game using Strategy Pattern.
 */


public class TicTacToeStrategy implements RowGameRulesStrategy {

    private int rows;
    private int cols;

    /**
    * Constructor method for ThreeInARowStrategy.
    * @param rows Rows of the game board.
    * @param cols Columns of the game board.
     */
    public TicTacToeStrategy(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    /**
    * Player move method. Sets content of JButton and disables JButton.
    * @param gameModel RowGameModel object that is affected.
    * @param row Row where JButton is present.
    * @param col Columnn where JButton is present.
     */
    @Override
    public void move(RowGameModel gameModel, int row, int col) {
        gameModel.setMovesLeft(gameModel.getMovesLeft() - 1);
        System.out.println("Moves Left: "+gameModel.getMovesLeft());
        String currentPlayer = gameModel.getPlayer();
        RowBlockModel[][] blocksData = gameModel.getBlocksData();

        if(blocksData[row][col].getIsLegalMove()) {
            if (currentPlayer.equals("1")) {
                blocksData[row][col].setContents("X");
                gameModel.setPlayer("2");
                blocksData[row][col].setIsLegalMove(false);
            } else {
                blocksData[row][col].setContents("0");
                gameModel.setPlayer("1");
                blocksData[row][col].setIsLegalMove(false);
            }
        }
    }

    /**
    * Resets the gameboard to begin a new game.
    * @param gameModel RowGameModel object that is affected.
     */
    @Override
    public void reset(RowGameModel gameModel) {
        for (int row=0; row<this.rows; row++) {
            for (int col=0; col<this.cols; col++) {
                gameModel.getBlocksData()[row][col].reset();
                gameModel.getBlocksData()[row][col].setIsLegalMove(true);
            }
        }
        gameModel.setPlayer("1");
        gameModel.setMovesLeft(this.rows*this.cols);
        gameModel.setFinalResult(null);
    }

    /**
    * Checks if current move leads to a win condition and returns true if any win condition is satisfied, otherwise return false.
    * @param gameModel RowGameModel object that is affected.
    * @param row Row of element to check for win condition.
    * @param col Column of element to check for win condition.
     */
    @Override
    public boolean isWin(RowGameModel gameModel, int row, int col) {
        // Horizontal win conditions
        if (checkPattern(gameModel, row, col, row, col-1, row, col-2)) return true;
        if (checkPattern(gameModel, row, col, row, col+1, row, col+1)) return true;
        if (checkPattern(gameModel, row, col, row, col-1, row, col+1)) return true;
        // Vertical win conditions
        if (checkPattern(gameModel, row, col, row-1, col, row-2, col)) return true;
        if (checkPattern(gameModel, row, col, row+1, col, row+2, col)) return true;
        if (checkPattern(gameModel, row, col, row-1, col, row+1, col)) return true;
        // Diagonal win conditions
        if (checkPattern(gameModel, row, col, row-1, col-1, row-2, col-2)) return true;
        if (checkPattern(gameModel, row, col, row+1, col+1, row+2, col+2)) return true;
        if (checkPattern(gameModel, row, col, row-1, col-1, row+1, col+1)) return true;
        // Antidiagonal win conditions
        if (checkPattern(gameModel, row, col, row+1, col-1, row+2, col-2)) return true;
        if (checkPattern(gameModel, row, col, row-1, col+1, row-2, col+2)) return true;
        if (checkPattern(gameModel, row, col, row-1, col+1, row+1, col-1)) return true;
        else return false;
    }

    /**
    * Checks if there is tie. Method should be invoked after checking for win condition.
    * @param gameModel RowGameModel object that is affected.
     */
    @Override
    public boolean isTie(RowGameModel gameModel) {
        boolean tie = gameModel.getMovesLeft() == 0 ? true : false;
        return tie;
    }

    /**
    * Checks if the content of the three points given are equal.
    * @param gameModel RowGameModel object that is affected.
    * @param x1 x (row) coordinate of first point.
    * @param y1 y (column) coordinate of first point.
    * @param x2 x (row) coordinate of second point.
    * @param y2 y (column) coordinate of second point.
    * @param x3 x (row) coordinate of third point.
    * @param y3 y (column) coordinate of third point.
     */
    public boolean checkPattern(RowGameModel gameModel, int x1, int y1, int x2, int y2, int x3, int y3) {

        if (x2 < 0 || x2  >= this.rows || x3 < 0 || x3 >= this.rows || y2 < 0 || y2 >= this.cols || y3 < 0 || y3 >= this.cols) {
            return false;
        } else {
            return gameModel.getBlocksData()[x1][y1].getContents().equals(gameModel.getBlocksData()[x2][y2].getContents()) && gameModel.getBlocksData()[x2][y2].getContents().equals(gameModel.getBlocksData()[x3][y3].getContents());
        }
    }
}