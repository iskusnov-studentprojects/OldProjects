package com.objects.creator;

import com.objects.planet.Planet;
import com.objects.ships.SpaceShip;

/**
 * Created by Sergey on 28.01.2016.
 */
public interface ShipCreator {

    public SpaceShip createWarShip();
    public SpaceShip createTransportShip();
    public SpaceShip createRadarShip();
    public SpaceShip createRegenShip();
    public SpaceShip createStealthShip();

}
