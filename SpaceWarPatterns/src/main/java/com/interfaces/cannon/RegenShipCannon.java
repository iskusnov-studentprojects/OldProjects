package com.interfaces.cannon;

import com.Constants;
import com.objects.LocalizableObject;

import java.util.*;

/**
 * 
 */
public class RegenShipCannon implements ICannon {


	/**
	 * Default constructor
	 */
	public RegenShipCannon() {
		this.damage = Constants.REGENSHIPDAMAGE;
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