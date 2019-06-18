package com.interfaces.action;

import com.objects.ships.SpaceShip;

import java.util.*;

/**
 * 
 */
public class WarShipActionDecorator implements IAction {

	protected SpaceShip ship;
        private IAction action;
	/**
	 * Default constructor
	 */
	public WarShipActionDecorator(SpaceShip ship) {
            action = new WarShipAction(ship); 
		this.ship = ship;
	}


	/**
	 * @return
	 */
	public void action() {
		action.action();
	}

}