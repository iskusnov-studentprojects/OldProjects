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
public class WarShip extends SpaceShip {
    protected int damage;
    
    public WarShip(int x, int y, PlayerColor _playerColor){
        super(x, y, _playerColor);
        armor = Constants.WARSHIPARMOR;
        speed = Constants.WARSHIPSPEED;
        visibility = Constants.WARSHIPVISBILITY;
        damage = Constants.WARSHIPDAMAGE;
        price = Constants.WARSHIPPRICE;
    }
    
    public static LocalizableObject creation(int x, int y, PlayerColor _playerColor){
        return new WarShip(x, y, _playerColor);
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
        canvas.fillArc((int)(location.X*w), (int)(location.Y*h - 3*h), (int)(5*w), (int)(5*h), 240, 60);
    }
    
    @Override
    public String toString(){
        return "WarShip";
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
                if(space.field[i][j] != null && space.field[i][j].playerColor != playerColor && space.field[i][j].visible && !space.field[i][j].isImmortal()){
                    if(distance(location.X,location.Y,space.field[i][j].location.X,space.field[i][j].location.Y) < visibility){
                        target.setLocation(space.field[i][j].location.X, space.field[i][j].location.Y);
                        if(distance(location.X,location.Y,space.field[i][j].location.X,space.field[i][j].location.Y) < visibility * 0.7)
                            space.field[i][j].attacked(damage);
                        else
                            moveTo();
                        return;
                    }
                }
            }
        
        super.action();
    }
}
