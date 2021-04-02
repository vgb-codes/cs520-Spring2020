package controller;
import model.RowGameModel;

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
        String currentPlayer = gameModel.getPlayer();

        if(gameModel.getBlocksData()[row][col].getIsLegalMove()) {
            if (currentPlayer.equals("1")) {
                gameModel.getBlocksData()[row][col].setContents("X");
                gameModel.setPlayer("2");
                gameModel.getBlocksData()[row][col].setIsLegalMove(false);
            } else {
                gameModel.getBlocksData()[row][col].setContents("0");
                gameModel.setPlayer("1");
                gameModel.getBlocksData()[row][col].setIsLegalMove(false);
            }
            if (row > 0) {
                gameModel.getBlocksData()[row-1][col].setIsLegalMove(true);
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

}
