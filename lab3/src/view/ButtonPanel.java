package view;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.Observable;
import java.util.Observer;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JPanel;
import javax.swing.JToggleButton;

import model.UpdateStatus;

public class ButtonPanel extends JPanel implements Observer {
   

	private final JButton btnNew;
    private final JButton btnCheck;
    private final JButton btnExit;
    private final JButton btnSolution;
    private final JButton btnFinish;
    private final JCheckBox help;
    private final ButtonGroup buttonGroupNumbers;
    private final JToggleButton[] buttonNumbers;

  
    public ButtonPanel() {

        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain, BoxLayout.Y_AXIS));
        this.add(panelMain, BorderLayout.NORTH);

        JPanel panelMenu = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelMenu.setBorder(BorderFactory.createTitledBorder(" Menu "));
        panelMain.add(panelMenu);

        btnNew = new JButton("NEW GAME");
        btnNew.setPreferredSize(new Dimension(100,50));
        panelMenu.add(btnNew);

        btnCheck = new JButton("CHECK");
        btnCheck.setPreferredSize(new Dimension(100,50));
        panelMenu.add(btnCheck);
        
        btnFinish = new JButton("FINISH GAME");
        btnFinish.setPreferredSize(new Dimension(100,50));
        panelMenu.add(btnFinish);
        
        btnSolution=new JButton("QUICK SOLUTION");
        btnSolution.setPreferredSize(new Dimension(150,50));
        panelMenu.add(btnSolution);
        

        btnExit = new JButton("EXIT GAME");
        btnExit.setPreferredSize(new Dimension(100,50));
        panelMenu.add(btnExit);

        JPanel panelHelpAndNumbers = new JPanel();
        panelHelpAndNumbers.setLayout(new BoxLayout(panelHelpAndNumbers, BoxLayout.Y_AXIS));
        panelHelpAndNumbers.setBorder(BorderFactory.createTitledBorder("NUMBERS"));
        panelMain.add(panelHelpAndNumbers);

        JPanel panelHelp = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelHelpAndNumbers.add(panelHelp);

        help = new JCheckBox("HELP", true);
        help.setBackground(Color.GREEN);
        help.setPreferredSize(new Dimension(60,30));
        panelHelp.add(help);

        
        JPanel panelNumbers = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelHelpAndNumbers.add(panelNumbers);

        buttonGroupNumbers = new ButtonGroup();
        buttonNumbers = new JToggleButton[9];
        for (int i = 0; i < 9; i++) {
            buttonNumbers[i] = new JToggleButton("" + (i + 1));
            buttonNumbers[i].setPreferredSize(new Dimension(50, 50));
            buttonGroupNumbers.add(buttonNumbers[i]);
            panelNumbers.add(buttonNumbers[i]);
        }
       
    }

    public JButton getBtnSolution() {
		return btnSolution;
	}

	public JButton getBtnNew() {
		return btnNew;
	}

	public JButton getBtnCheck() {
		return btnCheck;
	}

	public JButton getBtnExit() {
		return btnExit;
	}

	public JButton getBtnFinish() {
		return btnFinish;
	}

	public JCheckBox getHelp() {
		return help;
	}

	public JToggleButton[] getButtonNumbers() {
		return buttonNumbers;
	}

	
    public void update(Observable o, Object arg) {
        switch ((UpdateStatus)arg) {
            case NEW_GAME:
            case CHECK:
                buttonGroupNumbers.clearSelection();
                break;
		default:
			break;
        }
    }

   
}