package com.company;

import java.awt.*;
import java.awt.event.KeyEvent;

public class Paddles extends Rectangle{

    private int paddleID;
    private int ySpeed;
    private int movement=11;//Paddle moving speed

    public Paddles(int x,int y,int pWidth,int pHeight,int paddleID){
        super(x,y,pWidth,pHeight);
        this.paddleID=paddleID;}




    //method
    public void keyPressed(KeyEvent e){
        if (paddleID==1) {//P1
            if (e.getKeyCode() == KeyEvent.VK_W) {
                     setYaxis(-movement);//
                move();}

            else if (e.getKeyCode() == KeyEvent.VK_S) {
                 setYaxis(movement);
                move();}
        }

            if (paddleID==2) {//p2
                if (e.getKeyCode() == KeyEvent.VK_UP) {
                    setYaxis(-movement);
                    move();
                }
                else if (e.getKeyCode() == KeyEvent.VK_DOWN) {

                    setYaxis(movement);
                    move();
                }
            }
        }



    //method
    public void keyReleased(KeyEvent e){
        if (paddleID==1) {//P1
            if (e.getKeyCode() == KeyEvent.VK_W) {
                setYaxis(0);
                move();}

            else if (e.getKeyCode() == KeyEvent.VK_S) {
                setYaxis(0);
                move();}
        }

        if (paddleID==2) {//p2
            if (e.getKeyCode() == KeyEvent.VK_UP) {
                setYaxis(0);
                move();
            }
            else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
                setYaxis(0);
                move();
            }
        }
    }

    //method to set the vertical movement of the paddles
    public void setYaxis(int Yaxis){
        ySpeed=Yaxis;

    }

    //method
    public void move(){
        y=y+ySpeed;
    }

    //method
    public void draw(Graphics g){
        g.setColor(Color.orange);

        g.fillRect(x,y,width,height);


    }
}
