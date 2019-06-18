package main;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import helpclasses.*;
import java.awt.*;

/**
 *
 * @author домо
 */
public class RadarShip extends SpaceShip {
    public RadarShip(int x, int y, PlayerColor _playerColor){
        super(x, y, _playerColor);
        armor = Constants.RADARSHIPARMOR;
        speed = Constants.RADARSHIPSPEED;
        visibility = Constants.RADARSHIPVISIBILITY;
        price = Constants.RADARSHIPPRICE;
    }
    
    public static LocalizableObject creation(int x, int y, PlayerColor _playerColor){
        return new RadarShip(x, y, _playerColor);
    }
    
    @Override
    public void draw(Graphics canvas, int canvasWidth, int canvasHeight){
        switch(playerColor){
            case Red: canvas.setColor(Color.RED);
                break;
            case Green: canvas.setColor(Color.GREEN);
                break;
            case Blue: canvas.setColor(Color.BLUE);
                break;
            case None: canvas.setColor(Color.GRAY);
        }
        double w = canvasWidth/Constants.SPACEWIDTH,
               h = canvasHeight/Constants.SPACEHEIGHT;
        canvas.fillRect((int)(location.X*w - 1.5*w), (int)(location.Y*h - 0.75*h), (int)(3*w), (int)(1.5*h));
        canvas.fillOval((int)(location.X*w - 0.75*w), (int)(location.Y*h - 1.5*h), (int)(1.5*w), (int)(3*h));
    }
    
    @Override
    public String toString(){
        return "RadarShip";
    }
    
    @Override
    public void action(){
        int begi, endi, begj, endj;
        if(location.X - visibility > 0)
            begi = location.X - visibility;
        else
            begi = 0;
        if(location.X + visibility < Constants.SPACEWIDTH + 2)
            endi = location.X + visibility;
        else
            endi = Constants.SPACEHEIGHT + 2;
        if(location.Y - visibility > 0)
            begj = location.Y - visibility;
        else
            begj = 0;
        if(location.Y + visibility < Constants.SPACEHEIGHT + 2)
            endj = location.Y + visibility;
        else
            endj = Constants.SPACEHEIGHT + 2;
        
        for(int i = begi; i < endi; i++)
            for(int j = begj; j < endj; j++){
                if(space.field[i][j] != null && space.field[i][j].playerColor != playerColor){
                    if(distance(location.X,location.Y,space.field[i][j].location.X,space.field[i][j].location.Y) < visibility){
                        space.field[i][j].setVisible(true);
                    }
                }
            }
        
        super.action();
    }
}
