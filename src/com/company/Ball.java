package com.company;

import java.awt.*;
import java.util.Random;

public class Ball extends Rectangle{

    private int ySpeed;
    private int xSpeed;
    private Random random;


    public Ball(int x,int y,int width,int height){
        super(x,y,width,height);
        random=new Random();
        int ranXdir=random.nextInt(2);//For X axis movement
        if (ranXdir==0)
            ranXdir--;//ball will go towards left cuz x is -ve
        setXaxis(ranXdir+3);//+2 is the speeding of the ball along x..(increase for ball speed)

        int ranYdir=random.nextInt(2);//For Y axis movement
        if (ranYdir==0)
            ranYdir--;
        setYaxis(ranYdir+3);//+2 is the speeding of the ball along y..(increase for ball speed)
    }



    public int getySpeed() {
        return ySpeed;
    }

    public int getxSpeed() {
        return xSpeed;
    }

    public void setySpeed(int ySpeed) {
        this.ySpeed = ySpeed;
    }

    public void setxSpeed(int xSpeed) {
        this.xSpeed = xSpeed;
    }

    public void setXaxis(int randomXaxis){
        xSpeed=randomXaxis;
    }


    public void setYaxis(int randomYaxis){
      ySpeed=randomYaxis;
    }


    public void move(){
    x+=xSpeed;
    y+=ySpeed;
    }


    public void draw(Graphics g){
        g.setColor(Color.GRAY);
        g.fillRect(x,y,width,height);

    }


}
