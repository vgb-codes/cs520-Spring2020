package view;

import model.RowGameModel;

/**
* RowGameView interface. Used for the implementation of RowGameBoardView and RowGameStatusView.
 */

public interface RowGameView
{
	/** Updates status to be shown.
	* @param gameModel RowGameModel object.
	 */
    public void update(RowGameModel gameModel);
}
