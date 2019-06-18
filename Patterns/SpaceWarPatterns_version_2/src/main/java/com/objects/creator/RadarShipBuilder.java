package com.objects.creator;

import com.enums.ShipTypes;
import com.interfaces.action.RadarShipAction;
import com.interfaces.drawing.RadarShipDrawing;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.interfaces.move.Movable;
import com.sql.SingletonSQLQuerys;

/**
 * Created by Sergey on 28.01.2016.
 */
public class RadarShipBuilder extends ShipBuilder {

    public RadarShipBuilder() {
        price = SingletonSQLQuerys.getInstance().getShipPrices().getRadarShipPrice();
    }

    @Override
    public void buildDelegates() {
        ship.setAction(new RadarShipAction(ship));
        ship.setDrawing(new RadarShipDrawing(ship));
        ship.setMovable(new Movable(ship));
        ship.setInvisibility(new Invisibility(ship, false));
        ship.setiVisible(new MakeVisible(true));
        ship.setImmortal(false);
    }

    @Override
    public void buildAttributes() {
        ship.setAttributes(SingletonSQLQuerys.getInstance().getRadarShipAttributes());
        ship.setType(ShipTypes.RadarShip);
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null)
            return false;
        if(this.getClass() == obj.getClass())
            return true;
        else
            return false;
    }
}
