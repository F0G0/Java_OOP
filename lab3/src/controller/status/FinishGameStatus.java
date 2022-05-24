package controller.status;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import model.Game;


public class FinishGameStatus extends AbstractAction {
	

	private Game game;
	public FinishGameStatus(Game game){
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
		  getGame().setFinishGame();
		
	}

}
