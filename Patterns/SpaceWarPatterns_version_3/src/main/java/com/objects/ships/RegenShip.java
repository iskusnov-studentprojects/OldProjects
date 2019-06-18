package com.objects.ships;

import com.enums.ShipTypes;
import com.interfaces.action.RegenShipAction;
import com.interfaces.cannon.RegenShipCannon;
import com.interfaces.drawing.RegenShipDrawingAdapter;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.interfaces.move.Movable;
import com.sql.SingletonSQLQuerys;

/**
 * Created by Сергей on 05.02.2016.
 */
public class RegenShip extends SpaceShip {
    public RegenShip() {
        this.setAction(new RegenShipAction(this));
        this.setDrawing(new RegenShipDrawingAdapter(this));
        this.setMovable(new Movable(this));
        this.setInvisibility(new Invisibility(this, false));
        this.setiVisible(new MakeVisible(true));
        this.setCannon(new RegenShipCannon());
        this.setImmortal(false);
        this.setAttributes(SingletonSQLQuerys.getInstance().getRegenShipAttributes());
        this.setType(ShipTypes.RegenShip);
    }
}
