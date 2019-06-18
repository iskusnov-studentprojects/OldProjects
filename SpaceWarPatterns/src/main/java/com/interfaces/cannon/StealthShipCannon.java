package com.interfaces.cannon;

import com.Constants;
import com.objects.LocalizableObject;

import java.util.*;

/**
 * 
 */
public class StealthShipCannon implements ICannon {

	/**
	 * Default constructor
	 */
	public StealthShipCannon() {
		damage = Constants.STEALTHSHIPDAMAGE;
	}

	/**
	 * 
	 */
	protected int damage;

	/**
	 * @param ship 
	 * @return
	 */
	public void fire(LocalizableObject ship) {
		ship.getDamage(damage);
	}

}