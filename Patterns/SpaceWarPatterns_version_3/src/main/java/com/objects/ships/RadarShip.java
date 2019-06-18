package com.objects.ships;

import com.enums.ShipTypes;
import com.interfaces.action.RadarShipAction;
import com.interfaces.drawing.RadarShipDrawing;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.interfaces.move.Movable;
import com.sql.SingletonSQLQuerys;

/**
 * Created by Сергей on 05.02.2016.
 */
public class RadarShip extends SpaceShip{
    public RadarShip() {
        this.setAction(new RadarShipAction(this));
        this.setDrawing(new RadarShipDrawing(this));
        this.setMovable(new Movable(this));
        this.setInvisibility(new Invisibility(this, false));
        this.setiVisible(new MakeVisible(true));
        this.setImmortal(false);
        this.setAttributes(SingletonSQLQuerys.getInstance().getRadarShipAttributes());
        this.setType(ShipTypes.RadarShip);
    }
}
