package com.objects.creator;

import com.objects.ships.SpaceShip;

/**
 * Created by Sergey on 28.01.2016.
 */
public abstract class ShipBuilder {
    protected SpaceShip ship;
    protected int price;
    public abstract void buildDelegates();
    public abstract void buildAttributes();
    public SpaceShip getShip(){
        return ship;
    }
    public void createNewShip(){
        ship = new SpaceShip();
    }

    public int getPrice() {
        return price;
    }

    @Override
    public abstract boolean equals(Object obj);
}
