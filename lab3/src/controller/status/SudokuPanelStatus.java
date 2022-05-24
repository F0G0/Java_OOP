package controller.status;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

import view.Field;
import model.Game;


public class SudokuPanelStatus implements MouseListener {
	
	private Game game;
	public SudokuPanelStatus(Game game){
          setGame(game);
	}
	
	
	public Game getGame() {
		return game;
	}


	public void setGame(Game game) {
		this.game = game;
	}

      public void mouseClicked(MouseEvent e){
    	  JPanel panel = (JPanel)e.getSource();
          Component component = panel.getComponentAt(e.getPoint());
          if (component instanceof Field field) {
              int x = field.getFieldX();
              int y = field.getFieldY();
 
     
              if (e.getButton() == MouseEvent.BUTTON1 && (game.getNumber(x, y) == 0 || field.getForeground().equals(Color.BLUE))) {
            	  int number = game.getSelectedNumber();
                  if (number == -1)
                      return;
                  game.setNumber(x, y, number);
                  field.setNumber(number, true);
              }
              else if (e.getButton() == MouseEvent.BUTTON3 && !field.getForeground().equals(Color.BLACK)) {
                  game.setNumber(x, y, 0);
                  field.setNumber(0, false);
              }
              
          }
      }



	@Override
	public void mouseEntered(MouseEvent e) { }
	
	@Override
	public void mouseExited(MouseEvent e) { }

	@Override
	public void mousePressed(MouseEvent e) { }
		
	@Override
	public void mouseReleased(MouseEvent e) { }
		
}


