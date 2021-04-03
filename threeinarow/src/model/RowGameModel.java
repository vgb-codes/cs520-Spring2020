package model;
import java.util.List;
import java.util.ArrayList;
import view.RowGameGUI;

/**
* The RowGameModel class represents the game board.
 */
public class RowGameModel 
{
    public static final String GAME_END_NOWINNER = "Game ends in a draw";

    private List<RowGameGUI> observers = new ArrayList<RowGameGUI>();

    public void addObserver(RowGameGUI gameView) {
        observers.add(gameView);
    }

    public void notifyObservers() {
        for (RowGameGUI observer: observers) {
            observer.update(this);
        }
    }

    public void stateChanged() {
        notifyObservers();
    }

    private RowBlockModel[][] blocksData;

    /**
     * The current player taking their turn
     */
    private String player = "1";
    private int movesLeft = 9;
    private String finalResult = null;
    private int rows;
    private int cols;

    /**
    * Setter method for player.
    * @param player String value to be set.
     */
    public void setPlayer(String player) {
        this.player = player;
        notifyObservers();
    }

    /**
    * Getter method for player.
     */
    public String getPlayer() {
        return this.player;
    }

    /**
    * Setter method for movesLeft.
    * @param moves Integer value to be set.
     */
    public void setMovesLeft(int moves) {
        this.movesLeft = moves;
        notifyObservers();
    }

    /**
    * Getter method for movesLeft.
     */
    public int getMovesLeft() {
        return this.movesLeft;
    }

    /**
    * Getter method for blocksData
     */
    public RowBlockModel[][] getBlocksData() {
        return this.blocksData;
    }

    /**
    * Constructor for RowGameModel object.
    * @param rows Total number of gameboard rows.
    * @param cols Total number of gameboard cols.
     */
    public RowGameModel(int rows, int cols) {
        super();
        this.rows = rows;
        this.cols = cols;
        this.movesLeft = this.rows*this.cols;
        this.blocksData = new RowBlockModel[rows][cols];

        for (int row = 0; row < this.rows; row++) {
            for (int col = 0; col < this.cols; col++) {
            blocksData[row][col] = new RowBlockModel(this);
            }
        }
    }

    /**
    * Getter method for finalResult.
     */
    public String getFinalResult() {
	    return this.finalResult;
    }

    /**
    * Setter method for finalResult.
    * @param finalResult boolean value to be set.
     */
    public void setFinalResult(String finalResult) {
	    this.finalResult = finalResult;
        notifyObservers();
    }
}
