package com.interfaces.action;

import com.objects.ships.SpaceShip;

/**
 * 
 */
public class StealthShipAction extends WarShipActionDecorator {

	/**
	 * Default constructor
	 */
	public StealthShipAction(SpaceShip ship) {
		super(ship);
	}

	/**
	 * @return
	 */
	public void action() {
		super.action();
	}

}