package com.objects.creator;

import com.objects.Race;
import com.objects.planet.Planet;
import com.objects.ships.*;
import com.sql.SingletonSQLQuerys;

/**
 * Created by Сергей on 05.02.2016.
 */
public class RegenRaceFactory implements ShipCreator{
    private Planet planet;
    private ShipPrices prices;
    private Race race;

    public RegenRaceFactory(Planet planet) {
        this.planet = planet;
        prices = SingletonSQLQuerys.getInstance().getShipPrices();
    }

    @java.lang.Override
    public SpaceShip createWarShip() {
        planet.pay(prices.getWarShipPrice());
        WarShip ship = new WarShip();
        ship.setRace(race);
        ship.setPlayerColor(planet.getPlayerColor());
        ship.setLocation(planet.getLocation().X + 1, planet.getLocation().Y);
        return ship;
    }

    @java.lang.Override
    public SpaceShip createTransportShip() {
        planet.pay(prices.getTransportShipPrice());
        TransportShip ship = new TransportShip();
        ship.setRace(race);
        ship.setPlayerColor(planet.getPlayerColor());
        ship.setLocation(planet.getLocation().X + 1, planet.getLocation().Y);
        return ship;
    }

    @java.lang.Override
    public SpaceShip createRadarShip() {
        planet.pay(prices.getRadarShipPrice());
        RadarShip ship = new RadarShip();
        ship.setRace(race);
        ship.setPlayerColor(planet.getPlayerColor());
        ship.setLocation(planet.getLocation().X + 1, planet.getLocation().Y);
        return ship;
    }

    @java.lang.Override
    public SpaceShip createSpecialShip() {
        planet.pay(prices.getRegenShipPrice());
        RegenShip ship = new RegenShip();
        ship.setRace(race);
        ship.setPlayerColor(planet.getPlayerColor());
        ship.setLocation(planet.getLocation().X + 1, planet.getLocation().Y);
        return ship;
    }

    public void setRace(Race race) {
        this.race = race;
    }
}
