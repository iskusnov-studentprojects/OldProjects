package com.interfaces.action;

import com.objects.Space;
import com.objects.ships.SpaceShip;

/**
 * 
 */
public class RadarShipAction implements IAction {

	/**
	 * Default constructor
	 */
	public RadarShipAction(SpaceShip ship) {
		this.ship = ship;
	}

	/**
	 * 
	 */
	protected SpaceShip ship;

	/**
	 * @return
	 */
	public void action() {

		if(Space.distance(ship.location.X, ship.location.Y, ship.getTarget().X, ship.getTarget().Y) < 4 || ship.getTarget().X <= 0)
			do{
				ship.setRandomTarget();
			}while(Space.getInstance().get(ship.getTarget().X,ship.getTarget().Y) != null || Space.distance(ship.location.X, ship.location.Y, ship.getTarget().X, ship.getTarget().Y) < 4);
		ship.getMovable().move(ship.getTarget().X, ship.getTarget().Y);
	}

}