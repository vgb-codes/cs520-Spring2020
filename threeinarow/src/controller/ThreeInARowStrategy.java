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
