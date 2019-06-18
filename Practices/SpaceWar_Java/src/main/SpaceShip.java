package main;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import helpclasses.*;
import java.util.*;

/**
 *
 * @author домо
 */
public class SpaceShip extends LocalizableObject {
    protected int armor;
    protected int speed;
    protected int visibility;
    public static int price = 0;
    protected Location target;
    
    public SpaceShip(int x, int y, PlayerColor _playerColor){
        super(x, y);
        armor = 0;
        speed = 0;
        visibility = 0;
        playerColor = _playerColor;
        visible = true;
        target = new Location(-1,-1);
    }
    
    public int armor(){
        return armor;
    }
    
    public static LocalizableObject creation(int x, int y, PlayerColor _playerColor){
        return new SpaceShip(x, y, _playerColor);
    }
    
    public void getDamage(int damage){
        armor -= damage;
    }
    
    private void move(int tx, int ty){
        double mindist = distance(location.X, location.Y, tx, ty);
        int newx = location.X,
            newy = location.Y;
        
        //Up-Left
        if(space.field[location.X - 1][location.Y - 1] == null)
            if(mindist > distance(location.X - 1, location.Y - 1, tx, ty)){
                mindist = distance(location.X - 1, location.Y - 1, tx, ty);
                newx = location.X - 1;
                newy = location.Y - 1;
            }
        
        //Up
        if(space.field[location.X][location.Y - 1] == null)
            if(mindist > distance(location.X, location.Y - 1, tx, ty)){
                mindist = distance(location.X, location.Y - 1, tx, ty);
                newx = location.X;
                newy = location.Y - 1;
            }
        
        //Up-Right
        if(space.field[location.X + 1][location.Y - 1] == null)
            if(mindist > distance(location.X + 1, location.Y - 1, tx, ty)){
                mindist = distance(location.X + 1, location.Y - 1, tx, ty);
                newx = location.X + 1;
                newy = location.Y - 1;
            }
        
        //Left
        if(space.field[location.X - 1][location.Y] == null)
            if(mindist > distance(location.X - 1, location.Y, tx, ty)){
                mindist = distance(location.X - 1, location.Y, tx, ty);
                newx = location.X - 1;
                newy = location.Y;
            }
        
        //Right
        if(space.field[location.X + 1][location.Y] == null)
            if(mindist > distance(location.X + 1, location.Y, tx, ty)){
                mindist = distance(location.X + 1, location.Y, tx, ty);
                newx = location.X + 1;
                newy = location.Y;
            }
        
        //Down-Left
        if(space.field[location.X - 1][location.Y + 1] == null)
            if(mindist > distance(location.X - 1, location.Y + 1, tx, ty)){
                mindist = distance(location.X - 1, location.Y + 1, tx, ty);
                newx = location.X - 1;
                newy = location.Y + 1;
            }
        
        //Down
        if(space.field[location.X][location.Y + 1] == null)
            if(mindist > distance(location.X, location.Y + 1, tx, ty)){
                mindist = distance(location.X, location.Y + 1, tx, ty);
                newx = location.X;
                newy = location.Y + 1;
            }
        
        //Donw-Right
        if(space.field[location.X + 1][location.Y + 1] == null)
            if(mindist > distance(location.X + 1, location.Y + 1, tx, ty)){
                newx = location.X + 1;
                newy = location.Y + 1;
            }
        
        //Change location
        if(space.field[location.X][location.Y] == this)
            space.field[location.X][location.Y] = null;
        location.setLocation(newx, newy);
        space.field[newx][newy] = this;
    }
    
    protected void moveTo(){
        for(int i = 0; i < speed; i++)
            move(target.X,target.Y);
    }
    
    protected double distance(int sx, int sy, int tx, int ty){
        return Math.sqrt((sx - tx)*(sx - tx) + (sy - ty)*(sy - ty));
    }
    
    @Override
    public void action(){
        if(distance(location.X, location.Y, target.X, target.Y) < 4 || target.X <= 0)
            do{
                setRandomTarget();
            }while(space.field[target.X][target.Y] != null || distance(location.X, location.Y, target.X, target.Y) < 4);
        moveTo();
    }
    
    private void setRandomTarget(){
        Random rand = new Random();
        target.setLocation(rand.nextInt(Constants.SPACEWIDTH + 1) + 1, rand.nextInt(Constants.SPACEHEIGHT + 1) + 1);
    }
    
    @Override
    public boolean isImmortal(){
        return false;
    }
    
    @Override
    public void attacked(int damage){
        getDamage(damage);
        if(armor <= 0){
            space.field[location.X][location.Y] = null;
            playerColor = PlayerColor.None;
        }
    }
    
    @Override
    public String toString(){
        return "SpaceShip";
    }
}
