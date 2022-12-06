package com.common;

import javax.swing.JFrame;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Menu extends JFrame{

void SetMenu(){

    setTitle("Menu Window");
    setLayout(new FlowLayout());

    Container c=getContentPane();

    c.add(new SetDifficulty());
    c.add(new RestartGame());
    c.add(new CloseButton());
    c.add(new TimerWindow(100));
    // new SetDifficulty();
    // new RestartGame();
    // new CloseButton();
    // new TimerWindow(100);
    setSize(500,400);
    setVisible(true);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
}
