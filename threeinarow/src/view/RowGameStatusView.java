package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.RowGameController;
import model.RowGameModel;

/**
* GUI component that displays player turn, winner message, and tie message.
 */

public class RowGameStatusView implements RowGameView
{
	/**
	* JTextArea object that shows player turn.
	 */
    public JTextArea playerturn = new JTextArea();

	/**
	* JPanel object where playerturn is added.
	 */
    public JPanel messages = new JPanel(new FlowLayout());

	/**
	* Player 1 turn message.
	 */
	private static final String PLAY_PLAYER_ONE = "Player 1 to play 'X'";

	/**
	* Player 2 turn message.
	 */
	private static final String PLAY_PLAYER_TWO = "Player 2 to play 'O'";

    /**
	* Constructor method for RowGameStatusView.
	* @param gameController RowGameController object.
	 */
    public RowGameStatusView(RowGameController gameController) {
		super();

		messages.setBackground(Color.white);
		messages.add(playerturn);
    }

	/**
	* Updates status to be shown.
	* @param gameModel RowGameModel object.
	 */
    public void update(RowGameModel gameModel) {
		if (gameModel.getFinalResult() == null) {
			if (gameModel.getPlayer().equals("1")) {
				playerturn.setText(PLAY_PLAYER_ONE);
			}
			else {
				playerturn.setText(PLAY_PLAYER_TWO);
			}
		}
		else {
				playerturn.setText(gameModel.getFinalResult());
		}	
    }
}
