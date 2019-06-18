package com.objects.visitor;

import com.objects.planet.Planet;
import com.objects.ships.SpaceShip;

/**
 * Created by Sergey on 03.02.2016.
 */
public interface CollectorOfInformation {
    public void visitShip(SpaceShip ship);
    public void visitPlanet(Planet planet);
    public String[] getInformation();
}
