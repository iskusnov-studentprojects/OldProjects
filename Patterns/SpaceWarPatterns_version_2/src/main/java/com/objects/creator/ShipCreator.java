package com.objects.creator;

import com.objects.planet.Planet;
import com.objects.ships.SpaceShip;

/**
 * Created by Sergey on 28.01.2016.
 */
public class ShipCreator {
    Planet planet;
    ShipBuilder builder;

    public ShipCreator(Planet planet) {
        this.planet = planet;
        builder = null;
    }

    public void setBuilder(Class<? extends ShipBuilder> builderClass){
        try {
            if (builder == null || !builderClass.equals(this.builder.getClass()))
                this.builder = builderClass.newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public void constructShip(){
        builder.createNewShip();
        builder.buildAttributes();
        builder.buildDelegates();
    }

    public SpaceShip getShip(){
        planet.pay(builder.getPrice());
        builder.getShip().setRace(planet.getRace());
        builder.getShip().setPlayerColor(planet.getPlayerColor());
        builder.getShip().setLocation(planet.getLocation().X + 1, planet.getLocation().Y);
        return builder.getShip();
    }
}
