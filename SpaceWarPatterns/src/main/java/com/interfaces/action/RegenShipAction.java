package com.interfaces.action;

import com.Constants;
import com.objects.Space;
import com.objects.ships.SpaceShip;

import java.util.*;

/**
 * 
 */
public class RegenShipAction extends WarShipActionDecorator {

	/**
	 * Default constructor
	 */
	public RegenShipAction(SpaceShip ship) {
		super(ship);
	}

	/**
	 * @return
	 */
	public void action() {
		regeneration();
		super.action();
	}

	protected void regeneration(){
		ship.setArmor(ship.getArmor()+1);
	}
}