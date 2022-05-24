package view;


import java.awt.BorderLayout;

import javax.swing.JFrame;

import model.Game;
import controller.ButtonController;
import controller.SudokuController;



public class Sudoku extends JFrame {


	public Sudoku() {

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        getContentPane().setLayout(new BorderLayout());

        Game game = new Game();
        ButtonPanel buttonPanel = new ButtonPanel();
        SudokuPanel sudokuPanel = new SudokuPanel();
        
        ButtonController controllerButton = new ButtonController(buttonPanel,game);
        controllerButton.updateController();
        
        SudokuController controllerPanel = new SudokuController(sudokuPanel,game);
        controllerPanel.updateController();
        
        add(buttonPanel, BorderLayout.NORTH);
        add(sudokuPanel, BorderLayout.SOUTH);

        game.addObserver(buttonPanel);
        game.addObserver(sudokuPanel);
        
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

}