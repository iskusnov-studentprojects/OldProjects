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
public class TransportShip extends SpaceShip {
    private boolean busy;
    
    public TransportShip(int x, int y, PlayerColor _playerColor){
        super(x, y, _playerColor);
        armor = Constants.TRANSPORTSHIPARMOR;
        speed = Constants.TRANSPORTSHIPSPEED;
        visibility = Constants.TRANSPORTSHIPVISIBILITY;
        price = Constants.TRANSPORTSHIPPRICE;
        busy = false;
    }
    
    public static LocalizableObject creation(int x, int y, PlayerColor _playerColor){
        return new TransportShip(x, y, _playerColor);
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
    }
    
    @Override
    public String toString(){
        return "TransportShip";
    }
    
    @Override
    public void action(){
        if(!busy || space.field[target.X][target.Y].playerColor != PlayerColor.None){
            int allPlanets = 0, poorPlanets = 0;
            for(Planet i: Constants.PLANETS)
                if(i.playerColor == playerColor){
                    allPlanets++;
                    if(i.resources < 1000)
                        poorPlanets++;
                }
            if(allPlanets == poorPlanets)
                for(Planet i: Constants.PLANETS){
                    if(i.playerColor == PlayerColor.None){
                        busy = true;
                        target.setLocation(i.location.X, i.location.Y);
                        break;
                    }
                }
            if(!busy)
                super.action();
        }else{
            if(space.field[target.X][target.Y].playerColor != PlayerColor.None)
                busy = false;
            if(distance(location.X, location.Y, target.X, target.Y) < visibility * 0.2){
                ((Planet)space.field[target.X][target.Y]).captured(playerColor);
                attacked(100000);
            }
            else
                moveTo();
        }
    }
}
