package com.common;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

//modified code sourced from https://www.educba.com/timer-in-java-swing/
class TimerWindow extends JFrame implements ActionListener{  //creation of an window using JFrame
    private int start = 1;
    private JButton jbtn; //creation of button inside the JFrame window
    private Timer swingtimer; //swing timer instance

    TimerWindow(int tm) {
        start += tm;
        setTitle("Timer Window");
        setLayout(new FlowLayout());
        setTimer();
        setSize(300,100);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void setTimer() {
        jbtn = new JButton("Starting 100 second countdown...");
        add(jbtn);
        swingtimer = new Timer(2222,this);
        swingtimer.start();
    }
    public void actionPerformed(ActionEvent evnt) {
        start--;
        if(start>=1)
        {
        jbtn.setText("Time : "+start); //changing the label of button as the timer decrases
        }else{
        jbtn.setText("Timeout... Now,Close the Window");
        swingtimer.stop();
        }
    }
}
