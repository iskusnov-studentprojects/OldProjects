package com.objects.creator;

import com.enums.ShipTypes;
import com.interfaces.action.TransportShipAction;
import com.interfaces.drawing.TransportShipDrawing;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.interfaces.move.Movable;
import com.sql.SingletonSQLQuerys;

/**
 * Created by Sergey on 28.01.2016.
 */
public class TransportShipBuilder extends ShipBuilder {

    public TransportShipBuilder() {
        price = SingletonSQLQuerys.getInstance().getShipPrices().getTransportShipPrice();
    }

    @Override
    public void buildDelegates() {
        ship.setAction(new TransportShipAction(ship));
        ship.setDrawing(new TransportShipDrawing(ship));
        ship.setMovable(new Movable(ship));
        ship.setInvisibility(new Invisibility(ship, false));
        ship.setiVisible(new MakeVisible(true));
        ship.setImmortal(false);
    }

    @Override
    public void buildAttributes() {
        ship.setAttributes(SingletonSQLQuerys.getInstance().getTransportShipAttributes());
        ship.setType(ShipTypes.TransportShip);
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
