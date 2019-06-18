package com.objects.creator;

import com.enums.ShipTypes;
import com.interfaces.action.RegenShipAction;
import com.interfaces.cannon.RegenShipCannon;
import com.interfaces.drawing.RegenShipDrawing;
import com.interfaces.drawing.RegenShipDrawingAdapter;
import com.interfaces.invisibility.Invisibility;
import com.interfaces.invisibility.MakeVisible;
import com.interfaces.move.Movable;
import com.sql.SingletonSQLQuerys;

/**
 * Created by Sergey on 28.01.2016.
 */
public class RegenShipBuilder extends ShipBuilder {

    public RegenShipBuilder() {
        price = SingletonSQLQuerys.getInstance().getShipPrices().getRegenShipPrice();
    }

    @Override
    public void buildDelegates() {
        ship.setAction(new RegenShipAction(ship));
        ship.setDrawing(new RegenShipDrawingAdapter(ship));
        ship.setMovable(new Movable(ship));
        ship.setInvisibility(new Invisibility(ship, false));
        ship.setiVisible(new MakeVisible(true));
        ship.setCannon(new RegenShipCannon());
        ship.setImmortal(false);
    }

    @Override
    public void buildAttributes() {
        ship.setAttributes(SingletonSQLQuerys.getInstance().getRegenShipAttributes());
        ship.setType(ShipTypes.RegenShip);
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
