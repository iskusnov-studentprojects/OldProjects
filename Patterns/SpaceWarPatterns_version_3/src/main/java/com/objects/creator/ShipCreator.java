package com.objects.creator;

import com.objects.Race;
import com.objects.planet.Planet;
import com.objects.ships.SpaceShip;

/**
 * Created by Sergey on 28.01.2016.
 */
public interface ShipCreator {

    public SpaceShip createWarShip();
    public SpaceShip createTransportShip();
    public SpaceShip createRadarShip();
    public SpaceShip createSpecialShip();
    public void setRace(Race race);
}
