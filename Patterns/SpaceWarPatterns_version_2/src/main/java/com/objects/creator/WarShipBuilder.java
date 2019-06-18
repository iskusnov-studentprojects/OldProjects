package com.objects.creator;

import com.enums.ShipTypes;
import com.interfaces.action.WarShipAction;
import com.interfaces.cannon.WarShipCannon;
import com.interfaces.drawing.WarShipDrawing;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.interfaces.move.Movable;
import com.sql.SingletonSQLQuerys;

/**
 * Created by Sergey on 28.01.2016.
 */
public class WarShipBuilder extends ShipBuilder {

    public WarShipBuilder() {
        price = SingletonSQLQuerys.getInstance().getShipPrices().getWarShipPrice();
    }

    @Override
    public void buildDelegates() {
        ship.setAction(new WarShipAction(ship));;
        ship.setDrawing(new WarShipDrawing(ship));
        ship.setMovable(new Movable(ship));
        ship.setInvisibility(new Invisibility(ship, false));
        ship.setCannon(new WarShipCannon());
        ship.setiVisible(new MakeVisible(false));
        ship.setImmortal(false);
    }

    @Override
    public void buildAttributes() {
        ship.setAttributes(SingletonSQLQuerys.getInstance().getWarShipAttributes());
        ship.setType(ShipTypes.WarShip);
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
