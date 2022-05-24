package controller.status;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import model.Game;


public class NewGameStatus extends AbstractAction {
	

	private Game game;
	public NewGameStatus(Game game){
		super("New");
		setGame(game);
		
	}
	
	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void actionPerformed(ActionEvent e){
        getGame().newGame();
	}
	

}
