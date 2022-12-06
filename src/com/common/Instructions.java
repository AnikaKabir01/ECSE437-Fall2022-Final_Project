package com.common;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

//code modified from https://www.geeksforgeeks.org/jlabel-java-swing/
public class Instructions extends JFrame {
    
    
// Java Program to create a
// blank label and add text to it. 
    // frame
    static JFrame f;
 
    // label to display text
    static JLabel l;
 
    // default constructor
    Instructions() {

        setTitle("Instructions Window");
        setLayout(new FlowLayout());
        setSize(700,250);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // f = new JFrame("label");
 
        // create a label to display text
        l = new JLabel();
 
        // add text to label
        l.setText("<html>Welcome to the Minesweeper Game!<br/>Here are the rules to play this classic game:<br>The goal of the game is to uncover all empty cells using the left-click (cells without a mine)<br>To achieve this goal you can mark cells you are confident have a flag on them by right-clicking on the cell.<br>If a cell has a number on it, it indicates the number of bombs adjacent to it (up-down, left-right, corners)<br>Use this information to determine where bombs are located<br><br>Other features such as a 100 second countodown to keep track of progress, 'start new game' and 'Quit Game'<br>buttons as well as options to set difficulty are provided to you to better enjoy the game.<br>Good Luck!</html>");
 
        // create a panel
        JPanel p = new JPanel();
 
        // add label to panel
        p.add(l);
 
        // add panel to frame
        add(p);
 
        // set the size of frame
        // f.setSize(300, 300);
 
    }
 
    // main class
    // public static void main(String[] args)
    // {
    //     // create a new frame to store text field and button
    //     // f = new JFrame("label");
 
    //     // // create a label to display text
    //     // l = new JLabel();
 
    //     // // add text to label
    //     // l.setText("label text");
 
    //     // // create a panel
    //     // JPanel p = new JPanel();
 
    //     // // add label to panel
    //     // p.add(l);
 
    //     // // add panel to frame
    //     // f.add(p);
 
    //     // // set the size of frame
    //     // f.setSize(300, 300);
 
    //     // f.show();
    //     // f.isVisible(true);
    // }
// }

}
