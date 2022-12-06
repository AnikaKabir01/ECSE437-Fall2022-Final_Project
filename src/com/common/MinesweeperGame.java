package com.common;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JLabel;


public class MinesweeperGame extends JFrame {

    private JLabel statusbar;

    public MinesweeperGame(int nCols, int nRows, int nMines) {
        initUI(nCols, nRows, nMines);
    }

    private void initUI(int nCols, int nRows, int nMines) {

        statusbar = new JLabel("");
        add(statusbar, BorderLayout.SOUTH);

        add(new GameBoard(statusbar, nCols, nRows, nMines));

        setResizable(false);
        pack();

        setTitle("Minesweeper");
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {

            var ex = new MinesweeperGame(16, 16, 40);
            ex.setVisible(true);

            new SetDifficulty();
            new RestartGame();
            new CloseButton();
            new TimerWindow(100);
            new Instructions();
            
            
            // new Menu();
        });
    }
}
