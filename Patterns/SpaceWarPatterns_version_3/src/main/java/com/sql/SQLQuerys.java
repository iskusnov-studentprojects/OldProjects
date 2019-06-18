package com.sql;

import com.objects.ships.ShipAttributes;
import com.objects.ships.ShipPrices;
import com.objects.ships.ShipsLimit;

/**
 * Created by Sergey on 27.01.2016.
 */
public abstract class SQLQuerys {
    private SQLQuerysImpl impl;

    public SQLQuerys(SQLQuerysImpl impl) {
        this.impl = impl;
    }

    public void connect(){
        impl.connect();
    }
    public void disconnect(){
        impl.disconnect();
    }

    public ShipAttributes getWarShipAttributes(){
        return impl.getWarShipAttributes();
    }
    public ShipAttributes getStealthShipAttributes(){
        return impl.getStealthShipAttributes();
    }
    public ShipAttributes getRegenShipAttributes(){
        return impl.getRegenShipAttributes();
    }
    public ShipAttributes getRadarShipAttributes(){
        return impl.getRadarShipAttributes();
    }
    public ShipAttributes getTransportShipAttributes(){
        return impl.getTransportShipAttributes();
    }
    public int getWarShipDamage(){
        return impl.getWarShipDamage();
    }
    public int getRegenShipDamage(){
        return impl.getRegenShipDamage();
    }
    public int getStealthShipDamage(){
        return impl.getStealthShipDamage();
    }
    public int getNumberPlanets(){
        return impl.getNumberPlanets();
    }
    public int getMakeVisibleRange(){
        return impl.getMakeVisibleRange();
    }
    public ShipPrices getShipPrices(){
        return impl.getShipPrices();
    }
    public int getPlanetResources(){
        return impl.getPlanetResources();
    }

    public ShipsLimit getShipsLimit(){
        return impl.getShipsLimit();
    }
}
