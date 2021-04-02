package view;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import controller.RowGameController;
import model.RowGameModel;


public class RowGameStatusView implements RowGameView
{
    public JTextArea playerturn = new JTextArea();
    public JPanel messages = new JPanel(new FlowLayout());

	private static final String PLAY_PLAYER_ONE = "Player 1 to play 'X'";
	private static final String PLAY_PLAYER_TWO = "Player 2 to play 'O'";

    
    public RowGameStatusView(RowGameController gameController) {
		super();

		messages.setBackground(Color.white);
		messages.add(playerturn);
    }

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
