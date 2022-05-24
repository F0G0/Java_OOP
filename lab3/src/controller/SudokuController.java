package controller;


import controller.status.SudokuPanelStatus;
import model.Game;
import view.SudokuPanel;



public class SudokuController {
   

    private final SudokuPanel panel;
    private final Game game;
    public SudokuController(SudokuPanel panel,Game game) {
        this.panel=panel;
        this.game=game;
    }

    public void updateController() {
    	for (int y = 0; y < 3; y++) {
            for (int x = 0; x < 3; x++)
                 panel.getPanels()[y][x].addMouseListener(new SudokuPanelStatus(game));
                
        } 
    	
    }
    
}
    
  

