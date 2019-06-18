/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.util.*;
import helpclasses.*;
import java.awt.*;

/**
 *
 * @author домо
 */
public class Race extends BaseClass implements Drawing{
    public ArrayList<LocalizableObject> ships;
    public ArrayList<Planet> planets;
    protected int warShipNumber;
    protected int stealthShipNumber;
    protected int regenShipNumber;
    protected int radarShipNumber;
    protected int transportShipNumber;
    public RaceType raceType;
    public PlayerColor playerColor;
    
    public Race(RaceType _raceType, PlayerColor _playerColor){
        ships = new ArrayList<LocalizableObject>();
        planets = Constants.PLANETS;
        warShipNumber = 0;
        stealthShipNumber = 0;
        regenShipNumber = 0;
        radarShipNumber = 0;
        transportShipNumber = 0;
        raceType = _raceType;
        playerColor = _playerColor;
    }
    
    public void turn(){
        for(int i = 0; i < ships.size(); i++)
            if(ships.get(i).playerColor != this.playerColor){
                switch(ships.get(i).toString()){
                    case "WarShip": warShipNumber--;
                        break;
                    case "RegenShip": regenShipNumber--;
                        break;
                    case "StealthShip": stealthShipNumber--;
                        break;
                    case "TransportShip": transportShipNumber--;
                        break;
                    case "RadarShip": radarShipNumber--;
                }
                ships.remove(i);
                i--;
            }
        for(LocalizableObject i: ships)
            i.action();
        for(Planet i: planets)
            if(i.playerColor == this.playerColor && i.resources > 150)
                planetAction(i);
    }
    
    private void planetAction(Planet p){
        if(warShipNumber < Constants.MAXWARSHIPNUMBER){
            ships.add(p.createShip("WarShip"));
            warShipNumber++;
            return;
        }
        if(transportShipNumber < Constants.MAXTRANSPORTSHIPNUMBER){
           ships.add(p.createShip("TransportShip"));
           transportShipNumber++;
           return;
        }
        if(radarShipNumber < Constants.MAXRADARSHIPNUMBER){
            ships.add(p.createShip("RadarShip"));
            radarShipNumber++;
            return;
        }
        if(raceType == RaceType.RegenerationRace){
            if(regenShipNumber < Constants.MAXREGENSHIPNUMBER){
                ships.add(p.createShip("RegenShip"));
                regenShipNumber++;
                return;
            }
        }else{
            if(stealthShipNumber < Constants.MAXSTEALTHSHIPNUMBER){
                ships.add(p.createShip("StealthShip"));
                stealthShipNumber++;
                return;
            }
        }
        
    }
    
    @Override
    public void draw(Graphics canvas, int canvasWidth, int canvasHeight){
        for(LocalizableObject i : ships){
            i.draw(canvas, canvasWidth, canvasHeight);
        }
    }
}
