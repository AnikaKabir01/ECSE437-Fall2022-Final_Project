package com.common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class SetDifficulty extends JFrame {
    private JButton easyBtn; //creation of button inside the JFrame window
    private JButton mediumBtn;
    private JButton hardBtn;
    
    public void setDifficultyButtons() {
        easyBtn = new JButton("Easy difficulty");
        mediumBtn = new JButton("Medium difficulty");
        hardBtn = new JButton("Hard difficulty");

        // quitBtn.addActionListener(new CloseListener());
        easyBtn.addActionListener(new DifficultyListener(16, 16, 40));
        mediumBtn.addActionListener(new DifficultyListener(25, 25, 100));
        hardBtn.addActionListener(new DifficultyListener(40, 40, 250));

        add(easyBtn);
        add(mediumBtn);
        add(hardBtn);
        // JButton[] jbtnArray = new JButton[3];
        // jbtnArray[0] = easyBtn;
        // jbtnArray[1] = mediumBtn;
        // jbtnArray[2] = hardBtn;
        // return jbtnArray;

    }

    SetDifficulty() {
        setTitle("Difficulty Window");
        setLayout(new FlowLayout());
        setDifficultyButtons();
        setSize(2000,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class DifficultyListener extends MinesweeperGame implements ActionListener{

        public DifficultyListener(int nCols, int nRows, int nMines) {
            super(nCols, nRows, nMines);
            //TODO Auto-generated constructor stub
        }

        @Override
        public void actionPerformed(ActionEvent evnt) {
            if(evnt.getSource() == easyBtn)
            {
                MinesweeperGame diffGame = new MinesweeperGame(16, 16, 40);

                diffGame.setVisible(true);
                super.dispose();
            }

            if(evnt.getSource() == mediumBtn)
            {
                MinesweeperGame diffGame = new MinesweeperGame(25, 25, 100);

                diffGame.setVisible(true);
                super.dispose();
            }

            if(evnt.getSource() == hardBtn)
            {
                MinesweeperGame diffGame = new MinesweeperGame(40, 40, 250);

                diffGame.setVisible(true);
                super.dispose();
            }
        }
    }
}
