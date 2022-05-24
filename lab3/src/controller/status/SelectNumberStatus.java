package controller.status;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import model.Game;


public class SelectNumberStatus extends AbstractAction {

	private Game game;
	public SelectNumberStatus(Game game){
		setGame(game);
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void actionPerformed(ActionEvent e){
		getGame().setSelectedNumber(Integer.parseInt(e.getActionCommand())); 
	}

}
