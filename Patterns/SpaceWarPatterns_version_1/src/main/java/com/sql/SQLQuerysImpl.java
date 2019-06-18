package com.sql;

import com.objects.ships.ShipAttributes;
import com.objects.ships.ShipPrices;
import com.objects.ships.ShipsLimit;

import java.sql.Connection;

/**
 * Created by Sergey on 27.01.2016.
 */
public abstract class SQLQuerysImpl {
    public abstract void connect();
    public abstract void disconnect();

    public abstract ShipAttributes getWarShipAttributes();
    public abstract ShipAttributes getStealthShipAttributes();
    public abstract ShipAttributes getRegenShipAttributes();
    public abstract ShipAttributes getRadarShipAttributes();
    public abstract ShipAttributes getTransportShipAttributes();
    public abstract int getWarShipDamage();
    public abstract int getRegenShipDamage();
    public abstract int getStealthShipDamage();
    public abstract int getNumberPlanets();
    public abstract int getMakeVisibleRange();
    public abstract ShipPrices getShipPrices();
    public abstract int getPlanetResources();
    public abstract ShipsLimit getShipsLimit();
}
