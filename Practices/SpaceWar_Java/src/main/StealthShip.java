package main;

import helpclasses.*;
import java.awt.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author домо
 */
public class StealthShip extends WarShip {
    public StealthShip(int x, int y, PlayerColor _playerColor){
        super(x, y, _playerColor);
        armor = Constants.STEALTHSHIPARMOR;
        speed = Constants.STEALTHSHIPSPEED;
        visibility = Constants.STEALTHSHIPVISIBILITY;
        damage = Constants.STEALTHSHIPDAMAGE;
        price = Constants.STEALTHSHIPPRICE;
    }
    
    public static LocalizableObject creation(int x, int y, PlayerColor _playerColor){
        return new StealthShip(x, y, _playerColor);
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
        canvas.fillArc((int)(location.X*w-2.5*w), (int)(location.Y*h - 4*h), (int)(5*w), (int)(5*h), 240, 60);
        canvas.fillOval((int)(location.X*w - 0.75*w), (int)(location.Y*h - 1.5*h), (int)(1.5*w), (int)(1.5*h));
    }
    
    @Override
    public void setVisible(boolean vis){
        visible = vis;
    }
    
    @Override
    public String toString(){
        return "StealthShip";
    }
    
    @Override
    public void action(){
        setVisible(false);
        super.action();
    }
}
