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
public class LocalizableObject extends BaseClass implements Drawing{
    public Location location;
    public Space space;
    public PlayerColor playerColor;
    protected boolean visible;
     
    public LocalizableObject(int x, int y){
        space = helpclasses.Constants.SPACE;
        location = new Location(x,y);
        playerColor = PlayerColor.None;
        visible = false;
    }
    
    @Override
    public void draw(Graphics canvas, int canvasWidth, int canvasHeight){}
    
    public void action(){}
    
    public void attacked(int damege){}
    
    public boolean isImmortal(){
        return true;
    }
    
    public boolean isVisible(){
        return visible;
    }
    
    public void setVisible(boolean vis){}
    
    @Override
    public String toString(){
        return "LocalizableObject";
    }
}
