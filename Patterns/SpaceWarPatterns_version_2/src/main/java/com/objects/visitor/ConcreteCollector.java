package com.objects.visitor;

import com.objects.planet.Planet;
import com.objects.ships.SpaceShip;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sergey on 03.02.2016.
 */
public class ConcreteCollector implements CollectorOfInformation {

    List<String> information;

    public ConcreteCollector() {
        information = new ArrayList<String>();
    }

    public void visitShip(SpaceShip ship) {
        String str = "%s - l:(%d;%d), a: %d",
                name = "";
        switch (ship.getType()){
            case WarShip:
                name = "WarShip";
                break;
            case StealthShip:
                name = "StealthShip";
                break;
            case RegenShip:
                name = "RegenShip";
                break;
            case TransportShip:
                name = "TransportShip";
                break;
            case RadarShip:
                name = "RadarShip";
                break;
        }

        str = String.format(str,name,ship.location.X,ship.location.Y,ship.getArmor());
        information.add(str);
    }

    public void visitPlanet(Planet planet) {
        String str = "Planet - r: (%d)";
        str = String.format(str, planet.getResources());
        information.add(str);
    }

    public String[] getInformation() {
        return information.toArray(new String[information.size()]);
    }
}
