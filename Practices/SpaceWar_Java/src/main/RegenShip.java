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
public class RegenShip extends WarShip {
    public RegenShip(int x, int y, PlayerColor _playerColor){
        super(x, y, _playerColor);
        armor = Constants.REGENSHIPARMOR;
        speed = Constants.REGENSHIPSPEED;
        visibility = Constants.REGENSHIPVISIBILITY;
        damage = Constants.REGENSHIPDAMAGE;
        price = Constants.REGENSHIPPRICE;
    }
    
    public static LocalizableObject creation(int x, int y, PlayerColor _playerColor){
        return new RegenShip(x, y, _playerColor);
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
    }
    
    @Override
    public String toString(){
        return "RegenShip";
    }
    
    @Override
    public void action(){
        armor += 2;
        super.action();
    }
}
