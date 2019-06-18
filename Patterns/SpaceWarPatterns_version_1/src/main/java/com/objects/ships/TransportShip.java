package com.objects.ships;

import com.enums.ShipTypes;
import com.interfaces.action.TransportShipAction;
import com.interfaces.drawing.TransportShipDrawing;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.interfaces.move.Movable;
import com.sql.SingletonSQLQuerys;

/**
 * Created by Сергей on 05.02.2016.
 */
public class TransportShip extends SpaceShip{
    public TransportShip() {
        this.setAction(new TransportShipAction(this));
        this.setDrawing(new TransportShipDrawing(this));
        this.setMovable(new Movable(this));
        this.setInvisibility(new Invisibility(this, false));
        this.setiVisible(new MakeVisible(true));
        this.setImmortal(false);
        this.setAttributes(SingletonSQLQuerys.getInstance().getTransportShipAttributes());
        this.setType(ShipTypes.TransportShip);
    }
}
