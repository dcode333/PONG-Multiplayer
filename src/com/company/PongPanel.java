package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class PongPanel extends JPanel implements Runnable{

    public static final int PANEL_WIDTH=1000;
    public static final int PANEL_Height=(int)(PANEL_WIDTH*(.5555));
    private static final Dimension dimension=new Dimension(PANEL_WIDTH,PANEL_Height);
    private static final int BALL_DIAMETER=20;
    private static final int PADDLE_WIDTH=30;
    private static final int PADDLE_HEIGHT=100;
    Thread thread;
    Image image;
    Random rand;
    Paddles paddle1;
    Paddles paddle2;
    Ball ball;
    Score score;
    Graphics graphics;



    public PongPanel() {


        newPaddles();
        newBall();
        score=new Score(PANEL_Height,PANEL_WIDTH);
        setFocusable(true);
        addKeyListener(new handler());
        setPreferredSize(dimension);



        thread=new Thread(this);
        thread.start();


    }
 //Method
    public void newPaddles(){
        paddle1=new Paddles(0,(PANEL_Height/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,1);
        paddle2=new Paddles(PANEL_WIDTH-PADDLE_WIDTH,(PANEL_Height/2)-(PADDLE_HEIGHT/2),PADDLE_WIDTH,PADDLE_HEIGHT,2);

    }

    //Method
    public void newBall(){
        rand=new Random();
        int i=rand.nextInt();
        ball=new Ball(PANEL_Height-BALL_DIAMETER,(PANEL_Height/2)-(BALL_DIAMETER/2),BALL_DIAMETER,BALL_DIAMETER);

    }

    //Method
    @Override
    public void paint(Graphics g){
        image=createImage(this.getWidth(),this.getHeight());
        graphics=image.getGraphics();//Creates a graphics context for drawing to an off-screen image.
                                 // This method can only be called for off-screen images.(An off screen image on which components are being drawn)
        draw(graphics);
        g.drawImage(image,0,0,this);

    }

    //Method
    public void draw(Graphics g){
        paddle1.draw(g);
        paddle2.draw(g);
        ball.draw(g);
        score.draw(g);

    }


    //Method
    @Override
    public void run(){//60fps running
    //game loop
        long lastTime = System.nanoTime();
        double amountOfTicks =60.0;
        double ns = 1000000000 / amountOfTicks;
        double delta = 0;
        while(true){
            long now =System.nanoTime();
            delta += (now -lastTime)/ns;
            lastTime = now;
            if(delta >=1){
                move();
                collisionCheck();
                repaint();//imlicit call to paint method
                delta--;
            }
        }

    }

    //Method
    public void move(){
        paddle1.move();
        paddle2.move();
        ball.move();

    }

    //Method
    public void collisionCheck(){//will prevent the paddle from going outta border
        if(paddle1.y<=0)//if paddle 1 touches the upper border of the panel it wont go further
            paddle1.y=0;
        if (paddle1.y>=(PANEL_Height-PADDLE_HEIGHT))//if paddle 1 touches the lower border of the panel it wont go further
            paddle1.y=PANEL_Height-PADDLE_HEIGHT;

        if(paddle2.y<=0)//if paddle 2 touches the upper border of the panel it wont go further
            paddle2.y=0;
        if (paddle2.y>=(PANEL_Height-PADDLE_HEIGHT))//if paddle 2 touches the lower border of the panel it wont go further
            paddle2.y=PANEL_Height-PADDLE_HEIGHT;

        if (ball.y<=0)//when ball touches the upper border it'll reflect
            ball.setYaxis(-ball.getySpeed());
        if (ball.y>PANEL_Height-BALL_DIAMETER)//when ball touches the lower border it'll reflect
            ball.setYaxis(-ball.getySpeed());

        if(ball.intersects(paddle1)){
          ball.setXaxis(-ball.getxSpeed());
          ball.setxSpeed(ball.getxSpeed()+1);//difficulty lvl
          if(ball.y>0)
             ball.setySpeed(ball.getySpeed()+1);//difficulty lvl (y speed of ball increasing by 1)
          else
              ball.setySpeed(ball.getySpeed()-1);//difficulty lvl (y speed of ball increasing by 1 in -ve)

            ball.setXaxis(ball.getxSpeed());
            ball.setYaxis(ball.getySpeed());
        }

        if(ball.intersects(paddle2)){
            ball.setXaxis(-ball.getxSpeed());
            ball.setxSpeed(ball.getxSpeed()+1);//difficulty lvl
            if(ball.y>0)
                ball.setySpeed(ball.getySpeed()+1);//difficulty lvl (y speed of ball inceasing by 1)
            else
                ball.setySpeed(ball.getySpeed()-1);//difficulty lvl (y speed of ball inceasing by 1 in -ve)

            ball.setXaxis(-ball.getxSpeed());//since in case of paddle2 ball is moving towards right so we need
                                             // to reverse its x direction towards left by a -ve sign
            ball.setYaxis(ball.getySpeed());
        }

        //if ball gets out of the screen then new ball will appear
        if(ball.x<0){
            newBall();
            score.p1Score++;
        }
        if(ball.x>PANEL_WIDTH-BALL_DIAMETER){
            newBall();
            score.p2Score++;
        }

    }

    //Inner Class
    private class handler extends KeyAdapter{
        //These calls are linked to the paddle class to handle events on the component
        // of the paddle class by declaring KeyPressed/Released methods in the paddle class
        @Override
        public void keyPressed(KeyEvent e) {
            paddle1.keyPressed(e);
            paddle2.keyPressed(e);

        }

        @Override
        public void keyReleased(KeyEvent e) {
            paddle1.keyReleased(e);
            paddle2.keyReleased(e);

        }
    }

}
