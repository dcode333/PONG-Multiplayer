package com.company;

import java.awt.*;

public class Score extends Rectangle{

    private static int GAME_WIDTH;
    private static int GAME_HEIGHT;
    public int p1Score=0;
    public int p2Score=0;

    public Score(int GAME_HEIGHT,int GAME_WIDTH) {
        Score.GAME_HEIGHT=GAME_HEIGHT;
        Score.GAME_WIDTH=GAME_WIDTH;
    }



    public void draw(Graphics g){
        g.setColor(Color.orange);

        g.drawLine(GAME_WIDTH/2,0,GAME_WIDTH/2,GAME_HEIGHT);
        g.setColor(Color.orange);
        g.setFont(new Font("MV Boli",Font.BOLD,40));
        g.drawString(String.format("%d",p1Score),(GAME_WIDTH/2)+14,37);//Score for player 2
        g.drawString(String.format("%d",p2Score),(GAME_WIDTH/2)-45,37);//Score for player 2

    }
}
