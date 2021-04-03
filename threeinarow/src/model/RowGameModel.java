package model;
import java.util.List;
import java.util.ArrayList;
import view.RowGameGUI;

/**
* The RowGameModel class represents the game board. This class fulfills the role of a subject/observable.
 */
public class RowGameModel 
{
    /**
    * Tie status message.
     */
    public static final String GAME_END_NOWINNER = "Game ends in a draw";

    /**
    * List of observers.
     */
    private List<RowGameGUI> observers = new ArrayList<RowGameGUI>();

    /**
    * Add an observer to the list of observers
    * @param gameView RowGameGUI object that will be registered as observer.
     */
    public void addObserver(RowGameGUI gameView) {
        observers.add(gameView);
    }

    /**
    * Method that will notify all observers to update.
     */
    public void notifyObservers() {
        for (RowGameGUI observer: observers) {
            observer.update(this);
        }
    }

    /**
    * Method that will invoke notifyObservers in case of state change.
     */
    public void stateChanged() {
        notifyObservers();
    }

    /**
    * 2D array of RowBlockModel objects.
     */
    private RowBlockModel[][] blocksData;

    /**
     * The current player taking their turn
     */
    private String player = "1";

    /**
    * Moves left in the game.
     */
    private int movesLeft = 9;

    /**
    * Final result string.
     */
    private String finalResult = null;

    /**
    * Number of rows in gameboard.
     */
    private int rows;
    
    /**
    * Number of columns in gameboard.
     */
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
    * @return Player string.
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
    * @return Integer value of moves left.
     */
    public int getMovesLeft() {
        return this.movesLeft;
    }

    /**
    * Getter method for blocksData
    * @return 2D array of RowBlockModel objects.
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
    * @return Final result string.
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
