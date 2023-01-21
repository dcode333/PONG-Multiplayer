package com.company;

import javax.swing.*;
import java.awt.*;

public class PongFrame extends JFrame{

    PongPanel pp;
    public PongFrame(){
        super("PING PONG");

        pp=new PongPanel();
        add(pp);

        setResizable(false);
        setBackground(Color.BLACK);
        pack();//will set the size of the frame acc to the panel
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);

    }

}
