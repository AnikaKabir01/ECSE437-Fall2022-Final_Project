package com.common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.nio.channels.NonWritableChannelException;

//modified code sourced from https://www.educba.com/timer-in-java-swing/
class RestartGame extends JFrame {  //creation of an window using JFrame
    private JButton newBtn; //creation of button inside the JFrame window
    
    public void setRestartButton() {
        newBtn = new JButton("Start new game");
        // quitBtn.addActionListener(new CloseListener());
        newBtn.addActionListener(new RestartListener(16, 16, 40));

        add(newBtn);
    }

    RestartGame() {
        setTitle("Restart Window");
        setLayout(new FlowLayout());
        setRestartButton();
        setSize(1000,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private class RestartListener extends MinesweeperGame implements ActionListener{

        public RestartListener(int nCols, int nRows, int nMines) {
            super(nCols, nRows, nMines);
            //TODO Auto-generated constructor stub
        }

        @Override
        public void actionPerformed(ActionEvent evnt) {
            if(evnt.getSource() == newBtn)
            {

                // GameBoard.wantRestart = true;
                
                new MinesweeperGame(16, 16, 40).setVisible(true);

                super.dispose();
            }
        }
    }
}
