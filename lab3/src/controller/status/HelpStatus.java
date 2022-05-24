package controller.status;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;
import javax.swing.JCheckBox;

import model.Game;

public class HelpStatus extends AbstractAction {
	

	private Game game;

	public HelpStatus(Game game){
		setGame(game);
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		game.setHelp(((JCheckBox)e.getSource()).isSelected());
	}

}
