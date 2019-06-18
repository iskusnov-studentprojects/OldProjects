package com.interfaces.cannon;

import com.objects.LocalizableObject;
import com.sql.SingletonSQLQuerys;

/**
 * 
 */
public class StealthShipCannon implements ICannon {

	/**
	 * Default constructor
	 */
	public StealthShipCannon() {
		damage = SingletonSQLQuerys.getInstance().getStealthShipDamage();
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