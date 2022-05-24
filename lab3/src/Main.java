import model.Game;
import view.Sudoku;

import javax.swing.*;

public class Main {
    public static void main(String[] args){
        try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
        catch (Exception ex) { ex.printStackTrace(); }
        new Sudoku();
    }
}
