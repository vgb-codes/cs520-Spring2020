package controller;

import model.RowGameModel;

/**
* RowGameRulesStrategy interface. Used for the implementation of Strategy Pattern.
 */

public interface RowGameRulesStrategy
{
    /**
    * Resets the gameboard to begin a new game.
    * @param gameModel RowGameModel object that is affected.
     */
    public void reset(RowGameModel gameModel);

    /**
    * Player move method. Sets content of JButton and disables JButton.
    * @param gameModel RowGameModel object that is affected.
    * @param row Row where JButton is present.
    * @param col Columnn where JButton is present.
     */
    public void move(RowGameModel gameModel, int row, int col);

    /**
    * Checks if current move leads to a win condition and returns true if any win condition is satisfied, otherwise return false.
    * @param gameModel RowGameModel object that is affected.
    * @param row Row of element to check for win condition.
    * @param col Column of element to check for win condition.
    * @return true or false depending on whether win condition is satified.
     */
    public boolean isWin(RowGameModel gameModel, int row, int col);

    /**
    * Checks if there is tie. Method should be invoked after checking for win condition.
    * @param gameModel RowGameModel object that is affected.
    * @return true or false depending on whether gameboard has reached tie status.
     */
    public boolean isTie(RowGameModel gameModel);
}
