package com.objects.ships;

import com.enums.ShipTypes;
import com.interfaces.action.StealthShipAction;
import com.interfaces.cannon.StealthShipCannon;
import com.interfaces.drawing.StealthShipDrawing;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.interfaces.move.Movable;
import com.sql.SingletonSQLQuerys;

/**
 * Created by Сергей on 05.02.2016.
 */
public class StealthShip extends SpaceShip{
    public StealthShip() {
        this.setAction(new StealthShipAction(this));
        this.setDrawing(new StealthShipDrawing(this));
        this.setMovable(new Movable(this));
        this.setInvisibility(new Invisibility(this, true));
        this.setiVisible(new MakeVisible(true));
        this.setCannon(new StealthShipCannon());
        this.setImmortal(false);
        this.setAttributes(SingletonSQLQuerys.getInstance().getStealthShipAttributes());
        this.setType(ShipTypes.StealthShip);
    }
}
