package com.objects.creator;

import com.enums.ShipTypes;
import com.interfaces.action.StealthShipAction;
import com.interfaces.cannon.StealthShipCannon;
import com.interfaces.drawing.StealthShipDrawing;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.interfaces.move.Movable;
import com.sql.SingletonSQLQuerys;

/**
 * Created by Sergey on 28.01.2016.
 */
public class StealthShipBuilder extends ShipBuilder {

    public StealthShipBuilder() {
        price = SingletonSQLQuerys.getInstance().getShipPrices().getStealthShipPrice();
    }

    @Override
    public void buildDelegates() {
        ship.setAction(new StealthShipAction(ship));
        ship.setDrawing(new StealthShipDrawing(ship));
        ship.setMovable(new Movable(ship));
        ship.setInvisibility(new Invisibility(ship, true));
        ship.setiVisible(new MakeVisible(true));
        ship.setCannon(new StealthShipCannon());
        ship.setImmortal(false);
    }

    @Override
    public void buildAttributes() {
        ship.setAttributes(SingletonSQLQuerys.getInstance().getStealthShipAttributes());
        ship.setType(ShipTypes.StealthShip);
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
