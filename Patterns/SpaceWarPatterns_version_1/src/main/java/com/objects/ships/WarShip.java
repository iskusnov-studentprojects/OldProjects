package com.objects.ships;

import com.enums.ShipTypes;
import com.interfaces.action.WarShipAction;
import com.interfaces.cannon.WarShipCannon;
import com.interfaces.drawing.WarShipDrawing;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.interfaces.move.Movable;
import com.sql.SingletonSQLQuerys;

/**
 * Created by Сергей on 05.02.2016.
 */
public class WarShip extends SpaceShip {
    public WarShip() {
        this.setAction(new WarShipAction(this));;
        this.setDrawing(new WarShipDrawing(this));
        this.setMovable(new Movable(this));
        this.setInvisibility(new Invisibility(this, false));
        this.setCannon(new WarShipCannon());
        this.setiVisible(new MakeVisible(false));
        this.setImmortal(false);
        this.setAttributes(SingletonSQLQuerys.getInstance().getWarShipAttributes());
        this.setType(ShipTypes.WarShip);
    }
}
