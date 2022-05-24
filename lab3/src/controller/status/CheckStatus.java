package controller.status;

import java.awt.event.ActionEvent;

import javax.swing.AbstractAction;

import model.Game;


public class CheckStatus extends AbstractAction {

	  private Game game;
	
      public CheckStatus(Game game){
    	  super("Check");
    	  setGame(game);
      }
      
      public void actionPerformed(ActionEvent e){
    	  getGame().checkGame();
      }
      
      public Game getGame() {
  		return game;
  	 }

  	 public void setGame(Game game) {
  		this.game = game;
  	 }
}
