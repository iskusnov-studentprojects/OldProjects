package com.interfaces.cannon;

import com.Constants;
import com.objects.LocalizableObject;

import java.util.*;

/**
 * 
 */
public class WarShipCannon implements ICannon {

	/**
	 * Default constructor
	 */
	public WarShipCannon() {
		damage = Constants.WARSHIPDAMAGE;
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