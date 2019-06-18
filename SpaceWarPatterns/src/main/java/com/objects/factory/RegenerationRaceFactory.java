package com.objects.factory;

import com.Constants;
import com.enums.ShipTypes;
import com.interfaces.IPrototype;
import com.interfaces.ShipCreator;
import com.objects.Planet;
import com.objects.ships.SpaceShip;
import com.objects.ships.prototypes.ShipPrototypes;

import java.util.*;

/**
 * 
 */
public class RegenerationRaceFactory implements ShipCreator {

	Planet planet;
	/**
	 * Default constructor
	 */
	public RegenerationRaceFactory(Planet planet) {
		this.planet = planet;
	}

	/**
	 * @return
	 */
	public SpaceShip createWarShip() {
		planet.pay(Constants.WARSHIPPRICE);
		SpaceShip ship = (SpaceShip)ShipPrototypes.getShipPrototype(ShipTypes.WarShip).clone();
		ship.setPlayerColor(planet.getPlayerColor());
		ship.setLocation(planet.getLocation().X, planet.getLocation().Y);
		return ship;
	}

	/**
	 * @return
	 */
	public SpaceShip createTransportShip() {
		planet.pay(Constants.TRANSPORTSHIPPRICE);
		SpaceShip ship = (SpaceShip)ShipPrototypes.getShipPrototype(ShipTypes.TransportShip).clone();
		ship.setPlayerColor(planet.getPlayerColor());
		ship.setLocation(planet.getLocation().X, planet.getLocation().Y);
		return ship;
	}

	/**
	 * @return
	 */
	public SpaceShip createRadarShip() {
		planet.pay(Constants.RADARSHIPPRICE);
		SpaceShip ship = (SpaceShip)ShipPrototypes.getShipPrototype(ShipTypes.RadarShip).clone();
		ship.setPlayerColor(planet.getPlayerColor());
		ship.setLocation(planet.getLocation().X, planet.getLocation().Y);
		return ship;
	}

	/**
	 * @return
	 */
	public SpaceShip createSpecialShip() {
		planet.pay(Constants.REGENSHIPPRICE);
		SpaceShip ship = (SpaceShip)ShipPrototypes.getShipPrototype(ShipTypes.RegenShip).clone();
		ship.setPlayerColor(planet.getPlayerColor());
		ship.setLocation(planet.getLocation().X, planet.getLocation().Y);
		return ship;
	}

}