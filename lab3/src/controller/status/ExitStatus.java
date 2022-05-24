package controller.status;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import model.Game;



public class ExitStatus extends AbstractAction {

	private Game game;
     public ExitStatus(Game game){
    	 super("Exit");
    	 setGame(game);
     }
     
     public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public void actionPerformed(ActionEvent e){
    	 System.exit(0);
     }
     
}
