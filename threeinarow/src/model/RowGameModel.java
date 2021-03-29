package model;


public class RowGameModel 
{
    public static final String GAME_END_NOWINNER = "Game ends in a draw";

    private RowBlockModel[][] blocksData;

    /**
     * The current player taking their turn
     */
    private String player = "1";
    private int movesLeft = 9;
    private String finalResult = null;
    private int rows;
    private int cols;

    public void setPlayer(String player) {
        this.player = player;
    }

    public String getPlayer() {
        return this.player;
    }

    public RowBlockModel[][] getBlocksData() {
        return this.blocksData;
    }

    public RowGameModel(int rows, int cols) {
        super();
        this.rows = rows;
        this.cols = cols;
        this.movesLeft = this.rows*this.cols;
        this.blocksData = new RowBlockModel[rows][cols];

        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
            blocksData[row][col] = new RowBlockModel(this);
            } // end for col
        } // end for row
    }

    public String getFinalResult() {
	    return this.finalResult;
    }

    public void setFinalResult(String finalResult) {
	    this.finalResult = finalResult;
    }
}
