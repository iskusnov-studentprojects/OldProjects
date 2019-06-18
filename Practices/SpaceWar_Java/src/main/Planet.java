package main;


/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import helpclasses.*;
import java.awt.*;
import java.util.Random;

/**
 *
 * @author домо
 */
public class Planet extends LocalizableObject {
    public int resources;
    
    public Planet(int x, int y, PlayerColor _playerColor){
        super(x,y);
        playerColor = _playerColor;
        visible = true;
        Random rand = new Random();
        resources = Constants.PLANETRESOURCES - rand.nextInt(2000) + 1000;
    }
    
    public LocalizableObject createShip(String type){
        switch(type){
            case "SpaceShip": resources -= SpaceShip.price;
                return SpaceShip.creation(location.X, location.Y, playerColor);
            case "WarShip": resources -= WarShip.price;
                return WarShip.creation(location.X, location.Y, playerColor);
            case "RegenShip": resources -= RegenShip.price;
                return RegenShip.creation(location.X, location.Y, playerColor);
            case "StealthShip": resources -= StealthShip.price;
                return StealthShip.creation(location.X, location.Y, playerColor);
            case "RadarShip": resources -= RadarShip.price;
                return RadarShip.creation(location.X, location.Y, playerColor);
            case "TransportShip": resources -= TransportShip.price;
                return TransportShip.creation(location.X, location.Y, playerColor);
        }
        return null;
    }
    
    @Override
    public void draw(Graphics canvas, int canvasWidth, int canvasHeight) {
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
        canvas.fillOval((int)(location.X*w - 2*w), (int)(location.Y*h - 2*h), (int)(4*w), (int)(4*h));
    }
    
    @Override
    public String toString(){
        return "Planet";
    }
    
    public void captured(PlayerColor pc){
        playerColor = pc;
    }
}
