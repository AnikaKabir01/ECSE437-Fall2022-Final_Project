package com.common;

import javax.swing.*;
import javax.swing.JFrame;
import java.awt.*;

// public class CloseButton extends JPanel {
public class CloseButton extends JFrame {

    private JButton quitBtn; //creation of button inside the JFrame window
    
    public void setQuitButton() {
        quitBtn = new JButton("Quit the game");
        // quitBtn.addActionListener(new CloseListener());
        quitBtn.addActionListener(e -> System.exit(0));

        
        add(quitBtn);
    }

    CloseButton() {
        setTitle("Quit Window");
        setLayout(new FlowLayout());
        setQuitButton();
        setSize(800,200);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
