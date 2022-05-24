package controller;



import controller.status.CheckStatus;
import controller.status.ExitStatus;
import controller.status.FinishGameStatus;
import controller.status.HelpStatus;
import controller.status.NewGameStatus;
import controller.status.SelectNumberStatus;
import controller.status.SolutionStatus;
import model.Game;
import view.ButtonPanel;


public class ButtonController  {
   
      
	private final ButtonPanel button;
	private final Game game;
    public ButtonController(ButtonPanel button,Game game) {
       this.button=button;
       this.game=game;
       
    }

   
   public void updateController(){  
	   button.getBtnNew().addActionListener(new NewGameStatus(game));
	   button.getBtnExit().addActionListener(new ExitStatus(game));
	   button.getBtnFinish().addActionListener(new FinishGameStatus(game));
	   button.getHelp().addActionListener(new HelpStatus(game));
	   button.getBtnCheck().addActionListener(new CheckStatus(game));
	   button.getBtnSolution().addActionListener(new SolutionStatus(game));
	   
	   for (int i = 0; i < 9; i++)
		button.getButtonNumbers()[i].addActionListener(new SelectNumberStatus(game));
	   
   }
}
