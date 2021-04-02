package controller;
import model.RowGameModel;
import model.RowBlockModel;

public class ThreeInARowStrategy implements RowGameRulesStrategy {
    
    private int rows;
    private int cols;

    public ThreeInARowStrategy(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

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
            if (row > 0) {
                blocksData[row-1][col].setIsLegalMove(true);
            }
        }
    }
    
    @Override
    public void reset(RowGameModel gameModel) {
        for (int row=0; row<this.rows; row++) {
            for (int col=0; col<this.cols; col++) {
                gameModel.getBlocksData()[row][col].reset();
                gameModel.getBlocksData()[row][col].setIsLegalMove(row == (this.rows -1));
            }
        }
        gameModel.setPlayer("1");
        gameModel.setMovesLeft(this.rows*this.cols);
        gameModel.setFinalResult(null);
    }

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

    @Override
    public boolean isTie(RowGameModel gameModel) {
        boolean tie = gameModel.getMovesLeft() == 0 ? true : false;
        return tie;
    }

    public boolean checkPattern(RowGameModel gameModel, int x1, int y1, int x2, int y2, int x3, int y3) {

        if (x2 < 0 || x2  >= this.rows || x3 < 0 || x3 >= this.rows || y2 < 0 || y2 >= this.cols || y3 < 0 || y3 >= this.cols) {
            return false;
        } else {
            return gameModel.getBlocksData()[x1][y1].getContents().equals(gameModel.getBlocksData()[x2][y2].getContents()) && gameModel.getBlocksData()[x2][y2].getContents().equals(gameModel.getBlocksData()[x3][y3].getContents());
        }
    }
}
