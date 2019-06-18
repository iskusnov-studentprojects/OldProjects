package com.objects.planet;

import com.enums.PlayerColor;

/**
 * Created by Sergey on 31.01.2016.
 */
public class PlanetMemento {
    private int resources;
    private PlayerColor color;

    public PlanetMemento(int resources, PlayerColor color) {
        this.resources = resources;
        this.color = color;
    }

    public int getResources() {
        return resources;
    }

    public PlayerColor getColor() {
        return color;
    }

    public void setState(int resources, PlayerColor color){
        this.resources = resources;
        this.color = color;
    }
}
